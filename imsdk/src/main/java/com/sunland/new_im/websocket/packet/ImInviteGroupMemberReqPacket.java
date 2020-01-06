package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kai on 2018/11/2
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImInviteGroupMemberReqPacket extends ImBaseRequestPacket {
    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:7001","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配。","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * InviteGroupMemberReq : {"groupId":"类型:Number 说明:新建群设为0; 为已有群添加成员设置为已有群的groupId","inviterName":"类型:String 说明:群创建者或邀请者的名字","inviteeIdList":"类型:Array[Number] 说明:被邀请者imid列表","groupName":"类型:String 说明:群名称","imageUrl":"类型:String 说明:群头像url"}
     */
    public static final long NEW_GROUP = 0L;

    private InviteGroupMemberReqBean InviteGroupMemberReq;

    public ImInviteGroupMemberReqPacket(EntryBean entry, InviteGroupMemberReqBean InviteGroupMemberReq ) {
        super(entry);
        this.InviteGroupMemberReq = InviteGroupMemberReq;
    }

    public static class InviteGroupMemberReqBean {
        /**
         * groupId : 类型:Number 说明:新建群设为0; 为已有群添加成员设置为已有群的groupId
         * inviterName : 类型:String 说明:群创建者或邀请者的名字
         * inviteeIdList : 类型:Array[Number] 说明:被邀请者imid列表
         * groupName : 类型:String 说明:群名称
         * imageUrl : 类型:String 说明:群头像url
         * groupType: 类型Number 说明：群类型是否为部门群(1：部门群， 0： 普通群)
         * inviteMethod: 类型:Number 说明:邀请方式，已有群邀请新成员时使用。普通邀请群成员不填或填1， 二维码进群方式填2，二维码进群每次只能加一人
         */

        private long groupId;
        private String inviterName;
        private long inviterId;
        private List<Long> inviteeIdList = new ArrayList<>(0);
        private String groupName;
        private String imageUrl;
        @SerializedName("groupType")
        private int groupType;
        @SerializedName("inviteMethod")
        private int inviteMethod;

        public void setGroupId(long groupId) {
            this.groupId = groupId;
        }

        public void setInviterName(String inviterName) {
            this.inviterName = inviterName;
        }

        public long getInviterId() {
            return inviterId;
        }

        public void setInviterId(long inviterId) {
            this.inviterId = inviterId;
        }

        public void setInviteeIdList(List<Long> inviteeIdList) {
            this.inviteeIdList = inviteeIdList;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getGroupType() {
            return groupType;
        }

        public void setGroupType(int groupType) {
            this.groupType = groupType;
        }

        public void setInviteMethod(int inviteMethod) {
            this.inviteMethod = inviteMethod;
        }
    }
}
