package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by kai on 2018/8/15
 * Email：kaihu1989@gmail.com
 * Feature: IM将消息发送给接收方的所有终端、发送方的其他终端
 */
public class ImReceivedSingleMsgPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:6003
     * IMMsgDataDeliver : {"fromUserId":"类型:Number 说明:消息发送方imid","sessionId":"类型:Number 说明:会话id。单聊：接收方imid， 群聊：群组id","mSessionType":"类型:Number 说明:会话类型。1：单聊， 2：群聊","msgType":"类型:Number 说明:消息类型。 1：文本，2：音频，3：图片，4：视频，5：文件","msgContent":"类型:Number 说明:消息内容。最长600字符。不支持超长文本","msgId":"类型:Number 说明:消息id","createTime":"类型:Number 说明:消息发送时间，以服务器时间为准","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配。"}
     */


    private IMMsgDataDeliverBean IMMsgDataDeliver;

    public IMMsgDataDeliverBean getIMMsgDataDeliver() {
        return IMMsgDataDeliver;
    }

    public void setIMMsgDataDeliver(IMMsgDataDeliverBean IMMsgDataDeliver) {
        this.IMMsgDataDeliver = IMMsgDataDeliver;
    }

    public static class IMMsgDataDeliverBean{
        /**
         * fromUserId : 类型:Number 说明:消息发送方imid
         * "fromUserName": "类型:String 说明:消息发送方名字"
         * imageUrl": "类型:String 说明:消息发送方头像url"
         * sessionId : 类型:Number 说明:会话id。单聊：接收方imid， 群聊：群组id
         * mSessionType : 类型:Number 说明:会话类型。1：单聊， 2：群聊
         * msgType : 类型:Number 说明:消息类型。 1：文本，2：音频，3：图片，4：视频，5：文件
         * msgContent : 类型:Number 说明:消息内容。最长600字符。不支持超长文本
         * msgId : 类型:Number 说明:消息id
         * createTime : 类型:Number 说明:消息发送时间，以服务器时间为准
         * extContent: "类型:String 说明:消息类型8使用"
         */

        private long fromUserId;
        private String fromUserName;
        private String imageUrl;
        private long sessionId;
        private int sessionType;
        private int msgType;
        private String msgContent;
        private int msgId;
        private long createTime;
        private String extContent;

        public long getFromUserId() {
            return fromUserId;
        }

        public void setFromUserId(long fromUserId) {
            this.fromUserId = fromUserId;
        }

        public String getFromUserName() {
            return fromUserName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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

        public int getMsgType() {
            return msgType;
        }

        public void setMsgType(int msgType) {
            this.msgType = msgType;
        }

        public String getMsgContent() {
            return msgContent;
        }

        public void setMsgContent(String msgContent) {
            this.msgContent = msgContent;
        }

        public int getMsgId() {
            return msgId;
        }

        public void setMsgId(int msgId) {
            this.msgId = msgId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public String getExtContent() {
            return extContent;
        }

    }
}
