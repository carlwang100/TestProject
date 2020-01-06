package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kai on 2018/8/27
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImUserInfoResPacket extends ImBaseResponsePacket {
    /**
     * cid : 类型:Number 说明:1014
     * UserInfoQueryRes : {"imid":"类型:Number 说明:发送请求用户的imid","queryIdList":"类型:Array[Number] 说明:请求中要查询的用户id列表（或者单聊的会话id）","resultCode":"类型:Number 说明:返回错误码，0表示成功，1表示失败","queryResList":[{"queryId":"类型:Number 说明:查询成功的用户id","userName":"类型:String 说明:用户名称","imageUrl":"类型:String 说明:用户头像url","status":"类型:Number 说明:1:在职，2：离职"},{"more":"..."}]}
     */

    private UserInfoQueryResBean UserInfoQueryRes;

    public UserInfoQueryResBean getUserInfoQueryRes() {
        return UserInfoQueryRes;
    }

    public void setUserInfoQueryRes(UserInfoQueryResBean UserInfoQueryRes) {
        this.UserInfoQueryRes = UserInfoQueryRes;
    }


    public static class UserInfoQueryResBean {
        /**
         * imid : 类型:Number 说明:发送请求用户的imid
         * queryIdList : 类型:Array[Number] 说明:请求中要查询的用户id列表（或者单聊的会话id）
         * resultCode : 类型:Number 说明:返回错误码，0表示成功，1表示失败
         * queryResList : [{"queryId":"类型:Number 说明:查询成功的用户id","userName":"类型:String 说明:用户名称","imageUrl":"类型:String 说明:用户头像url","status":"类型:Number 说明:1:在职，2：离职"},{"more":"..."}]
         */

        private long imid;
        private String queryIdList;
        private int resultCode;
        private List<QueryResListBean> queryResList = new ArrayList<>(0);

        public long getImid() {
            return imid;
        }

        public void setImid(long imid) {
            this.imid = imid;
        }

        public String getQueryIdList() {
            return queryIdList;
        }

        public void setQueryIdList(String queryIdList) {
            this.queryIdList = queryIdList;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public List<QueryResListBean> getQueryResList() {
            return queryResList;
        }

        public void setQueryResList(List<QueryResListBean> queryResList) {
            this.queryResList = queryResList;
        }

        public static class QueryResListBean {
            /**
             * queryId : 类型:Number 说明:查询成功的用户id
             * userName : 类型:String 说明:用户名称
             * imageUrl : 类型:String 说明:用户头像url
             * status : 类型:Number 说明:1:在职，2：离职
             * more : ...
             */

            private int queryId;
            private String userName;

            private String imageUrl;
            private int status;

            public int getQueryId() {
                return queryId;
            }

            public void setQueryId(int queryId) {
                this.queryId = queryId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
