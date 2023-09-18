package com.liudonghan.component.calendar;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.liudonghan.component.MainActivity;
import com.liudonghan.component.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.calendar.ADCalendarEntity;
import com.liudonghan.view.calendar.ADCalendarView;
import com.liudonghan.view.snackbar.ADSnackBarManager;
import com.liudonghan.view.title.ADTitleBuilder;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class CalendarActivity extends ADBaseActivity<CalendarPresenter> implements CalendarContract.View, ADCalendarView.OnADCalendarViewListener {

    @BindView(R.id.activity_calendar_cv)
    ADCalendarView activityCalendarCv;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_calendar;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return new ADTitleBuilder(this).setMiddleTitleBgRes("日历").setLeftImageRes(R.drawable.ad_back_black).setLeftRelativeLayoutListener(this);
    }

    @Override
    protected CalendarPresenter createPresenter() throws RuntimeException {
        return (CalendarPresenter) new CalendarPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        mPresenter.getCalendarList();
    }

    @Override
    protected void addListener() throws RuntimeException {
        activityCalendarCv.setOnADCalendarViewListener(this);
    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(CalendarContract.Presenter presenter) {
        mPresenter = (CalendarPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }

    @Override
    public void showCalendarList(List<ADCalendarEntity> adCalendarEntities) {
        activityCalendarCv.setData(adCalendarEntities);
        if (null != MainActivity.startDay) {
            activityCalendarCv.setDefaultDay(MainActivity.startDay, MainActivity.endDay);
        }
    }

    @Override
    public void onCalendarPickData(ADCalendarEntity.Day start, ADCalendarEntity.Day end) {
        Log.i("Mac_Liu", start.getDescribe() + " - " + end.getDescribe());
        MainActivity.startDay = start;
        MainActivity.endDay = end;
    }

    @Override
    public void onResetPicData() {
        MainActivity.startDay = null;
        MainActivity.endDay = null;
    }
}
