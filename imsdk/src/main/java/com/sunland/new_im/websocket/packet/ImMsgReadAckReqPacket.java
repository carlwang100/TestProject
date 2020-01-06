package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by kai on 2018/9/4
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImMsgReadAckReqPacket extends ImBaseRequestPacket {

    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:6005","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * IMMsgDataReadAck : {"sessionId":"类型:Number 说明:会话id。单聊：消息发送方imid， 群聊：群组id","mSessionType":"类型:Number 说明:会话类型。1：单聊， 2：群聊","recvMsgId":"类型:Number 说明:目前用于文件已读标识：消息接收方 当前已接收的文件消息id","latestReadMsgId":"类型:Number 说明:消息接收方 当前已读的最大消息id（不根据此最大消息id更新文件的已读状态）","备注：：：：：：：：：：：：：":"类型:String 说明:recvMsgId当文件接收成功后设置字段值；recvMsgId 和 latestReadMsgId 不能同时设置"}
     */

    private IMMsgDataReadAckBean IMMsgDataReadAck;

    public ImMsgReadAckReqPacket(EntryBean entry,IMMsgDataReadAckBean IMMsgDataReadAck) {
        super(entry);
        this.IMMsgDataReadAck = IMMsgDataReadAck;
    }

    public static class IMMsgDataReadAckBean {
        /**
         * sessionId : 类型:Number 说明:会话id。单聊：消息发送方imid， 群聊：群组id
         * mSessionType : 类型:Number 说明:会话类型。1：单聊， 2：群聊
         * recvMsgId : 类型:Number 说明:目前用于文件已读标识：消息接收方 当前已接收的文件消息id
         * latestReadMsgId : 类型:Number 说明:消息接收方 当前已读的最大消息id（不根据此最大消息id更新文件的已读状态）
         * 备注：：：：：：：：：：：：： : 类型:String 说明:recvMsgId当文件接收成功后设置字段值；recvMsgId 和 latestReadMsgId 不能同时设置
         */

        private long sessionId;
        private int sessionType;
        private int recvMsgId;
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

        public long getRecvMsgId() {
            return recvMsgId;
        }

        public void setRecvMsgId(int recvMsgId) {
            this.recvMsgId = recvMsgId;
        }

        public long getLatestReadMsgId() {
            return latestReadMsgId;
        }

        public void setLatestReadMsgId(int latestReadMsgId) {
            this.latestReadMsgId = latestReadMsgId;
        }
    }
}
