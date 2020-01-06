package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * CID7051 - 群消息撤回通知
 */
public class ImRevokeGroupMsgNotifyPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:7051
     * RevokeGroupMsgNotify : {"revokerId":"类型:Number 说明:消息撤回者的imid","sessionId":"类型:Number 说明:会话id：群聊id","msgId":"类型:Number 说明:撤回的msgId","revokeTime":"类型:Number 说明:撤回时间","revokerName":"类型:String 说明:撤回者名字"}
     */

    @SerializedName("RevokeGroupMsgNotify")
    private RevokeGroupMsgNotifyBean RevokeGroupMsgNotify;

    public RevokeGroupMsgNotifyBean getRevokeGroupMsgNotify() {
        return RevokeGroupMsgNotify;
    }

    public void setRevokeGroupMsgNotify(RevokeGroupMsgNotifyBean RevokeGroupMsgNotify) {
        this.RevokeGroupMsgNotify = RevokeGroupMsgNotify;
    }

    public static class RevokeGroupMsgNotifyBean {
        /**
         * revokerId : 类型:Number 说明:消息撤回者的imid
         * sessionId : 类型:Number 说明:会话id：群聊id
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
