package com.liudonghan.view.calendar;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:8/24/23
 */
public class ADCalendarEntity {

    private int year;
    private int month;
    private String describe;
    private List<Day> dayList;
    private String zodiac;

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<Day> getDayList() {
        return dayList;
    }

    public void setDayList(List<Day> dayList) {
        this.dayList = dayList;
    }

    public static class Day {
        // todo 日期
        private int day;
        // todo 是否可点击
        private boolean enable;
        // todo 是否选中
        private boolean selector = false;
        // todo 日期描述（ 例如：2023年8月23号 ）
        private String describe;
        // todo 农历月份（ 例如：正月 ）
        private String lunarMonth;
        // todo 农历日期（ 例如：初一 ）
        private String lunarDay;
        // todo 二十四节气（ 例如：立春 ）
        private String solarTerm;
        // todo 星座（ 例如：水瓶座 ）
        private String constellation;
        // todo 星座时段（ 例如： 01.20—02.18 ）
        private String constellationDate;
        // todo 公历节日（ 例如：元旦 ）
        private String gregorianHoliday;
        // todo 农历节日（ 例如：除夕 ）
        private String lunarHoliday;
        // todo 宜忌（ 例如：宜:疗病.结婚.交易.入仓.求职-忌:安葬.动土.针灸 ）
        private String fitAvoid;
        // todo 星期（ 例如：周五 ）
        private String weekDesc;
        // todo 星期
        private int week;
        // todo 当日时间戳
        private long timeInMillis;

        public long getTimeInMillis() {
            return timeInMillis;
        }

        public void setTimeInMillis(long timeInMillis) {
            this.timeInMillis = timeInMillis;
        }

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }

        public String getWeekDesc() {
            return weekDesc;
        }

        public void setWeekDesc(String weekDesc) {
            this.weekDesc = weekDesc;
        }

        public String getFitAvoid() {
            return fitAvoid;
        }

        public void setFitAvoid(String fitAvoid) {
            this.fitAvoid = fitAvoid;
        }

        public String getLunarHoliday() {
            return lunarHoliday;
        }

        public void setLunarHoliday(String lunarHoliday) {
            this.lunarHoliday = lunarHoliday;
        }

        public String getGregorianHoliday() {
            return gregorianHoliday;
        }

        public void setGregorianHoliday(String gregorianHoliday) {
            this.gregorianHoliday = gregorianHoliday;
        }

        public String getConstellationDate() {
            return constellationDate;
        }

        public void setConstellationDate(String constellationDate) {
            this.constellationDate = constellationDate;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public String getSolarTerm() {
            return solarTerm;
        }

        public void setSolarTerm(String solarTerm) {
            this.solarTerm = solarTerm;
        }

        public String getLunarMonth() {
            return lunarMonth;
        }

        public void setLunarMonth(String lunarMonth) {
            this.lunarMonth = lunarMonth;
        }

        public String getLunarDay() {
            return lunarDay;
        }

        public void setLunarDay(String lunarDay) {
            this.lunarDay = lunarDay;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public boolean isSelector() {
            return selector;
        }

        public void setSelector(boolean selector) {
            this.selector = selector;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        @Override
        public String toString() {
            return "Day{" +
                    "day=" + day +
                    ", enable=" + enable +
                    ", selector=" + selector +
                    ", describe='" + describe + '\'' +
                    ", lunarMonth='" + lunarMonth + '\'' +
                    ", lunarDay='" + lunarDay + '\'' +
                    ", solarTerm='" + solarTerm + '\'' +
                    ", constellation='" + constellation + '\'' +
                    ", constellationDate='" + constellationDate + '\'' +
                    ", gregorianHoliday='" + gregorianHoliday + '\'' +
                    ", lunarHoliday='" + lunarHoliday + '\'' +
                    ", fitAvoid='" + fitAvoid + '\'' +
                    ", weekDesc='" + weekDesc + '\'' +
                    ", week=" + week +
                    ", timeInMillis=" + timeInMillis +
                    '}';
        }
    }
}
