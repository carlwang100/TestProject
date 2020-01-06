package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by kai on 2018/8/28
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImSetSessionOptionReqPacket extends ImBaseRequestPacket {
    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:4005","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * SetSessionOptionReq : {"sessionId":"类型:Number 说明:会话id。单聊：接收方imid， 群聊：群组id","mSessionType":"类型:Number 说明:会话类型。1：单聊， 2：群聊","action":"类型:Number 说明:右键设置选项。1：置顶，2：取消置顶，3：免打扰，4：取消免打扰，5：删除"}
     */

    private SetSessionOptionReqBean SetSessionOptionReq;

    public ImSetSessionOptionReqPacket(EntryBean entry, SetSessionOptionReqBean SetSessionOptionReq) {
        super(entry);
        this.SetSessionOptionReq = SetSessionOptionReq;
    }

    public static class SetSessionOptionReqBean{

        /**
         * sessionId : 类型:Number 说明:会话id。单聊：接收方imid， 群聊：群组id
         * mSessionType : 类型:Number 说明:会话类型。1：单聊， 2：群聊
         * action : 类型:Number 说明:右键设置选项。1：置顶，2：取消置顶，3：免打扰，4：取消免打扰，5：删除
         */

        private long sessionId;
        private int sessionType;
        private int action;

        public long getSessionId() {
            return sessionId;
        }

        public void setSessionId(long sessionId) {
            this.sessionId = sessionId;
        }

        public int getSessionType() {
            return sessionType;
        }

        public void setSessionType(int sessionType) {
            this.sessionType = sessionType;
        }

        public int getAction() {
            return action;
        }

        public void setAction(int action) {
            this.action = action;
        }
    }
}
