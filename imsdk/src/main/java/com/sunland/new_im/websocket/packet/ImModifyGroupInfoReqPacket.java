package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseRequestPacket;

import java.util.Map;

/**
 * Created by kai on 2018/11/12
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImModifyGroupInfoReqPacket extends ImBaseRequestPacket {
    /**
     * ModifyGroupInfoReq : {"fromUserName":"类型:String 说明:修改者群昵称","groupId":"类型:Number 说明:群聊会话id","modifyInfo":{"修改信息类型":{"打算修改成的群名称":"类型:String 说明:打算修改成的群名称"}}}
     */

    private ModifyGroupInfoReqBean ModifyGroupInfoReq;

    public ImModifyGroupInfoReqPacket(EntryBean entry,ModifyGroupInfoReqBean ModifyGroupInfoReq) {
        super(entry);
        this.ModifyGroupInfoReq = ModifyGroupInfoReq;
    }


    public static class ModifyGroupInfoReqBean {
        /**
         * fromUserName : 类型:String 说明:修改者群昵称
         * groupId : 类型:Number 说明:群聊会话id
         * modifyInfo : [{"1":"new name"},{"2":"other"}]
         */

        private String fromUserName;
        private long groupId;
        private Map<Integer, String> modifyInfo;

        public void setFromUserName(String fromUserName) {
            this.fromUserName = fromUserName;
        }

        public void setGroupId(long groupId) {
            this.groupId = groupId;
        }

        public void setModifyInfo(Map<Integer, String> modifyInfo) {
            this.modifyInfo = modifyInfo;
        }
    }
}
