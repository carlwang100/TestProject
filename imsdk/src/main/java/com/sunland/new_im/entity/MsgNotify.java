package com.sunland.new_im.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fengd on 2018/11/30.
 */

public class MsgNotify {

    public static final int MSG_TYPE_WORK_NOTICE = 1;
    public static final int MSG_TYPE_SYSTEM_MESSAGE = 2;

    /**
     * "msgType":1,
     * UDMPushInfo : {"content":"你有一份公司的祝福等待你打开","extraInfo":"{\"advertPicUrl\":\"http://172.16.101.130:19911/mobile-web/images/123.jpg\",\"backContent\":\"HAPPY BIRTH DAY\",\"companyName\":\"尚德机构\",\"details\":\"今天是您的生日&#10;感谢您对尚德的付出&#10;再您生日的这一天&#10;送上我们最真诚的祝福&#10;祝您生日快乐&#10;工作顺心\",\"time\":\"2018.08.24\",\"userName\":\"唐利\"}","title":"生日快乐"}
     * msgData : {"content":"你有一份公司的祝福等待你打开","employeeId":47041,"extra":"{\"advertPicUrl\":\"http://172.16.101.130:19911/mobile-web/images/123.jpg\",\"backContent\":\"HAPPY BIRTH DAY\",\"companyName\":\"尚德机构\",\"details\":\"今天是您的生日&#10;感谢您对尚德的付出&#10;再您生日的这一天&#10;送上我们最真诚的祝福&#10;祝您生日快乐&#10;工作顺心\",\"time\":\"2018.08.24\",\"userName\":\"唐利\"}","link":"{\"android\":\"{\\\"android\\\":\\\"greetingCard\\\",\\\"ios\\\":\\\"greetingCard\\\"}\",\"ios\":\"{\\\"android\\\":\\\"greetingCard\\\",\\\"ios\\\":\\\"greetingCard\\\"}\"}","logoUrl":"","name":"生日快乐","title":"生日快乐","type":61}
     */

    @SerializedName("msgType")
    private int msgType;
    @SerializedName("UDMPushInfo")
    private UDMPushInfo UDMPushInfo;
    @SerializedName("msgData")
    private String msgData;

    public int getMsgType() {
        return msgType;
    }

    public UDMPushInfo getUDMPushInfo() {
        return UDMPushInfo;
    }

    public void setUDMPushInfo(UDMPushInfo UDMPushInfo) {
        this.UDMPushInfo = UDMPushInfo;
    }

    public String getMsgData() {
        return msgData;
    }

    public void setMsgData(String msgData) {
        this.msgData = msgData;
    }

    public static class UDMPushInfo {
        /**
         * content : 你有一份公司的祝福等待你打开
         * extraInfo : {"advertPicUrl":"http://172.16.101.130:19911/mobile-web/images/123.jpg","backContent":"HAPPY BIRTH DAY","companyName":"尚德机构","details":"今天是您的生日&#10;感谢您对尚德的付出&#10;再您生日的这一天&#10;送上我们最真诚的祝福&#10;祝您生日快乐&#10;工作顺心","time":"2018.08.24","userName":"唐利"}
         * title : 生日快乐
         */

        @SerializedName("content")
        private String content;
        @SerializedName("extraInfo")
        private String extraInfo;
        @SerializedName("title")
        private String title;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getExtraInfo() {
            return extraInfo;
        }

        public void setExtraInfo(String extraInfo) {
            this.extraInfo = extraInfo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
