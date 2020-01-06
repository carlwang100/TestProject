package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by kai on 2018/8/28
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImLogoutResPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:1006
     * UserLogoutRes : {"imid":"类型:Number","resultCode":"类型:Number 说明:注册请求结果. 0 代表成功； 非0代表失败，具体意义参考后台定义.","clientType":"类型:Number"}
     */

    private UserLogoutResBean UserLogoutRes;

    public UserLogoutResBean getUserLogoutRes() {
        return UserLogoutRes;
    }

    public void setUserLogoutRes(UserLogoutResBean UserLogoutRes) {
        this.UserLogoutRes = UserLogoutRes;
    }

    public static class UserLogoutResBean {
        /**
         * imid : 类型:Number
         * resultCode : 类型:Number 说明:注册请求结果. 0 代表成功； 非0代表失败，具体意义参考后台定义.
         * clientType : 类型:Number
         */

        private long imid;
        private int resultCode;
        private int clientType;

        public long getImid() {
            return imid;
        }

        public void setImid(long imid) {
            this.imid = imid;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public int getClientType() {
            return clientType;
        }

        public void setClientType(int clientType) {
            this.clientType = clientType;
        }
    }
}
