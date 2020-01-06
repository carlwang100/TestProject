package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by kai on 2018/10/11
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImMsgReadNotify extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:6007
     * IMMsgDataReadNotify : {"readerId":"类型:Number 说明:读取了消息的用户id","sessionId":"类型:Number 说明:会话id。单聊：接收方imid， 群聊：群组id","mSessionType":"类型:Number 说明:会话类型。1：单聊， 2：群聊","recvMsgId":"类型:Number 说明:目前用于文件已读标识：消息接收方 当前已接收的文件消息id","latestReadMsgId":"类型:Number 说明:消息接收方 当前已读的最大消息id（不根据此最大消息id更新文件的已读状态）","readTime":"类型:Number 说明:读消息的时间，时间戳，单位 毫秒，类型为long"}
     */
    private IMMsgDataReadNotifyBean IMMsgDataReadNotify;

    public IMMsgDataReadNotifyBean getIMMsgDataReadNotify() {
        return IMMsgDataReadNotify;
    }

    public static class IMMsgDataReadNotifyBean {
        /**
         * readerId : 类型:Number 说明:读取了消息的用户id
         * sessionId : 类型:Number 说明:会话id。单聊：接收方imid， 群聊：群组id
         * mSessionType : 类型:Number 说明:会话类型。1：单聊， 2：群聊
         * recvMsgId : 类型:Number 说明:目前用于文件已读标识：消息接收方 当前已接收的文件消息id
         * latestReadMsgId : 类型:Number 说明:消息接收方 当前已读的最大消息id（不根据此最大消息id更新文件的已读状态）
         * readTime : 类型:Number 说明:读消息的时间，时间戳，单位 毫秒，类型为long
         */

        private long readerId;
        private long sessionId;
        private int sessionType;
        private int recvMsgId;
        private int latestReadMsgId;
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
