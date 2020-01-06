package com.sunland.new_im.websocket;

import android.util.Log;

import com.sunland.new_im.websocket.utils.WsUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * Created by HK on 2017/7/14.
 * Email: kaihu1989@gmail.com.
 * Desc：websocket开始、断开连接，发送、接收数据，状态改变回调等
 */

public class WebSocketClient {
    private static final String TAG = WebSocketClient.class.getSimpleName();
    private static final int NORMAL_CLOSE = 1000;
    private final WsClientListener listener;

    private State state = State.IDLE;
    private WebSocket webSocket;
    private OkHttpClient mOkHttpClient;

    public WebSocketClient(WsClientListener listener) {
        this.listener = listener;
    }

    public enum State {
        IDLE("闲置"),
        CONNECTING("连接中"),
        CONNECTED("已连接"),
        CLOSED("已断开"),
        FAIL("连接失败");

        private String value;

        State(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    public void connect(String address) {
        Log.d(TAG, "connecting: " + address);

        if (mOkHttpClient == null){
            mOkHttpClient = WsUtil.getUnsafeOkHttpClient();

        }
        Request request = new Request.Builder()
                .url(address)
                .build();

        mOkHttpClient.dispatcher().cancelAll();
        notifyStateChange(State.CONNECTING);
        //建立连接
        mOkHttpClient.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                WebSocketClient.this.webSocket = webSocket;
                notifyStateChange(State.CONNECTED);
                Log.d(TAG, "connected");
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                notifyReceiveData(text);
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                notifyStateChange(State.CLOSED);
                Log.d(TAG, "closed");
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                //出现异常会进入此回调
                notifyStateChange(State.FAIL);
                String failMsg = "";
                if (t != null){
                    if (t.getCause() != null){
                        failMsg = t.getCause().getMessage();
                    }else {
                        failMsg = t.getMessage();
                    }
                }
                Log.e(TAG, "fail：" + failMsg);
            }
        });
        mOkHttpClient.dispatcher().executorService().shutdown();
    }

    public void sendPacket(String msgJson) {
        if (webSocket == null) {
            Log.e(TAG, "sendPacket failed, WebSocket is null");
            return;
        }
        webSocket.send(msgJson);
        Log.d(TAG, "发送数据:" + msgJson);
    }

    public void close() {
        if (mOkHttpClient != null) {
            mOkHttpClient.dispatcher().cancelAll();
        }

        if (webSocket != null) {
            boolean isClosed = webSocket.close(NORMAL_CLOSE, "normal close");
            webSocket = null;

            notifyStateChange(State.CLOSED);
        }
    }

    public boolean isConnected () {
        return state == State.CONNECTED && webSocket != null;
    }

    private void notifyReceiveData (String text){
        if (listener == null) {
            Log.d(TAG, "listener 不能为空");
            return;
        }
        listener.onDataReceived(text);
        Log.d(TAG, "收到数据:" + text);
    }

    private void notifyStateChange (State changedState){
        if (state == changedState) {
            Log.d(TAG, "状态不变");
            return;
        }

        if (listener == null) {
            Log.e(TAG, "listener 不能为空");
            return;
        }

        state = changedState;

        listener.onStateReceived(changedState);
    }

    public interface WsClientListener {
        void onDataReceived(String text);

        void onStateReceived(State state);
    }
}
