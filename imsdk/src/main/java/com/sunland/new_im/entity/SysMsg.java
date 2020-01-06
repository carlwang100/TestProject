package com.sunland.new_im.entity;

import android.text.Html;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengd on 2019/1/21.
 */

public class SysMsg {

    /**
     * pattern : {name}发起了群聊
     * params : [{"imid":"1002000000002539","name":"陈玉莹"}]
     * systemMsgSubType : 1
     * supportClick : 0
     */

//    {
//        "pattern": "类型:String 说明:如： {name}邀请你和{name}加入群聊 ",
//            "params": [
//        {
//            "imid": "类型:String 说明:imid",
//                "name": "类型:String 说明:imid对应的群昵称"
//        },
//        {
//            "more": "..."
//        }
//            ],
//        "systemMsgSubType": "类型:Number 说明:系统消息子类型。0表示IM内部出错，此时不返回params字段，pattern为IM提供的默认提示消息。 1表示建群，2表示已有群邀请新成员",
//            "supportClick": "类型:Number 说明:0：不支持可点击，1支持可点击。"
//    }

    @SerializedName("pattern")
    private String pattern;
    @SerializedName("systemMsgSubType")
    private int systemMsgSubType;
    @SerializedName("supportClick")
    private int supportClick;
    @SerializedName("params")
    private List<ParamsBean> params = new ArrayList<>(0);

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int getSystemMsgSubType() {
        return systemMsgSubType;
    }

    public void setSystemMsgSubType(int systemMsgSubType) {
        this.systemMsgSubType = systemMsgSubType;
    }

    public boolean getSupportClick() {
        return supportClick == 1;
    }

    public void setSupportClick(boolean supportClick) {
        this.supportClick = supportClick ? 1 : 0;
    }

    public List<ParamsBean> getParams() {
        return params;
    }

    public void setParams(List<ParamsBean> params) {
        this.params = params;
    }

    public static class ParamsBean {
        /**
         * imid : 1002000000002539
         * name : 陈玉莹
         */

        @SerializedName("imid")
        private String imid;
        @SerializedName("name")
        private String name;

        public String getImid() {
            return imid;
        }

        public void setImid(String imid) {
            this.imid = imid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getSessionDisplay() {
        String patCopy = pattern;
        if (params != null) {
            for (ParamsBean pb : params) {
                patCopy = patCopy.replaceFirst("\\{name\\}", pb.getName());
            }
        }
        return patCopy;
    }

    public CharSequence getChatListDisplay() {
        CharSequence charSequence = getSessionDisplay();
        if (getSupportClick()) {
            String patCopy = pattern;
            if (params != null) {
                for (ParamsBean pb : params) {
                    StringBuilder sb = new StringBuilder("<a href=\'").append(pb.getImid()).append("\'>").append(pb.getName()).append("</a>");
                    patCopy = patCopy.replaceFirst("\\{name\\}", sb.toString());
                }
                charSequence = Html.fromHtml(patCopy);
            }
        }
        return charSequence;
    }
}
