package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * CID6010 - 单聊 撤回消息请求应答
 */
public class ImRevokeMsgResPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:6010
     * RevokeMsgRes : {"revokerId":"类型:Number 说明:撤回者imid","sessionId":"类型:Number 说明:会话id。单聊：相对撤回者imid而言的会话id","msgId":"类型:Number 说明:消息id","revokeTime":"类型:Number 说明:消息撤回时间戳（以服务器时间为准），单位 毫秒，类型为long。","resultCode":"类型:Number 说明:消息发送结果. 0 代表成功； 非0代表失败，resultCode = 0 （成功） 1（失败），3007（消息已被撤回）","reason":"类型:String 说明:错误码描述"}
     */

    @SerializedName("RevokeMsgRes")
    private RevokeMsgResBean RevokeMsgRes;

    public RevokeMsgResBean getRevokeMsgRes() {
        return RevokeMsgRes;
    }

    public void setRevokeMsgRes(RevokeMsgResBean RevokeMsgRes) {
        this.RevokeMsgRes = RevokeMsgRes;
    }

    public static class RevokeMsgResBean {
        /**
         * revokerId : 类型:Number 说明:撤回者imid
         * sessionId : 类型:Number 说明:会话id。单聊：相对撤回者imid而言的会话id
         * msgId : 类型:Number 说明:消息id
         * revokeTime : 类型:Number 说明:消息撤回时间戳（以服务器时间为准），单位 毫秒，类型为long。
         * resultCode : 类型:Number 说明:消息发送结果. 0 代表成功； 非0代表失败，resultCode = 0 （成功） 1（失败），3007（消息已被撤回）
         * reason : 类型:String 说明:错误码描述
         */

        @SerializedName("revokerId")
        private long revokerId;
        @SerializedName("sessionId")
        private long sessionId;
        @SerializedName("msgId")
        private long msgId;
        @SerializedName("revokeTime")
        private long revokeTime;
        @SerializedName("resultCode")
        private int resultCode;
        @SerializedName("reason")
        private String reason;

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

        public long getMsgId() {
            return msgId;
        }

        public void setMsgId(long msgId) {
            this.msgId = msgId;
        }

        public long getRevokeTime() {
            return revokeTime;
        }

        public void setRevokeTime(long revokeTime) {
            this.revokeTime = revokeTime;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
