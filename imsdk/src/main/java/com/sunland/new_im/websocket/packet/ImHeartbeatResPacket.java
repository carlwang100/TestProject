package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

/**
 * Created by kai on 2018/8/20
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImHeartbeatResPacket extends ImBaseResponsePacket {
    /**
     * cid : 类型:Number 说明:3001
     * ClientHeartBeat : {"info":"类型:String 说明:按客户设置返回"}
     */

    private ClientHeartBeatBean ClientHeartBeat;

    public ClientHeartBeatBean getClientHeartBeat() {
        return ClientHeartBeat;
    }

    public void setClientHeartBeat(ClientHeartBeatBean ClientHeartBeat) {
        this.ClientHeartBeat = ClientHeartBeat;
    }


    public static class ClientHeartBeatBean {
        /**
         * info : 类型:String 说明:按客户设置返回
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
