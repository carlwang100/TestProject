package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by fengd on 2018/12/4.
 */

public class ImSetMessageNoDisturbStatusResPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:8002
     * SetMessageNoDisturbStatusRes : {"resultCode":"类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义","reason":"类型:String 说明:结果描述"}
     */

    @SerializedName("SetMessageNoDisturbStatusRes")
    private SetMessageNoDisturbStatusResBean SetMessageNoDisturbStatusRes;

    public SetMessageNoDisturbStatusResBean getSetMessageNoDisturbStatusRes() {
        return SetMessageNoDisturbStatusRes;
    }

    public void setSetMessageNoDisturbStatusRes(SetMessageNoDisturbStatusResBean SetMessageNoDisturbStatusRes) {
        this.SetMessageNoDisturbStatusRes = SetMessageNoDisturbStatusRes;
    }

    public static class SetMessageNoDisturbStatusResBean {
        /**
         * resultCode : 类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义
         * reason : 类型:String 说明:结果描述
         */

        @SerializedName("resultCode")
        private int resultCode;
        @SerializedName("reason")
        private String reason;

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
