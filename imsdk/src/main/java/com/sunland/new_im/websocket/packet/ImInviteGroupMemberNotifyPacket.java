package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.Map;

/**
 * Created by fengd on 2018/11/14.
 */

public class ImInviteGroupMemberNotifyPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:7003
     * InviteGroupMemberNotify : {"inviterId":"类型:Number 说明:群创建者或邀请者imid","inviterName":"类型:String 说明:群创建者或邀请者名字","inviteeUserInfoMap":{"被邀请人1的imid":{"被邀请人1的名字":"类型:String"},"被邀请人2的imid":{"被邀请人1的名字":"类型:String"}},"groupId":"类型:Number 说明:群组id","imageUrl":"类型:String 说明:群头像URL","groupName":"类型:String 说明:群名称","memberNumber":"类型:Number 说明:当前群成员个数","memberNumberMaximum":"类型:Number 说明:群最大支持人员个数","inviteTime":"类型:Number 说明:本次邀请时间戳，毫秒级，13位 long型","bCreateGroup":"类型:Number 说明:首次创建： 1， 后续邀请：0"}
     */

    @SerializedName("InviteGroupMemberNotify")
    private InviteGroupMemberNotifyBean InviteGroupMemberNotify;

    public InviteGroupMemberNotifyBean getInviteGroupMemberNotify() {
        return InviteGroupMemberNotify;
    }

    public void setInviteGroupMemberNotify(InviteGroupMemberNotifyBean InviteGroupMemberNotify) {
        this.InviteGroupMemberNotify = InviteGroupMemberNotify;
    }

    public static class InviteGroupMemberNotifyBean {
        /**
         * inviterId : 类型:Number 说明:群创建者或邀请者imid
         * inviterName : 类型:String 说明:群创建者或邀请者名字
         * inviteeUserInfoMap : {"被邀请人1的imid":{"被邀请人1的名字":"类型:String"},"被邀请人2的imid":{"被邀请人1的名字":"类型:String"}}
         * groupId : 类型:Number 说明:群组id
         * imageUrl : 类型:String 说明:群头像URL
         * groupName : 类型:String 说明:群名称
         * memberNumber : 类型:Number 说明:当前群成员个数
         * memberNumberMaximum : 类型:Number 说明:群最大支持人员个数
         * inviteTime : 类型:Number 说明:本次邀请时间戳，毫秒级，13位 long型
         * bCreateGroup : 类型:Number 说明:首次创建： 1， 后续邀请：0
         * groupType: 类型Number 说明：群类型是否为部门群(1：部门群， 0： 普通群)
         */

        @SerializedName("inviterId")
        private long inviterId;
        @SerializedName("inviterName")
        private String inviterName;
        @SerializedName("inviteeUserInfoMap")
        private Map<Long, String> inviteeUserInfoMap;
        @SerializedName("groupId")
        private long groupId;
        @SerializedName("imageUrl")
        private String imageUrl;
        @SerializedName("groupName")
        private String groupName;
        @SerializedName("memberNumber")
        private int memberNumber;
        @SerializedName("memberNumberMaximum")
        private int memberNumberMaximum;
        @SerializedName("inviteTime")
        private long inviteTime;
        @SerializedName("bCreateGroup")
        private int bCreateGroup;
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


    }
}
