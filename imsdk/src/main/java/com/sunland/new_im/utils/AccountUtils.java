package com.sunland.new_im.utils;

import android.content.Context;
import android.text.TextUtils;

/**
 * Preference.
 */
public class AccountUtils {

    private static Long RANDOM_TIME = System.currentTimeMillis();

    public static long getEhrIMId(Context context) {
        //todo 名称可以动态去配置
        PreferenceUtil preferenceUtil = PreferenceUtil.getInstance(context);
        long ehrImId = preferenceUtil.getLong("ehrImId", 0L);
        return ehrImId;
    }

    public static String getUserName(Context context) {
        PreferenceUtil preferenceUtil = PreferenceUtil.getInstance(context);
        //todo 名称可以动态去配置
        return preferenceUtil.getString("userName", "");
    }

    public static String getUserId(Context context) {
        PreferenceUtil preferenceUtil = PreferenceUtil.getInstance(context);
        String userId = preferenceUtil.getString("userId", "");

        if (!TextUtils.isEmpty(userId)) return userId;
        return getVisitId(context);
    }

    public static String getVisitId(Context context) {
        PreferenceUtil preferenceUtil = PreferenceUtil.getInstance(context);
        return preferenceUtil.getString("visitId", "");
    }

    public static String getAccountAvatarByUserId(String userId) {
        return NET_SUNLAND_USER_AVATAR_DOMAIN_DEBUG + userId + "/" + userId + ".jpg" + "?" +
                RANDOM_TIME;
    }

    //todo  需要统一到工具类
    private static final String NET_SUNLAND_USER_AVATAR_DOMAIN_DEBUG = "http://static.sunlands.com/user_center_qiye_app/newUserImagePath/";
}
