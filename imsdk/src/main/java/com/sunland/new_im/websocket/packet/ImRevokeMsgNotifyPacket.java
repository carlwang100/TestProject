package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

public class ImRevokeMsgNotifyPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:6011
     * RevokeMsgNotify : {"revokerId":"类型:Number 说明:消息撤回者的imid","sessionId":"类型:Number 说明:revokerId的会话id","msgId":"类型:Number 说明:撤回的msgId","revokeTime":"类型:Number 说明:撤回时间","revokerName":"类型:String 说明:撤回者名字"}
     */

    @SerializedName("RevokeMsgNotify")
    private RevokeMsgNotifyBean RevokeMsgNotify;

    public RevokeMsgNotifyBean getRevokeMsgNotify() {
        return RevokeMsgNotify;
    }

    public void setRevokeMsgNotify(RevokeMsgNotifyBean RevokeMsgNotify) {
        this.RevokeMsgNotify = RevokeMsgNotify;
    }

    public static class RevokeMsgNotifyBean {
        /**
         * revokerId : 类型:Number 说明:消息撤回者的imid
         * sessionId : 类型:Number 说明:revokerId的会话id
         * msgId : 类型:Number 说明:撤回的msgId
         * revokeTime : 类型:Number 说明:撤回时间
         * revokerName : 类型:String 说明:撤回者名字
         */

        @SerializedName("revokerId")
        private long revokerId;
        @SerializedName("sessionId")
        private long sessionId;
        @SerializedName("msgId")
        private int msgId;
        @SerializedName("revokeTime")
        private long revokeTime;
        @SerializedName("revokerName")
        private String revokerName;

        public long getRevokerId() {
            return revokerId;
        }

        public void setRevokerId(long revokerId) {
            this.revokerId = revokerId;
        }

        public long getSessionId() {
            return sessionId;
        }

        public void setSessionId(long sessionId) {
            this.sessionId = sessionId;
        }

        public int getMsgId() {
            return msgId;
        }

        public void setMsgId(int msgId) {
            this.msgId = msgId;
        }

        public long getRevokeTime() {
            return revokeTime;
        }

        public void setRevokeTime(long revokeTime) {
            this.revokeTime = revokeTime;
        }

        public String getRevokerName() {
            return revokerName;
        }

        public void setRevokerName(String revokerName) {
            this.revokerName = revokerName;
        }
    }
}
