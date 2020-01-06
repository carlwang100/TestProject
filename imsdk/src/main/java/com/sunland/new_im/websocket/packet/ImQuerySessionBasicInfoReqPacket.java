package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

import java.util.ArrayList;
import java.util.List;

/**
 * CID7025 - 获取会话基本信息（可拉取通讯录中的群聊会话）
 */

public class ImQuerySessionBasicInfoReqPacket extends ImBaseRequestPacket {
    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:7025","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配。","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * QuerySessionBasicInfoReq : {"singleChatList":"类型:Array[Number] 说明:单聊会话id列表（1、传输列表时，会拉取列表内的人员会话信息（如果单纯想拉取头像和名字，此信息有些许冗余），2、列表内仅包含一个0，表示获取跟imid聊过的所有单聊会话）","groupChatList":"类型:Array[Number] 说明:群聊会话id列表（1、传输列表时，会拉取列表内imid当前所在的群组信息，2、列表内仅包含一个0，表示获取imid当前所在的所有群组信息）","bGetUnReadGroupAtMsgIdList":"类型:Boolean 说明:False， 不获取群聊中的未读@消息id列表； True获取群聊中的未读@消息id列表。默认不填为False"}
     */

    @SerializedName("QuerySessionBasicInfoReq")
    private QuerySessionBasicInfoReqBean QuerySessionBasicInfoReq;

    public ImQuerySessionBasicInfoReqPacket(EntryBean entry, QuerySessionBasicInfoReqBean reqBean) {
        super(entry);
        this.QuerySessionBasicInfoReq = reqBean;
    }

    public QuerySessionBasicInfoReqBean getQuerySessionBasicInfoReq() {
        return QuerySessionBasicInfoReq;
    }

    public void setQuerySessionBasicInfoReq(QuerySessionBasicInfoReqBean QuerySessionBasicInfoReq) {
        this.QuerySessionBasicInfoReq = QuerySessionBasicInfoReq;
    }

    public static class QuerySessionBasicInfoReqBean {
        /**
         * singleChatList : 类型:Array[Number] 说明:单聊会话id列表（1、传输列表时，会拉取列表内的人员会话信息（如果单纯想拉取头像和名字，此信息有些许冗余），2、列表内仅包含一个0，表示获取跟imid聊过的所有单聊会话）
         * groupChatList : 类型:Array[Number] 说明:群聊会话id列表（1、传输列表时，会拉取列表内imid当前所在的群组信息，2、列表内仅包含一个0，表示获取imid当前所在的所有群组信息）
         * bGetUnReadGroupAtMsgIdList : 类型:Boolean 说明:False， 不获取群聊中的未读@消息id列表； True获取群聊中的未读@消息id列表。默认不填为False
         */

        @SerializedName("singleChatList")
        private List<Long> singleChatList = new ArrayList<>(0);
        @SerializedName("groupChatList")
        private List<Long> groupChatList = new ArrayList<>(0);
        @SerializedName("bGetUnReadGroupAtMsgIdList")
        private boolean bGetUnReadGroupAtMsgIdList;

        public List<Long> getSingleChatList() {
            return singleChatList;
        }

        public void setSingleChatList(List<Long> singleChatList) {
            this.singleChatList = singleChatList;
        }

        public List<Long> getGroupChatList() {
            return groupChatList;
        }

        public void setGroupChatList(List<Long> groupChatList) {
            this.groupChatList = groupChatList;
        }

        public boolean isBGetUnReadGroupAtMsgIdList() {
            return bGetUnReadGroupAtMsgIdList;
        }

        public void setBGetUnReadGroupAtMsgIdList(boolean bGetUnReadGroupAtMsgIdList) {
            this.bGetUnReadGroupAtMsgIdList = bGetUnReadGroupAtMsgIdList;
        }
    }
}
