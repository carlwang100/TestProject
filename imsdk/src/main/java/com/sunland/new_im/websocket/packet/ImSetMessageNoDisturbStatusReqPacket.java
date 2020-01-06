package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by fengd on 2018/12/4.
 */

public class ImSetMessageNoDisturbStatusReqPacket extends ImBaseRequestPacket {

    public static final int IS_NO_DISTURB = 1;
    public static final int IS_DISTURBED = 0;

    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:8001","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配。","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * SetMessageNoDisturbStatusReq : {"noDisturb":"类型:Number 说明:1、设置免打扰  0、取消免打扰"}
     */

    @SerializedName("SetMessageNoDisturbStatusReq")
    private SetMessageNoDisturbStatusReq SetMessageNoDisturbStatusReq;

    public ImSetMessageNoDisturbStatusReqPacket(EntryBean entry, SetMessageNoDisturbStatusReq statusReq) {
        super(entry);
        this.SetMessageNoDisturbStatusReq = statusReq;
    }

    public static class SetMessageNoDisturbStatusReq {
        /**
         * noDisturb : 类型:Number 说明:1、设置免打扰  0、取消免打扰
         */

        @SerializedName("noDisturb")
        private int noDisturb;

        public int getNoDisturb() {
            return noDisturb;
        }

        public void setNoDisturb(int noDisturb) {
            this.noDisturb = noDisturb;
        }
    }
}
