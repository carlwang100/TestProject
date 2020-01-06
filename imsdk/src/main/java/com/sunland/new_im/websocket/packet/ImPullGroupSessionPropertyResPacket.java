package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by fengd on 2018/11/12.
 */

public class ImPullGroupSessionPropertyResPacket extends ImBaseResponsePacket {


    /**
     * cid : 类型:Number 说明:7038
     * PullGroupChatPropertyRes : {"sessionId":"类型:Number 说明:会话id","sessionType":"类型:Number 说明:会话类型。","sessionName":"类型:String 说明:群名称","sessionImageUrl":"类型:String 说明:群头像URL","memberNumber":"类型:Number 说明:成员数目","memberNumberMaximum":"类型:Number 说明:成员上限数目","isTop":"类型:Number 说明:是否置顶。1：置顶，0：非置顶","topTime":"类型:Number 说明:置顶时间，单位毫秒。类型为long","noDisturb":"类型:Number 说明:是否设置免打扰。1：免打扰i，0：未设置免打扰","latestMsgCreateTime":"类型:Number 说明:最新一条消息时间","resultCode":"类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义"}
     */

    @SerializedName("PullGroupChatPropertyRes")
    private PullGroupChatPropertyResBean PullGroupChatPropertyRes;

    public PullGroupChatPropertyResBean getPullGroupChatPropertyRes() {
        return PullGroupChatPropertyRes;
    }

    public void setPullGroupChatPropertyRes(PullGroupChatPropertyResBean PullGroupChatPropertyRes) {
        this.PullGroupChatPropertyRes = PullGroupChatPropertyRes;
    }

    public static class PullGroupChatPropertyResBean {
        /**
         * sessionId : 类型:Number 说明:会话id
         * sessionType : 类型:Number 说明:会话类型。
         * sessionName : 类型:String 说明:群名称
         * sessionImageUrl : 类型:String 说明:群头像URL
         * memberNumber : 类型:Number 说明:成员数目
         * memberNumberMaximum : 类型:Number 说明:成员上限数目
         * isTop : 类型:Number 说明:是否置顶。1：置顶，0：非置顶
         * topTime : 类型:Number 说明:置顶时间，单位毫秒。类型为long
         * noDisturb : 类型:Number 说明:是否设置免打扰。1：免打扰i，0：未设置免打扰
         * latestMsgCreateTime : 类型:Number 说明:最新一条消息时间
         * resultCode : 类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义
         * groupType: 类型Number 说明：群类型是否为部门群(1：部门群， 0： 普通群)
         */

        @SerializedName("sessionId")
        private long sessionId;
        @SerializedName("sessionType")
        private int sessionType;
        @SerializedName("sessionName")
        private String sessionName;
        @SerializedName("sessionImageUrl")
        private String sessionImageUrl;
        @SerializedName("memberNumber")
        private int memberNumber;
        @SerializedName("memberNumberMaximum")
        private int memberNumberMaximum;
        @SerializedName("isTop")
        private int isTop;
        @SerializedName("topTime")
        private long topTime;
        @SerializedName("noDisturb")
        private int noDisturb;
        @SerializedName("latestMsgCreateTime")
        private long latestMsgCreateTime;
        @SerializedName("resultCode")
        private int resultCode;
        @SerializedName("groupType")
        private int groupType;

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

        public int getMemberNumber() {
            return memberNumber;
        }

        public void setMemberNumber(int memberNumber) {
            this.memberNumber = memberNumber;
        }

        public int getMemberNumberMaximum() {
            return memberNumberMaximum;
        }

        public void setMemberNumberMaximum(int memberNumberMaximum) {
            this.memberNumberMaximum = memberNumberMaximum;
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

        public int getGroupType() {
            return groupType;
        }

        public void setGroupType(int groupType) {
            this.groupType = groupType;
        }
    }
}
