package com.sunland.new_im.websocket.packet.base;

/**
 * Created by kai on 2018/8/15
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImBaseResponsePacket {
    private int cid;
    private String uniqueKey;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

}
