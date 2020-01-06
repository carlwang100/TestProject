package com.sunland.new_im.db;


import android.content.Context;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.sunland.new_im.websocket.packet.ImPullRecentSessionListResPacket;
import com.sunland.new_im.websocket.utils.StringUtils;


/**
 * 会话列表
 * 交互场景
 * 首次进入IM拉取会话列表->插入表session->更新UI
 * 会话列表下拉刷新->更新表session->更新UI
 * 发出消息->更新表message和表session->更新UI
 * 收到消息->更新表message和表session->更新UI
 * 被通知会话置顶，删除等->更新表session->更新UI
 * 相关接口
 * CID4006 - 会话右键选项Notify
 * CID4007 - 拉取左侧会话列表请求接口
 * CID6001 - MS1-发送方发出消息到IM
 * CID6003 - MS2-IM将消息发给接收方
 * CID6005 - MS3-接收方消息已读回执
 */
public class Session extends BaseDaoEnabled<Session, Long> {

    //会话id。单聊为对方imid，群聊为群组id
    public static final String SESSION_ID = "sessionId";
    @DatabaseField(id = true, columnName = SESSION_ID)
    long sessionId;

    public static final String SESSION_TYPE = "sessionType";
    //会话类型：1表示单聊，2表示群聊,
    @DatabaseField(columnName = SESSION_TYPE)
    int sessionType;

    public static final String SESSION_NAME = "sessionName";
    //会话名称：单聊为对方名称，群聊为群组名称,
    @DatabaseField
    String sessionName;

    //会话头像url。单聊为对方头像，群聊为群组头像,
    @DatabaseField
    String imageUrl;

    //最新一条消息id,
    @DatabaseField
    int latestMsgId;

    //最新一条消息类型
    @DatabaseField
    int latestMsgType;

    public static final String LATEST_MSG_CREATE_TIME = "latestMsgCreateTime";
    //最新一条消息时间
    @DatabaseField(columnName = LATEST_MSG_CREATE_TIME)
    long latestMsgCreateTime;

    //最后一条消息发送者的imid,
    @DatabaseField
    long latestSenderId;

    //最后一条消息发送者名字（只有当最后一条是对方发送，才设置为对方名字）,
    @DatabaseField
    String latestSenderName;

    //最后一条消息状态（1：正常，2：被撤回，3x:错误， x代表cid6002后台给的错误码）,
    @DatabaseField
    int latestMsgStatus;

    //最新一条消息内容（当消息未被撤回，才设置字段值。被撤回时，由前端自行设置提示语）,
    @DatabaseField
    String lastestMsgContent;

    public static final String UNREAD_NUM = "unReadNum";
    //未读消息数目,
    @DatabaseField
    int unReadNum;

    public static final int RECEIVED_PEER_READ = 1;
    public static final int RECEIVED_PEER_UNREAD = 0;
    public static final String RECEIVED_PEER_MSG = "receivedPeerMsg";
    // 单聊使用，对方是否读取本条消息，0：已读， 1：未读。仅适用于本方发送出去的消息，如果是对方发送的消息此字段填 1
    @DatabaseField(columnName = RECEIVED_PEER_MSG)
    int receivedPeerMsg = RECEIVED_PEER_UNREAD;

    public static final int CONSTANT_NOT_TOP = 0;
    public static final int CONSTANT_IS_TOP = 1;

    public static final int CONSTANT_CAN_DISTURB = 0;
    public static final int CONSTANT_NO_DISTURB = 1;

    //是否被置顶。0：未置顶，1：置顶,
    @DatabaseField
    int isTop;

    //是否设置免打扰。0：未设置免打扰，1：设置免打扰,
    @DatabaseField
    int noDisturb;

    public static final int STATUS_ON_JOB = 1;
    public static final int STATUS_LEAVE_JOB = 2;

    //说明:1：对方在职；2：对方离职"
    @DatabaseField
    int status = STATUS_ON_JOB;

    public static final String NEED_SHOW = "needShow";
    // 是否需要显示，后台接口没有这个字段，如下情况使用
    // 如果设置没有联系过的联系人免打扰时，需要在数据库增加一条needShow==false的记录
    // 其他情况，比如将此联系人设置置顶则创建needShow==true的记录
    @DatabaseField
    boolean needShow = true;

    /**
     * 设置为置顶的时间，取手机本地时间
     */
    @DatabaseField
    long topTime;

    public static final String FIRST_UNREAD_GROUP_AT_MSG_ID = "firstUnReadGroupAtMsgId";
    // 说明:（群聊使用）第一条未读的@自己的msgId （备注：修改）
    @DatabaseField(columnName = FIRST_UNREAD_GROUP_AT_MSG_ID)
    int firstUnReadGroupAtMsgId = -1;

    // 说明:（群聊使用）现有成员数目（备注：新增）
    @DatabaseField
    int memberNum;

    // 说明:（群聊使用）成员数目上限（备注：新增）
    @DatabaseField
    int memberNumberMaximum;

    // 2019/2/26 V1.3.2 消息催办新增
    @DatabaseField
    String latestExtContent;

    // 2019/3/20 V1.3.6 IM外显异常工作状态
    @DatabaseField
    String description;

    public static final int IS_NORMAL_GROUP_TYPE = 0;
    public static final int IS_DEPARTMENT_GROUP_TYPE = 1;
    //群聊类型  是否是部门群
    @DatabaseField
    int groupType;

