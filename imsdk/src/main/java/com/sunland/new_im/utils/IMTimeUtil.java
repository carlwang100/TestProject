package com.sunland.new_im.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by fengd on 2018/8/28.
 */

public class IMTimeUtil {

    long now;
    int thisYear;
    int thisMonth;
    int today;
    int dayOfWeekNow;

    int yesterdayYear;
    int yesterdayDayOfYear;

    long firstDayOfThisWeekBegin;

    private static final SimpleDateFormat SDF_HHMM = new SimpleDateFormat("HH:mm");

    private static final SimpleDateFormat SDF_WEEK = new SimpleDateFormat("EEEE");

    private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("yyyy-MM-dd");

    private static final String YESTERDAY = "昨天";
    private static final String PM = "下午";
    private static final String AM = "上午";

    public IMTimeUtil() {
        now = System.currentTimeMillis();

        Calendar calNow = Calendar.getInstance();
        calNow.setTimeInMillis(now);
        thisYear = calNow.get(Calendar.YEAR);
        thisMonth = calNow.get(Calendar.MONTH);
        today = calNow.get(Calendar.DAY_OF_YEAR);
        dayOfWeekNow = calNow.get(Calendar.DAY_OF_WEEK);

        // to yesterday
        calNow.add(Calendar.DATE, -1);

        yesterdayYear = calNow.get(Calendar.YEAR);
        yesterdayDayOfYear = calNow.get(Calendar.DAY_OF_YEAR);

        // to first day of this week
        calNow.add(Calendar.DATE, -1 * dayOfWeekNow + 2);

        calNow.set(Calendar.HOUR_OF_DAY, 0);
        calNow.set(Calendar.MINUTE, 0);
        calNow.set(Calendar.SECOND, 0);
        calNow.set(Calendar.MILLISECOND, 0);

        firstDayOfThisWeekBegin = calNow.getTimeInMillis();

        // to first day of last week
        calNow.add(Calendar.DATE, -7);
    }

    public String getSessionListDisplayDate(long time) {

        String dateText = "";

        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(time);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_YEAR);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        // first letter is used for sort
        if (year == thisYear && day == today) {
            String timeSuffix = SDF_HHMM.format(new Date(time));

            dateText = /*getAmPm(hour) + " " +*/ timeSuffix;

        } else if (year == yesterdayYear && day == yesterdayDayOfYear) {
            dateText = YESTERDAY;
        } else if (time <= now && time >= firstDayOfThisWeekBegin) {
            dateText = SDF_WEEK.format(new Date(time));
        } else {
            // branch
            String monthText = SDF_DATE.format(time);
            dateText = monthText;
        }

        return dateText;
    }

    public static String getAmPm(int hour){
        if (hour >= 0 && hour < 13) {
            return AM;
        }else {
            return PM;
        }
    }

    public String getTimeLineContent(long time) {
        // TODO: 时间线显示内容未完成
        // 当天消息   hh:mm
        // 24h外,48h内消息    昨天hh:mm（最高优先级，当昨天是上周天则优先显示为昨天）
        // 昨天之前本周以内    星期 {week}HH:MM
        // 本周之前消息     {data} HH:MM

        String dateText = "";

        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(time);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_YEAR);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        // first letter is used for sort
        String timeSuffix = SDF_HHMM.format(new Date(time));
        if (year == thisYear && day == today) {

            dateText = /*getAmPm(hour) + " " +*/ timeSuffix;

        } else if (year == yesterdayYear && day == yesterdayDayOfYear) {
            dateText = YESTERDAY + " " + timeSuffix;
        } else if (time <= now && time >= firstDayOfThisWeekBegin) {
            dateText = SDF_WEEK.format(new Date(time)) + " " + timeSuffix;
        } else {
            // branch
            String monthText = SDF_DATE.format(time) + " " + timeSuffix;
            dateText = monthText;
        }

        return dateText;

    }

}
