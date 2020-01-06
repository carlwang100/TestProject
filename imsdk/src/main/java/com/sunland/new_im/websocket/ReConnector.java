package com.sunland.new_im.websocket;

import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by HK on 2017/7/18.
 * Email: kaihu1989@gmail.com.
 * Desc: websocket断开重连
 */

public abstract class ReConnector {
    private static final String TAG = "WebSocket ReConnector";
    private static final double MIN_RETRY_INTERVAL = 1000L;
    private static final int RETRY_COUNT = 10;
    private int retryCount = RETRY_COUNT; //最大重连次数
    private ScheduledExecutorService executorService;
    private ScheduledFuture<?> scheduledFuture;

    abstract void reConnect();

    void reTry() {
        if (retryCount <= 0) {
            reTryFailed();
            reSet();
            return;
        }

        release();

        executorService = Executors.newScheduledThreadPool(1);

        try {
            scheduledFuture = executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    if (executorService == null || executorService.isShutdown()) {
                        return;
                    }
                    retryCount--;
                    Log.i(TAG, "WebSocket重连 剩余次数：" + retryCount);
                    reConnect();
                }
            }, generateRandomTimeInterval(), TimeUnit.MILLISECONDS);
        }catch (Exception e){
            Log.e(TAG,e.getMessage());
        }
    }

    void reSet() {
        retryCount = RETRY_COUNT;
        release();
    }

    private void release() {
        if(executorService != null && !executorService.isShutdown()){
            if (scheduledFuture != null){
                scheduledFuture.cancel(true);
            }
            executorService.shutdownNow();
            executorService = null;
        }
    }

    private void reTryFailed() {
        Log.d(TAG, "WebSocket自动重连失败");
    }

    //重连间隔，如果是第一次重连则立即重连
    private int generateRandomTimeInterval() {
        if (isFirstReconnect()) return 0;
        return (int) (MIN_RETRY_INTERVAL + (5000L * Math.random()));
    }

    private boolean isFirstReconnect(){
        return retryCount == RETRY_COUNT;
    }
}
