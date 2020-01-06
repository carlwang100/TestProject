package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by kai on 2018/8/28
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImPullMsgRecordReqPacket extends ImBaseRequestPacket {
    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:4003","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * PullChatMsgRecordReq : {"mSessionType":"类型:Number 说明:0：无效，1：单聊，2：群聊","sessionId":"类型:Number 说明:会话对方Id，群聊为群id，单聊为对方imid","msgId":"类型:Number 说明:0：从会话的第一个消息，-1: 从会话的最后一条消息，>0：从指定的消息开始拉取","orient":"类型:Number 说明:0： 只拉取一条记录, 1：拉取指定msgid及之后的记录，-1：拉取指定msgid及之前的","num":"类型:Number 说明:0：拉取指定方向的所有记录，>0：拉取指定方向的指定数目","needUserInfo":"类型:Number 说明:是否需要查询用户信息， 1：需要， 0： 不需要"}
     */
    public static final int FROM_FIRST = 0;
    public static final int FROM_LAST = -1;
    public static final int ORIENT_ONLY_ONE = 0;
    public static final int ORIENT_AFTER_MSGID = 1;
    public static final int ORIENT_BEFORE_MSGID = -1;

    private PullChatMsgRecordReqBean PullChatMsgRecordReq;

    public ImPullMsgRecordReqPacket(ImBaseRequestPacket.EntryBean entry, PullChatMsgRecordReqBean PullChatMsgRecordReq) {
        super(entry);
        this.PullChatMsgRecordReq = PullChatMsgRecordReq;
    }

    public static class PullChatMsgRecordReqBean{

        /**
         * sessionType : 类型:Number 说明:0：无效，1：单聊，2：群聊
         * sessionId : 类型:Number 说明:会话对方Id，群聊为群id，单聊为对方imid
         * msgId : 类型:Number 说明:0：从会话的第一个消息，-1: 从会话的最后一条消息，>0：从指定的消息开始拉取
         * orient : 类型:Number 说明:0： 只拉取一条记录, 1：拉取指定msgid及之后的记录，-1：拉取指定msgid及之前的
         * num : 类型:Number 说明:0：拉取指定方向的所有记录，>0：拉取指定方向的指定数目
         * needUserInfo : 类型:Number 说明:是否需要查询用户信息， 1：需要， 0： 不需要
         */

        private int sessionType;
        private long sessionId;
        private int msgId;
        private int orient;
        private int num;
        private int needUserInfo;

        public int getSessionType() {
            return sessionType;
        }

        public void setSessionType(int sessionType) {
            this.sessionType = sessionType;
        }

        public long getSessionId() {
            return sessionId;
        }

        public void setSessionId(long sessionId) {
            this.sessionId = sessionId;
        }

        public int getMsgId() {
            return msgId;
        }

        public void setMsgId(int msgId) {
            this.msgId = msgId;
        }

        public int getOrient() {
            return orient;
        }

        public void setOrient(int orient) {
            this.orient = orient;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getNeedUserInfo() {
            return needUserInfo;
        }

        public void setNeedUserInfo(int needUserInfo) {
            this.needUserInfo = needUserInfo;
        }
    }
}
