package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

import java.util.List;

/**
 * Created by kai on 2018/8/27
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImUserInfoReqPacket extends ImBaseRequestPacket {
    /**
     * Entry : {"imid":"类型:Number 说明:发送请求用户的imid","cid":"类型:Number 说明:1013","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * UserInfoQueryReq : {"queryIdList":"类型:Array[Number] 说明:要查询的用户imid列表"}
     */

    private UserInfoQueryReqBean UserInfoQueryReq;

    public ImUserInfoReqPacket(EntryBean entry, UserInfoQueryReqBean UserInfoQueryReq) {
        super(entry);
        this.UserInfoQueryReq = UserInfoQueryReq;
    }

    public UserInfoQueryReqBean getUserInfoQueryReq() {
        return UserInfoQueryReq;
    }

    public void setUserInfoQueryReq(UserInfoQueryReqBean UserInfoQueryReq) {
        this.UserInfoQueryReq = UserInfoQueryReq;
    }

    public static class UserInfoQueryReqBean {
        /**
         * queryIdList : 类型:Array[Number] 说明:要查询的用户imid列表
         */

        private List<Long> queryIdList;

        public List<Long> getQueryIdList() {
            return queryIdList;
        }

        public void setQueryIdList(List<Long> queryIdList) {
            this.queryIdList = queryIdList;
        }
    }
}
