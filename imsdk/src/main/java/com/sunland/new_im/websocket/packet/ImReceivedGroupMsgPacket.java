package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.Map;

/**
 * Created by kai on 2018/11/6
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImReceivedGroupMsgPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:7013
     * IMGroupMsgDataDeliver : {"fromUserId":"类型:Number 说明:消息发送方imid","fromUserName":"类型:String 说明:消息发送方名字","fromUserImageUrl":"类型:String 说明:消息发送方头像url","sessionId":"类型:Number 说明:群组groupid","sessionName":"类型:String 说明:群名称","sessionImageUrl":"类型:String 说明:群头像URL","mSessionType":"类型:Number 说明:2：群聊","msgType":"类型:Number 说明:消息类型。 1：文本，2：音频，3：图片，4：视频，5：文件","msgContent":"类型:String 说明:消息内容。最长600字符。不支持超长文本","msgId":"类型:Number 说明:消息id","createTime":"类型:Number 说明:消息发送时间戳，以服务器时间为准， 单位 毫秒，类型为long","groupAtType":"类型:Number 说明:0:没有at消息， 1: at部分人, 2:at所有人","groupAtMember":[]}
     */

    private IMGroupMsgDataDeliverBean IMGroupMsgDataDeliver;

    public IMGroupMsgDataDeliverBean getIMGroupMsgDataDeliver() {
        return IMGroupMsgDataDeliver;
    }

    public void setIMGroupMsgDataDeliver(IMGroupMsgDataDeliverBean IMGroupMsgDataDeliver) {
        this.IMGroupMsgDataDeliver = IMGroupMsgDataDeliver;
    }

    public static class IMGroupMsgDataDeliverBean {
        /**
         * fromUserId : 类型:Number 说明:消息发送方imid
         * fromUserName : 类型:String 说明:消息发送方名字
         * fromUserImageUrl : 类型:String 说明:消息发送方头像url
         * sessionId : 类型:Number 说明:群组groupid
         * sessionName : 类型:String 说明:群名称
         * sessionImageUrl : 类型:String 说明:群头像URL
         * mSessionType : 类型:Number 说明:2：群聊
         * msgType : 类型:Number 说明:消息类型。 1：文本，2：音频，3：图片，4：视频，5：文件
         * msgContent : 类型:String 说明:消息内容。最长600字符。不支持超长文本
         * msgId : 类型:Number 说明:消息id
         * createTime : 类型:Number 说明:消息发送时间戳，以服务器时间为准， 单位 毫秒，类型为long
         * groupAtType : 类型:Number 说明:0:没有at消息， 1: at部分人, 2:at所有人
         * groupAtMember : []
         * groupType: 类型Number 说明：群类型是否为部门群(1：部门群， 0： 普通群)
         */

        private long fromUserId;
        private String fromUserName;
        private String fromUserImageUrl;
        private long sessionId;
        private String sessionName;
        private String sessionImageUrl;
        private int sessionType;
        private int msgType;
        private String msgContent;
        private int msgId;
        private long createTime;
        private int groupAtType;
        private Map<Long,String> groupAtMember;
        private String extContent;
        private int groupType;

        public long getFromUserId() {
            return fromUserId;
        }

        public void setFromUserId(long fromUserId) {
            this.fromUserId = fromUserId;
        }

        public String getFromUserName() {
            return fromUserName;
        }

        public void setFromUserName(String fromUserName) {
            this.fromUserName = fromUserName;
        }

        public String getFromUserImageUrl() {
            return fromUserImageUrl;
        }

        public void setFromUserImageUrl(String fromUserImageUrl) {
            this.fromUserImageUrl = fromUserImageUrl;
        }

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

        public Map<Long,String> getGroupAtMember() {
            return groupAtMember;
        }

        public String getExtContent() {
            return extContent;
        }

        public int getGroupType() {
            return groupType;
        }

        public void setGroupType(int groupType) {
            this.groupType = groupType;
        }
    }
}
