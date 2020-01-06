package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by kai on 2018/8/28
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImLogoutPacket extends ImBaseRequestPacket {
    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:1005","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * UserLogoutReq : {"logoutType":"类型:Number 说明:1"}
     */

    private UserLogoutReqBean UserLogoutReq;

    public ImLogoutPacket(EntryBean entry, UserLogoutReqBean UserLogoutReq) {
        super(entry);
        this.UserLogoutReq = UserLogoutReq;
    }

    public static class UserLogoutReqBean{
        private int logoutType;

        public UserLogoutReqBean(int logoutType) {
            this.logoutType = logoutType;
        }
    }

}
