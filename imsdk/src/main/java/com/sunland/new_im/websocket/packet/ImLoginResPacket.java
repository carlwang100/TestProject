package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by kai on 2018/8/20
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImLoginResPacket extends ImBaseResponsePacket {
    /**
     * cid : 类型:Number 说明:1004
     * userLoginRes : {"serverTime":"类型:Number 说明:服务器时间戳","resultCode":"类型:Number 说明:注册请求结果. 0 代表成功； 非0代表失败，具体意义参考后台定义.","onlineStatus":"类型:Number 说明:用户在线状态, 1 在线, 2 忙碌, 3 离开, 4 隐身","userInfo":{"imid":"类型:Number 说明:用户imid","userName":"类型:String 说明:用户名字","imageUrl":"类型:String 说明:用户头像url","status":"类型:Number 说明:用户状态, 1 在职， 2 离职"}}
     */
    private UserLoginResBean UserLoginRes;

    public UserLoginResBean getUserLoginRes() {
        return UserLoginRes;
    }

    public static class UserLoginResBean {
        /**
         * serverTime : 类型:Number 说明:服务器时间戳
         * resultCode : 类型:Number 说明:注册请求结果. 0 代表成功； 非0代表失败，具体意义参考后台定义.
         * onlineStatus : 类型:Number 说明:用户在线状态, 1 在线, 2 忙碌, 3 离开, 4 隐身
         * userInfo : {"imid":"类型:Number 说明:用户imid","userName":"类型:String 说明:用户名字","imageUrl":"类型:String 说明:用户头像url","status":"类型:Number 说明:用户状态, 1 在职， 2 离职"}
         */

        private long serverTime;
        private int resultCode;
        private int onlineStatus;
        private UserInfoBean userInfo;

        public long getServerTime() {
            return serverTime;
        }
        public int getResultCode() {
            return resultCode;
        }
        public int getOnlineStatus() {
            return onlineStatus;
        }
        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public static class UserInfoBean {
            /**
             * imid : 类型:Number 说明:用户imid
             * userName : 类型:String 说明:用户名字
             * imageUrl : 类型:String 说明:用户头像url
             * status : 类型:Number 说明:用户状态, 1 在职， 2 离职
             */

            private long imid;
            private String userName;
            private String imageUrl;
            private int status;

            public long getImid() {
                return imid;
            }

            public void setImid(long imid) {
                this.imid = imid;
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
