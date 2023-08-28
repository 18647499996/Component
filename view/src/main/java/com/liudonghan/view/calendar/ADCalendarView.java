package com.liudonghan.view.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.liudonghan.view.R;
import com.liudonghan.view.radius.ADButton;
import com.liudonghan.view.radius.ADConstraintLayout;
import com.liudonghan.view.snackbar.ADSnackBarManager;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:8/25/23
 */
public class ADCalendarView extends ADConstraintLayout implements CalendarAdapter.OnCalendarAdapterListener {

    private Context context;
    private RecyclerView recyclerView;
    private CalendarAdapter calendarAdapter;
    private CircularProgressIndicator circularProgressIndicator;
    private ViewSwitcher viewSwitcher;
    private TextView textViewStartHint, textViewStartDate, textViewStartWeek, textViewEndHint, textViewEndDate, textViewEndWeek, textViewDayCount;
    private ADButton buttonSubmit;
    private ADCalendarEntity.Day startDay, endDay;
    private OnADCalendarViewListener onADCalendarViewListener;


    public ADCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View inflate = View.inflate(context, R.layout.ad_calendar, this);
        recyclerView = inflate.findViewById(R.id.ad_calendar_rv);
        circularProgressIndicator = inflate.findViewById(R.id.ad_calendar_circular_progress_indicator);
        viewSwitcher = inflate.findViewById(R.id.ad_calendar_view_switcher);
        textViewStartHint = inflate.findViewById(R.id.ad_calendar_tv_start_hint);
        textViewStartDate = inflate.findViewById(R.id.ad_calendar_tv_start_date);
        textViewStartWeek = inflate.findViewById(R.id.ad_calendar_tv_start_week);
        textViewEndHint = inflate.findViewById(R.id.ad_calendar_tv_end_hint);
        textViewEndDate = inflate.findViewById(R.id.ad_calendar_tv_end_date);
        textViewEndWeek = inflate.findViewById(R.id.ad_calendar_tv_end_week);
        textViewDayCount = inflate.findViewById(R.id.ad_calendar_tv_day_count);
        buttonSubmit = inflate.findViewById(R.id.ad_calendar_btn_submit);
        initData();
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        buttonSubmit.setAlpha((float) 0.3);
    }

    /**
     * 初始化列表
     */
    private void initData() {
        calendarAdapter = new CalendarAdapter(R.layout.ad_item_calendar);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(calendarAdapter);
        viewSwitcher.setDisplayedChild(0);
        calendarAdapter.setOnCalendarAdapterListener(this);
        buttonSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null == startDay) {
                    ADSnackBarManager.getInstance().showWarn(context, "请选择开始日期");
                    return;
                }
                if (null == endDay) {
                    ADSnackBarManager.getInstance().showWarn(context, "请选择结束日期");
                    return;
                }
                onADCalendarViewListener.onCalendarPickData(startDay, endDay);
            }
        });
    }

    /**
     * 设置数据源
     *
     * @param data 日历列表
     */
    public void setData(List<ADCalendarEntity> data) {
        viewSwitcher.setDisplayedChild(1);
        calendarAdapter.setNewData(data);
    }

    @Override
    public void onItemClick(CalendarAdapter.CalendarChildAdapter calendarChildAdapter, ADCalendarEntity.Day day, int position, int status, int month) {
        switch (status) {
            case 1:
                start(calendarChildAdapter, day, position, month);
                break;
            case 2:
                end(calendarChildAdapter, day, position, month);
                break;
            case 3:
                reset(calendarChildAdapter, day, position, month);
                break;
        }
    }

    /**
     * 选中开始日期
     *
     * @param calendarChildAdapter 日期适配器
     * @param day                  日期信息
     * @param position             索引
     * @param month                月份
     */
    @SuppressLint("SetTextI18n")
    private void start(CalendarAdapter.CalendarChildAdapter calendarChildAdapter, ADCalendarEntity.Day day, int position, int month) {
        if (null == startDay) {
            calendarChildAdapter.getData().get(position).setSelector(true);
            calendarAdapter.setStartDate(day.getTimeInMillis());
            calendarAdapter.notifyDataSetChanged();
            startDay = day;
            textViewStartDate.setText(month + "月" + day.getDay() + "日");
            textViewStartWeek.setText(day.getWeekDesc());
            buttonSubmit.setAlpha((float) 0.3);
            textViewStartHint.setVisibility(VISIBLE);
            textViewStartWeek.setVisibility(VISIBLE);
            textViewEndWeek.setVisibility(INVISIBLE);
            textViewEndHint.setVisibility(INVISIBLE);
            textViewEndDate.setText("结束日期");
        }
    }

    /**
     * 选中结束日期
     *
     * @param calendarChildAdapter 日期适配器
     * @param day                  日期信息
     * @param position             索引
     * @param month                月份
     */
    @SuppressLint("SetTextI18n")
    private void end(CalendarAdapter.CalendarChildAdapter calendarChildAdapter, ADCalendarEntity.Day day, int position, int month) {
        if (startDay.getTimeInMillis() > day.getTimeInMillis()) {
            reset(calendarChildAdapter, day, position, month);
            return;
        }
        if (null == endDay) {
            calendarChildAdapter.getData().get(position).setSelector(true);
            calendarAdapter.setEndDate(day.getTimeInMillis());
            calendarAdapter.notifyDataSetChanged();
            endDay = day;
            textViewEndDate.setText(month + "月" + day.getDay() + "日");
            textViewEndWeek.setText(day.getWeekDesc());
            buttonSubmit.setAlpha((float) 1.0);
            textViewEndHint.setVisibility(View.VISIBLE);
            textViewEndWeek.setVisibility(View.VISIBLE);
            textViewDayCount.setText("共" + ADCalendarHelp.getInstance().getDifferDay(endDay.getTimeInMillis(), startDay.getTimeInMillis()) + "天");
        }
    }

    /**
     * 重置选中日期
     */
    public void reset() {
        reset(null, null, 0, 0);
    }

    /**
     * 重置选中日期
     *
     * @param calendarChildAdapter 日期适配器
     * @param day                  日期信息
     * @param position             索引
     * @param month                月份
     */
    private void reset(CalendarAdapter.CalendarChildAdapter calendarChildAdapter, ADCalendarEntity.Day day, int position, int month) {
        calendarAdapter.setResetData();
        calendarAdapter.notifyDataSetChanged();
        startDay = null;
        endDay = null;
        if (null != calendarChildAdapter) {
            start(calendarChildAdapter, day, position, month);
        }
    }

    public void setOnADCalendarViewListener(OnADCalendarViewListener onADCalendarViewListener) {
        this.onADCalendarViewListener = onADCalendarViewListener;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public CalendarAdapter getCalendarAdapter() {
        return calendarAdapter;
    }

    public CircularProgressIndicator getCircularProgressIndicator() {
        return circularProgressIndicator;
    }

    public ViewSwitcher getViewSwitcher() {
        return viewSwitcher;
    }

    public TextView getTextViewStartHint() {
        return textViewStartHint;
    }

    public TextView getTextViewStartDate() {
        return textViewStartDate;
    }

    public TextView getTextViewStartWeek() {
        return textViewStartWeek;
    }

    public TextView getTextViewEndHint() {
        return textViewEndHint;
    }

    public TextView getTextViewEndDate() {
        return textViewEndDate;
    }

    public TextView getTextViewEndWeek() {
        return textViewEndWeek;
    }

    public TextView getTextViewDayCount() {
        return textViewDayCount;
    }

    public ADButton getButtonSubmit() {
        return buttonSubmit;
    }

    public ADCalendarEntity.Day getStartDay() {
        return startDay;
    }

    public ADCalendarEntity.Day getEndDay() {
        return endDay;
    }

    public interface OnADCalendarViewListener {
        /**
         * 选择日期数据
         *
         * @param start 开始时间
         * @param end   结束时间
         */
        void onCalendarPickData(ADCalendarEntity.Day start, ADCalendarEntity.Day end);
    }
}