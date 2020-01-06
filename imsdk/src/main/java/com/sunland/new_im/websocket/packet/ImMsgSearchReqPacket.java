package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by fengd on 2018/12/13.
 */

public class ImMsgSearchReqPacket extends ImBaseRequestPacket {


    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:1015","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * MsgSearchReq : {"keywords":"类型:String 说明:搜索关键词，非空","days":"类型:Number 说明:当前时间往前多少天的搜索范围，如：7：表示搜索7天内的聊天记录，包含第7天","searchType":"类型:Number 说明:0：默认0，全局搜索会话记录"}
     */


    @SerializedName("MsgSearchReq")
    private MsgSearchReqBean MsgSearchReq;

    public ImMsgSearchReqPacket(EntryBean entry, MsgSearchReqBean reqBean) {
        super(entry);
        this.MsgSearchReq = reqBean;
    }

    public MsgSearchReqBean getMsgSearchReq() {
        return MsgSearchReq;
    }

    public void setMsgSearchReq(MsgSearchReqBean MsgSearchReq) {
        this.MsgSearchReq = MsgSearchReq;
    }


    public static class MsgSearchReqBean {
        /**
         * keywords : 类型:String 说明:搜索关键词，非空
         * days : 类型:Number 说明:当前时间往前多少天的搜索范围，如：7：表示搜索7天内的聊天记录，包含第7天
         * searchType : 类型:Number 说明:0：默认0，全局搜索会话记录
         */

        @SerializedName("keywords")
        private String keywords;
        @SerializedName("days")
        private int days;
        @SerializedName("searchType")
        private int searchType = 1;

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public int getSearchType() {
            return searchType;
        }

        public void setSearchType(int searchType) {
            this.searchType = searchType;
        }
    }
}
