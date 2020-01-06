package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kai on 2018/8/28
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImPullMsgRecordResPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:4004
     * PullChatMsgRecordRes : {"fromUserId":"类型:Number 说明:聊天记录拉取者的imid","resultCode":"类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义","sessionId":"类型:Number","sessionType":"类型:Number 说明:1：单聊，2：群聊","sessionName":"类型:String 说明:对方名字。A拉取聊天记录。SessionName为B的名字","imageUrl":"类型:String 说明:对方头像Url。A拉取聊天记录。为B的头像Url","status":"类型:Number 说明:对方在职状态。A拉取聊天记录，B的在职状态","num":"类型:Number 说明:返回的聊天记录数","singleChatHistoryMsgList":[{"msgId":"类型:Number 说明:消息id","msgType":"类型:Number 说明:消息类型","msgCreateTime":"类型:String 说明:消息发送时间，格式如：2018-08-08 14:47:28","msgStatus":"类型:Number 说明:1：正常， 2：撤销","msgContent":"类型:String 说明:消息内容， 撤销时为空","senderId":"类型:Number 说明:发送方的imid","peerUnRead":"类型:Number 说明:对方是否读取本条消息，0：已读， 1：未读。 仅适用于本方发送出去的消息，如果是对方发送的消息无此字段"},{"more":"..."}],"groupChatHistoryMsgList":[{"more...":"类型:String 说明:群聊保留"},{"more":"..."}]}
     */


    private PullChatMsgRecordResBean PullChatMsgRecordRes;


    public PullChatMsgRecordResBean getPullChatMsgRecordRes() {
        return PullChatMsgRecordRes;
    }

    public void setPullChatMsgRecordRes(PullChatMsgRecordResBean PullChatMsgRecordRes) {
        this.PullChatMsgRecordRes = PullChatMsgRecordRes;
    }

    public static class PullChatMsgRecordResBean {
        /**
         * fromUserId : 类型:Number 说明:聊天记录拉取者的imid
         * resultCode : 类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义
         * sessionId : 类型:Number
         * mSessionType : 类型:Number 说明:1：单聊，2：群聊
         * sessionName : 类型:String 说明:对方名字。A拉取聊天记录。SessionName为B的名字
         * imageUrl : 类型:String 说明:对方头像Url。A拉取聊天记录。为B的头像Url
         * status : 类型:Number 说明:对方在职状态。A拉取聊天记录，B的在职状态
         * num : 类型:Number 说明:返回的聊天记录数
         * singleChatHistoryMsgList : [{"msgId":"类型:Number 说明:消息id","msgType":"类型:Number 说明:消息类型","msgCreateTime":"类型:String 说明:消息发送时间，格式如：2018-08-08 14:47:28","msgStatus":"类型:Number 说明:1：正常， 2：撤销","msgContent":"类型:String 说明:消息内容， 撤销时为空","senderId":"类型:Number 说明:发送方的imid","peerUnRead":"类型:Number 说明:对方是否读取本条消息，0：已读， 1：未读。 仅适用于本方发送出去的消息，如果是对方发送的消息无此字段"},{"more":"..."}]
         * groupChatHistoryMsgList : [{"more...":"类型:String 说明:群聊保留"},{"more":"..."}]
         */

        private long fromUserId;
        private int resultCode;
        private long sessionId;
        private int sessionType;
        private String sessionName;
        private String imageUrl;
        private int status;
        private int num;
        private List<SingleChatHistoryMsgListBean> singleChatHistoryMsgList = new ArrayList<>();
        private List<GroupChatHistoryMsgListBean> groupChatHistoryMsgList = new ArrayList<>();
        private Map<Long, SenderUserInfo> groupMsgSenderUserInfoMap = new HashMap<>();

        public long getFromUserId() {
            return fromUserId;
        }

        public void setFromUserId(long fromUserId) {
            this.fromUserId = fromUserId;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
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

        public String getSessionName() {
            return sessionName;
        }

        public void setSessionName(String sessionName) {
            this.sessionName = sessionName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<SingleChatHistoryMsgListBean> getSingleChatHistoryMsgList() {
            return singleChatHistoryMsgList;
        }

        public List<GroupChatHistoryMsgListBean> getGroupChatHistoryMsgList() {
            return groupChatHistoryMsgList;
        }


        public Map<Long, SenderUserInfo> getGroupMsgSenderUserInfoMap() {
            return groupMsgSenderUserInfoMap;
        }

        public static class ChatHistoryMsg{
            private int msgId;
            private int msgType;
            private long msgCreateTime;
            private int msgStatus;
            private String msgContent;
            private long senderId;
            private String extContent;
            private int receivedPeerMsg;

            public int getMsgId() {
                return msgId;
            }

            public int getMsgType() {
                return msgType;
            }

            public long getMsgCreateTime() {
                return msgCreateTime;
            }

            public int getMsgStatus() {
                return msgStatus;
            }

            public String getMsgContent() {
                return msgContent;
            }

            public long getSenderId() {
                return senderId;
            }

            public String getExtContent() {
                return extContent;
            }

            public int getReceivedPeerMsg() {
                return receivedPeerMsg;
            }

            public void setReceivedPeerMsg(int receivedPeerMsg) {
                this.receivedPeerMsg = receivedPeerMsg;
            }
        }

        public static class SingleChatHistoryMsgListBean extends ChatHistoryMsg{
            /**
             * msgId : 类型:Number 说明:消息id
             * msgType : 类型:Number 说明:消息类型
             * msgCreateTime : 类型:Number 说明:消息发送时间，时间戳，单位 毫秒，类型为long
             * msgStatus : 类型:Number 说明:1：正常， 2：撤销
             * msgContent : 类型:String 说明:消息内容， 撤销时为空
             * senderId : 类型:Number 说明:发送方的imid
             * peerUnRead : 类型:Number 说明:对方是否读取本条消息，0：已读， 1：未读。 仅适用于本方发送出去的消息，如果是对方发送的消息无此字段
             *  "receivedPeerMsg": "类型:Number 说明:针对于别人发的未撤回的语音消息，1：已接收，0：未接收"
             * more : ...
             */

            private int peerUnRead;

            public int getPeerUnRead() {
                return peerUnRead;
            }
        }

        public static class GroupChatHistoryMsgListBean extends ChatHistoryMsg{

            /**
             * msgId : 类型:Number
             * msgType : 类型:Number
             * msgCreateTime : 类型:Number
             * msgStatus : 类型:Number
             * msgContent : 类型:String
             * unReadPeerNum : 类型:Number
             * haveReadPeerNum : 类型:Number
             * senderId : 类型:Number
             */

            private int unReadPeerNum;
            private int haveReadPeerNum;

            public int getUnReadPeerNum() {
                return unReadPeerNum;
            }

            public int getHaveReadPeerNum() {
                return haveReadPeerNum;
            }
        }

        public static class SenderUserInfo {

            /**
             * imid : 类型:Number 说明:imid
             * memberDegree : 类型:Number 说明:成员身份。1：普通成员，2：群主
             * enterTime : 类型:Number 说明:进群时间。单位毫秒
             * userName : 类型:String 说明:用户名字（真实昵称）
             * nickName : 类型:String 说明:用户在本群的群昵称
             * status : 类型:Number 说明:用户状态（1：在职，2：离职）
             * imageUrl : 类型:String 说明:用户头像url
             */

            private long imid;
            private int memberDegree;
            private long enterTime;
            private String userName;
            private String nickName;
            private int status;
            private String imageUrl;

            public long getImid() {
                return imid;
            }

            public void setImid(long imid) {
                this.imid = imid;
            }

            public int getMemberDegree() {
                return memberDegree;
            }

            public void setMemberDegree(int memberDegree) {
                this.memberDegree = memberDegree;
            }

            public long getEnterTime() {
                return enterTime;
            }

            public void setEnterTime(long enterTime) {
                this.enterTime = enterTime;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }
        }
    }
}
