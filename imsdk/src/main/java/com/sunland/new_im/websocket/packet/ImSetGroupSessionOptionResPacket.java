package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by fengd on 2018/11/12.
 */

public class ImSetGroupSessionOptionResPacket extends ImBaseResponsePacket {

    /**
     * SetGroupSessionOptionRes : {"fromUserId":"类型:Number 说明:发起方的imid","sessionId":"类型:Number 说明:群id","action":"类型:Number 说明:1: 置顶， 2：取消置顶， 3：免打扰， 4： 取消免打扰， 5: 删除","sessionType":"类型:Number 说明:2：群聊","setTime":"类型:Number 说明:本次设置时间戳，毫秒级，13位 long型","resultCode":"类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义"}
     */

    @SerializedName("SetGroupSessionOptionRes")
    private SetGroupSessionOptionResBean SetGroupSessionOptionRes;

    public SetGroupSessionOptionResBean getSetGroupSessionOptionRes() {
        return SetGroupSessionOptionRes;
    }

    public void setSetGroupSessionOptionRes(SetGroupSessionOptionResBean SetGroupSessionOptionRes) {
        this.SetGroupSessionOptionRes = SetGroupSessionOptionRes;
    }

    public static class SetGroupSessionOptionResBean {
        /**
         * fromUserId : 类型:Number 说明:发起方的imid
         * sessionId : 类型:Number 说明:群id
         * action : 类型:Number 说明:1: 置顶， 2：取消置顶， 3：免打扰， 4： 取消免打扰， 5: 删除
         * sessionType : 类型:Number 说明:2：群聊
         * setTime : 类型:Number 说明:本次设置时间戳，毫秒级，13位 long型
         * resultCode : 类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义
         */

        @SerializedName("fromUserId")
        private long fromUserId;
        @SerializedName("sessionId")
        private long sessionId;
        @SerializedName("action")
        private int action;
        @SerializedName("sessionType")
        private int sessionType;
        @SerializedName("setTime")
        private long setTime;
        @SerializedName("resultCode")
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

        public long getSetTime() {
            return setTime;
        }

        public void setSetTime(long setTime) {
            this.setTime = setTime;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }
    }
}
