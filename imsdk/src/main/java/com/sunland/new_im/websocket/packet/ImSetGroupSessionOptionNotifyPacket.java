package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by fengd on 2018/11/14.
 */

public class ImSetGroupSessionOptionNotifyPacket extends ImBaseResponsePacket{


    /**
     * cid : 类型:Number 说明:7023
     * SetGroupSessionOptionNotify : {"fromUserId":"类型:Number 说明:设置方imid","sessionId":"类型:Number 说明:群id","sessionType":"类型:Number 说明:2：群聊","action":"类型:Number 说明:1: 置顶， 2：取消置顶， 3：免打扰， 4： 取消免打扰， 5: 删除","setTime":"类型:Number 说明:本次设置时间戳，毫秒级，13位 long型"}
     */

    @SerializedName("SetGroupSessionOptionNotify")
    private SetGroupSessionOptionNotifyBean SetGroupSessionOptionNotify;

    public SetGroupSessionOptionNotifyBean getSetGroupSessionOptionNotify() {
        return SetGroupSessionOptionNotify;
    }

    public void setSetGroupSessionOptionNotify(SetGroupSessionOptionNotifyBean SetGroupSessionOptionNotify) {
        this.SetGroupSessionOptionNotify = SetGroupSessionOptionNotify;
    }

    public static class SetGroupSessionOptionNotifyBean {
        /**
         * fromUserId : 类型:Number 说明:设置方imid
         * sessionId : 类型:Number 说明:群id
         * sessionType : 类型:Number 说明:2：群聊
         * action : 类型:Number 说明:1: 置顶， 2：取消置顶， 3：免打扰， 4： 取消免打扰， 5: 删除
         * setTime : 类型:Number 说明:本次设置时间戳，毫秒级，13位 long型
         */

        @SerializedName("fromUserId")
        private long fromUserId;
        @SerializedName("sessionId")
        private long sessionId;
        @SerializedName("sessionType")
        private int sessionType;
        @SerializedName("action")
        private int action;
        @SerializedName("setTime")
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
