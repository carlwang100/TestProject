package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;
import com.sunland.new_im.websocket.utils.WsUtil;

/**
 * CID7049 -群聊 撤回消息请求
 */
public class ImRevokeGroupMsgReqPacket extends ImBaseRequestPacket {

    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:7049","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配。","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * RevokeGroupMsgReq : {"revokerId":"类型:Number 说明:撤回者imid","sessionId":"类型:String 说明:会话id。单聊：接收方imid","msgId":"类型:String 说明:要撤回的msgId"}
     */

    @SerializedName("RevokeGroupMsgReq")
    private RevokeGroupMsgReqBean RevokeGroupMsgReq;

    public ImRevokeGroupMsgReqPacket(long imId, long sesId, long msgId) {
        super(new ImBaseRequestPacket.EntryBean(imId, ImCid.REVOKE_GRP_MSG_REG, WsUtil.generateUniqueKey()));
        RevokeGroupMsgReq = new RevokeGroupMsgReqBean();
        RevokeGroupMsgReq.setRevokerId(imId);
        RevokeGroupMsgReq.setSessionId(sesId);
        RevokeGroupMsgReq.setMsgId(msgId);
    }

    public RevokeGroupMsgReqBean getRevokeGroupMsgReq() {
        return RevokeGroupMsgReq;
    }

    public void setRevokeGroupMsgReq(RevokeGroupMsgReqBean RevokeGroupMsgReq) {
        this.RevokeGroupMsgReq = RevokeGroupMsgReq;
    }

    public static class RevokeGroupMsgReqBean {
        /**
         * revokerId : 类型:Number 说明:撤回者imid
         * sessionId : 类型:String 说明:会话id。单聊：接收方imid
         * msgId : 类型:String 说明:要撤回的msgId
         */

        @SerializedName("revokerId")
        private long revokerId;
        @SerializedName("sessionId")
        private long sessionId;
        @SerializedName("msgId")
        private long msgId;

        public long getRevokerId() {
            return revokerId;
        }

        public void setRevokerId(long revokerId) {
            this.revokerId = revokerId;
        }

        public long getSessionId() {
            return sessionId;
        }

        public void setSessionId(long sessionId) {
            this.sessionId = sessionId;
        }

        public long getMsgId() {
            return msgId;
        }

        public void setMsgId(long msgId) {
            this.msgId = msgId;
        }
    }
}
