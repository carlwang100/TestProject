package com.sunland.new_im.websocket;

public class NetConstant {
    /**新IM websocket登录 appid*/
    private static final String IM_APPID_TEST = "kdldldldld";
    private static final String IM_APPID_RELEASE = "100450246dfb2a60d9ee97350ee8108949a";
    public static String getImAppId(){
        //todo 需要区分测试和正式环境
        if (false){
            return IM_APPID_RELEASE;
        }else {
            return IM_APPID_TEST;
        }
    }
}
