package com.sunland.new_im.db;

import android.content.Context;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;


/**
 * 待发送消息
 * 交互场景
 * 客户端发送消息->插入tmp_message设置为正在发送状态->发送成功收到后台回执->删除tmp_message数据插入message->更新UI
 * 相关接口 CID6001 - MS1-发送方发出消息到IM
 */
public class TmpMessage extends BaseDaoEnabled<TmpMessage, Long> {

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
    @DatabaseField
    long sessionId;

    public static final String SESSION_TYPE = "sessionType";
    // 1：单聊，2：群聊",
    @DatabaseField
    int sessionType = 1;

    //会话名称：单聊为对方名称，群聊为群组名称,
    @DatabaseField
    String sessionName = "";

    //会话头像url。单聊为对方头像，群聊为群组头像,
    @DatabaseField
    String imageUrl = "";

    public static final String MESSAGE_TYPE = "msgType";
    // 消息类型, 1：文本，2：音频，3：图片，4：视频，5：文件",
    @DatabaseField
    int msgType;

    // 消息发送时间,
    @DatabaseField
    long msgCreateTime;

    // 已尝试发送次数，如果为0显示发送中，当大于0时消息窗口对该消息显示一个重发按钮,发送后收到后台回执从此表删除对应记录，添加msgId加入messsage表
    @DatabaseField
    int sentTimes;

    public static final String UNIQUE_KEY = "uniqueKey";
    // 消息发送唯一标识， 由客户端设置。用于回复消息匹配,
    @DatabaseField
    String uniqueKey;

    // 消息内容， 撤销时为空,
    @DatabaseField
    String msgContent = "";

    //说明:0:没有at消息， 1: at部分人, 2:at所有人
    @DatabaseField
    int groupAtType = 0;

    //被at的群成员imid--->nickName的ma
    @DatabaseField
    String groupAtMember = "";

    public TmpMessage() {
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

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public int getSentTimes() {
        return sentTimes;
    }

    public void setSentTimes(int sentTimes) {
        this.sentTimes = sentTimes;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getMsgContent() {
        return msgContent;
    }

    /**
     * 显示在会话列表上的最新消息内容，例如如果是图片，显示为[图片]
     * @return
     */
    public String getDisplayMsgContent(Context context) {
        int msgType = getMsgType();
        String msgContent = getMsgContent();
        return SessionDisplayUtil.getSessionDisplayContent(context,this);
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
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

    public boolean isSentFailed(){
        return sentTimes > 0 || System.currentTimeMillis() - msgCreateTime > 10 * 1000;
    }

}
