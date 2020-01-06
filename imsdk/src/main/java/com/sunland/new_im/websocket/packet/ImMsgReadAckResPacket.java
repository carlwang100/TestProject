package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by kai on 2018/9/4
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImMsgReadAckResPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:6006
     * IMMsgDataReadAckResponse : {"readerId":"类型:Number 说明:消息已读者，也就是CID6005中传的imid","resultCode":"类型:Number 说明:请求结果. 0 代表成功； 非0代表失败，具体意义参考后台定义.","sessionId":"类型:Number","mSessionType":"类型:Number 说明:会话类型。1：单聊， 2：群聊","recvMsgId":"类型:Number","latestReadMsgId":"类型:Number"}
     */

    private IMMsgDataReadAckResponseBean IMMsgDataReadAckResponse;

    public IMMsgDataReadAckResponseBean getIMMsgDataReadAckResponse() {
        return IMMsgDataReadAckResponse;
    }

    public void setIMMsgDataReadAckResponse(IMMsgDataReadAckResponseBean IMMsgDataReadAckResponse) {
        this.IMMsgDataReadAckResponse = IMMsgDataReadAckResponse;
    }

    public static class IMMsgDataReadAckResponseBean {
        /**
         * readerId : 类型:Number 说明:消息已读者，也就是CID6005中传的imid
         * resultCode : 类型:Number 说明:请求结果. 0 代表成功； 非0代表失败，具体意义参考后台定义.
         * sessionId : 类型:Number
         * mSessionType : 类型:Number 说明:会话类型。1：单聊， 2：群聊
         * recvMsgId : 类型:Number
         * latestReadMsgId : 类型:Number
         */

        private long readerId;
        private int resultCode;
        private long sessionId;
        private int sessionType;
        private int recvMsgId;
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
