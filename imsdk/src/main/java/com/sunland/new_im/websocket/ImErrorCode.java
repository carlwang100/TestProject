package com.sunland.new_im.websocket;

/**
 * Created by kai on 2018/11/20
 * Email：kaihu1989@gmail.com
 * Feature: 新IM服务器错误码
 */
public class ImErrorCode {
    public static final int SUCCESS = 0;
    public static final int FAILED = 1;
    public static final int UNKNOWN_ERR = -1;
    public static final int PROTOCOL_ERROR = 1002;
    public static final int PARAM_ERROR = 1003;
    public static final int INTERNAL_ERROR = 1004;
    public static final int REQUEST_DENY = 1006;
    public static final int UNKNOWN_CID = 2001;
    public static final int EXCEPTION = 2002;
    public static final int UNKNOWN_EXCEPTION = 2003;
    public static final int CONVTOTARS_FAIL = 2004;
    public static final int CONVTOSTR_FAIL = 2005;
    public static final int UNKNOWN_CLSNAME = 2006;
    public static final int UNKNOWN_PUSHPLATFORM = 2007;
    public static final int CURLPERFORM_FAIL = 2008;

    public static final int SvcReqRC_LOGIN_IMID_INVALID_ERROR = 2021;
    public static final int SvcReqRC_LOGIN_ERROR_ATTEMPT_LIMIT_ERROR = 2022;
    public static final int SvcReqRC_LOGIN_PASSWORD_ERROR = 2023;

    //消息相关
    public static final int GET_RELATION_ERROR = 3001;
    public static final int SINGLE_SHIELD_BY_PEER = 3002;
    public static final int MSG_TYPE_ERROR = 3003;
    public static final int CLIENT_TYPE_ERROR = 3004;
    public static final int MSG_CONTENT_EMPTY = 3005;
    public static final int MSG_CONTENT_SENSITIVE = 3006;
    //群聊相关
    public static final int ERROR_NO_INVITE_GROUP_MEMBER_PERMISSION = 4001;
    public static final int ERROR_INVITE_GROUP_MEMBER_EXCEED_MAXIMUM = 4002;
}
