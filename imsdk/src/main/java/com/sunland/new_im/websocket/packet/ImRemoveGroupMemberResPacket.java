package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.Map;

/**
 * Created by kai on 2018/11/12
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImRemoveGroupMemberResPacket extends ImBaseResponsePacket {

    /**
     * RemoveGroupMemberRes : {"removerId":"类型:Number 说明:群移除发起方的imid","removerName":"类型:String 说明:群移除发起方的名字","removedUserInfoMap":[],"groupId":"类型:Number 说明:群id","memberNumber":"类型:Number 说明:群成员个数","memberNumberMaximum":"类型:Number 说明:群最大支持人员个数","removeTime":"类型:Number 说明:本次移除时间戳，毫秒级，13位 long型","resultCode":"类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义","reason":"类型:String 说明:请求结果描述"}
     */

    private RemoveGroupMemberResBean RemoveGroupMemberRes;

    public RemoveGroupMemberResBean getRemoveGroupMemberRes() {
        return RemoveGroupMemberRes;
    }

    public void setRemoveGroupMemberRes(RemoveGroupMemberResBean RemoveGroupMemberRes) {
        this.RemoveGroupMemberRes = RemoveGroupMemberRes;
    }

    public static class RemoveGroupMemberResBean {
        /**
         * removerId : 类型:Number 说明:群移除发起方的imid
         * removerName : 类型:String 说明:群移除发起方的名字
         * removedUserInfoMap : []
         * groupId : 类型:Number 说明:群id
         * memberNumber : 类型:Number 说明:群成员个数
         * memberNumberMaximum : 类型:Number 说明:群最大支持人员个数
         * removeTime : 类型:Number 说明:本次移除时间戳，毫秒级，13位 long型
         * resultCode : 类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义
         * reason : 类型:String 说明:请求结果描述
         */

        private long removerId;
        private String removerName;
        private long groupId;
        private int memberNumber;
        private int memberNumberMaximum;
        private long removeTime;
        private int resultCode;
        private String reason;
        private Map<Long, String> removedUserInfoMap;

        public long getRemoverId() {
            return removerId;
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

        public Map<Long, String> getRemovedUserInfoMap() {
            return removedUserInfoMap;
        }

    }
}
