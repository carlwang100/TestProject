package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by kai on 2018/8/16
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImSendSingleMsgPacket extends ImBaseRequestPacket {

    /**
     * IMMsgData : {"sessionId":0,"mSessionType":0,"msgType":1,"uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配。","msgContent":"类型:String 说明:消息内容。最长600字符。不支持超长文本"}
     */

    private IMMsgDataBean IMMsgData;

    public ImSendSingleMsgPacket(EntryBean entry, IMMsgDataBean IMMsgData) {
        super(entry);
        this.IMMsgData = IMMsgData;
    }

    public IMMsgDataBean getIMMsgData() {
        return IMMsgData;
    }

    public void setIMMsgData(IMMsgDataBean IMMsgData) {
        this.IMMsgData = IMMsgData;
    }

    public static class IMMsgDataBean {
        /**
         * sessionId : 0
         * mSessionType : 0
         * msgType : 1
         * uniqueKey : 类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配。
         * msgContent : 类型:String 说明:消息内容。最长600字符。不支持超长文本
         * fromUserName: "类型:String 说明:消息发送方名字",
         * imageUrl: "类型:String 说明:消息发送方头像url",
         */

        private long sessionId;
        private int sessionType;
        private int msgType;
        private String uniqueKey;
        private String msgContent;
        private String fromUserName;
        private String imageUrl;

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

        public String getUniqueKey() {
            return uniqueKey;
        }

        public void setUniqueKey(String uniqueKey) {
            this.uniqueKey = uniqueKey;
        }

        public String getMsgContent() {
            return msgContent;
        }

        public void setMsgContent(String msgContent) {
            this.msgContent = msgContent;
        }

        public String getFromUserName() {
            return fromUserName;
        }

        public void setFromUserName(String fromUserName) {
            this.fromUserName = fromUserName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
