package com.liudonghan.view.calendar;

import android.text.TextUtils;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liudonghan.view.R;
import com.liudonghan.view.radius.ADConstraintLayout;
import com.liudonghan.view.recycler.ADRecyclerView;

import java.util.Objects;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:8/25/23
 */
public class CalendarAdapter extends BaseQuickAdapter<ADCalendarEntity, BaseViewHolder> {

    private OnCalendarAdapterListener onCalendarAdapterListener;
    public long startTimeInMillis, endTimeInMillis;

    public CalendarAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ADCalendarEntity item) {
        helper.setText(R.id.ad_item_calendar_tv_date, item.getDescribe());
        ADRecyclerView recyclerView = helper.getView(R.id.ad_item_calendar_rv_child);
        CalendarChildAdapter calendarChildAdapter = new CalendarChildAdapter(R.layout.ad_item_calendar_child);
        recyclerView.setAdapter(calendarChildAdapter);
        recyclerView.setItemViewCacheSize(200);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        calendarChildAdapter.setNewData(item.getDayList());
        calendarChildAdapter.setOnItemClickListener((adapter, view, position) -> {
            ADCalendarEntity.Day day = (ADCalendarEntity.Day) adapter.getItem(position);
            if (!Objects.requireNonNull(day).isEnable()) {
                // todo 小于当天的日期无法点击
                return;
            }
            onCalendarAdapterListener.onItemClick((CalendarChildAdapter) adapter, day, position, 0 == startTimeInMillis ? 1 : 0 == endTimeInMillis ? 2 : 3, item.getMonth(), helper.getAdapterPosition());
        });
    }

    public class CalendarChildAdapter extends BaseQuickAdapter<ADCalendarEntity.Day, BaseViewHolder> {


        public CalendarChildAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, ADCalendarEntity.Day item) {
            ADConstraintLayout adConstraintLayout = helper.getView(R.id.ad_item_calendar_child_layout);
            helper.setText(R.id.ad_item_calendar_child_tv_date, 0 == item.getDay() ? "" : String.valueOf(item.getDay()))
                    .setText(R.id.ad_item_calendar_child_tv_desc, !TextUtils.isEmpty(item.getLunarHoliday()) ?
                            item.getLunarHoliday() : !TextUtils.isEmpty(item.getGregorianHoliday()) ?
                            item.getGregorianHoliday() : item.getLunarDay())
                    .setAlpha(R.id.ad_item_calendar_child_layout, (float) (item.isEnable() ? 1.0 : 0.2))
                    .setTextColor(R.id.ad_item_calendar_child_tv_date, mContext.getResources().getColor(item.isSelector() ? R.color.white : R.color.color_342e2e))
                    .setTextColor(R.id.ad_item_calendar_child_tv_desc, mContext.getResources().getColor(item.isSelector() ? R.color.white : R.color.color_7c7c7c))
                    .setBackgroundColor(R.id.ad_item_calendar_child_layout, mContext.getResources().getColor(item.isSelector() ?
                            R.color.color_ff7900 : 0 == startTimeInMillis && 0 == endTimeInMillis ?
                            R.color.white : item.getTimeInMillis() > startTimeInMillis && item.getTimeInMillis() < endTimeInMillis ?
                            R.color.color_FFF9F3 : R.color.white));
            adConstraintLayout.setTopLeftRadius(startTimeInMillis == item.getTimeInMillis() ? 12 : 0);
            adConstraintLayout.setBottomLeftRadius(startTimeInMillis == item.getTimeInMillis() ? 12 : 0);
            adConstraintLayout.setTopRightRadius(endTimeInMillis == item.getTimeInMillis() ? 12 : 0);
            adConstraintLayout.setBottomRightRadius(endTimeInMillis == item.getTimeInMillis() ? 12 : 0);
        }
    }

    /**
     * 开始时间
     *
     * @param startTimeInMillis 时间戳
     */
    public void setStartDate(long startTimeInMillis) {
        this.startTimeInMillis = startTimeInMillis;
    }

    /**
     * 结束时间
     *
     * @param endTimeInMillis 时间戳
     */
    public void setEndDate(long endTimeInMillis) {
        this.endTimeInMillis = endTimeInMillis;
    }

    /**
     * 重置时间
     */
    public void setResetData() {
        for (int i = 0; i < getData().size(); i++) {
            for (int j = 0; j < getData().get(i).getDayList().size(); j++) {
                if (getData().get(i).getDayList().get(j).getTimeInMillis() == startTimeInMillis || getData().get(i).getDayList().get(j).getTimeInMillis() == endTimeInMillis) {
                    getData().get(i).getDayList().get(j).setSelector(false);
                }
            }

        }
        startTimeInMillis = 0;
        endTimeInMillis = 0;
    }

    public void setOnCalendarAdapterListener(OnCalendarAdapterListener onCalendarAdapterListener) {
        this.onCalendarAdapterListener = onCalendarAdapterListener;
    }

    public interface OnCalendarAdapterListener {
        /**
         * 条目点击事件
         *
         * @param day             点击
         * @param position        索引
         * @param status          点击状态 1.开始 2.结束 3.重置
         * @param month           月份
         * @param adapterPosition 一级列表索引
         */
        void onItemClick(CalendarChildAdapter calendarChildAdapter, ADCalendarEntity.Day day, int position, int status, int month, int adapterPosition);
    }
}
