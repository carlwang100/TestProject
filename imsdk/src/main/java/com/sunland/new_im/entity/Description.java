package com.sunland.new_im.entity;

import com.google.gson.annotations.SerializedName;

public class Description {

    /**
     * personWorkStatus : {"workStatus":"类型:Number 说明:0-默认状态，1-出差中  2-生病中  3-休假中  4-加班中","workStatusIconUrl":"类型:String 说明:状态图标url","workStatusInfoShow":"类型:String 说明:员工状态信息展示"}
     */

    @SerializedName("personWorkStatus")
    private PersonWorkStatusBean personWorkStatus;

    public PersonWorkStatusBean getPersonWorkStatus() {
        return personWorkStatus;
    }

    public static class PersonWorkStatusBean {
        /**
         * workStatus : 类型:Number 说明:0-默认状态，1-出差中  2-生病中  3-休假中  4-加班中
         * workStatusIconUrl : 类型:String 说明:状态图标url
         * workStatusInfoShow : 类型:String 说明:员工状态信息展示
         */

        @SerializedName("workStatus")
        private int workStatus;
        @SerializedName("workStatusIconUrl")
        private String workStatusIconUrl;
        @SerializedName("workStatusInfoShow")
        private String workStatusInfoShow;

        public int getWorkStatus() {
            return workStatus;
        }

        public void setWorkStatus(int workStatus) {
            this.workStatus = workStatus;
        }

        public String getWorkStatusIconUrl() {
            return workStatusIconUrl;
        }

        public void setWorkStatusIconUrl(String workStatusIconUrl) {
            this.workStatusIconUrl = workStatusIconUrl;
        }

        public String getWorkStatusInfoShow() {
            return workStatusInfoShow;
        }

        public void setWorkStatusInfoShow(String workStatusInfoShow) {
            this.workStatusInfoShow = workStatusInfoShow;
        }
    }
}
