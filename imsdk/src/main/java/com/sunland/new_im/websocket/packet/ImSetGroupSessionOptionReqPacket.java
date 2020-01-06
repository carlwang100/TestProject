package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by fengd on 2018/11/12.
 */

public class ImSetGroupSessionOptionReqPacket extends ImBaseRequestPacket {
    /**
     * SetGroupSessionOptionReq : {"sessionId":"类型:Number 说明:群的groupId","action":"类型:Number 说明:1: 置顶， 2：取消置顶， 3：免打扰， 4： 取消免打扰， 5: 删除","sessionType":"类型:Number 说明:2：群聊"}
     */

    @SerializedName("SetGroupSessionOptionReq")
    private SetGroupSessionOptionReqBean SetGroupSessionOptionReq;

    public ImSetGroupSessionOptionReqPacket(EntryBean entry, SetGroupSessionOptionReqBean optionReqBean) {
        super(entry);
        this.SetGroupSessionOptionReq = optionReqBean;
    }

    public static class SetGroupSessionOptionReqBean {
        /**
         * sessionId : 类型:Number 说明:群的groupId
         * action : 类型:Number 说明:1: 置顶， 2：取消置顶， 3：免打扰， 4： 取消免打扰， 5: 删除
         * sessionType : 类型:Number 说明:2：群聊
         */

        @SerializedName("sessionId")
        private long sessionId;
        @SerializedName("action")
        private int action;

        @SerializedName("sessionType")
        private int sessionType = ImSessionType.TYPE_SINGLE_CHAT;

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

    }
}