    //单聊类型，账号类型：0代表普通用户，1代表通知类型
    @DatabaseField
    int userType;
    public static final int IS_NORMAL_USER_TYPE = 1;
    public static final int IS_NOTIFY_TYPE = 2;


    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }


    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getReceivedPeerMsg() {
        return receivedPeerMsg;
    }

    public void setReceivedPeerMsg(int receivedPeerMsg) {
        this.receivedPeerMsg = receivedPeerMsg;
    }

    public Session() {
    }

    public Session(ImPullRecentSessionListResPacket.PullRecentSessionListResBean.RecentSessionListBean wsBean) {
        this.sessionId = wsBean.getSessionId();
        this.sessionType = wsBean.getSessionType();
        this.sessionName = wsBean.getSessionName();
        this.imageUrl = wsBean.getImageUrl();
        this.latestMsgId = wsBean.getLatestMsgId();
        this.latestMsgType = wsBean.getLatestMsgType();
        this.latestMsgCreateTime = wsBean.getLatestMsgCreateTime();
        this.latestSenderId = wsBean.getLatestSenderId();
        this.latestSenderName = wsBean.getLatestSenderName();
        this.latestMsgStatus = wsBean.getLatestMsgStatus();
        this.lastestMsgContent = wsBean.getLastestMsgContent();
        this.unReadNum = wsBean.getUnReadNum();
        this.isTop = wsBean.getIsTop();
        this.topTime = wsBean.getTopTime();
        this.noDisturb = wsBean.getNoDisturb();
        this.status = wsBean.getStatus();
        this.firstUnReadGroupAtMsgId = wsBean.getFirstUnReadGroupAtMsgId();
        this.memberNum = wsBean.getMemberNum();
        this.memberNumberMaximum = wsBean.getMemberNumberMaximum();
        this.latestExtContent = wsBean.getLastestMsgExtContent();
        this.description = wsBean.getDescription();
        this.groupType = wsBean.getGroupType();
        this.userType = wsBean.getUserType();
        this.receivedPeerMsg = wsBean.getReceivedPeerMsg();
    }

    public long getTopTime() {
        return topTime;
    }

    public void setTopTime(long topTime) {
        this.topTime = topTime;
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

    public int getLatestMsgId() {
        return latestMsgId;
    }

    public void setLatestMsgId(int latestMsgId) {
        this.latestMsgId = latestMsgId;
    }

    public int getLatestMsgType() {
        return latestMsgType;
    }

    public void setLatestMsgType(int latestMsgType) {
        this.latestMsgType = latestMsgType;
    }

    public long getLatestMsgCreateTime() {
        return latestMsgCreateTime;
    }

    public void setLatestMsgCreateTime(long latestMsgCreateTime) {
        this.latestMsgCreateTime = latestMsgCreateTime;
    }

    public long getLatestSenderId() {
        return latestSenderId;
    }

    public void setLatestSenderId(long latestSenderId) {
        this.latestSenderId = latestSenderId;
    }

    public String getLatestSenderName() {
        return latestSenderName;
    }

    public void setLatestSenderName(String latestSenderName) {
        this.latestSenderName = latestSenderName;
    }

    public int getLatestMsgStatus() {
        return latestMsgStatus;
    }

    public void setLatestMsgStatus(int latestMsgStatus) {
        this.latestMsgStatus = latestMsgStatus;
    }

    public String getLastestMsgContent() {
        return StringUtils.avoidNull(lastestMsgContent);
    }

    /**
     * 显示在会话列表上的最新消息内容，例如如果是图片，显示为[图片]
     * @return
     */
    public String getDisplayMsgContent(Context context) {
        return SessionDisplayUtil.getSessionDisplayContent(context, this);
    }

    public void setLastestMsgContent(String lastestMsgContent) {
        this.lastestMsgContent = lastestMsgContent;
    }

    public int getUnReadNum() {
        return unReadNum;
    }

    public void setUnReadNum(int unReadNum) {
        this.unReadNum = unReadNum;
    }

    public boolean getIsTop() {
        return isTop == CONSTANT_IS_TOP;
    }

    public void setIsTop(boolean isTop) {
        this.isTop = isTop ? CONSTANT_IS_TOP : CONSTANT_NOT_TOP;
        setTopTime(System.currentTimeMillis());
    }

    public boolean getNoDisturb() {
        return noDisturb == CONSTANT_NO_DISTURB;
    }

    public void setNoDisturb(boolean noDisturb) {
        this.noDisturb = noDisturb ? CONSTANT_NO_DISTURB : CONSTANT_CAN_DISTURB;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isNeedShow() {
        return needShow;
    }

    public void setNeedShow(boolean needShow) {
        this.needShow = needShow;
    }

    public int getFirstUnReadGroupAtMsgId() {
        return firstUnReadGroupAtMsgId;
    }

    public void setFirstUnReadGroupAtMsgId(int firstUnReadGroupAtMsgId) {
        this.firstUnReadGroupAtMsgId = firstUnReadGroupAtMsgId;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public int getMemberNumberMaximum() {
        return memberNumberMaximum;
    }

    public void setMemberNumberMaximum(int memberNumberMaximum) {
        this.memberNumberMaximum = memberNumberMaximum;
    }

    public String getLatestExtContent() {
        return latestExtContent;
    }

    public void setLatestExtContent(String latestExtContent) {
        this.latestExtContent = latestExtContent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
