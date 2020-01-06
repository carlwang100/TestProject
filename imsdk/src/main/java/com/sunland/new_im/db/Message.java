package com.sunland.new_im.db;

import android.content.Context;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.sunland.new_im.websocket.packet.ImMsgType;


/**
 *  消息列表
 *  交互场景
 *  打开聊天窗口->显示本地数据库最新消息20条->从后台获取比当前本地消息更新的消息->更新UI
 *  后台推送消息->更新本地数据库->更新界面
 *  客户端发送消息->插入tmp_message设置为正在发送状态->发送成功收到后台回执->删除tmp_message数据插入message，更新表session->更新UI
 *  相关接口
 *  CID4003 - 拉取聊天记录
 *  CID6001 - MS1-发送方发出消息到IM
 *  CID6003 - MS2-IM将消息发给接收方
 *  CID6005 - MS3-接收方消息已读回执
 */
// 这里实际主键是sessionId + msgId
public class Message extends BaseDaoEnabled<Message, Long> {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @DatabaseField(generatedId = true)
    long id;

    public static final String SESSION_ID = "sessionId";
    // 会话对方Id，群聊为群id，单聊为对方imid",
    @DatabaseField/*(uniqueCombo = true)*/
    long sessionId;

    public static final String SESSION_TYPE = "sessionType";
    // 1：单聊，2：群聊",
    @DatabaseField
    int sessionType = 1;

    public static final String MESSAGE_ID = "msgId";
    // 消息id,
    @DatabaseField/*(uniqueCombo = true)*/
    int msgId;

    public static final String MESSAGE_TYPE = "msgType";
    /**
     * @see ImMsgType
     */
    @DatabaseField
    int msgType = 1;

    public static final String MSG_CREATE_TIME = "msgCreateTime";
    // 消息发送时间,
    @DatabaseField(columnName = MSG_CREATE_TIME)
    long msgCreateTime;

    public static class Status{
        public static final int NORMAL = 1;
        public static final int REVOKED = 2;
    }

    public static final String MSG_STATUS = "msgStatus";
    // 1：正常， 2：撤销， 3X:错误， x代表cid6002后台给的错误码
    @DatabaseField(columnName = MSG_STATUS)
    int msgStatus;

    public static final String MSG_CONTENT = "msgContent";
    // 消息内容， 撤销时为空,
    @DatabaseField(columnName = MSG_CONTENT)
    String msgContent = "";

    public static final String SENDER_ID = "senderId";
    // 发送方的imid,
    @DatabaseField(columnName = SENDER_ID)
    long senderId;

    //单聊为对方头像，可以从session对象里拿，群聊为每个消息对应的发送者,
    @DatabaseField
    String senderImageUrl = "";

    // 消息发送者的昵称，因为已经有senderId，考虑通过senderId来拿，后期改掉
    @DatabaseField
    String senderName = "";

    public static final int CONSTANT_PEER_READ = 0;
    public static final int CONSTANT_PEER_UNREAD = 1;

    public static final String PEER_UNREAD = "peerUnRead";
    // 单聊使用，对方是否读取本条消息，0：已读， 1：未读。仅适用于本方发送出去的消息，如果是对方发送的消息此字段填 1
    @DatabaseField(columnName = PEER_UNREAD)
    int peerUnRead = CONSTANT_PEER_UNREAD;

    public static final int RECEIVED_PEER_READ = 1;
    public static final int RECEIVED_PEER_UNREAD = 0;
    public static final String RECEIVED_PEER_MSG = "receivedPeerMsg";
    //针对于别人发的未撤回的语音消息，1：已接收，0：未接收
    @DatabaseField(columnName = RECEIVED_PEER_MSG)
    int receivedPeerMsg = RECEIVED_PEER_UNREAD;


    public static final String UNIQUE_KEY = "uniqueKey";
    // 消息发送唯一标识， 由客户端设置。用于回复消息匹配,
    @DatabaseField(columnName = UNIQUE_KEY)
    String uniqueKey;

    public enum GroupAtType {
        NO_AT,
        AT_SOME_ONE,
        AT_ALL;
    }

    //说明:0:没有at消息， 1: at部分人, 2:at所有人
    @DatabaseField
    int groupAtType = 0;

    //被at的群成员imid--->nickName的map, json形式
    @DatabaseField
    String groupAtMember = "";

    // 群消息使用，此条消息未读人数
    @DatabaseField
    int unReadPeerNum;

    // 群消息使用，此条消息已读人数
    @DatabaseField
    int haveReadPeerNum;

    // 2019/2/26 V1.3.2 开发，适配消息催办
    @DatabaseField
    String extContent = "";

    public Message() {
    }

    /**
     *  @param tmpMessage 发送成功收到系统回执并被删除的tmpMessage
     * @param senderId 发送者imid
     * @param msgId 来自服务端应答，cid 6002
     * @param msgStatus 1：正常， 2：撤销
     */
    public Message(TmpMessage tmpMessage, int msgId, long senderId, int msgStatus) {
        this.sessionId = tmpMessage.getSessionId();
        this.sessionType = tmpMessage.getSessionType();
        this.msgId = msgId;
        this.senderId = senderId;
        this.msgType = tmpMessage.getMsgType();
        this.uniqueKey = tmpMessage.getUniqueKey();
        this.msgCreateTime = tmpMessage.getMsgCreateTime();
        this.msgStatus = msgStatus;
        this.msgContent = tmpMessage.getMsgContent();
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getSenderImageUrl() {
        return senderImageUrl;
    }

    public void setSenderImageUrl(String senderImageUrl) {
        this.senderImageUrl = senderImageUrl;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getSessionType() {
        return sessionType;
    }

    public void setSessionType(int sessionType) {
        this.sessionType = sessionType;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public long getMsgCreateTime() {
        return msgCreateTime;
    }

    public void setMsgCreateTime(long msgCreateTime) {
        this.msgCreateTime = msgCreateTime;
    }

    public int getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(int msgStatus) {
        this.msgStatus = msgStatus;
    }

    public String getMsgContent() {
        return msgContent;
    }

    /**
     * 显示在通知栏上的最新消息内容，例如如果是图片，显示为[图片]
     * @return
     */
    public String getDisplayMsgContent(Context context) {
        return SessionDisplayUtil.getSessionDisplayContent(context, this);
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public int getPeerUnRead() {
        return peerUnRead;
    }

    public void setPeerUnRead(int peerUnRead) {
        this.peerUnRead = peerUnRead;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public int getGroupAtType() {
        return groupAtType;
    }

    public void setGroupAtType(int groupAtType) {
        this.groupAtType = groupAtType;
    }

    public String getGroupAtMember() {
        return groupAtMember;
    }

    public void setGroupAtMember(String groupAtMember) {
        this.groupAtMember = groupAtMember;
    }

    public int getUnReadPeerNum() {
        return unReadPeerNum;
    }

    public void setUnReadPeerNum(int unReadPeerNum) {
        this.unReadPeerNum = unReadPeerNum;
    }

    public int getHaveReadPeerNum() {
        return haveReadPeerNum;
    }

    public void setHaveReadPeerNum(int haveReadPeerNum) {
        this.haveReadPeerNum = haveReadPeerNum;
    }

    public String getExtContent() {
        return extContent;
    }

    public void setExtContent(String extContent) {
        this.extContent = extContent;
    }

    public int getReceivedPeerMsg() {
        return receivedPeerMsg;
    }

    public void setReceivedPeerMsg(int receivedPeerMsg) {
        this.receivedPeerMsg = receivedPeerMsg;
    }
}
