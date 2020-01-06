package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

import java.util.Map;

/**
 * Created by kai on 2018/11/2
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImSendGroupMsgPacket extends ImBaseRequestPacket {

    /**
     * IMGroupMsgData : {"sessionId":"类型:Number 说明:群组groupid","fromUserName":"类型:String 说明:消息发送方名字","fromUserImageUrl":"类型:String 说明:消息发送方头像url","sessionName":"类型:String 说明:群名称","sessionImageUrl":"类型:String 说明:群头像URL","mSessionType":"类型:Number 说明:会话类型。 2：群聊","msgType":"类型:Number 说明:消息类型。 1：文本，2：音频，3：图片，4：视频，5：文件","msgContent":"类型:String 说明:消息内容。最长600字符。不支持超长文本","groupAtType":"类型:Number 说明:0:没有at消息， 1: at部分人, 2:at所有人","groupAtMember":{"101000110010":"被@的人1","101000110012":"被@的人2"}}
     */

    private IMGroupMsgDataBean IMGroupMsgData;

    public ImSendGroupMsgPacket(EntryBean entry, IMGroupMsgDataBean IMGroupMsgData) {
        super(entry);
        this.IMGroupMsgData = IMGroupMsgData;
    }

    public static class IMGroupMsgDataBean {
        /**
         * sessionId : 类型:Number 说明:群组groupid
         * fromUserName : 类型:String 说明:消息发送方名字
         * fromUserImageUrl : 类型:String 说明:消息发送方头像url
         * sessionName : 类型:String 说明:群名称
         * sessionImageUrl : 类型:String 说明:群头像URL
         * mSessionType : 类型:Number 说明:会话类型。 2：群聊
         * msgType : 类型:Number 说明:消息类型。 1：文本，2：音频，3：图片，4：视频，5：文件
         * msgContent : 类型:String 说明:消息内容。最长600字符。不支持超长文本
         * groupAtType : 类型:Number 说明:0:没有at消息， 1: at部分人, 2:at所有人
         * groupAtMember : {"101000110010":"被@的人1","101000110012":"被@的人2"}
         */

        private long sessionId;
        private String fromUserName;
        private String fromUserImageUrl;
        private String sessionName;
        private String sessionImageUrl;
        private int sessionType;
        private int msgType;
        private String msgContent;
        private int groupAtType;
        private Map<Long, String> groupAtMember;

        public void setSessionId(long sessionId) {
            this.sessionId = sessionId;
        }

        public void setFromUserName(String fromUserName) {
            this.fromUserName = fromUserName;
        }

        public void setFromUserImageUrl(String fromUserImageUrl) {
            this.fromUserImageUrl = fromUserImageUrl;
        }


        public void setSessionName(String sessionName) {
            this.sessionName = sessionName;
        }

        public void setSessionImageUrl(String sessionImageUrl) {
            this.sessionImageUrl = sessionImageUrl;
        }
        public void setSessionType(int sessionType) {
            this.sessionType = sessionType;
        }


        public void setMsgType(int msgType) {
            this.msgType = msgType;
        }


        public void setMsgContent(String msgContent) {
            this.msgContent = msgContent;
        }


        public void setGroupAtType(int groupAtType) {
            this.groupAtType = groupAtType;
        }


        public void setGroupAtMember(Map<Long, String> groupAtMember) {
            this.groupAtMember = groupAtMember;
        }
    }
}
