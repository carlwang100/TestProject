package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by cheng on 2019/3/6.
 */

public class ImTransferGroupIdentifyNotify extends ImBaseResponsePacket {

    @SerializedName("TransferGroupIdentityNotify")
    private TransferGroupIdentifyNotify TransferGroupIdentityNotify;

    public ImTransferGroupIdentifyNotify.TransferGroupIdentifyNotify getTransferGroupIdentifyNotify() {
        return TransferGroupIdentityNotify;
    }

    public void setTransferGroupIdentifyNotify(ImTransferGroupIdentifyNotify.TransferGroupIdentifyNotify transferGroupIdentifyNotify) {
        TransferGroupIdentityNotify = transferGroupIdentifyNotify;
    }


    public static class TransferGroupIdentifyNotify {

        /**
         * groupId : 类型:Number 说明:群id
         * memberDegree : 类型:String 说明:要转移的群身份。群主为2.
         * fromUserId : 类型:Number 说明:发起转移身份的人员imid
         * fromUserName : 类型:String 说明:发起转移身份的人员昵称
         * toUserId : 类型:String 说明:被转移身份的人员imid
         * toUserName : 类型:Number 说明:被转移身份的人员昵称
         * transferTime : 类型:Number 说明:转移时间
         */

        private long groupId;
        private int memberDegree;
        private long fromUserId;
        private String fromUserName;
        private long toUserId;
        private String toUserName;
        private long transferTime;

        public long getGroupId() {
            return groupId;
        }

        public void setGroupId(long groupId) {
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
    }
}
