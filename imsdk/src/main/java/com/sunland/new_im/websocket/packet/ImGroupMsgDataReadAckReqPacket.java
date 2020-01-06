package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by fengd on 2018/11/13.
 */

public class ImGroupMsgDataReadAckReqPacket extends ImBaseRequestPacket {
    /**
     * IMGroupMsgDataReadAck : {"sessionId":"类型:Number 说明:群组groupid","sessionType":"类型:Number 说明:2：群聊","recvMsgId":"类型:Number 说明:目前用于文件已读标识：消息接收方 当前已接收的文件消息id","latestReadMsgId":"类型:Number 说明:消息接收方 当前已读的最大消息id（不根据此最大消息id更新文件的已读状态）"}
     */

    @SerializedName("IMGroupMsgDataReadAck")
    private IMGroupMsgDataReadAckBean IMGroupMsgDataReadAck;

    public ImGroupMsgDataReadAckReqPacket(EntryBean entry) {
        super(entry);
    }

    public ImGroupMsgDataReadAckReqPacket(EntryBean entryBean, IMGroupMsgDataReadAckBean imMsgDataReadAck) {
        super(entryBean);
        this.IMGroupMsgDataReadAck = imMsgDataReadAck;
    }

    public static class IMGroupMsgDataReadAckBean {
        /**
         * sessionId : 类型:Number 说明:群组groupid
         * sessionType : 类型:Number 说明:2：群聊
         * recvMsgId : 类型:Number 说明:目前用于文件已读标识：消息接收方 当前已接收的文件消息id
         * latestReadMsgId : 类型:Number 说明:消息接收方 当前已读的最大消息id（不根据此最大消息id更新文件的已读状态）
         */

        @SerializedName("sessionId")
        private long sessionId;
        @SerializedName("sessionType")
        private int sessionType;
        @SerializedName("recvMsgId")
        private int recvMsgId;
        @SerializedName("latestReadMsgId")
        private int latestReadMsgId;

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
