package com.sunland.new_im.websocket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.sunland.new_im.websocket.utils.NetworkUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.sunland.new_im.websocket.utils.NetworkMonitor.NETWORK_CHANGE;
import static com.sunland.new_im.websocket.utils.NetworkMonitor.NET_AVAILABLE;

/**
 * Created by kai on 2018/8/17
 * Email：kaihu1989@gmail.com
 * Feature: websocket长连接，整合心跳、断线重连机制
 */
public abstract class WebSocketChannel<D> implements WebSocketClient.WsClientListener {
    private final String TAG = "WebSocketChannel";
    private WebSocketChannel.NetWorkReceiver mNetWorkReceiver;
    private WebSocketClient.State state = WebSocketClient.State.CLOSED;
    private String host;
    private final Context mContext;
    private WebSocketHeartbeatCenter heartbeatCenter;
    private ReConnector reConnector;
    private ExecutorService threadExecutor;
    private WebSocketClient wsClient;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public Context getmContext() {
        return mContext;
    }

    public WebSocketHeartbeatCenter getHeartbeatCenter() {
        return heartbeatCenter;
    }

    public ReConnector getReConnector() {
        return reConnector;
    }

    public ExecutorService getThreadExecutor() {
        return threadExecutor;
    }

    public WebSocketClient getWsClient() {
        return wsClient;
    }

    /**
     * @param context  Context
     * @param host     ws地址
     * @param interval 心跳间隔
     */
    WebSocketChannel(Context context, String host, long interval) {
        this.host = host;
        this.mContext = context;

        threadExecutor = Executors.newSingleThreadExecutor();
        isClosing = false;

        //定时心跳，保持长连接
        heartbeatCenter = new WebSocketHeartbeatCenter(this, interval);

        //广播接收网络状态变化
        registerNetworkReceiver();

        //断线自动重连
        reConnector = new ReConnector() {
            @Override
            void reConnect() {
                if (shouldReConnect()) {
                    connect();
                }
            }
        };
    }

    /**
     * 注册网络监听
     */
    private void registerNetworkReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NETWORK_CHANGE);
        mNetWorkReceiver = new WebSocketChannel.NetWorkReceiver();
        LocalBroadcastManager.getInstance(mContext).registerReceiver(mNetWorkReceiver, intentFilter);
    }

    /**
     * 连接WebSocket
     */
    public void connect() {
        disconnect();
        threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                wsClient = new WebSocketClient(WebSocketChannel.this);
                wsClient.connect(host);
            }
        });
    }

    /**
     * 断开WebSocket长连接
     */
    private void disconnect() {
        if (isThreadNotWork()) return;
        try {
            threadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    if (wsClient != null) {
                        wsClient.close();
                        wsClient = null;
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private boolean isClosing; //正在关闭

    /**
     * 关闭长连接，释放资源
     */
    public void release() {
        isClosing = true;
        disconnect();

        if (mNetWorkReceiver != null) {
            try {
                LocalBroadcastManager.getInstance(mContext).unregisterReceiver(mNetWorkReceiver);
            } catch (Exception e) {
                //ignore
            }
        }

        if (reConnector != null) {
            reConnector.reSet();
        }

        heartbeatCenter.release();
        stopWorkThread();
    }

    /**
     * @return WebSocket是否连接
     */
    boolean isConnected() {
        return wsClient != null && wsClient.isConnected();
    }

    //发送数据包
    abstract void sendPacket(D packet);

    //处理收到的数据
    abstract void handleData(String receivedData);

    //登录websocket
    abstract void loginWebSocket();

    //发送心跳包
    abstract void sendHeartbeatPacket();

    //websocket长连接状态改变通知
    abstract void onStateChanged(WebSocketClient.State state);

    @Override
    public void onDataReceived(final String json) {
        if (isThreadNotWork()) return;
        try {
            threadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            handleData(json);
                        }
                    });
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onStateReceived(final WebSocketClient.State state) {
        this.state = state;

        if (isThreadNotWork()) return;
        try {
            threadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    //1.登录房间WebSocket
                    if (state == WebSocketClient.State.CONNECTED) {
                        if (reConnector != null) {
                            reConnector.reSet();
                        }
                        loginWebSocket();
                    }

                    onStateChanged(state);

                    //3.如果连接失败则重连
                    if (shouldReConnect()) {
                        heartbeatCenter.stopHeartbeat();
                        reConnector.reTry();
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e == null || e.getMessage() == null ? "" : e.getMessage());
        }

    }

    /**
     * @return 线程池是否正常工作
     */
    synchronized boolean isThreadNotWork() {//存在多线程同时访问 导致状态紊乱
        return threadExecutor == null || threadExecutor.isShutdown() || threadExecutor.isTerminated();
    }

    private void stopWorkThread() {
        try {
            if (threadExecutor == null) {
                return;
            }
            if (!threadExecutor.isShutdown()) {
                threadExecutor.shutdownNow();
            }
            threadExecutor = null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * 网络改变触发自动重连（从无网到有网）
     */
    public class NetWorkReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            boolean available = intent.getBooleanExtra(NET_AVAILABLE, true);
            if (!available) {
                return;
            }

            if (shouldReConnect()) {
                reConnector.reSet();
                reConnector.reTry();
            }
        }
    }

    /**
     * @return 是否需要重连
     */
    public boolean shouldReConnect() {
        return NetworkUtils.isConnected(mContext) && (state == WebSocketClient.State.CLOSED || state == WebSocketClient.State.FAIL) && reConnector != null && !isClosing;
    }

    /**
     * 心跳断开触发自动重连
     */
    void onHeartbeatDeath() {
        disconnect();
        if (isThreadNotWork()) return;
        try {
            threadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    if (shouldReConnect()) {
                        reConnector.reTry();
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
