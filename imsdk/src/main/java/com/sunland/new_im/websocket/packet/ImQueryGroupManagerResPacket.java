package com.sunland.new_im.websocket.packet;

import android.annotation.SuppressLint;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CID7054 - 拉取群管理人员信息应答
 */
public class ImQueryGroupManagerResPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:7054
     * QueryGroupManagerRes : {"fromUserId":"类型:Number 说明:群组groupid","resultCode":"类型:Number 说明:0 成功，非0失败","groupManagerMap":{"群组1的id":{"imid":"类型:Number 说明:imid","memberDegree":"类型:Number 说明:1：普通群成员 ，2：群主，其他暂未定（这个接口返回的是管理人员，不会是1）","enterTime":"类型:Number 说明:进群时间，单位毫秒，类型为long","userName":"类型:String 说明:用户真实名字","nickName":"类型:String 说明:用户群昵称","status":"类型:Number 说明:用户在职状态。1：在职，2：离职","imageUrl":"类型:String 说明:用户头像url"},"群组2的id":{}}}
     */

    @com.google.gson.annotations.SerializedName("QueryGroupManagerRes")
    private QueryGroupManagerResBean QueryGroupManagerRes;

    public QueryGroupManagerResBean getQueryGroupManagerRes() {
        return QueryGroupManagerRes;
    }

    public static class QueryGroupManagerResBean{

        /**
         * fromUserId : 类型:Number 说明:群组groupid
         * resultCode : 类型:Number 说明:0 成功，非0失败
         * groupManagerMap : {"群组1的id":{"imid":"类型:Number 说明:imid","memberDegree":"类型:Number 说明:1：普通群成员 ，2：群主，其他暂未定（这个接口返回的是管理人员，不会是1）","enterTime":"类型:Number 说明:进群时间，单位毫秒，类型为long","userName":"类型:String 说明:用户真实名字","nickName":"类型:String 说明:用户群昵称","status":"类型:Number 说明:用户在职状态。1：在职，2：离职","imageUrl":"类型:String 说明:用户头像url"},"群组2的id":{}}
         */

        @com.google.gson.annotations.SerializedName("fromUserId")
        private String fromUserId;

        @com.google.gson.annotations.SerializedName("resultCode")
        private int resultCode;

        @SuppressLint("UseSparseArrays")
        @com.google.gson.annotations.SerializedName("groupManagerMap")
        private Map<Long, List<GroupManagerMapBean>> groupManagerMap = new HashMap<>();

        public int getResultCode() {
            return resultCode;
        }

        public Map<Long, List<GroupManagerMapBean>> getGroupManagerMap() {
            return groupManagerMap;
        }

    }

    public static class GroupManagerMapBean {

        /**
         * imid : 类型:Number 说明:imid
         * memberDegree : 类型:Number 说明:1：普通群成员 ，2：群主，其他暂未定（这个接口返回的是管理人员，不会是1）
         * enterTime : 类型:Number 说明:进群时间，单位毫秒，类型为long
         * userName : 类型:String 说明:用户真实名字
         * nickName : 类型:String 说明:用户群昵称
         * status : 类型:Number 说明:用户在职状态。1：在职，2：离职
         * imageUrl : 类型:String 说明:用户头像url
         */

        @SerializedName("imid")
        private long imid;
        @SerializedName("memberDegree")
        private int memberDegree;
        @SerializedName("enterTime")
        private long enterTime;
        @SerializedName("userName")
        private String userName;
        @SerializedName("nickName")
        private String nickName;
        @SerializedName("status")
        private int status;
        @SerializedName("imageUrl")
        private String imageUrl;

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
    }
}
