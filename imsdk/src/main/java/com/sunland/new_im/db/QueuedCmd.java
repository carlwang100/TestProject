package com.sunland.new_im.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;


/**
 * 待发送命令
 * 交互场景
 * 客户端会话列表左划进行操作>更新session表>更新UI>插入表queued_command>通知工作线程发送给后台>收到后台回复后删除queued_command对应记录
 * 相关接口
 * CID4005 - 会话右键选项设置
 */
public class QueuedCmd extends BaseDaoEnabled<QueuedCmd, Long> {

    // CID4005 - 会话右键选项设置 1：置顶，2：取消置顶，3：免打扰，4：取消免打扰，5：删除"
    public static int CID_4005 = 4005;

    public static final int ACTION_SET_TOP = 1;

    public static final int ACTION_CANCEL_TOP = 2;

    public static final int ACTION_SET_NO_DISTURB = 3;

    public static final int ACTION_CANCEL_NO_DISTURB = 4;

    public static final int ACTION_DELETE = 5;

    public long getId() {
        return id;
    }

    @DatabaseField(generatedId = true)
    long id;

    //命令码
    @DatabaseField
    int cid;

    //会话对方Id，群聊为群id，单聊为对方imid",
    @DatabaseField
    long sessionId;

    //1：单聊，2：群聊",
    @DatabaseField
    int sessionType;

    //1：置顶，2：取消置顶，3：免打扰，4：取消免打扰，5：删除"
    @DatabaseField
    int action;

    public QueuedCmd() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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
}
