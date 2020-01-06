package com.sunland.new_im.websocket;

import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by HK on 2017/7/25.
 * Email: kaihu1989@gmail.com.
 * Desc：心跳
 */

public class WebSocketHeartbeatCenter {
    private final String TAG = WebSocketHeartbeatCenter.class.getSimpleName();
    private WebSocketChannel webSocketChannel;
    private ScheduledExecutorService executorService;
    private long interval;
    private static final int MAX_HEARTBEAT_COUNT = 3;
    private int heartBeatRetryCount;   //心跳重试次数
    private int heartbeatCount;

    public WebSocketHeartbeatCenter(WebSocketChannel webSocketChannel, long interval) {
        this.webSocketChannel = webSocketChannel;
        this.interval = interval;
    }
    //see https://bugly.qq.com/v2/crash-reporting/crashes/161f94eb00/35426?pid=1
    synchronized void startHeartbeat() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdownNow();
            executorService = null;
        }

        heartBeatRetryCount = 0;
        heartbeatCount = 0;

        executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "心跳次数: " + String.valueOf(++heartbeatCount));
                if (shouldSendHeartbeatPacket()) {
                    sendHeartbeatPacket();
                }
            }
        }, 0, interval, TimeUnit.SECONDS);
    }

    //确认收到心跳
    public void onHeartbeatPacketAck() {
        heartBeatRetryCount = 0;
    }

    private void sendHeartbeatPacket() {
        webSocketChannel.sendHeartbeatPacket();
        heartBeatRetryCount++;
    }

    //失败时，最多发送3次
    private boolean shouldSendHeartbeatPacket() {
        if (heartBeatRetryCount >= MAX_HEARTBEAT_COUNT) {
            onHeartbeatDeath();
            return false;
        }
        return true;
    }

    synchronized void stopHeartbeat() {
            //see https://bugly.qq.com/v2/crash-reporting/crashes/161f94eb00/35127?pid=1  多线程同步引发的问题
            if (executorService != null) {
                executorService.shutdownNow();
                executorService = null;
                Log.i(TAG, "stopHeartbeat");
            }
    }

    void release() {
        stopHeartbeat();
        webSocketChannel = null;
    }

    private void onHeartbeatDeath() {
        heartBeatRetryCount = 0;
        stopHeartbeat();
        if (webSocketChannel != null) {
            webSocketChannel.onHeartbeatDeath();
        }
        Log.i(TAG, "onHeartbeatDeath");
    }
}
