package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by kai on 2018/8/28
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImPullRecentSessionListReqPacket extends ImBaseRequestPacket {
    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:4017","uniqueKey":"类型:String 说明:消息发送唯一标识， 由客户端设置。用于回复消息匹配","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * PullRecentSessionListReq : {"pullSessionNum":"类型:Number 说明:拉取最近会话条数。>0表示拉取会话条数，0表示拉取所有会话，<0 失败"}
     */

    private PullRecentSessionListReqBean PullRecentSessionListReq;

    public ImPullRecentSessionListReqPacket(EntryBean entry, PullRecentSessionListReqBean PullRecentSessionListReq) {
        super(entry);
        this.PullRecentSessionListReq = PullRecentSessionListReq;
    }

    public static class PullRecentSessionListReqBean{
        private int pullSessionNum;

        public void setPullSessionNum(int pullSessionNum) {
            this.pullSessionNum = pullSessionNum;
        }
    }
}
