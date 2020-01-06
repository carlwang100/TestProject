package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by fengd on 2018/11/12.
 */

public class ImPullGroupSessionPropertyReqPacket extends ImBaseRequestPacket {

    /**
     * PullGroupChatPropertyReq : {"sessionId":1111111111111111,"sessionType":"：2"}
     */

    @SerializedName("PullGroupChatPropertyReq")
    private PullGroupChatPropertyReqBean PullGroupChatPropertyReq;

    public ImPullGroupSessionPropertyReqPacket(EntryBean entry, long sessionId) {
        super(entry);
        this.PullGroupChatPropertyReq = new PullGroupChatPropertyReqBean();
        this.PullGroupChatPropertyReq.setSessionId(sessionId);
    }

    public PullGroupChatPropertyReqBean getPullGroupChatPropertyReq() {
        return PullGroupChatPropertyReq;
    }

    public void setPullGroupChatPropertyReq(PullGroupChatPropertyReqBean PullGroupChatPropertyReq) {
        this.PullGroupChatPropertyReq = PullGroupChatPropertyReq;
    }

    public static class PullGroupChatPropertyReqBean {
        /**
         * sessionId : 1111111111111111
         * sessionType : ：2
         */

        @SerializedName("sessionId")
        private long sessionId;
        @SerializedName("sessionType")
        private int sessionType = ImSessionType.TYPE_GROUP_CHAT;

        public long getSessionId() {
            return sessionId;
        }

        public void setSessionId(long sessionId) {
            this.sessionId = sessionId;
        }

        public int getSessionType() {
            return sessionType;
        }

    }
}
