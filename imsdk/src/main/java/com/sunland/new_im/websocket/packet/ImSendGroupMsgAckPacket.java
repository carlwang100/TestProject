package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.Map;

/**
 * Created by kai on 2018/11/2
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImSendGroupMsgAckPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:7012
     * IMGroupMsgDataAck : {"sessionId":"类型:Number 说明:群组groupid","mSessionType":"类型:Number 说明:2：群聊","msgId":"类型:Number 说明:消息id","msgType":"类型:Number 说明:1：文本，2：音频，3：图片，4：视频，5：文件","createTime":"类型:Number 说明:消息发送时间戳（以服务器时间为准），单位 毫秒，类型为long。","resultCode":"类型:Number 说明:消息发送结果. 0 代表成功； 非0代表失败，具体意义参考后台定义","groupAtType":"类型:Number 说明:0:没有at消息， 1: at部分人, 2:at所有人","groupAtMember":{"10101100101":"@的人1","10101010012":"@的人2"},"receiverNum":"类型:Number 说明:该消息应接收成员人数"}
     */

    private IMGroupMsgDataAckBean IMGroupMsgDataAck;

    public IMGroupMsgDataAckBean getIMGroupMsgDataAck() {
        return IMGroupMsgDataAck;
    }

    public static class IMGroupMsgDataAckBean {
        /**
         * sessionId : 类型:Number 说明:群组groupid
         * mSessionType : 类型:Number 说明:2：群聊
         * msgId : 类型:Number 说明:消息id
         * msgType : 类型:Number 说明:1：文本，2：音频，3：图片，4：视频，5：文件
         * createTime : 类型:Number 说明:消息发送时间戳（以服务器时间为准），单位 毫秒，类型为long。
         * resultCode : 类型:Number 说明:消息发送结果. 0 代表成功； 非0代表失败，具体意义参考后台定义
         * groupAtType : 类型:Number 说明:0:没有at消息， 1: at部分人, 2:at所有人
         * groupAtMember : {"10101100101":"@的人1","10101010012":"@的人2"}
         * receiverNum : 类型:Number 说明:该消息应接收成员人数
         */

        private long sessionId;
        private int sessionType;
        private int msgId;
        private int msgType;
        private long createTime;
        private int resultCode;
        private int groupAtType;
        private Map<Long, String> groupAtMember;
        private String receiverNum;

        public long getSessionId() {
            return sessionId;
        }

        public int getSessionType() {
            return sessionType;
        }

        public int getMsgId() {
            return msgId;
        }

        public int getMsgType() {
            return msgType;
        }

        public long getCreateTime() {
            return createTime;
        }

        public int getResultCode() {
            return resultCode;
        }

        public int getGroupAtType() {
            return groupAtType;
        }

        public Map<Long, String> getGroupAtMember() {
            return groupAtMember;
        }

        public String getReceiverNum() {
            return receiverNum;
        }
    }
}
