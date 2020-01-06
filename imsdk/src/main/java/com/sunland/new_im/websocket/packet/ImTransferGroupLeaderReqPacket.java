package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by cheng on 2019/3/5.
 */

public class ImTransferGroupLeaderReqPacket extends ImBaseRequestPacket {
    public ImTransferGroupLeaderReqPacket(EntryBean entry) {
        super(entry);
    }

    private ImTransferGroupLeaderReq TransferGroupIdentityReq;

    public ImTransferGroupLeaderReqPacket(EntryBean entryBean, ImTransferGroupLeaderReq req) {
        super(entryBean);
        setImTransferGroupLeaderReq(req);
    }

    public ImTransferGroupLeaderReq getImTransferGroupLeaderReq() {
        return TransferGroupIdentityReq;
    }

    public void setImTransferGroupLeaderReq(ImTransferGroupLeaderReq imTransferGroupLeaderReq) {
        this.TransferGroupIdentityReq = imTransferGroupLeaderReq;
    }

    public static class ImTransferGroupLeaderReq {

        /**
         * groupId : 1
         * memberDegree : 1
         * toUserId : 1
         */

        private long groupId;
        private int memberDegree;
        private long toUserId;

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

        public long getToUserId() {
            return toUserId;
        }

        public void setToUserId(long toUserId) {
            this.toUserId = toUserId;
        }
    }
}
