package com.sunland.new_im.db;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;

/**
 * 草稿
 * 交互场景
 * 客户端打开消息窗口->输入文字后退出消息窗口->插入draft表->更新会话列表UI
 */
public class Draft extends BaseDaoEnabled<Draft, Long> {

    public static final String SESSION_ID = "sessionId";
    //会话id。单聊为对方imid，群聊为群组id，一个session只能有一个draft，所以这里的主键是它
    @DatabaseField(columnName = SESSION_ID, id = true)
    long sessionId;

    public static final String SESSION_TYPE = "sessionType";
    //会话类型：1表示单聊，2表示群聊,
    @DatabaseField(columnName = SESSION_TYPE)
    int sessionType = 1;

    //会话名称：单聊为对方名称，群聊为群组名称,
    @DatabaseField
    String sessionName = "";

    //会话头像url。单聊为对方头像，群聊为群组头像,
    @DatabaseField
    String imageUrl = "";

    // 消息创建时间,
    @DatabaseField
    long msgCreateTime;

    //消息内容，
    @DatabaseField
    String msgContent = "";

    public static final int KEYBOARD_TEXT = 0;
    public static final int KEYBOARD_VOICE = 1;
    public static final String KEYBOARD_TYPE = "keyboardType";
    //会话类型：0表示文字，1表示语音,
    @DatabaseField(columnName = KEYBOARD_TYPE)
    int keyboardType = KEYBOARD_TEXT;

    public Draft() {
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

    public long getMsgCreateTime() {
        return msgCreateTime;
    }

    public void setMsgCreateTime(long msgCreateTime) {
        this.msgCreateTime = msgCreateTime;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public int getKeyboardType() {
        return keyboardType;
    }

    public void setKeyboardType(int keyboardType) {
        this.keyboardType = keyboardType;
    }
}
