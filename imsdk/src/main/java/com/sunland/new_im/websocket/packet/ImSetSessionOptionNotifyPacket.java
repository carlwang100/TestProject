package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by kai on 2018/8/29
 * Email：kaihu1989@gmail.com
 * Feature: 会话右键选项Notify，用于多终端同步会话右键选项设置
 */
public class ImSetSessionOptionNotifyPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:4007
     * SetSessionOptionNotify : {"fromUserId":"类型:Number 说明:用户imid","sessionId":"类型:Number 说明:会话id。单聊：接收方imid， 群聊：群组id","mSessionType":"类型:Number 说明:会话类型。1：单聊， 2：群聊","action":"类型:Number 说明:右键设置选项。1：置顶，2：取消置顶，3：免打扰，4：取消免打扰，5：删除"}
     */

    private SetSessionOptionNotifyBean SetSessionOptionNotify;

    public SetSessionOptionNotifyBean getSetSessionOptionNotify() {
        return SetSessionOptionNotify;
    }

    public void setSetSessionOptionNotify(SetSessionOptionNotifyBean SetSessionOptionNotify) {
        this.SetSessionOptionNotify = SetSessionOptionNotify;
    }

    public static class SetSessionOptionNotifyBean {
        /**
         * fromUserId : 类型:Number 说明:用户imid
         * sessionId : 类型:Number 说明:会话id。单聊：接收方imid， 群聊：群组id
         * mSessionType : 类型:Number 说明:会话类型。1：单聊， 2：群聊
         * action : 类型:Number 说明:右键设置选项。1：置顶，2：取消置顶，3：免打扰，4：取消免打扰，5：删除
         * setTime: "类型:Number 说明:设置操作的服务器时间戳，类型为long，单位为毫秒"
         */

        private long fromUserId;
        private long sessionId;
        private int sessionType;
        private int action;
        private long setTime;

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

        public long getSetTime() {
            return setTime;
        }

        public void setSetTime(long setTime) {
            this.setTime = setTime;
        }
    }
}
