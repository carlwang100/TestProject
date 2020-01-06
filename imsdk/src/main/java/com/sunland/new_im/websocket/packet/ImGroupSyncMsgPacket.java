package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.Map;

/**
 * Created by kai on 2018/11/13
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImGroupSyncMsgPacket extends ImBaseResponsePacket {

    /**
     * IMGroupMsgDataTerminalSync : {"sessionId":"类型:Number 说明:群组groupid","sessionName":"类型:String 说明:群名称","sessionImageUrl":"类型:String 说明:群头像URL","sessionType":"类型:Number 说明:2：群聊","msgType":"类型:Number 说明:消息类型。 1：文本，2：音频，3：图片，4：视频，5：文件","msgContent":"类型:String 说明:消息内容。最长600字符。不支持超长文本","msgId":"类型:Number 说明:消息id","createTime":"类型:Number 说明:消息发送时间戳，以服务器时间为准， 单位 毫秒，类型为long","groupAtType":"类型:Number 说明:0:没有at消息， 1: at部分人, 2:at所有人","groupAtMember":[],"receiverNum":"类型:Number 说明:该消息应接收成员人数"}
     */

    private IMGroupMsgDataTerminalSyncBean IMGroupMsgDataTerminalSync;

    public IMGroupMsgDataTerminalSyncBean getIMGroupMsgDataTerminalSync() {
        return IMGroupMsgDataTerminalSync;
    }

    public void setIMGroupMsgDataTerminalSync(IMGroupMsgDataTerminalSyncBean IMGroupMsgDataTerminalSync) {
        this.IMGroupMsgDataTerminalSync = IMGroupMsgDataTerminalSync;
    }

    public static class IMGroupMsgDataTerminalSyncBean {
        /**
         * sessionId : 类型:Number 说明:群组groupid
         * sessionName : 类型:String 说明:群名称
         * sessionImageUrl : 类型:String 说明:群头像URL
         * sessionType : 类型:Number 说明:2：群聊
         * msgType : 类型:Number 说明:消息类型。 1：文本，2：音频，3：图片，4：视频，5：文件
         * msgContent : 类型:String 说明:消息内容。最长600字符。不支持超长文本
         * msgId : 类型:Number 说明:消息id
         * createTime : 类型:Number 说明:消息发送时间戳，以服务器时间为准， 单位 毫秒，类型为long
         * groupAtType : 类型:Number 说明:0:没有at消息， 1: at部分人, 2:at所有人
         * groupAtMember : [] 被at的群成员imid--->nickName的map
         * receiverNum : 类型:Number 说明:该消息应接收成员人数
         */

        private long sessionId;
        private int sessionType;
        private String sessionName;
        private String sessionImageUrl;
        private int msgType;
        private String msgContent;
        private int msgId;
        private long createTime;
        private int groupAtType;
        private int receiverNum;
        private Map<Long, String> groupAtMember;
        private String extContent;

        public long getSessionId() {
            return sessionId;
        }

        public void setSessionId(long sessionId) {
            this.sessionId = sessionId;
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

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getGroupAtType() {
            return groupAtType;
        }

        public void setGroupAtType(int groupAtType) {
            this.groupAtType = groupAtType;
        }

        public int getReceiverNum() {
            return receiverNum;
        }

        public void setReceiverNum(int receiverNum) {
            this.receiverNum = receiverNum;
        }

        public Map<Long, String> getGroupAtMember() {
            return groupAtMember;
        }

        public void setGroupAtMember(Map<Long, String> groupAtMember) {
            this.groupAtMember = groupAtMember;
        }

        public String getExtContent() {
            return extContent;
        }
    }
}
