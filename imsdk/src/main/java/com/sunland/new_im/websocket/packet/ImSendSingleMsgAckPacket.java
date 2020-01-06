package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by kai on 2018/8/16
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImSendSingleMsgAckPacket extends ImBaseResponsePacket {
    /**
     * cid : 类型:Number 说明:6002
     * iMMsgDataAck : {"fromUserId":"类型:Number 说明:消息发送方imid","sessionId":"类型:Number 说明:会话id。单聊：接收方imid， 群聊：群组id","mSessionType":"类型:Number 说明:会话类型。1：单聊， 2：群聊","msgId":"类型:Number 说明:消息id","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配。","createTime":"类型:Number 说明:消息发送时间戳（以服务器时间为准）， 10位","resultCode":"类型:Number 说明:消息发送结果. 0 代表成功； 非0代表失败，具体意义参考后台定义"}
     */

    private IMMsgDataAckBean IMMsgDataAck;

    public IMMsgDataAckBean getiMMsgDataAck() {
        return IMMsgDataAck;
    }

    public void setiMMsgDataAck(IMMsgDataAckBean iMMsgDataAck) {
        this.IMMsgDataAck = iMMsgDataAck;
    }


    public static class IMMsgDataAckBean {
        /**
         * fromUserId : 类型:Number 说明:消息发送方imid
         * sessionId : 类型:Number 说明:会话id。单聊：接收方imid， 群聊：群组id
         * mSessionType : 类型:Number 说明:会话类型。1：单聊， 2：群聊
         * msgId : 类型:Number 说明:消息id
         * uniqueKey : 类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配。
         * createTime : 类型:Number 说明:消息发送时间戳（以服务器时间为准）， 10位
         * resultCode : 类型:Number 说明:消息发送结果. 0 代表成功； 非0代表失败，具体意义参考后台定义
         */

        private long fromUserId;
        private long sessionId;
        private int sessionType;
        private int msgId;
        private long createTime;
        private int resultCode;

        public long getFromUserId() {
            return fromUserId;
        }
        public long getSessionId() {
            return sessionId;
        }
        public int getSessionType() {
            return sessionType;
        }
        public int getMsgId() {
            return msgId;
        }
        public long getCreateTime() {
            return createTime;
        }
        public int getResultCode() {
            return resultCode;
        }

    }
}
