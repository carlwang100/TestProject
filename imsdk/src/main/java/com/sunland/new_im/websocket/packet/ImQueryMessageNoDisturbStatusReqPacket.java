package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by fengd on 2018/12/4.
 */

public class ImQueryMessageNoDisturbStatusReqPacket extends ImBaseRequestPacket {
    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:8003","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配。","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * QueryMessageNoDisturbStatusReq : {"extralInfo":"类型:String 说明:备用字段，目前什么都不用传"}
     */

    @SerializedName("QueryMessageNoDisturbStatusReq")
    private QueryMessageNoDisturbStatusReqBean QueryMessageNoDisturbStatusReq;

    public ImQueryMessageNoDisturbStatusReqPacket(EntryBean entry, QueryMessageNoDisturbStatusReqBean statusReqBean) {
        super(entry);
        QueryMessageNoDisturbStatusReq = statusReqBean;
    }

    public QueryMessageNoDisturbStatusReqBean getQueryMessageNoDisturbStatusReq() {
        return QueryMessageNoDisturbStatusReq;
    }

    public void setQueryMessageNoDisturbStatusReq(QueryMessageNoDisturbStatusReqBean QueryMessageNoDisturbStatusReq) {
        this.QueryMessageNoDisturbStatusReq = QueryMessageNoDisturbStatusReq;
    }

    public static class QueryMessageNoDisturbStatusReqBean {
        /**
         * extralInfo : 类型:String 说明:备用字段，目前什么都不用传
         */

        @SerializedName("extralInfo")
        private String extralInfo;

        public String getExtralInfo() {
            return extralInfo;
        }

        public void setExtralInfo(String extralInfo) {
            this.extralInfo = extralInfo;
        }
    }
}
