package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by kai on 2018/8/20
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImLoginPacket extends ImBaseRequestPacket {

    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:1003","clientType":"类型:Number 说明:3（WEB）"}
     * userLoginReq : {"appid":"类型:String 说明:与注册时appid一致，由IM后台发布， 每个接入方共用一个appid, 测试环境填 kdldldldld","userType":"类型:Number 说明:填1 （代表员工)","onlineStatus":"类型:Number 说明:用户在线状态, 1 在线, 2 忙碌, 3 离开, 4 隐身","clientVersion":"类型:String 说明:客户端版本号"}
     */

    private UserLoginReqBean UserLoginReq;

    public ImLoginPacket(ImBaseRequestPacket.EntryBean entry, UserLoginReqBean userLoginReq) {
        super(entry);
        this.UserLoginReq = userLoginReq;
    }

    public static class UserLoginReqBean {
        /**
         * appid : 类型:String 说明:与注册时appid一致，由IM后台发布， 每个接入方共用一个appid, 测试环境填 kdldldldld
         * userType : 类型:Number 说明:填1 （代表员工)
         * onlineStatus : 类型:Number 说明:用户在线状态, 1 在线, 2 忙碌, 3 离开, 4 隐身
         * clientVersion : 类型:String 说明:客户端版本号
         */

        private String appid;
        private int userType;
        private int onlineStatus;
        private String clientVersion;

        public void setAppid(String appid) {
            this.appid = appid;
        }


        public void setUserType(int userType) {
            this.userType = userType;
        }


        public void setOnlineStatus(int onlineStatus) {
            this.onlineStatus = onlineStatus;
        }

        public void setClientVersion(String clientVersion) {
            this.clientVersion = clientVersion;
        }
    }
}
