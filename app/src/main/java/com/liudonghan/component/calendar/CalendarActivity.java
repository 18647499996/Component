package com.liudonghan.component.calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liudonghan.component.MainActivity;
import com.liudonghan.component.R;
import com.liudonghan.component.databinding.ActivityCalendarBinding;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.calendar.ADCalendarEntity;
import com.liudonghan.view.calendar.ADCalendarView;
import com.liudonghan.view.snackbar.ADSnackBarManager;
import com.liudonghan.view.title.ADTitleBuilder;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class CalendarActivity extends ADBaseActivity<CalendarPresenter, ActivityCalendarBinding> implements CalendarContract.View, ADCalendarView.OnADCalendarViewListener {

    @Override
    protected ActivityCalendarBinding getActivityBinding() throws RuntimeException {
        return ActivityCalendarBinding.inflate(getLayoutInflater());
    }

    @Override
    protected View getViewBindingLayout() throws RuntimeException {
        return mViewBinding.getRoot();
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
        mViewBinding.activityCalendarCv.setOnADCalendarViewListener(this);
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
        mViewBinding.activityCalendarCv.setData(adCalendarEntities);
        if (null != MainActivity.startDay) {
            mViewBinding.activityCalendarCv.setDefaultDay(MainActivity.startDay, MainActivity.endDay);
        }
    }

    @Override
    public void onCalendarPickData(ADCalendarEntity.Day start, ADCalendarEntity.Day end) {
        Log.i("Mac_Liu", start.getDescribe() + " - " + end.getDescribe());
        MainActivity.startDay = start;
        MainActivity.endDay = end;
    }

    @Override
    public void onResetPickDate() {
        MainActivity.startDay = null;
        MainActivity.endDay = null;
    }

    @Override
    public void onError(int errorCode, String errorMsg) {

    }
}
