package com.sunland.new_im.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;


/**
 * 待发送已读回执
 * 交互场景
 * 客户端收到推送消息->打开消息界面->插入表read_reply->更新message对应sessionId下的所有未读消息为已读->更新表session对应记录
 * 相关接口
 * CID6005 - MS3-接收方消息已读回执
 */
public class ReadReply extends BaseDaoEnabled<ReadReply, Long> {

    public long getId() {
        return id;
    }

    @DatabaseField(generatedId = true)
    long id;

    public static final String SESSION_ID = "sessionId";
    //会话id。单聊为对方imid，群聊为群组id
    @DatabaseField(columnName = SESSION_ID)
    long sessionId;

    public static final String SESSION_TYPE = "sessionType";
    //会话类型：1表示单聊，2表示群聊,
    @DatabaseField(columnName = SESSION_TYPE)
    int sessionType;

    //用于文件已读标识：消息接收方 当前已读的文件消息id",
    @DatabaseField
    long recvFileMsgId;

    //消息接收方 当前已读的最大消息id（不根据此最大消息id更新文件的已读状态）"
    @DatabaseField
    long latestReadMsgId;

    public ReadReply() {
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

    public long getRecvFileMsgId() {
        return recvFileMsgId;
    }

    public void setRecvFileMsgId(long recvFileMsgId) {
        this.recvFileMsgId = recvFileMsgId;
    }

    public long getLatestReadMsgId() {
        return latestReadMsgId;
    }

    public void setLatestReadMsgId(long latestReadMsgId) {
        this.latestReadMsgId = latestReadMsgId;
    }
}
