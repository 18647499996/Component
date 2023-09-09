package com.liudonghan.view.calendar;

import android.annotation.SuppressLint;

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
     *
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
        return getCalendarList(date, true);
    }

    /**
     * todo 获取日历列表
     *
     * @param date 时间日期
     * @param sort 排序
     *             true.正序时间（ 从当前月份累加 ) 例如：2023-08... 2023-09
     *             false.倒序时间 ( 从当前月份累减 ）例如：2023-08... 2023-07
     * @return List<ADCalendarEntity>
     */
    public List<ADCalendarEntity> getCalendarList(Date date, boolean sort) {
        return getCalendarList(date, sort, 6);
    }


    /**
     * todo 获取日历列表（ 自定义时间 ）
     *
     * @param date  Date时间
     * @param sort  排序
     *              true.正序时间（ 从当前月份累加 ) 例如：2023-08... 2023-09
     *              false.倒序时间 ( 从当前月份累减 ）例如：2023-08... 2023-07
     * @param count 共计月份
     * @return List<ADCalendarEntity>
     */
    public List<ADCalendarEntity> getCalendarList(Date date, boolean sort, int count) {
        List<ADCalendarEntity> calendarList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 0; i < count; i++) {
            ADCalendarEntity calendarEntity = new ADCalendarEntity();
            List<ADCalendarEntity.Day> dayList = new ArrayList<>();
            // 年
            int year = calendar.get(Calendar.YEAR);
            // 月
            int month = calendar.get(Calendar.MONTH) + 1;
            // 日
            int day = calendar.get(Calendar.DATE);
            // todo 星期（ 当月第一天 ）
            int week = findDateByWeek(year, month, 1);
            // 最大天数（ 当月 ）
            int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            // 填补当月空白数据
            for (int j = 0; j < week; j++) {
                ADCalendarEntity.Day dayEntity = new ADCalendarEntity.Day();
                dayList.add(dayEntity);
            }
            // 获取天数（ 当月 ）
            for (int j = 1; j <= maxDay; j++) {
                ADCalendarEntity.Day dayEntity = new ADCalendarEntity.Day();
                dayEntity.setDay(j);
                dayEntity.setDescribe(year + "年" + month + "月" + formatDayDesc(j) + "日");
                dayEntity.setEnable(getCurrentYear() != year || (getCurrentMonth() != month || sort ? (j >= day) : (j <= day)));
                dayEntity.setLunarMonth(ChineseLunarHelp.getInstance().getLunarMonth(year, month, j));
                dayEntity.setLunarDay(ChineseLunarHelp.getInstance().getLunarDay(year, month, j));
                dayEntity.setSolarTerm(ChineseLunarHelp.getInstance().getSolarTerms(year, month, j));
                dayEntity.setConstellation(ChineseLunarHelp.getInstance().getConstellation(month, j).split(":")[0]);
                dayEntity.setConstellationDate(ChineseLunarHelp.getInstance().getConstellation(month, j).split(":")[1]);
                dayEntity.setGregorianHoliday(ChineseLunarHelp.getInstance().getGregorianHoliday(month, j));
                dayEntity.setLunarHoliday(ChineseLunarHelp.getInstance().getLunarHoliday(year, month, j));
                dayEntity.setFitAvoid(ChineseLunarHelp.getInstance().getFitAvoid(year, month, j));
                dayEntity.setWeekDesc(formatWeekDesc(findDateByWeek(year, month, j)));
                dayEntity.setWeek(findDateByWeek(year, month, j));
                dayEntity.setTimeInMillis(getTimeInMillis(year, month, j));
                dayList.add(dayEntity);
            }

            calendarEntity.setYear(year);
            calendarEntity.setMonth(month);
            calendarEntity.setZodiac(ChineseLunarHelp.getInstance().getZodiac(year));
            calendarEntity.setDescribe(year + "年" + month + "月");
            calendarEntity.setDayList(dayList);
            if (sort) {
                calendarList.add(calendarEntity);
                calendar.add(Calendar.MONTH, 1);
            } else {
                calendarList.add(0, calendarEntity);
                calendar.add(Calendar.MONTH, -1);
            }
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

    /**
     * todo 计算两个时间戳相差天数
     *
     * @param end   结束时间戳
     * @param start 开始时间戳
     * @return long
     */
    public long getDifferDay(long end, long start) {
        return (end - start) / (1000 * 60 * 60 * 24);
    }
}
