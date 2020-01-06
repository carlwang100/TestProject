package com.sunland.new_im.websocket.packet;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengd on 2018/12/13.
 */

public class ImMsgSearchResPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:1016
     * MsgSearchRes : {"resultCode":"类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义","keywords":"类型:String 说明:搜索关键词，与请求时相同","sessionNum":"类型:Number 说明:匹配到的会话数","sessionList":[{"sessionType":"类型:Number 说明:会话类型，0：单聊；1：群聊","sessionId":"类型:Number 说明:对方Id，单聊为对方imid，群聊为群id","sessionName":"类型:String 说明:单聊：对方名字；群聊：群名称","sessionImgUrl":"类型:String 说明:会话头像，单聊：对方的头像；群聊：群头像","msgNum":"类型:Number 说明:当前会话匹配到的记录数","msgList":[{"msgId":"类型:Number 说明:消息id","msgType":"类型:Number 说明:消息类型","msgCreateTime":"类型:Number 说明:消息发送时间，时间戳，单位 毫秒，类型为long","msgContent":"类型:String 说明:消息内容","senderId":"类型:Number 说明:发送方的imid","senderImgUrl":"类型:String 说明:发送者头像","senderName":"类型:String 说明:发送者的名字"}]}]}
     */

    @SerializedName("MsgSearchRes")
    private MsgSearchResBean MsgSearchRes;

    public MsgSearchResBean getMsgSearchRes() {
        return MsgSearchRes;
    }

    public void setMsgSearchRes(MsgSearchResBean MsgSearchRes) {
        this.MsgSearchRes = MsgSearchRes;
    }

    public static class MsgSearchResBean {
        /**
         * resultCode : 类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义
         * keywords : 类型:String 说明:搜索关键词，与请求时相同
         * sessionNum : 类型:Number 说明:匹配到的会话数
         * sessionList : [{"sessionType":"类型:Number 说明:会话类型，0：单聊；1：群聊","sessionId":"类型:Number 说明:对方Id，单聊为对方imid，群聊为群id","sessionName":"类型:String 说明:单聊：对方名字；群聊：群名称","sessionImgUrl":"类型:String 说明:会话头像，单聊：对方的头像；群聊：群头像","msgNum":"类型:Number 说明:当前会话匹配到的记录数","msgList":[{"msgId":"类型:Number 说明:消息id","msgType":"类型:Number 说明:消息类型","msgCreateTime":"类型:Number 说明:消息发送时间，时间戳，单位 毫秒，类型为long","msgContent":"类型:String 说明:消息内容","senderId":"类型:Number 说明:发送方的imid","senderImgUrl":"类型:String 说明:发送者头像","senderName":"类型:String 说明:发送者的名字"}]}]
         */

        @SerializedName("resultCode")
        private int resultCode;
        @SerializedName("keywords")
        private String keywords;
        @SerializedName("sessionNum")
        private String sessionNum;
        @SerializedName("sessionList")
        private List<SessionListBean> sessionList;

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getSessionNum() {
            return sessionNum;
        }

        public void setSessionNum(String sessionNum) {
            this.sessionNum = sessionNum;
        }

        public List<SessionListBean> getSessionList() {
            return sessionList;
        }

        public void setSessionList(List<SessionListBean> sessionList) {
            this.sessionList = sessionList;
        }

        public static class SessionListBean implements Parcelable {
            /**
             * sessionType : 类型:Number 说明:会话类型，0：单聊；1：群聊
             * sessionId : 类型:Number 说明:对方Id，单聊为对方imid，群聊为群id
             * sessionName : 类型:String 说明:单聊：对方名字；群聊：群名称
             * sessionImgUrl : 类型:String 说明:会话头像，单聊：对方的头像；群聊：群头像
             * msgNum : 类型:Number 说明:当前会话匹配到的记录数
             * msgList : [{"msgId":"类型:Number 说明:消息id","msgType":"类型:Number 说明:消息类型","msgCreateTime":"类型:Number 说明:消息发送时间，时间戳，单位 毫秒，类型为long","msgContent":"类型:String 说明:消息内容","senderId":"类型:Number 说明:发送方的imid","senderImgUrl":"类型:String 说明:发送者头像","senderName":"类型:String 说明:发送者的名字"}]
             * groupType: 类型Number 说明：群类型是否为部门群(1：部门群， 0： 普通群)
             */

            @SerializedName("sessionType")
            private int sessionType;
            @SerializedName("sessionId")
            private long sessionId;
            @SerializedName("sessionName")
            private String sessionName;
            @SerializedName("sessionImgUrl")
            private String sessionImgUrl;
            @SerializedName("msgNum")
            private String msgNum;
            @SerializedName("msgList")
            private List<MsgListBean> msgList = new ArrayList<>();
            @SerializedName("groupType")
            private int groupType;

            protected SessionListBean(Parcel in) {
                sessionType = in.readInt();
                sessionId = in.readLong();
                sessionName = in.readString();
                sessionImgUrl = in.readString();
                msgNum = in.readString();
                msgList = in.createTypedArrayList(MsgListBean.CREATOR);
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(sessionType);
                dest.writeLong(sessionId);
                dest.writeString(sessionName);
                dest.writeString(sessionImgUrl);
                dest.writeString(msgNum);
                dest.writeTypedList(msgList);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<SessionListBean> CREATOR = new Creator<SessionListBean>() {
                @Override
                public SessionListBean createFromParcel(Parcel in) {
                    return new SessionListBean(in);
                }

                @Override
                public SessionListBean[] newArray(int size) {
                    return new SessionListBean[size];
                }
            };

            public int getSessionType() {
                return sessionType;
            }

            public void setSessionType(int sessionType) {
                this.sessionType = sessionType;
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

            public String getSessionImgUrl() {
                return sessionImgUrl;
            }

            public void setSessionImgUrl(String sessionImgUrl) {
                this.sessionImgUrl = sessionImgUrl;
            }

            public String getMsgNum() {
                return msgNum;
            }

            public void setMsgNum(String msgNum) {
                this.msgNum = msgNum;
            }

            public List<MsgListBean> getMsgList() {
                return msgList;
            }

            public void setMsgList(List<MsgListBean> msgList) {
                this.msgList = msgList;
            }

            public int getGroupType() {
                return groupType;
            }

            public void setGroupType(int groupType) {
                this.groupType = groupType;
            }

            public static class MsgListBean implements Parcelable{
                /**
                 * msgId : 类型:Number 说明:消息id
                 * msgType : 类型:Number 说明:消息类型
                 * msgCreateTime : 类型:Number 说明:消息发送时间，时间戳，单位 毫秒，类型为long
                 * msgContent : 类型:String 说明:消息内容
                 * senderId : 类型:Number 说明:发送方的imid
                 * senderImgUrl : 类型:String 说明:发送者头像
                 * senderName : 类型:String 说明:发送者的名字
                 */

                @SerializedName("msgId")
                private int msgId;
                @SerializedName("msgType")
                private int msgType;
                @SerializedName("msgCreateTime")
                private long msgCreateTime;
                @SerializedName("msgContent")
                private String msgContent;
                @SerializedName("senderId")
                private long senderId;
                @SerializedName("senderImgUrl")
                private String senderImgUrl;
                @SerializedName("senderName")
                private String senderName;

                protected MsgListBean(Parcel in) {
                    msgId = in.readInt();
                    msgType = in.readInt();
                    msgCreateTime = in.readLong();
                    msgContent = in.readString();
                    senderId = in.readLong();
                    senderImgUrl = in.readString();
                    senderName = in.readString();
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(msgId);
                    dest.writeInt(msgType);
                    dest.writeLong(msgCreateTime);
                    dest.writeString(msgContent);
                    dest.writeLong(senderId);
                    dest.writeString(senderImgUrl);
                    dest.writeString(senderName);
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                public static final Creator<MsgListBean> CREATOR = new Creator<MsgListBean>() {
                    @Override
                    public MsgListBean createFromParcel(Parcel in) {
                        return new MsgListBean(in);
                    }

                    @Override
                    public MsgListBean[] newArray(int size) {
                        return new MsgListBean[size];
                    }
                };

                public int getMsgId() {
                    return msgId;
                }

                public void setMsgId(int msgId) {
                    this.msgId = msgId;
                }

                public int getMsgType() {
                    return msgType;
                }

                public void setMsgType(int msgType) {
                    this.msgType = msgType;
                }

                public long getMsgCreateTime() {
                    return msgCreateTime;
                }

                public void setMsgCreateTime(long msgCreateTime) {
                    this.msgCreateTime = msgCreateTime;
                }

                public String getMsgContent() {
                    return msgContent;
                }

                public void setMsgContent(String msgContent) {
                    this.msgContent = msgContent;
                }

                public long getSenderId() {
                    return senderId;
                }

                public void setSenderId(long senderId) {
                    this.senderId = senderId;
                }

                public String getSenderImgUrl() {
                    return senderImgUrl;
                }

                public void setSenderImgUrl(String senderImgUrl) {
                    this.senderImgUrl = senderImgUrl;
                }

                public String getSenderName() {
                    return senderName;
                }

                public void setSenderName(String senderName) {
                    this.senderName = senderName;
                }
            }
        }
    }
}
