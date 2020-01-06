package com.sunland.new_im.websocket;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ImListenerQueue {
    private static final String TAG = ImListenerQueue.class.getSimpleName();
    private static ImListenerQueue listenerQueue = new ImListenerQueue();

    public static ImListenerQueue instance() {
        return listenerQueue;
    }

    private volatile boolean stopFlag = false;
    private volatile boolean hasTask = false;


    //callback 队列
    private Map<String, ImPacketListener> callBackQueue = new ConcurrentHashMap<>();
    private Handler timerHandler = new Handler();


    public void onStart() {
        Log.d(TAG,"ListenerQueue#onStart run");
        stopFlag = false;
        startTimer();
    }

    public void onDestory() {
        Log.d(TAG,"ListenerQueue#onDestory ");
        callBackQueue.clear();
        stopTimer();
    }

    //以前是TimerTask处理方式
    private void startTimer() {
        if (!stopFlag && !hasTask) {
            hasTask = true;
            timerHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    timerImpl();
                    hasTask = false;
                    startTimer();
                }
            }, 5 * 1000);
        }
    }

    private void stopTimer() {
        stopFlag = true;
    }

    private void timerImpl() {
        long currentRealtime = System.currentTimeMillis();

        for (Map.Entry<String, ImPacketListener> entry : callBackQueue.entrySet()) {

            ImPacketListener ImPacketListener = entry.getValue();
            String uniqueKey = entry.getKey();
            long timeRange = currentRealtime - ImPacketListener.getCreateTime();

            try {
                if (timeRange >= ImPacketListener.getTimeOut()) {
                    Log.e("WebSocketClient","IM WebSocket请求超时 uniqueKey：" + uniqueKey);
                    ImPacketListener listener = pop(uniqueKey);
                    if (listener != null) {
                        listener.onTimeOut(uniqueKey);
                    }
                }
            } catch (Exception e) {
                Log.d(TAG,"ListenerQueue#timerImpl onTimeout is Error,exception is %s", e.getCause());
            }
        }
    }

    public void push(String uniqueKey, ImPacketListener ImPacketListener) {
        if (TextUtils.isEmpty(uniqueKey)|| null == ImPacketListener) {
            Log.d(TAG,"ListenerQueue#push error, cause by Illegal mParams");
            return;
        }
        callBackQueue.put(uniqueKey, ImPacketListener);
    }

    @SuppressWarnings("unchecked")
    public <T> ImPacketListener<T>  pop(String uniqueKey) {
        if (TextUtils.isEmpty(uniqueKey)){
            Log.e(TAG, "uniqueKey can not be empty!");
            return null;
        }
        synchronized (ImListenerQueue.this) {
            if (callBackQueue.containsKey(uniqueKey)) {
                return callBackQueue.remove(uniqueKey);
            }
            return null;
        }
    }
}
