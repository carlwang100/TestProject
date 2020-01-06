package com.sunland.new_im.websocket.packet;

import com.sunland.new_im.websocket.packet.base.ImBaseResponsePacket;

import java.util.Map;

/**
 * Created by kai on 2018/11/12
 * Email：kaihu1989@gmail.com
 * Feature:
 */
public class ImModifyGroupInfoResPacket extends ImBaseResponsePacket {

    private ModifyGroupInfoRes ModifyGroupInfoRes;

    public ImModifyGroupInfoResPacket.ModifyGroupInfoRes getModifyGroupInfoRes() {
        return ModifyGroupInfoRes;
    }

    /**
     * fromUserId : 类型:Number 说明:会话id
     * fromUserName : 类型:Number 说明:会话类型。
     * groupId : 类型:String 说明:群名称
     * modifyTime : 类型:Number 说明:修改时间
     * resultCode : 类型:Number 说明:请求结果。 0 代表成功； 非0代表失败，具体意义参考后台定义
     * modifyInfo :修改信息：是一个 修改类型---> 修改后的结果(string)的map。 样例:modifyInfo:{"1":"newTitle"}
     */
    public static class ModifyGroupInfoRes{

        private long fromUserId;
        private String fromUserName;
        private long groupId;
        private long modifyTime;
        private int resultCode;
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

        public long getModifyTime() {
            return modifyTime;
        }

        public int getResultCode() {
            return resultCode;
        }

        public Map<String, String> getModifyInfo() {
            return modifyInfo;
        }
    }
}
