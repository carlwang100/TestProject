package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by kai on 2018/8/28
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImKickUserNotifyPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:1007
     * IMKickUser : {"imid":"类型:Number","newLoginDeviceType":"类型:Number 说明:最新登陆设备的类型. 1：ANDROID 2：IOS 3: WEB","serverTime":"类型:Number 说明:用户被踢时的服务器时间戳"}
     */

    private IMKickUserBean IMKickUser;

    public IMKickUserBean getIMKickUser() {
        return IMKickUser;
    }

    public void setIMKickUser(IMKickUserBean IMKickUser) {
        this.IMKickUser = IMKickUser;
    }

    public static class IMKickUserBean {
        /**
         * imid : 类型:Number
         * newLoginDeviceType : 类型:Number 说明:最新登陆设备的类型. 1：ANDROID 2：IOS 3: WEB
         * serverTime : 类型:Number 说明:用户被踢时的服务器时间戳
         */

        private long imid;
        private int newLoginDeviceType;
        private long serverTime;

        public long getImid() {
            return imid;
        }
        public int getNewLoginDeviceType() {
            return newLoginDeviceType;
        }
        public long getServerTime() {
            return serverTime;
        }
    }
}
