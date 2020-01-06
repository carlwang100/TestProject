package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

/**
 * Created by kai on 2018/8/20
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImHeartbeatPacket extends ImBaseRequestPacket {
    /**
     * Entry : {"imid":"类型:Number","cid":"类型:Number 说明:3001","clientType":"类型:Number 说明:1:ANDROID，2:IOS，3:WEB"}
     * ClientHeartBeat : {"info":"类型:String"}
     */

    private ClientHeartBeatBean ClientHeartBeat;

    public ImHeartbeatPacket(EntryBean entry, ClientHeartBeatBean ClientHeartBeat) {
        super(entry);
        this.ClientHeartBeat = ClientHeartBeat;
    }


    public ClientHeartBeatBean getClientHeartBeat() {
        return ClientHeartBeat;
    }

    public void setClientHeartBeat(ClientHeartBeatBean ClientHeartBeat) {
        this.ClientHeartBeat = ClientHeartBeat;
    }

    public static class ClientHeartBeatBean {
        /**
         * info : 类型:String
         */

        private String info;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
