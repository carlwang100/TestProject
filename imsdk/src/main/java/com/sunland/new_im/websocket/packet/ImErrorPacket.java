package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by kai on 2018/8/21
 * Email：kaihu1989@gmail.com
 * Feature: 当客户端请求服务发生错误时，根据错误类型进行相应的返回
 */
public class ImErrorPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:请求时entry里的cid
     * Error : {"imid":"类型:Number 说明:请求时entry里携带的imid","resultCode":"类型:Number 说明:返回的错误码：1002：协议错误，1004：内部错误，1006：请求被拒绝","reason":"类型:String 说明:错误描述"}
     */
    private ErrorBean Error;

    public ErrorBean getError() {
        return Error;
    }

    public static class ErrorBean {
        /**
         * imid : 类型:Number 说明:请求时entry里携带的imid
         * resultCode : 类型:Number 说明:返回的错误码：1002：协议错误，1004：内部错误，1006：请求被拒绝
         * reason : 类型:String 说明:错误描述
         */

        private long imid;
        private int resultCode;
        private String reason;
        private int cid;

        public long getImid() {
            return imid;
        }

        public int getResultCode() {
            return resultCode;
        }

        public String getReason() {
            return reason;
        }

        public int getCid() {
            return cid;
        }
    }
}
