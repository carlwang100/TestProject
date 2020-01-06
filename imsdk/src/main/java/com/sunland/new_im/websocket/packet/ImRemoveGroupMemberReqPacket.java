package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by kai on 2018/11/12
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImRemoveGroupMemberReqPacket extends ImBaseRequestPacket {
    /**
     * RemoveGroupMemberReq : {"groupId":"类型:Number 说明:新建群设为0; 为已有群添加成员设置为已有群的groupId","removedIdList":"类型:Array[Number] 说明:被移除者imid列表"}
     */

    private RemoveGroupMemberReqBean RemoveGroupMemberReq;

    public ImRemoveGroupMemberReqPacket(EntryBean entry, RemoveGroupMemberReqBean RemoveGroupMemberReq) {
        super(entry);
        this.RemoveGroupMemberReq = RemoveGroupMemberReq;
    }

    public static class RemoveGroupMemberReqBean {
        /**
         * groupId : 类型:Number 说明:新建群设为0; 为已有群添加成员设置为已有群的groupId
         * removedIdList : 类型:Array[Number] 说明:被移除者imid列表
         */

        private long groupId;
        private long[] removedIdList;

        public void setGroupId(long groupId) {
            this.groupId = groupId;
        }

        public void setRemovedIdList(long[] removedIdList) {
            this.removedIdList = removedIdList;
        }
    }
}
