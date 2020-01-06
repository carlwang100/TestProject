package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kai on 2018/11/8
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImGetGroupMemberListResPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:7030
     * GetGroupMemberListRes : {"fromUserId":"类型:Number 说明:发起查询的用户imid","groupId":"类型:Number 说明:查询的群组id","groupMemberList":[{"imid":"类型:Number 说明:成员imid","memberDegree":"类型:Number 说明:成员身份。1：普通成员，2：群主","enterTime":"类型:Number 说明:进群时间。单位毫秒","userName":"类型:String 说明:用户名字（真实昵称）","nickName":"类型:String 说明:用户在本群的群昵称","status":"类型:Number 说明:用户状态（1：在职，2：离职）","imageUrl":"类型:String 说明:用户头像url"},{"more":"..."}],"resultCode":"类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义"}
     */

    private GetGroupMemberListResBean GetGroupMemberListRes;

    public GetGroupMemberListResBean getGetGroupMemberListRes() {
        return GetGroupMemberListRes;
    }

    public void setGetGroupMemberListRes(GetGroupMemberListResBean GetGroupMemberListRes) {
        this.GetGroupMemberListRes = GetGroupMemberListRes;
    }

    public static class GetGroupMemberListResBean {
        /**
         * fromUserId : 类型:Number 说明:发起查询的用户imid
         * groupId : 类型:Number 说明:查询的群组id
         * groupMemberList : [{"imid":"类型:Number 说明:成员imid","memberDegree":"类型:Number 说明:成员身份。1：普通成员，2：群主","enterTime":"类型:Number 说明:进群时间。单位毫秒","userName":"类型:String 说明:用户名字（真实昵称）","nickName":"类型:String 说明:用户在本群的群昵称","status":"类型:Number 说明:用户状态（1：在职，2：离职）","imageUrl":"类型:String 说明:用户头像url"},{"more":"..."}]
         * resultCode : 类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义
         */

        private long fromUserId;
        private long groupId;
        private int resultCode;
        private List<GroupMemberListBean> groupMemberList = new ArrayList<>(0);

        public long getFromUserId() {
            return fromUserId;
        }

        public void setFromUserId(long fromUserId) {
            this.fromUserId = fromUserId;
        }

        public long getGroupId() {
            return groupId;
        }

        public void setGroupId(long groupId) {
            this.groupId = groupId;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public List<GroupMemberListBean> getGroupMemberList() {
            return groupMemberList;
        }

        public void setGroupMemberList(List<GroupMemberListBean> groupMemberList) {
            this.groupMemberList = groupMemberList;
        }

        public static class GroupMemberListBean {
            /**
             * imid : 类型:Number 说明:成员imid
             * memberDegree : 类型:Number 说明:成员身份。1：普通成员，2：群主
             * enterTime : 类型:Number 说明:进群时间。单位毫秒
             * userName : 类型:String 说明:用户名字（真实昵称）
             * nickName : 类型:String 说明:用户在本群的群昵称
             * status : 类型:Number 说明:用户状态（1：在职，2：离职）
             * imageUrl : 类型:String 说明:用户头像url
             * description : "类型:String 说明:用户描述信息"
             */

            private long imid;
            private int memberDegree;
            private long enterTime;
            private String userName;
            private String nickName;
            private int status;
            private String imageUrl;

            private String description;

            public long getImid() {
                return imid;
            }

            public void setImid(long imid) {
                this.imid = imid;
            }

            public int getMemberDegree() {
                return memberDegree;
            }

            public void setMemberDegree(int memberDegree) {
                this.memberDegree = memberDegree;
            }

            public long getEnterTime() {
                return enterTime;
            }

            public void setEnterTime(long enterTime) {
                this.enterTime = enterTime;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

        }
    }
}
