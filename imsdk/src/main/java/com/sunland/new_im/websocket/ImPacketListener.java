package com.sunland.new_im.websocket;

public abstract class ImPacketListener<T> {
    private static final long DEFAULT_TIMEOUT = 10 * 1000; //默认超时时间10s
    private long createTime;
    private long timeOut;

    public ImPacketListener(long timeOut) {
        this.timeOut = timeOut;
        long now = System.currentTimeMillis();
        createTime = now;
    }

    public ImPacketListener() {
        this.timeOut = DEFAULT_TIMEOUT;
        long now = System.currentTimeMillis();
        createTime = now;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    @SuppressWarnings("unchecked")
    public abstract void onSuccess(T response);

    public abstract void onFailed(String uniqueKey);

    public abstract void onTimeOut(String uniqueKey);
}
