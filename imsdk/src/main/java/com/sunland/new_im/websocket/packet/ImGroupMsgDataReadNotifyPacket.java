package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by fengd on 2018/12/10.
 */

public class ImGroupMsgDataReadNotifyPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:7017
     * IMGroupMsgDataReadNotify : {"readerId":"类型:Number 说明:读取了消息的用户id","sessionId":"类型:Number 说明:群聊groupid","sessionType":"类型:Number 说明:2：群聊","recvMsgId":"类型:Number 说明:目前用于文件已读标识：消息接收方 当前已接收的文件消息id","preReadMsgId":"类型:Number 说明:该会话内消息接收方上次已读的msgid, 由IM服务端计算设置， 用于通知区间(preReadMsgId, latestReadMsgId]内的各个消息发送方, 更新所发消息的已读计数。若preReadMsgId为0， 表示之前没有已读消息。","latestReadMsgId":"类型:Number 说明:消息接收方 当前已读的最大消息id（不根据此最大消息id更新文件的已读状态）","readTime":"类型:Number 说明:读消息的时间，时间戳，单位 毫秒，类型为long"}
     */

    @SerializedName("IMGroupMsgDataReadNotify")
    private IMGroupMsgDataReadNotifyBean IMGroupMsgDataReadNotify;

    public IMGroupMsgDataReadNotifyBean getIMGroupMsgDataReadNotify() {
        return IMGroupMsgDataReadNotify;
    }

    public void setIMGroupMsgDataReadNotify(IMGroupMsgDataReadNotifyBean IMGroupMsgDataReadNotify) {
        this.IMGroupMsgDataReadNotify = IMGroupMsgDataReadNotify;
    }

    public static class IMGroupMsgDataReadNotifyBean {
        /**
         * readerId : 类型:Number 说明:读取了消息的用户id
         * sessionId : 类型:Number 说明:群聊groupid
         * sessionType : 类型:Number 说明:2：群聊
         * recvMsgId : 类型:Number 说明:目前用于文件已读标识：消息接收方 当前已接收的文件消息id
         * preReadMsgId : 类型:Number 说明:该会话内消息接收方上次已读的msgid, 由IM服务端计算设置， 用于通知区间(preReadMsgId, latestReadMsgId]内的各个消息发送方, 更新所发消息的已读计数。若preReadMsgId为0， 表示之前没有已读消息。
         * latestReadMsgId : 类型:Number 说明:消息接收方 当前已读的最大消息id（不根据此最大消息id更新文件的已读状态）
         * readTime : 类型:Number 说明:读消息的时间，时间戳，单位 毫秒，类型为long
         */

        @SerializedName("readerId")
        private long readerId;
        @SerializedName("sessionId")
        private long sessionId;
        @SerializedName("sessionType")
        private int sessionType;
        @SerializedName("recvMsgId")
        private int recvMsgId;
        @SerializedName("preReadMsgId")
        private int preReadMsgId;
        @SerializedName("latestReadMsgId")
        private int latestReadMsgId;
        @SerializedName("readTime")
        private long readTime;

        public long getReaderId() {
            return readerId;
        }

        public void setReaderId(long readerId) {
            this.readerId = readerId;
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

        public int getPreReadMsgId() {
            return preReadMsgId;
        }

        public void setPreReadMsgId(int preReadMsgId) {
            this.preReadMsgId = preReadMsgId;
        }

        public int getLatestReadMsgId() {
            return latestReadMsgId;
        }

        public void setLatestReadMsgId(int latestReadMsgId) {
            this.latestReadMsgId = latestReadMsgId;
        }

        public long getReadTime() {
            return readTime;
        }

        public void setReadTime(long readTime) {
            this.readTime = readTime;
        }
    }
}
