package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by fengd on 2018/9/13.
 */

public class ImUpdateUserDeviceTokenReqPacket extends ImBaseRequestPacket {

    /**
     * UpdateUserDeviceTokenReq : {"pushPlatform":"类型:Number 说明:注册deviceToken的第三方推送服务提供商在IM的编号，1(信鸽)，2(小米)","deviceToken":"类型:String 说明:deviceToken为空，表示删除该设备下的deviceToken，此时无需指定pushPlatform；不为空表设置操作"}
     */

    @SerializedName("UpdateUserDeviceTokenReq")
    private UpdateUserDeviceTokenReqBean UpdateUserDeviceTokenReq;

    public ImUpdateUserDeviceTokenReqPacket(ImBaseRequestPacket.EntryBean entryBean, UpdateUserDeviceTokenReqBean updateUserDeviceTokenReqBean) {
        super(entryBean);
        this.UpdateUserDeviceTokenReq = updateUserDeviceTokenReqBean;
    }

    public UpdateUserDeviceTokenReqBean getUpdateUserDeviceTokenReq() {
        return UpdateUserDeviceTokenReq;
    }

    public void setUpdateUserDeviceTokenReq(UpdateUserDeviceTokenReqBean UpdateUserDeviceTokenReq) {
        this.UpdateUserDeviceTokenReq = UpdateUserDeviceTokenReq;
    }

    public static class UpdateUserDeviceTokenReqBean {
        /**
         * pushPlatform : 类型:Number 说明:注册deviceToken的第三方推送服务提供商在IM的编号，1(信鸽)，2(小米)
         * deviceToken : 类型:String 说明:deviceToken为空，表示删除该设备下的deviceToken，此时无需指定pushPlatform；不为空表设置操作
         */

        @SerializedName("pushPlatform")
        private int pushPlatform;
        @SerializedName("deviceToken")
        private String deviceToken;

        public int getPushPlatform() {
            return pushPlatform;
        }

        public void setPushPlatform(int pushPlatform) {
            this.pushPlatform = pushPlatform;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }
    }
}
