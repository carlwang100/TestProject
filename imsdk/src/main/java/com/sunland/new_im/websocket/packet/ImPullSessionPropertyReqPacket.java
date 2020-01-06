package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by kai on 2018/9/4
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImPullSessionPropertyReqPacket extends ImBaseRequestPacket {
    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:4009","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配","clientType":"类型:Number 说明:1 Android, 2 IOS, 3 WEB"}
     * PullSingleChatPropertyReq : {"sessionId":"类型:Number 说明:会话id。单聊：为对方imid","mSessionType":"类型:Number 说明:会话类型。1：单聊"}
     */

    private PullSingleChatPropertyReqBean PullSingleChatPropertyReq;

    public ImPullSessionPropertyReqPacket(EntryBean entry, PullSingleChatPropertyReqBean PullSingleChatPropertyReq) {
        super(entry);
        this.PullSingleChatPropertyReq  = PullSingleChatPropertyReq;
    }

    public static class PullSingleChatPropertyReqBean{

        /**
         * sessionId : 类型:Number 说明:会话id。单聊：为对方imid
         * mSessionType : 类型:Number 说明:会话类型。1：单聊
         */

        private long sessionId;
        private int sessionType;

        public void setSessionId(long sessionId) {
            this.sessionId = sessionId;
        }

        public void setSessionType(int sessionType) {
            this.sessionType = sessionType;
        }
    }

}
