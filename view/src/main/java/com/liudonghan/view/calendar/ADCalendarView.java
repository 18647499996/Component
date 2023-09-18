package com.liudonghan.view.calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.liudonghan.view.R;
import com.liudonghan.view.radius.ADButton;
import com.liudonghan.view.radius.ADConstraintLayout;
import com.liudonghan.view.recycler.ADRecyclerView;
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
    private ADRecyclerView recyclerView;
    private CalendarAdapter calendarAdapter;
    private CircularProgressIndicator circularProgressIndicator;
    private ViewSwitcher viewSwitcher;
    private TextView textViewStartHint, textViewStartDate, textViewStartWeek, textViewEndHint, textViewEndDate, textViewEndWeek, textViewDayCount;
    private ADButton buttonSubmit, buttonClear;
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
        buttonClear = inflate.findViewById(R.id.ad_calendar_btn_clear);
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
        recyclerView.setAdapter(calendarAdapter);
        // 缓存条目视图
        recyclerView.setItemViewCacheSize(200);
        // 是为了更改 adapter的内容不会改变 它的View的高度和宽度，那么就可以设置为 True来避免不必要的 requestLayout
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        viewSwitcher.setDisplayedChild(0);
        calendarAdapter.setOnCalendarAdapterListener(this);
        buttonSubmit.setOnClickListener(v -> {
            if (null == startDay) {
                ADSnackBarManager.getInstance().showWarn(context, "请选择开始日期");
                return;
            }
            if (null == endDay) {
                ADSnackBarManager.getInstance().showWarn(context, "请选择结束日期");
                return;
            }
            onADCalendarViewListener.onCalendarPickData(startDay, endDay);
        });
        buttonClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                onADCalendarViewListener.onResetPicData();
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
    public void onItemClick(CalendarAdapter.CalendarChildAdapter calendarChildAdapter, ADCalendarEntity.Day day, int position, int status, int month, int adapterPosition) {
        switch (status) {
            case 1:
                start(calendarChildAdapter, day, position, month, adapterPosition);
                break;
            case 2:
                end(calendarChildAdapter, day, position, month, adapterPosition);
                break;
            case 3:
                reset(calendarChildAdapter, day, position, month, adapterPosition);
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
     * @param adapterPosition      一级列表索引
     */
    @SuppressLint("SetTextI18n")
    private void start(CalendarAdapter.CalendarChildAdapter calendarChildAdapter, ADCalendarEntity.Day day, int position, int month, int adapterPosition) {
        if (null == startDay) {
            calendarChildAdapter.getData().get(position).setSelector(true);
            calendarAdapter.setStartDate(day.getTimeInMillis());
            calendarAdapter.notifyItemChanged(adapterPosition);
            startDay = day;
            textViewStartDate.setText(month + "月" + day.getDay() + "日");
            textViewStartWeek.setText(day.getWeekDesc());
            buttonSubmit.setAlpha((float) 0.3);
            textViewStartHint.setVisibility(VISIBLE);
            textViewStartWeek.setVisibility(VISIBLE);
            textViewEndWeek.setVisibility(INVISIBLE);
            textViewEndHint.setVisibility(INVISIBLE);
            textViewEndDate.setText("结束日期");
            textViewDayCount.setText("");
        }
    }

    /**
     * 选中结束日期
     *
     * @param calendarChildAdapter 日期适配器
     * @param day                  日期信息
     * @param position             索引
     * @param month                月份
     * @param adapterPosition      一级列表索引
     */
    @SuppressLint("SetTextI18n")
    private void end(CalendarAdapter.CalendarChildAdapter calendarChildAdapter, ADCalendarEntity.Day day, int position, int month, int adapterPosition) {
        if (startDay.getTimeInMillis() > day.getTimeInMillis()) {
            reset(calendarChildAdapter, day, position, month, adapterPosition);
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
        reset(null, null, 0, 0, 0);
    }

    /**
     * 重置选中日期
     *
     * @param calendarChildAdapter 日期适配器
     * @param day                  日期信息
     * @param position             索引
     * @param month                月份
     * @param adapterPosition      一级列表索引
     */
    private void reset(CalendarAdapter.CalendarChildAdapter calendarChildAdapter, ADCalendarEntity.Day day, int position, int month, int adapterPosition) {
        calendarAdapter.setResetData();
        calendarAdapter.notifyDataSetChanged();
        startDay = null;
        endDay = null;
        if (null != calendarChildAdapter) {
            start(calendarChildAdapter, day, position, month, adapterPosition);
        } else {
            textViewStartHint.setVisibility(INVISIBLE);
            textViewStartWeek.setVisibility(INVISIBLE);
            textViewEndWeek.setVisibility(INVISIBLE);
            textViewEndHint.setVisibility(INVISIBLE);
            textViewStartDate.setText("开始日期");
            textViewEndDate.setText("结束日期");
            textViewDayCount.setText("");
        }
    }

    /**
     * 设置默认时间
     *
     * @param startDay
     * @param endDay
     */
    @SuppressLint("SetTextI18n")
    public void setDefaultDay(ADCalendarEntity.Day startDay, ADCalendarEntity.Day endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
        textViewStartDate.setText(startDay.getMonth() + "月" + startDay.getDay() + "日");
        textViewStartWeek.setText(startDay.getWeekDesc());

        textViewEndDate.setText(endDay.getMonth() + "月" + endDay.getDay() + "日");
        textViewEndWeek.setText(endDay.getWeekDesc());

        textViewDayCount.setText("共" + ADCalendarHelp.getInstance().getDifferDay(endDay.getTimeInMillis(), startDay.getTimeInMillis()) + "天");

        buttonSubmit.setAlpha((float) 1.0);
        textViewStartHint.setVisibility(VISIBLE);
        textViewStartWeek.setVisibility(VISIBLE);
        textViewEndWeek.setVisibility(VISIBLE);
        textViewEndHint.setVisibility(VISIBLE);


        List<ADCalendarEntity> data = calendarAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            if (startDay.getYear() == data.get(i).getYear() && startDay.getMonth() == data.get(i).getMonth()) {
                for (int j = 0; j < data.get(i).getDayList().size(); j++) {
                    if (startDay.getTimeInMillis() == data.get(i).getDayList().get(j).getTimeInMillis()) {
                        data.get(i).getDayList().get(j).setSelector(true);
                        calendarAdapter.setStartDate(data.get(i).getDayList().get(j).getTimeInMillis());
                    }
                }
            }
            if (endDay.getYear() == data.get(i).getYear() && endDay.getMonth() == data.get(i).getMonth()) {
                for (int j = 0; j < data.get(i).getDayList().size(); j++) {
                    if (endDay.getTimeInMillis() == data.get(i).getDayList().get(j).getTimeInMillis()) {
                        data.get(i).getDayList().get(j).setSelector(true);
                        calendarAdapter.setEndDate(data.get(i).getDayList().get(j).getTimeInMillis());
                    }
                }
            }
        }
        calendarAdapter.notifyDataSetChanged();
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

        /**
         * 重置日期
         */
        void onResetPicData();
    }
}
