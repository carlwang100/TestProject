package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by kai on 2018/8/28
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImSetSessionOptionResPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:4006
     * SetSessionOptionRes : {"fromUserId":"类型:Number 说明:设置者的imid","sessionId":"类型:Number 说明:会话id. 单聊：接收方imid， 群聊：群组id","action":"类型:Number","mSessionType":"类型:Number","resultCode":"类型:Number 说明:0 代表成功； 非0代表失败，具体意义参考后台定义."}
     */

    private SetSessionOptionResBean SetSessionOptionRes;

    public SetSessionOptionResBean getSetSessionOptionRes() {
        return SetSessionOptionRes;
    }

    public void setSetSessionOptionRes(SetSessionOptionResBean SetSessionOptionRes) {
        this.SetSessionOptionRes = SetSessionOptionRes;
    }

    public static class SetSessionOptionResBean {
        /**
         * fromUserId : 类型:Number 说明:设置者的imid
         * sessionId : 类型:Number 说明:会话id. 单聊：接收方imid， 群聊：群组id
         * action : 类型:Number
         * mSessionType : 类型:Number
         * resultCode : 类型:Number 说明:0 代表成功； 非0代表失败，具体意义参考后台定义.
         */

        private long fromUserId;
        private long sessionId;
        private int action;
        private int sessionType;
        private int resultCode;

        public long getFromUserId() {
            return fromUserId;
        }

        public void setFromUserId(long fromUserId) {
            this.fromUserId = fromUserId;
        }

        public long getSessionId() {
            return sessionId;
        }

        public void setSessionId(long sessionId) {
            this.sessionId = sessionId;
        }

        public int getAction() {
            return action;
        }

        public void setAction(int action) {
            this.action = action;
        }

        public int getSessionType() {
            return sessionType;
        }

        public void setSessionType(int sessionType) {
            this.sessionType = sessionType;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }
    }
}
