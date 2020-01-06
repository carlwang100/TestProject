package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by fengd on 2018/11/13.
 */

public class ImGroupMsgDataReadAckResponsePacket extends ImBaseResponsePacket {

    @SerializedName("IMGroupMsgDataReadAckResponse")
    private IMGroupMsgDataReadAckResponseBean IMGroupMsgDataReadAckResponse;

    public IMGroupMsgDataReadAckResponseBean getIMGroupMsgDataReadAckResponse() {
        return IMGroupMsgDataReadAckResponse;
    }

    public static class IMGroupMsgDataReadAckResponseBean {
        /**
         * readerId : 类型:Number 说明:消息已读者imid
         * resultCode : 类型:Number 说明:请求结果. 0 代表成功； 非0代表失败，具体意义参考后台定义.
         * sessionId : 类型:Number
         * sessionType : 类型:Number 说明:2：群聊
         * recvMsgId : 类型:Number
         * latestReadMsgId : 类型:Number
         */

        @SerializedName("readerId")
        private long readerId;
        @SerializedName("resultCode")
        private int resultCode;
        @SerializedName("sessionId")
        private long sessionId;
        @SerializedName("sessionType")
        private int sessionType;
        @SerializedName("recvMsgId")
        private int recvMsgId;
        @SerializedName("latestReadMsgId")
        private int latestReadMsgId;

        public long getReaderId() {
            return readerId;
        }

        public void setReaderId(long readerId) {
            this.readerId = readerId;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public long getSessionId() {
            return sessionId;
        }

        public void setSessionId(long sessionId) {
            this.sessionId = sessionId;
        }

        public int getSessionType() {
            return sessionType;
        }

        public void setSessionType(int sessionType) {
            this.sessionType = sessionType;
        }

        public int getRecvMsgId() {
            return recvMsgId;
        }

        public void setRecvMsgId(int recvMsgId) {
            this.recvMsgId = recvMsgId;
        }

        public int getLatestReadMsgId() {
            return latestReadMsgId;
        }

        public void setLatestReadMsgId(int latestReadMsgId) {
            this.latestReadMsgId = latestReadMsgId;
        }
    }
}
