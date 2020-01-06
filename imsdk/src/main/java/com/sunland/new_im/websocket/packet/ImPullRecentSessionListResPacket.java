package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kai on 2018/8/28
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImPullRecentSessionListResPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:4018
     * PullRecentSessionListRes : {"fromUserId":"类型:Number 说明:用户imid","resultCode":"类型:Number 说明:请求返回码。0表示成功，其他表示失败","pullSessionNum":"类型:Number 说明:拉取请求中指定的拉取会话数目","getSessionNum":"类型:Number 说明:实际成功拉取的会话数目","recentSessionList":[{"sessionId":"类型:Number 说明:会话id。单聊为对方imid，群聊为群组id","mSessionType":"类型:Number 说明:会话类型：1表示单聊，2表示群聊","sessionName":"类型:String 说明:会话名称：单聊为对方名称，群聊为群组名称","imageUrl":"类型:String 说明:会话头像url。单聊为对方头像，群聊为群组头像","latestMsgId":"类型:Number 说明:最新一条消息id","latestMsgType":"类型:Number 说明:最新一条消息类型","latestMsgCreateTime":"类型:Number 说明:最新一条消息时间，时间戳","latestSenderId":"类型:Number 说明:最后一条消息发送者的imid","latestSenderName":"类型:String 说明:最后一条消息发送者名字（只有当最后一条是对方发送，才设置为对方名字）","latestMsgStatus":"类型:Number 说明:最后一条消息状态（1：正常，2：被撤回）","lastestMsgContent":"类型:String 说明:最新一条消息内容（当消息未被撤回，才设置字段值。被撤回时，由前端自行设置提示语）","unReadNum":"类型:Number 说明:未读消息数目","isTop":"类型:Number 说明:是否被置顶。0：未置顶，1：置顶","noDisturb":"类型:Number 说明:是否设置免打扰。0：未设置免打扰，1：设置免打扰","hasGroupAtMsg":"类型:Number 说明:是否有@自己的消息（群聊使用）","status":"类型:Number 说明:1：对方在职；2：对方离职"}]}
     */

    private PullRecentSessionListResBean PullRecentSessionListRes;

    public PullRecentSessionListResBean getPullRecentSessionListRes() {
        return PullRecentSessionListRes;
    }

    public static class PullRecentSessionListResBean {
        /**
         * fromUserId : 类型:Number 说明:用户imid
         * resultCode : 类型:Number 说明:请求返回码。0表示成功，其他表示失败
         * pullSessionNum : 类型:Number 说明:拉取请求中指定的拉取会话数目
         * getSessionNum : 类型:Number 说明:实际成功拉取的会话数目
         * recentSessionList : [{"sessionId":"类型:Number 说明:会话id。单聊为对方imid，群聊为群组id","mSessionType":"类型:Number 说明:会话类型：1表示单聊，2表示群聊","sessionName":"类型:String 说明:会话名称：单聊为对方名称，群聊为群组名称","imageUrl":"类型:String 说明:会话头像url。单聊为对方头像，群聊为群组头像","latestMsgId":"类型:Number 说明:最新一条消息id","latestMsgType":"类型:Number 说明:最新一条消息类型","latestMsgCreateTime":"类型:Number 说明:最新一条消息时间，时间戳","latestSenderId":"类型:Number 说明:最后一条消息发送者的imid","latestSenderName":"类型:String 说明:最后一条消息发送者名字（只有当最后一条是对方发送，才设置为对方名字）","latestMsgStatus":"类型:Number 说明:最后一条消息状态（1：正常，2：被撤回）","lastestMsgContent":"类型:String 说明:最新一条消息内容（当消息未被撤回，才设置字段值。被撤回时，由前端自行设置提示语）","unReadNum":"类型:Number 说明:未读消息数目","isTop":"类型:Number 说明:是否被置顶。0：未置顶，1：置顶","noDisturb":"类型:Number 说明:是否设置免打扰。0：未设置免打扰，1：设置免打扰","hasGroupAtMsg":"类型:Number 说明:是否有@自己的消息（群聊使用）","status":"类型:Number 说明:1：对方在职；2：对方离职"}]
         */

        private long fromUserId;
        private int resultCode;
        private int pullSessionNum;
        private int getSessionNum;
        private List<RecentSessionListBean> recentSessionList = new ArrayList<>(0);

        public long getFromUserId() {
            return fromUserId;
        }

        public int getResultCode() {
            return resultCode;
        }


        public int getPullSessionNum() {
            return pullSessionNum;
        }

        public int getGetSessionNum() {
            return getSessionNum;
        }

        public List<RecentSessionListBean> getRecentSessionList() {
            return recentSessionList;
        }


        public static class RecentSessionListBean {
            /**
             * sessionId : 类型:Number 说明:会话id。单聊为对方imid，群聊为群组id
             * mSessionType : 类型:Number 说明:会话类型：1表示单聊，2表示群聊
             * sessionName : 类型:String 说明:会话名称：单聊为对方名称，群聊为群组名称
             * imageUrl : 类型:String 说明:会话头像url。单聊为对方头像，群聊为群组头像
             * latestMsgId : 类型:Number 说明:最新一条消息id
             * latestMsgType : 类型:Number 说明:最新一条消息类型
             * latestMsgCreateTime : 类型:Number 说明:最新一条消息时间，时间戳
             * latestSenderId : 类型:Number 说明:最后一条消息发送者的imid
             * latestSenderName : 类型:String 说明:最后一条消息发送者名字（只有当最后一条是对方发送，才设置为对方名字）
             * latestMsgStatus : 类型:Number 说明:最后一条消息状态（1：正常，2：被撤回）
             * lastestMsgContent : 类型:String 说明:最新一条消息内容（当消息未被撤回，才设置字段值。被撤回时，由前端自行设置提示语）
             * unReadNum : 类型:Number 说明:未读消息数目
             * isTop : 类型:Number 说明:是否被置顶。0：未置顶，1：置顶
             * noDisturb : 类型:Number 说明:是否设置免打扰。0：未设置免打扰，1：设置免打扰
             * firstUnReadGroupAtMsgId : 类型:Number 说明:（群聊使用）第一条未读的@自己的msgId （备注：修改）
             * status : 类型:Number 说明:1：对方在职；2：对方离职
             * memberNum : 类型:Number 说明:（群聊使用）现有成员数目（备注：新增）
             * memberNumberMaximum : 类型:Number 说明:（群聊使用）成员数目上限（备注：新增）
             * lastestMsgExtContent: "类型:String 说明:消息类型8使用",
             * groupType: 类型Number 说明：群类型是否为部门群(1：部门群， 0： 普通群)
             * userType: 类型:Number 说明:单聊会话使用：1：普通用户，2：通知推送账号
             * description: "类型:String 说明:单聊会话使用：表示单聊会话对方的用户描述信息",
             * "receivedPeerMsg": "类型:Number 说明:针对于别人发的未撤回的语音消息，1：已接收，0：未接收",
             */

            private long sessionId;
            private int sessionType;
            private String sessionName;
            private String imageUrl;
            private int latestMsgId;
            private int latestMsgType;
            private long latestMsgCreateTime;
            private long latestSenderId;
            private String latestSenderName;
            private int latestMsgStatus;
            private String lastestMsgContent;
            private int unReadNum;
            private int isTop;
            private long topTime;
            private int noDisturb;
            private List<Integer> unReadAtMsgList = new ArrayList<>(0);
            private int status;
            private int memberNum;
            private int memberNumberMaximum;
            private String lastestMsgExtContent;
            private String description;
            private int groupType;
            private int userType;
            private int receivedPeerMsg;

            public long getSessionId() {
                return sessionId;
            }

            public int getSessionType() {
                return sessionType;
            }


            public String getSessionName() {
                return sessionName;
            }


            public String getImageUrl() {
                return imageUrl;
            }


            public int getLatestMsgId() {
                return latestMsgId;
            }


            public int getLatestMsgType() {
                return latestMsgType;
            }

            public long getLatestMsgCreateTime() {
                return latestMsgCreateTime;
            }


            public long getLatestSenderId() {
                return latestSenderId;
            }

            public String getLatestSenderName() {
                return latestSenderName;
            }

            public int getLatestMsgStatus() {
                return latestMsgStatus;
            }


            public String getLastestMsgContent() {
                return lastestMsgContent;
            }

            public int getUnReadNum() {
                return unReadNum;
            }

            public int getIsTop() {
                return isTop;
            }

            public long getTopTime() {
                return topTime;
            }

            public int getNoDisturb() {
                return noDisturb;
            }

            public int getFirstUnReadGroupAtMsgId() {
                if (unReadAtMsgList != null && !unReadAtMsgList.isEmpty()) {
                    return unReadAtMsgList.get(0);
                } else {
                    return 0;
                }
            }

            public int getStatus() {
                return status;
            }

            public int getMemberNum() {
                return memberNum;
            }

            public int getMemberNumberMaximum() {
                return memberNumberMaximum;
            }

            public String getLastestMsgExtContent() {
                return lastestMsgExtContent;
            }

            public int getGroupType() {
                return groupType;
            }

            public void setGroupType(int groupType) {
                this.groupType = groupType;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public String getDescription() {
                return description;
            }

            public int getReceivedPeerMsg() {
                return receivedPeerMsg;
            }

            public void setReceivedPeerMsg(int receivedPeerMsg) {
                this.receivedPeerMsg = receivedPeerMsg;
            }
        }
    }
}
