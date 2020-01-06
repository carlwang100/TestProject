package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.List;

/**
 * CID4020 - 拉取撤回消息记录应答
 */
public class ImPullRevokeMsgInfoListResPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:4020
     * PullRevokeMsgInfoListRes : {"resultCode":"类型:Number 说明:请求返回码。0表示成功，其他表示失败","revokeMsgInfoList":[{"sessionId":"类型:Number 说明:会话id。单聊为对方imid，群聊为群组id","sessionType":"类型:Number 说明:会话类型：1表示单聊，2表示群聊","revokeRecordList":[{"senderId":"类型:Number 说明:原消息发送者imid","msgId":"类型:Number 说明:被撤回的msgId","revokerId":"类型:Number 说明:撤回操作者imid","revokerName":"类型:String 说明:撤回者名字（群的话为昵称）","revokeTime":"类型:Number 说明:撤回时间"},{"more":"..."}]}]}
     */

    @SerializedName("PullRevokeMsgInfoListRes")
    private PullRevokeMsgInfoListResBean PullRevokeMsgInfoListRes;

    public PullRevokeMsgInfoListResBean getPullRevokeMsgInfoListRes() {
        return PullRevokeMsgInfoListRes;
    }

    public void setPullRevokeMsgInfoListRes(PullRevokeMsgInfoListResBean PullRevokeMsgInfoListRes) {
        this.PullRevokeMsgInfoListRes = PullRevokeMsgInfoListRes;
    }

    public static class PullRevokeMsgInfoListResBean {
        /**
         * resultCode : 类型:Number 说明:请求返回码。0表示成功，其他表示失败
         * revokeMsgInfoList : [{"sessionId":"类型:Number 说明:会话id。单聊为对方imid，群聊为群组id","sessionType":"类型:Number 说明:会话类型：1表示单聊，2表示群聊","revokeRecordList":[{"senderId":"类型:Number 说明:原消息发送者imid","msgId":"类型:Number 说明:被撤回的msgId","revokerId":"类型:Number 说明:撤回操作者imid","revokerName":"类型:String 说明:撤回者名字（群的话为昵称）","revokeTime":"类型:Number 说明:撤回时间"},{"more":"..."}]}]
         */

        @SerializedName("resultCode")
        private int resultCode;
        @SerializedName("revokeMsgInfoList")
        private List<RevokeMsgInfoListBean> revokeMsgInfoList;

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public List<RevokeMsgInfoListBean> getRevokeMsgInfoList() {
            return revokeMsgInfoList;
        }

        public void setRevokeMsgInfoList(List<RevokeMsgInfoListBean> revokeMsgInfoList) {
            this.revokeMsgInfoList = revokeMsgInfoList;
        }

        public static class RevokeMsgInfoListBean {
            /**
             * sessionId : 类型:Number 说明:会话id。单聊为对方imid，群聊为群组id
             * sessionType : 类型:Number 说明:会话类型：1表示单聊，2表示群聊
             * revokeRecordList : [{"senderId":"类型:Number 说明:原消息发送者imid","msgId":"类型:Number 说明:被撤回的msgId","revokerId":"类型:Number 说明:撤回操作者imid","revokerName":"类型:String 说明:撤回者名字（群的话为昵称）","revokeTime":"类型:Number 说明:撤回时间"},{"more":"..."}]
             */

            @SerializedName("sessionId")
            private long sessionId;
            @SerializedName("sessionType")
            private int sessionType;
            @SerializedName("revokeRecordList")
            private List<RevokeRecordListBean> revokeRecordList;

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

            public List<RevokeRecordListBean> getRevokeRecordList() {
                return revokeRecordList;
            }

            public void setRevokeRecordList(List<RevokeRecordListBean> revokeRecordList) {
                this.revokeRecordList = revokeRecordList;
            }

            public static class RevokeRecordListBean {
                /**
                 * senderId : 类型:Number 说明:原消息发送者imid
                 * msgId : 类型:Number 说明:被撤回的msgId
                 * revokerId : 类型:Number 说明:撤回操作者imid
                 * revokerName : 类型:String 说明:撤回者名字（群的话为昵称）
                 * revokeTime : 类型:Number 说明:撤回时间
                 * more : ...
                 */

                @SerializedName("senderId")
                private long senderId;
                @SerializedName("msgId")
                private int msgId;
                @SerializedName("revokerId")
                private long revokerId;
                @SerializedName("revokerName")
                private String revokerName;
                @SerializedName("revokeTime")
                private long revokeTime;

                public long getSenderId() {
                    return senderId;
                }

                public void setSenderId(long senderId) {
                    this.senderId = senderId;
                }

                public int getMsgId() {
                    return msgId;
                }

                public void setMsgId(int msgId) {
                    this.msgId = msgId;
                }

                public long getRevokerId() {
                    return revokerId;
                }

                public void setRevokerId(long revokerId) {
                    this.revokerId = revokerId;
                }

                public String getRevokerName() {
                    return revokerName;
                }

                public void setRevokerName(String revokerName) {
                    this.revokerName = revokerName;
                }

                public long getRevokeTime() {
                    return revokeTime;
                }

                public void setRevokeTime(long revokeTime) {
                    this.revokeTime = revokeTime;
                }
            }
        }
    }
}
