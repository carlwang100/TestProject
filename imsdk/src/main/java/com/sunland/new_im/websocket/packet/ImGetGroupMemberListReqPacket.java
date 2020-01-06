package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by kai on 2018/11/8
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImGetGroupMemberListReqPacket extends ImBaseRequestPacket {

    /**
     * GetGroupMemberListReq : {"groupId":"类型:Number 说明:群组Id，即群聊会话id。如果imid不在群组内，返回错误。","firstFewMembersNum":"类型:Number 说明:拉取群组的前几个成员。当值为0或者>群组成员数目时，全部拉出。小于0，返回错误"}
     */

    public ImGetGroupMemberListReqPacket(EntryBean entry,GetGroupMemberListReqBean GetGroupMemberListReq) {
        super(entry);
        this.GetGroupMemberListReq = GetGroupMemberListReq;
    }
    private GetGroupMemberListReqBean GetGroupMemberListReq;


    public GetGroupMemberListReqBean getGetGroupMemberListReq() {
        return GetGroupMemberListReq;
    }

    public void setGetGroupMemberListReq(GetGroupMemberListReqBean GetGroupMemberListReq) {
        this.GetGroupMemberListReq = GetGroupMemberListReq;
    }

    public static class GetGroupMemberListReqBean {
        /**
         * groupId : 类型:Number 说明:群组Id，即群聊会话id。如果imid不在群组内，返回错误。
         * firstFewMembersNum : 类型:Number 说明:拉取群组的前几个成员。当值为0或者>群组成员数目时，全部拉出。小于0，返回错误
         */

        private long groupId;
        private int firstFewMembersNum;

        public long getGroupId() {
            return groupId;
        }

        public void setGroupId(long groupId) {
            this.groupId = groupId;
        }

        public int getFirstFewMembersNum() {
            return firstFewMembersNum;
        }

        public void setFirstFewMembersNum(int firstFewMembersNum) {
            this.firstFewMembersNum = firstFewMembersNum;
        }
    }
}
