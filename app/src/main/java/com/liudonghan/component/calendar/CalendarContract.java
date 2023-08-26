package com.liudonghan.component.calendar;

import com.liudonghan.mvp.ADBasePresenter;
import com.liudonghan.mvp.ADBaseView;
import com.liudonghan.view.calendar.ADCalendarEntity;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public interface CalendarContract {

    interface View extends ADBaseView<Presenter> {

        /**
         * 显示日历列表
         * @param adCalendarEntities 列表数据
         */
        void showCalendarList(List<ADCalendarEntity> adCalendarEntities);
    }

    interface Presenter extends ADBasePresenter {

        /**
         * 获取日历列表
         */
        void getCalendarList();
    }
}