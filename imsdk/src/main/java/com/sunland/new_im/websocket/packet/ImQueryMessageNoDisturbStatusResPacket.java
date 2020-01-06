package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by fengd on 2018/12/4.
 */

public class ImQueryMessageNoDisturbStatusResPacket extends ImBaseResponsePacket {

    /**
     * cid : 类型:Number 说明:8004
     * QueryMessageNoDisturbStatusRes : {"resultCode":"类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义","reason":"类型:String 说明:结果描述","noDisturb":"类型:Number 说明:1、设置了免打扰  0、未设置免打扰"}
     */

    @SerializedName("QueryMessageNoDisturbStatusRes")
    private QueryMessageNoDisturbStatusResBean QueryMessageNoDisturbStatusRes;

    public QueryMessageNoDisturbStatusResBean getQueryMessageNoDisturbStatusRes() {
        return QueryMessageNoDisturbStatusRes;
    }

    public void setQueryMessageNoDisturbStatusRes(QueryMessageNoDisturbStatusResBean QueryMessageNoDisturbStatusRes) {
        this.QueryMessageNoDisturbStatusRes = QueryMessageNoDisturbStatusRes;
    }

    public static class QueryMessageNoDisturbStatusResBean {
        /**
         * resultCode : 类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义
         * reason : 类型:String 说明:结果描述
         * noDisturb : 类型:Number 说明:1、设置了免打扰  0、未设置免打扰
         */

        @SerializedName("resultCode")
        private int resultCode;
        @SerializedName("reason")
        private String reason;
        @SerializedName("noDisturb")
        private int noDisturb;

        public int getResultCode() {
            return resultCode;
        }

        public String getReason() {
            return reason;
        }

        public int getNoDisturb() {
            return noDisturb;
        }

    }
}
