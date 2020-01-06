package com.sunland.new_im.websocket.packet;

/**
 * Created by kai on 2018/8/20
 * Email：kaihu1989@gmail.com
 * Feature: IM协议 cid
 */
public class ImCid {
    //登录
    public static final int LOGIN_REQ = 1003;
    public static final int LOGIN_RES = 1004;

    //登出IM
    public static final int LOGOUT_REQ = 1005;
    public static final int LOGOUT_RES = 1006;

    //重复登陆IM，被踢通知
    public static final int KICK_OUT_NOTIFY = 1007;

    //IM用户信息变更(头像URL等)
    public static final int MODIFY_USER_INFO_REQ = 1009;
    public static final int MODIFY_USER_INFO_RES = 1010;

    //用户DeviceToken更新 [仅用于手机app推送]
    public static final int UPDATE_DEVICE_TOKEN_REQ = 1011;
    //用户DeviceToken更新应答
    public static final int UPDATE_DEVICE_TOKEN_RSP = 1012;
    //获取用户信息
    public static final int USER_INFO_REQ = 1013;
    public static final int USER_INFO_RES = 1014;

    //聊天记录搜索
    public static final int MESSAGE_SEARCH_REQ = 1015;

    //聊天记录搜索
    public static final int MESSAGE_SEARCH_RSP = 1016;

    //客户端与IM心跳
    public static final int HEARTBEAT = 3001;

    //IM后台返回错误
    public static final int ERROR = 3101;

    //拉取聊天记录
    public static final int PULL_MSG_RECORD_REQ = 4003;
    public static final int PULL_MSG_RECORD_RES = 4004;

    //单聊会话右键选项设置
    public static final int SESSION_OPERATION_REQ = 4005;
    public static final int SESSION_OPERATION_RES = 4006;

    // 单聊 撤回消息请求和应答
    public static final int REVOKE_MSG_REG = 6009;
    public static final int REVOKE_MSG_RES = 6010;

    // 群聊 撤回消息请求和应答
    public static final int REVOKE_GRP_MSG_REG = 7049;
    public static final int REVOKE_GRP_MSG_RES = 7050;

    //群聊会话右键选项设置
    public static final int SESSION_GROUP_OPERATION_REQ = 7021;
    public static final int SESSION_GROUP_OPERATION_RES = 7022;

    //会话右键选项通知
    public static final int SESSION_OPERATION_NOTIFY = 4007;

    //群聊右键选项通知
    public static final int GROUP_SESSION_OPERATION_NOTIFY = 7023;

    //拉取单聊会话属性
    public static final int PULL_SESSION_PROPERTY_REQ = 4009;
    public static final int PULL_SESSION_PROPERTY_RES = 4010;

    //拉取单聊会话属性
    public static final int PULL_GROUP_SESSION_PROPERTY_REQ = 7037;
    public static final int PULL_GROUP_SESSION_PROPERTY_RES = 7038;

    //拉取会话列表
    public static final int PULL_SESSION_LIST_REQ = 4017;
    public static final int PULL_SESSION_LIST_RES = 4018;

    //发送方发出单聊消息到IM
    public static final int SEND_SINGLE_MSG_REQ = 6001;
    public static final int SEND_SINGLE_MSG_ACK = 6002;
    //IM将消息发给接收方
    public static final int RECEIVE_SINGLE_MSG_NOTIFY = 6003;

    //IM单聊消息多终端同步
    public static final int MSG_SINGLE_SYNC_RES = 6004;
    //IM群聊消息多终端同步
    public static final int MSG_GROUP_SYNC_RES = 7014;

    //接收方消息已读回执
    public static final int MSG_READ_REQ = 6005;
    public static final int MSG_READ_RES = 6006;
    //IM通知发送方消息已读
    public static final int MSG_READ_NOTIFY = 6007;


    public static final int INVITE_GROUP_MEMBER_REQ = 7001;
    public static final int INVITE_GROUP_MEMBER_RES = 7002;

    //群创建或者群成员邀请完成后，IM将成员入群通知发送给所有群成员，多端同步
    public static final int INVITE_GROUP_MEMBER_NOTIFY = 7003;

    // CID7007 - 成员退群通知
    public static final int REMOVE_GROUP_MEMBER_NOTIFY = 7007;

    //发送方发出群聊消息到IM
    public static final int SEND_GROUP_MSG_REQ = 7011;
    public static final int SEND_GROUP_MSG_ACK = 7012;
    //IM将群聊消息发给接收者
    public static final int RECEIVE_GROUP_MSG_NOTIFY = 7013;

    //用于群消息接收方 发送消息已读回执给IM服务器
    public static final int GROUP_MSG_READ_REQ = 7015;
    public static final int GROUP_MSG_READ_RES = 7016;

    //用于IM服务器 发送“群消息已读通知” 给 消息发送者 和 读取消息者 所在的其他终端
    public static final int GROUP_MSG_READ_NOTIFY = 7017;

    //CID7025 - 获取会话基本信息（可拉取通讯录中的群聊会话）
    public static final int QUERY_SESSION_BASIC_INFO_REQ = 7025;
    public static final int QUERY_SESSION_BASIC_INFO_RES = 7026;

    //拉取群成员列表
    public static final int GET_GROUP_MEMBER_LIST_REQ = 7029;
    public static final int GET_GROUP_MEMBER_LIST_RES = 7030;

    //群信息修改
    public static final int MODIFY_GROUP_INFO_REQ = 7041;
    public static final int MODIFY_GROUP_INFO_RES = 7042;
    public static final int MODIFY_GROUP_INFO_NOTIFY = 7043;
    public static final int REMOVE_GROUP_MEMBER_REQ = 7005;
    public static final int REMOVE_GROUP_MEMBER_RES = 7006;

    // 设置全局消息免打扰
    public static final int SET_MESSAGE_NO_DISTURB_REQ = 8001;
    public static final int SET_MESSAGE_NO_DISTURB_RSP = 8002;

    public static final int QUERY_MESSAGE_NO_DISTURB_REQ = 8003;
    public static final int QUERY_MESSAGE_NO_DISTURB_RSP = 8004;

    //CID6011 - 单聊消息撤回通知
    public static final int REVOKE_MSG_NOTIFY = 6011;
    //CID7051 - 群消息撤回通知
    public static final int REVOKE_GRP_MSG_NOTIFY = 7051;

    //CID7053 - 拉取群管理人员信息
    public static final int QUERY_GROUP_MANAGER_REQ = 7053;
    public static final int QUERY_GROUP_MANAGER_RES = 7054;

    //CID4019 - 拉取撤回消息记录
    public static final int PULL_REVOKE_MSG_INFO_LIST_REQ = 4019;
    public static final int PULL_REVOKE_MSG_INFO_LIST_RES = 4020;
    public static final int TRANSFER_GROUP_LEADER = 7045;
    public static final int TRANSFER_GROUP_LEADER_RSP = 7046;
    public static final int TRANSFER_GROUP_LEADER_NOTIFY = 7047;
}
