package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by fengd on 2018/9/13.
 */

public class ImUpdateUserDeviceTokenResPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:1012
     * UpdateUserDeviceTokenRes : {"resultCode":"类型:Number 说明:注册请求结果. 0 代表成功； 非0代表失败，具体意义参考后台定义.","reason":"类型:String 说明:返回结果解释","imid":"类型:Number 说明:用户imid","clientType":"类型:Number 说明:ANDROID:1,  IOS:2"}
     */

    @SerializedName("UpdateUserDeviceTokenRes")
    private UpdateUserDeviceTokenResBean UpdateUserDeviceTokenRes;

    public UpdateUserDeviceTokenResBean getUpdateUserDeviceTokenRes() {
        return UpdateUserDeviceTokenRes;
    }

    public void setUpdateUserDeviceTokenRes(UpdateUserDeviceTokenResBean UpdateUserDeviceTokenRes) {
        this.UpdateUserDeviceTokenRes = UpdateUserDeviceTokenRes;
    }

    public static class UpdateUserDeviceTokenResBean {
        /**
         * resultCode : 类型:Number 说明:注册请求结果. 0 代表成功； 非0代表失败，具体意义参考后台定义.
         * reason : 类型:String 说明:返回结果解释
         * imid : 类型:Number 说明:用户imid
         * clientType : 类型:Number 说明:ANDROID:1,  IOS:2
         */

        @SerializedName("resultCode")
        private int resultCode;
        @SerializedName("reason")
        private String reason;
        @SerializedName("imid")
        private long imid;
        @SerializedName("clientType")
        private int clientType;

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

        public long getImid() {
            return imid;
        }

        public void setImid(int imid) {
            this.imid = imid;
        }

        public int getClientType() {
            return clientType;
        }

        public void setClientType(int clientType) {
            this.clientType = clientType;
        }
    }
}
