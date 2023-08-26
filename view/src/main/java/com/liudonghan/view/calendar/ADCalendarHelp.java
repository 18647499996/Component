package com.liudonghan.view.calendar;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:8/24/23
 */
public class ADCalendarHelp {
    private static final String TAG = "Mac_Liu";

    private static volatile ADCalendarHelp instance = null;

    private ADCalendarHelp() {
    }

    public static ADCalendarHelp getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADCalendarHelp.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADCalendarHelp();
                }
            }
        }
        return instance;
    }

    /**
     * todo 获取日历列表（ 当前时间 ）
     * @return List<ADCalendarEntity>
     */
    public List<ADCalendarEntity> getCalendarList() {
        return getCalendarList(Calendar.getInstance().getTime());
    }

    /**
     * todo 获取日历列表
     *
     * @param date 时间日期
     * @return List<ADCalendarEntity>
     */
    public List<ADCalendarEntity> getCalendarList(Date date) {
        return getCalendarList(date, 24);
    }

    /**
     * todo 获取日历列表（ 自定义时间 ）
     *
     * @param d Date时间
     * @return List<ADCalendarEntity>
     */
    public List<ADCalendarEntity> getCalendarList(Date d, int count) {
        List<ADCalendarEntity> calendarList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        for (int i = 0; i < count; i++) {
            ADCalendarEntity calendarEntity = new ADCalendarEntity();
            List<ADCalendarEntity.Day> dayList = new ArrayList<>();
            // 年
            int year = calendar.get(Calendar.YEAR);
            // 月
            int month = calendar.get(Calendar.MONTH) + 1;
            // 日
            int date = calendar.get(Calendar.DATE);
            // todo 星期（ 当月第一天 ）
            int week = findDateByWeek(year, month, 1);
            // 最大天数（ 当月 ）
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            // 填补当月空白数据
            for (int j = 0; j < week; j++) {
                ADCalendarEntity.Day day = new ADCalendarEntity.Day();
                dayList.add(day);
            }
            // 获取天数（ 当月 ）
            for (int j = 1; j <= maxDay; j++) {
                ADCalendarEntity.Day day = new ADCalendarEntity.Day();
                day.setDay(j);
                day.setDescribe(year + "年" + month + "月" + formatDayDesc(j) + "日");
                day.setEnable(getCurrentYear() != year || (getCurrentMonth() != month || (j >= date)));
                day.setLunarMonth(ChineseLunarHelp.getInstance().getLunarMonth(year, month, j));
                day.setLunarDay(ChineseLunarHelp.getInstance().getLunarDay(year, month, j));
                day.setSolarTerm(ChineseLunarHelp.getInstance().getSolarTerms(year, month, j));
                day.setConstellation(ChineseLunarHelp.getInstance().getConstellation(month, j).split(":")[0]);
                day.setConstellationDate(ChineseLunarHelp.getInstance().getConstellation(month, j).split(":")[1]);
                day.setGregorianHoliday(ChineseLunarHelp.getInstance().getGregorianHoliday(month, j));
                day.setLunarHoliday(ChineseLunarHelp.getInstance().getLunarHoliday(year, month, j));
                day.setFitAvoid(ChineseLunarHelp.getInstance().getFitAvoid(year, month, j));
                day.setWeekDesc(formatWeekDesc(findDateByWeek(year, month, j)));
                day.setWeek(findDateByWeek(year, month, j));
                day.setTimeInMillis(getTimeInMillis(year,month,j));
                dayList.add(day);
            }

            calendarEntity.setYear(year);
            calendarEntity.setMonth(month);
            calendarEntity.setZodiac(ChineseLunarHelp.getInstance().getZodiac(year));
            calendarEntity.setDescribe(year + "年" + month + "月");
            calendarEntity.setDayList(dayList);
            calendarList.add(calendarEntity);
            Log.i(TAG, "year " + year + "   month " + month + "   day " + date + "    week " + formatWeekDesc(week) + "     天数 " + maxDay);
            calendar.add(Calendar.MONTH, 1);
        }
        return calendarList;
    }

    /**
     * todo 格式化日期
     *
     * @param day 日期
     * @return String
     */
    private String formatDayDesc(int day) {
        return day < 10 ? "0" + day : String.valueOf(day);
    }

    /**
     * todo 格式化星期描述
     *
     * @param week 星期值
     * @return String
     */
    public String formatWeekDesc(int week) {
        switch (week) {
            case 0:
                return "周日";
            case 1:
                return "周一";
            case 2:
                return "周二";
            case 3:
                return "周三";
            case 4:
                return "周四";
            case 5:
                return "周五";
            case 6:
                return "周六";
            default:
                return "--";
        }
    }

    /**
     * todo 获取当前月份
     *
     * @return int
     */
    public int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * todo 获取当前年份
     *
     * @return int
     */
    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * todo 获取星期
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return int
     */
    @SuppressLint("NewApi")
    public int findDateByWeek(int year, int month, int day) {
        // todo 这里有个任务待完成（ 获取每个月第一天 ）
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(Objects.requireNonNull(format.parse(year + "-" + month + "-" + day)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c.get(Calendar.DAY_OF_WEEK) - 1;
    }

    @SuppressLint("NewApi")
    public long getTimeInMillis(int year, int month, int day) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(Objects.requireNonNull(format.parse(year + "年" + month + "月" + day + "日")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTimeInMillis();
    }
}
