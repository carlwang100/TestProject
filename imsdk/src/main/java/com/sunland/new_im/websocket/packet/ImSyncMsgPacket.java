package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by fengd on 2018/9/13.
 */

public class ImSyncMsgPacket extends ImBaseResponsePacket {


    /**
     * cid : 类型:Number 说明:6004
     * IMMsgDataTerminalSync : {"sessionId":"类型:Number 说明:会话id。单聊：接收方imid， 群聊：群组id","mSessionType":"类型:Number 说明:会话类型。1：单聊， 2：群聊","sessionName":"类型:String 说明:会话名字（消息接收者名字）","sessionImageUrl":"类型:String 说明:会话头像url（消息接收者头像）","msgType":"类型:Number 说明:消息类型。 1：文本，2：音频，3：图片，4：视频，5：文件","msgContent":"类型:String 说明:消息内容。最长600字符。不支持超长文本","msgId":"类型:Number 说明:消息id","createTime":"类型:Number 说明:消息发送时间戳，以服务器时间为准， 单位 毫秒，类型为long"}
     */

    @SerializedName("IMMsgDataTerminalSync")
    private IMMsgDataTerminalSyncBean IMMsgDataTerminalSync;

    public IMMsgDataTerminalSyncBean getIMMsgDataTerminalSync() {
        return IMMsgDataTerminalSync;
    }

    public void setIMMsgDataTerminalSync(IMMsgDataTerminalSyncBean IMMsgDataTerminalSync) {
        this.IMMsgDataTerminalSync = IMMsgDataTerminalSync;
    }

    public static class IMMsgDataTerminalSyncBean {
        /**
         * sessionId : 类型:Number 说明:会话id。单聊：接收方imid， 群聊：群组id
         * mSessionType : 类型:Number 说明:会话类型。1：单聊， 2：群聊
         * sessionName : 类型:String 说明:会话名字（消息接收者名字）
         * sessionImageUrl : 类型:String 说明:会话头像url（消息接收者头像）
         * msgType : 类型:Number 说明:消息类型。 1：文本，2：音频，3：图片，4：视频，5：文件
         * msgContent : 类型:String 说明:消息内容。最长600字符。不支持超长文本
         * msgId : 类型:Number 说明:消息id
         * createTime : 类型:Number 说明:消息发送时间戳，以服务器时间为准， 单位 毫秒，类型为long
         * extContent: "类型:String 说明:消息类型8使用"
         */

        @SerializedName("sessionId")
        private long sessionId;
        @SerializedName("sessionType")
        private int sessionType;
        @SerializedName("sessionName")
        private String sessionName;
        @SerializedName("sessionImageUrl")
        private String sessionImageUrl;
        @SerializedName("msgType")
        private int msgType;
        @SerializedName("msgContent")
        private String msgContent;
        @SerializedName("msgId")
        private int msgId;
        @SerializedName("createTime")
        private long createTime;
        @SerializedName("extContent")
        private String extContent;

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

        public String getSessionName() {
            return sessionName;
        }

        public void setSessionName(String sessionName) {
            this.sessionName = sessionName;
        }

        public String getSessionImageUrl() {
            return sessionImageUrl;
        }

        public void setSessionImageUrl(String sessionImageUrl) {
            this.sessionImageUrl = sessionImageUrl;
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

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getExtContent() {
            return extContent;
        }
    }
}
