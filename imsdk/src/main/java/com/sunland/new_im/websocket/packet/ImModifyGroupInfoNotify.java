package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.Map;

/**
 * Created by kai on 2018/11/12
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImModifyGroupInfoNotify extends ImBaseResponsePacket {

    private ModifyGroupInfoNotify ModifyGroupInfoNotify;

    public ImModifyGroupInfoNotify.ModifyGroupInfoNotify getModifyGroupInfoNotify() {
        return ModifyGroupInfoNotify;
    }

    public static class ModifyGroupInfoNotify{
        private long fromUserId;
        private String fromUserName;
        private long groupId;
        private Map<String, String> modifyInfo;

        public long getFromUserId() {
            return fromUserId;
        }

        public String getFromUserName() {
            return fromUserName;
        }

        public long getGroupId() {
            return groupId;
        }

        public Map<String, String> getModifyInfo() {
            return modifyInfo;
        }
    }
}
