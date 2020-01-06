package com.sunland.new_im.websocket.packet;

/**
 * Created by kai on 2018/9/4
 * Email：kaihu1989@gmail.com
 * Feature: IM的result code
 */
public class ImResultCode {
    public static final int SvcReqRC_SUCCESS = 0;
    public static final int SvcReqRC_FAILED = 1;
    public static final int SvcReqRC_UNKNOWN_ERR = -1;
    public static final int SvcReqRC_UNKNOWN_CID = 2001;
    public static final int SvcReqRC_EXCEPTION = 2002;
    public static final int SvcReqRC_UNKNOWN_EXCEPTION = 2003;
    public static final int SvcReqRC_CONVTOTARS_FAIL = 2004;
    public static final int SvcReqRC_CONVTOSTR_FAIL = 2005;
    public static final int SvcReqRC_UNKNOWN_CLSNAME = 2006;
    public static final int SvcReqRC_UNKNOWN_PUSHPLATFORM = 2007;
    public static final int SvcReqRC_CURLPERFORM_FAIL = 2008;
    public static final int SvcReqRC_GET_RELATION_ERROR = 3001;
    public static final int SvcReqRC_SINGLE_SHIELD_BY_PEER = 3002;
    public static final int SvcReqRC_MSG_TYPE_ERROR = 3003;
    public static final int SvcReqRC_CLIENT_TYPE_ERROR = 3004;
    public static final int SvcReqRC_MSG_CONTENT_EMPTY = 3005;
    public static final int SvcReqRC_MSG_CONTENT_SENSITIVE = 3006;
}
