package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by cheng on 2019/3/5.
 */

public class ImTransferGroupLeaderResPacket extends ImBaseResponsePacket {
    @SerializedName("TransferGroupIdentityRes")
    private ImTransferGroupLeaderRes imTransferGroupLeaderRes;

    public ImTransferGroupLeaderRes getImTransferGroupLeaderRes() {
        return imTransferGroupLeaderRes;
    }

    public void setImTransferGroupLeaderRes(ImTransferGroupLeaderRes imTransferGroupLeaderRes) {
        this.imTransferGroupLeaderRes = imTransferGroupLeaderRes;
    }


    public static class ImTransferGroupLeaderRes {
        /**
         * groupId : 10L
         * memberDegree : 1
         * fromUserId : 10L
         * fromUserName : 类型:String 说明:发起转移身份的人员昵称
         * toUserId : 1L
         * toUserName : 类型:Number 说明:被转移身份的人员昵称
         * transferTime : 10L
         * resultCode : 1
         */

        private String groupId;
        private int memberDegree;
        private long fromUserId;
        private String fromUserName;
        private long toUserId;
        private String toUserName;
        private long transferTime;
        private int resultCode;

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public int getMemberDegree() {
            return memberDegree;
        }

        public void setMemberDegree(int memberDegree) {
            this.memberDegree = memberDegree;
        }

        public long getFromUserId() {
            return fromUserId;
        }

        public void setFromUserId(long fromUserId) {
            this.fromUserId = fromUserId;
        }

        public String getFromUserName() {
            return fromUserName;
        }

        public void setFromUserName(String fromUserName) {
            this.fromUserName = fromUserName;
        }

        public long getToUserId() {
            return toUserId;
        }

        public void setToUserId(long toUserId) {
            this.toUserId = toUserId;
        }

        public String getToUserName() {
            return toUserName;
        }

        public void setToUserName(String toUserName) {
            this.toUserName = toUserName;
        }

        public long getTransferTime() {
            return transferTime;
        }

        public void setTransferTime(long transferTime) {
            this.transferTime = transferTime;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }
    }
}
