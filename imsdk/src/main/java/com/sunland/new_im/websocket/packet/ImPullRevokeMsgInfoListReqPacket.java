package com.sunland.new_im.websocket.packet;

import com.google.gson.annotations.SerializedName;
import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * CID4019 - 拉取撤回消息记录
 */
public class ImPullRevokeMsgInfoListReqPacket extends ImBaseRequestPacket {

    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:4019","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * PullRevokeMsgInfoListReq : {"clientLatestAccessTime":"类型:Number 说明:add by liguanghui on 2019-02-20，单位是ms。客户端跟服务端交互的最新的服务器时间。简单的话，可以是客户端缓存的所有会话的最新一条消息时间。目前客户端和服务端的心跳没有服务端时间戳，如果加上时间戳的话，可以是max(msgTime,heartBeatTime).       // 1）在线状态，刷新会话列表；2）WEB端或者客户端没有任何会话：不传 clientLatestAccessTime 或者 设置为0.          // 返回的撤回记录：撤回的原本消息是在clientLatestAccessTime以前的消息，撤回动作是在clientLatestAccessTime以后。"}
     */
    @SerializedName("PullRevokeMsgInfoListReq")
    private PullRevokeMsgInfoListReqBean PullRevokeMsgInfoListReq;

    public ImPullRevokeMsgInfoListReqPacket(EntryBean entry, PullRevokeMsgInfoListReqBean reqBean) {
        super(entry);
        PullRevokeMsgInfoListReq = reqBean;
    }

    public PullRevokeMsgInfoListReqBean getPullRevokeMsgInfoListReq() {
        return PullRevokeMsgInfoListReq;
    }

    public void setPullRevokeMsgInfoListReq(PullRevokeMsgInfoListReqBean PullRevokeMsgInfoListReq) {
        this.PullRevokeMsgInfoListReq = PullRevokeMsgInfoListReq;
    }

    public static class PullRevokeMsgInfoListReqBean {
        /**
         * clientLatestAccessTime : 类型:Number 说明:add by liguanghui on 2019-02-20，单位是ms。客户端跟服务端交互的最新的服务器时间。简单的话，可以是客户端缓存的所有会话的最新一条消息时间。目前客户端和服务端的心跳没有服务端时间戳，如果加上时间戳的话，可以是max(msgTime,heartBeatTime).       // 1）在线状态，刷新会话列表；2）WEB端或者客户端没有任何会话：不传 clientLatestAccessTime 或者 设置为0.          // 返回的撤回记录：撤回的原本消息是在clientLatestAccessTime以前的消息，撤回动作是在clientLatestAccessTime以后。
         */

        @SerializedName("clientLatestAccessTime")
        private long clientLatestAccessTime;

        public long getClientLatestAccessTime() {
            return clientLatestAccessTime;
        }

        public void setClientLatestAccessTime(long clientLatestAccessTime) {
            this.clientLatestAccessTime = clientLatestAccessTime;
        }
    }
}
