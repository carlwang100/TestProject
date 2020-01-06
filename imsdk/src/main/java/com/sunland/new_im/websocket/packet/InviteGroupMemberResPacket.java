package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.Map;

/**
 * Created by kai on 2018/11/2
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class InviteGroupMemberResPacket extends ImBaseResponsePacket {
    /**
     * InviteGroupMemberRes : {"inviterId":"类型:Number 说明:群邀请发起方的imid","inviterName":"类型:String 说明:群邀请发起方的名字","inviteeUserInfoMap":{"1001001001":"被邀请人的名字","1001001002":"名字2"},"groupId":"类型:Number 说明:群id","imageUrl":"类型:String 说明:群头像URL","groupName":"类型:String 说明:群名称","memberNumber":"类型:Number 说明:群成员个数","memberNumberMaximum":"类型:Number 说明:群最大支持人员个数","inviteTime":"类型:Number 说明:本次邀请时间戳，毫秒级，13位 long型","bCreateGroup":"类型:Number 说明:首次创建： 1， 后续邀请：0","resultCode":"类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义","reason":"类型:String 说明:请求结果描述"}
     */

    private InviteGroupMemberResBean InviteGroupMemberRes;

    public InviteGroupMemberResBean getInviteGroupMemberRes() {
        return InviteGroupMemberRes;
    }

    public static class InviteGroupMemberResBean {
        /**
         * inviterId : 类型:Number 说明:群邀请发起方的imid
         * inviterName : 类型:String 说明:群邀请发起方的名字
         * inviteeUserInfoMap : {1001001001:"被邀请人的名字",1001001002:"名字2"}
         * groupId : 类型:Number 说明:群id
         * imageUrl : 类型:String 说明:群头像URL
         * groupName : 类型:String 说明:群名称
         * memberNumber : 类型:Number 说明:群成员个数
         * memberNumberMaximum : 类型:Number 说明:群最大支持人员个数
         * inviteTime : 类型:Number 说明:本次邀请时间戳，毫秒级，13位 long型
         * bCreateGroup : 类型:Number 说明:首次创建： 1， 后续邀请：0
         * resultCode : 类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义
         * reason : 类型:String 说明:请求结果描述
         * groupType: 类型Number  说明：群类型是否为部门群(1：部门群， 0： 普通群)
         */

        private long inviterId;
        private String inviterName;
        private Map<String, String> inviteeUserInfoMap;
        private long groupId;
        private String imageUrl;
        private String groupName;
        private int memberNumber;
        private int memberNumberMaximum;
        private long inviteTime;
        private int bCreateGroup;
        private int resultCode;
        private String reason;
        @SerializedName("groupType")
        private int groupType;

        public int getGroupType() {
            return groupType;
        }

        public void setGroupType(int groupType) {
            this.groupType = groupType;
        }

        public long getInviterId() {
            return inviterId;
        }

        public void setInviterId(long inviterId) {
            this.inviterId = inviterId;
        }

        public String getInviterName() {
            return inviterName;
        }

        public void setInviterName(String inviterName) {
            this.inviterName = inviterName;
        }

        public Map<String, String> getInviteeUserInfoMap() {
            return inviteeUserInfoMap;
        }

        public void setInviteeUserInfoMap(Map<String, String> inviteeUserInfoMap) {
            this.inviteeUserInfoMap = inviteeUserInfoMap;
        }

        public long getGroupId() {
            return groupId;
        }

        public void setGroupId(long groupId) {
            this.groupId = groupId;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
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

        public long getInviteTime() {
            return inviteTime;
        }

        public void setInviteTime(long inviteTime) {
            this.inviteTime = inviteTime;
        }

        public int getBCreateGroup() {
            return bCreateGroup;
        }

        public void setBCreateGroup(int bCreateGroup) {
            this.bCreateGroup = bCreateGroup;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
