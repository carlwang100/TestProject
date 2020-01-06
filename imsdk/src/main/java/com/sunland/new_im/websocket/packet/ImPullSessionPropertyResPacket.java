package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by kai on 2018/9/4
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImPullSessionPropertyResPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:4010
     * PullSingleChatPropertyRes : {"sessionId":"类型:Number 说明:会话id。单聊为对方imid，群聊为群组id","mSessionType":"类型:String 说明:会话类型：1表示单聊","isTop":"类型:String 说明:是否被置顶。0：未置顶，1：置顶","topTime":"类型:Number 说明:置顶时间。服务器时间戳，类型为long，单位为毫秒","noDisturb":"类型:Number 说明:是否设置免打扰。0：未设置免打扰，1：设置免打扰","latestMsgCreateTime":"类型:Number 说明:最新一条消息时间，时间戳，单位 毫秒，类型为long","resultCode":"类型:Number 说明:0 代表成功； 非0代表失败，具体意义参考后台定义."}
     */

    private PullSingleChatPropertyResBean PullSingleChatPropertyRes;

    public PullSingleChatPropertyResBean getPullSingleChatPropertyRes() {
        return PullSingleChatPropertyRes;
    }

    public void setPullSingleChatPropertyRes(PullSingleChatPropertyResBean PullSingleChatPropertyRes) {
        this.PullSingleChatPropertyRes = PullSingleChatPropertyRes;
    }

    public static class PullSingleChatPropertyResBean {
        /**
         * sessionId : 类型:Number 说明:会话id。单聊为对方imid，群聊为群组id
         * sessionType : 类型:String 说明:会话类型：1表示单聊
         * isTop : 类型:String 说明:是否被置顶。0：未置顶，1：置顶
         * topTime : 类型:Number 说明:置顶时间。服务器时间戳，类型为long，单位为毫秒
         * noDisturb : 类型:Number 说明:是否设置免打扰。0：未设置免打扰，1：设置免打扰
         * latestMsgCreateTime : 类型:Number 说明:最新一条消息时间，时间戳，单位 毫秒，类型为long
         * unReadNum: "类型:Number 说明:消息未读数目                                       ---- add by liguanghui on 2019.01.08",
         * resultCode: "类型:Number 说明:0 代表成功； 非0代表失败，具体意义参考后台定义.",
         * sessionName: "类型:String 说明:会话名称：sessionId对应的名字",
         * sessionImageUrl: "类型:String 说明:会话头像：sessionId对应的头像url",
         * description: "类型:String 说明:sessionId对应的用户描述信息"
         */

        private long sessionId;
        private int sessionType;
        private int isTop;
        private long topTime;
        private int noDisturb;
        private long latestMsgCreateTime;
        private int unReadNum;
        private int resultCode;
        private String sessionName;
        private String sessionImageUrl;
        private String description;

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

        public int getIsTop() {
            return isTop;
        }

        public void setIsTop(int isTop) {
            this.isTop = isTop;
        }

        public long getTopTime() {
            return topTime;
        }

        public void setTopTime(long topTime) {
            this.topTime = topTime;
        }

        public int getNoDisturb() {
            return noDisturb;
        }

        public void setNoDisturb(int noDisturb) {
            this.noDisturb = noDisturb;
        }

        public long getLatestMsgCreateTime() {
            return latestMsgCreateTime;
        }

        public void setLatestMsgCreateTime(long latestMsgCreateTime) {
            this.latestMsgCreateTime = latestMsgCreateTime;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public int getUnReadNum() {
            return unReadNum;
        }

        public void setUnReadNum(int unReadNum) {
            this.unReadNum = unReadNum;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }
}
