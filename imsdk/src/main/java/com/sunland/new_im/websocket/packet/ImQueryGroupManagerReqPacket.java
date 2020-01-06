package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

import java.util.List;

/**
 * CID7053 - 拉取群管理人员信息
 */
public class ImQueryGroupManagerReqPacket extends ImBaseRequestPacket {
    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:7053","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配。","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * QueryGroupManagerReq : {"groupIdList":"类型:Array[Number] 说明:要查询的群id列表"}
     */
    @SerializedName("QueryGroupManagerReq")
    private QueryGroupManagerReqBean QueryGroupManagerReq;

    public ImQueryGroupManagerReqPacket(EntryBean entry) {
        super(entry);
    }

    public QueryGroupManagerReqBean getQueryGroupManagerReq() {
        return QueryGroupManagerReq;
    }

    public void setQueryGroupManagerReq(QueryGroupManagerReqBean QueryGroupManagerReq) {
        this.QueryGroupManagerReq = QueryGroupManagerReq;
    }

    public static class QueryGroupManagerReqBean {
        /**
         * groupIdList : 类型:Array[Number] 说明:要查询的群id列表
         */

        @SerializedName("groupIdList")
        private List<Long> groupIdList;

        public List<Long> getGroupIdList() {
            return groupIdList;
        }

        public void setGroupIdList(List<Long> groupIdList) {
            this.groupIdList = groupIdList;
        }
    }
}
