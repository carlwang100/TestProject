package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.Map;

/**
 * Created by fengd on 2018/11/15.
 */

public class ImRemoveGroupMemberNotifyPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:CID7007 - 成员退群通知
     * RemoveGroupMemberNotify : {"removerId":"类型:Number 说明:群移除发起方的imid","removerName":"类型:String 说明:群移除发起方的名字","removedUserInfoMap":[{},{"more":"..."}],"groupId":"类型:Number 说明:群组id","memberNumber":"类型:Number 说明:当前群成员个数","memberNumberMaximum":"类型:Number 说明:群最大支持人员个数","removeTime":"类型:Number 说明:本次移除时间戳，毫秒级，13位 long型"}
     */

    @SerializedName("RemoveGroupMemberNotify")
    private RemoveGroupMemberNotifyBean RemoveGroupMemberNotify;

    public RemoveGroupMemberNotifyBean getRemoveGroupMemberNotify() {
        return RemoveGroupMemberNotify;
    }

    public void setRemoveGroupMemberNotify(RemoveGroupMemberNotifyBean RemoveGroupMemberNotify) {
        this.RemoveGroupMemberNotify = RemoveGroupMemberNotify;
    }

    public static class RemoveGroupMemberNotifyBean {
        /**
         * removerId : 类型:Number 说明:群移除发起方的imid
         * removerName : 类型:String 说明:群移除发起方的名字
         * removedUserInfoMap : [{},{"more":"..."}]
         * groupId : 类型:Number 说明:群组id
         * memberNumber : 类型:Number 说明:当前群成员个数
         * memberNumberMaximum : 类型:Number 说明:群最大支持人员个数
         * removeTime : 类型:Number 说明:本次移除时间戳，毫秒级，13位 long型
         */

        @SerializedName("removerId")
        private long removerId;
        @SerializedName("removerName")
        private String removerName;
        @SerializedName("groupId")
        private long groupId;
        @SerializedName("memberNumber")
        private int memberNumber;
        @SerializedName("memberNumberMaximum")
        private int memberNumberMaximum;
        @SerializedName("removeTime")
        private long removeTime;
        @SerializedName("removedUserInfoMap")
        private Map<Long, String> removedUserInfoMap;

        public long getRemoverId() {
            return removerId;
        }

        public Map<Long, String> getRemovedUserInfoMap() {
            return removedUserInfoMap;
        }

        public void setRemoverId(long removerId) {
            this.removerId = removerId;
        }

        public String getRemoverName() {
            return removerName;
        }

        public void setRemoverName(String removerName) {
            this.removerName = removerName;
        }

        public long getGroupId() {
            return groupId;
        }

        public void setGroupId(long groupId) {
            this.groupId = groupId;
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

        public long getRemoveTime() {
            return removeTime;
        }

        public void setRemoveTime(long removeTime) {
            this.removeTime = removeTime;
        }

    }
}
