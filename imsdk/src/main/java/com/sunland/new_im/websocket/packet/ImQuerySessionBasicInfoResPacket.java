package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CID7026 - 获取会话基本信息（可拉取通讯录中的群聊会话）
 */
//{
//        "cid": "类型:Number 说明:7026",
//        "uniqueKey": "类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配。",
//        "QuerySessionBasicInfoRes": {
//        "sessionBasicInfoMap": {
//        "具体的sessionId": {
//        "sessionId": "类型:Number 说明:会话id",
//        "sessionType": "类型:Number 说明:会话类型。1：单聊，2：群聊",
//        "sessionName": "类型:String 说明:会话名称",
//        "sessionImageUrl": "类型:String 说明:会话头像url",
//        "status": "类型:Number 说明:单聊(1.对方在职  2.对方离职); 群聊(1:正常, 其他暂时未用)",
//        "preMsgId": "类型:Number 说明:群聊使用(表示查询者加群时的前一条msgId，比如A加群时的msgId=10， A所能收到和查询的msgId > 10)",
//        "unReadAtMsgIdList": "类型:Array[Number] 说明:（群聊使用）未读的@自己了的msgid列表（如果有未读@消息的话，有该字段。最好是先检测是否有该字段，再检测该字段是否为空）"
//        },
//        "下一个sessionId数值": {}
//        },
//        "resultCode": "类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义"
//        }
//}
public class ImQuerySessionBasicInfoResPacket extends ImBaseResponsePacket {


    @SerializedName("QuerySessionBasicInfoRes")
    private QuerySessionBasicInfoResBean QuerySessionBasicInfoRes;


    public QuerySessionBasicInfoResBean getQuerySessionBasicInfoRes() {
        return QuerySessionBasicInfoRes;
    }

    public void setQuerySessionBasicInfoRes(QuerySessionBasicInfoResBean QuerySessionBasicInfoRes) {
        this.QuerySessionBasicInfoRes = QuerySessionBasicInfoRes;
    }

    public static class QuerySessionBasicInfoResBean {
        /**
         * sessionBasicInfoMap : {"1111111111111":{"sessionId":11111111111,"sessionType":1,"sessionName":"类型:String 说明:会话名称","sessionImageUrl":"类型:String 说明:会话头像url","status":1,"preMsgId":1,"unReadAtMsgIdList":[1,2,3,4]}}
         * resultCode : 0
         */

        @SerializedName("sessionBasicInfoMap")
        private Map<Long, SessionBasicInfoMapBean> sessionBasicInfoMap = new HashMap<>(0);
        @SerializedName("resultCode")
        private int resultCode;

        public Map<Long, SessionBasicInfoMapBean> getSessionBasicInfoMap() {
            return sessionBasicInfoMap;
        }

        public void setSessionBasicInfoMap(Map<Long, SessionBasicInfoMapBean> sessionBasicInfoMap) {
            this.sessionBasicInfoMap = sessionBasicInfoMap;
        }

        public int getResultCode() {
            return resultCode;
        }

        public void setResultCode(int resultCode) {
            this.resultCode = resultCode;
        }

        public static class SessionBasicInfoMapBean {
            /**
             * sessionId : 11111111111
             * sessionType : 1
             * sessionName : 类型:String 说明:会话名称
             * sessionImageUrl : 类型:String 说明:会话头像url
             * status : 1
             * preMsgId : 1
             * unReadAtMsgIdList : [1,2,3,4]
             * groupType: 类型Number 说明：群类型是否为部门群(1：部门群， 0： 普通群)
             */

            @SerializedName("sessionId")
            private long sessionId;
            @SerializedName("sessionType")
            private int sessionType;
            @SerializedName("sessionName")
            private String sessionName;
            @SerializedName("sessionImageUrl")
            private String sessionImageUrl;
            @SerializedName("status")
            private int status;
            @SerializedName("preMsgId")
            private int preMsgId;
            @SerializedName("unReadAtMsgIdList")
            private List<Integer> unReadAtMsgIdList = new ArrayList<>();
            @SerializedName("groupType")
            private int groupType;

            public long getSessionId() {
                return sessionId;
            }

            public void setSessionId(long sessionId) {
                this.sessionId = sessionId;
            }

            public int getSessionType() {
                return sessionType;
            }

            public void setSessionType(int sessionType) {
                this.sessionType = sessionType;
            }

            public String getSessionName() {
                return sessionName;
            }

            public void setSessionName(String sessionName) {
                this.sessionName = sessionName;
            }

            public String getSessionImageUrl() {
                return sessionImageUrl;
            }

            public void setSessionImageUrl(String sessionImageUrl) {
                this.sessionImageUrl = sessionImageUrl;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getPreMsgId() {
                return preMsgId;
            }

            public void setPreMsgId(int preMsgId) {
                this.preMsgId = preMsgId;
            }

            public List<Integer> getUnReadAtMsgIdList() {
                return unReadAtMsgIdList;
            }

            public void setUnReadAtMsgIdList(List<Integer> unReadAtMsgIdList) {
                this.unReadAtMsgIdList = unReadAtMsgIdList;
            }

            public int getGroupType() {
                return groupType;
            }

            public void setGroupType(int groupType) {
                this.groupType = groupType;
            }
        }
    }
}
