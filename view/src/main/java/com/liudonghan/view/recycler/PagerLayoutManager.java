package com.liudonghan.view.recycler;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:9/18/23
 */
public class PagerLayoutManager extends LinearLayoutManager implements RecyclerView.OnChildAttachStateChangeListener {

    private PagerSnapHelper mPagerSnapHelper;
    private OnViewPagerListener onPageChangeListener;
    private int currentPosition;


    PagerLayoutManager(Context context) {
        super(context, ADRecyclerView.Orientation.Vertical.getId(), false);
        mPagerSnapHelper = new PagerSnapHelper();
    }

    PagerLayoutManager(Context context, int orientation) {
        super(context, orientation, false);
        mPagerSnapHelper = new PagerSnapHelper();
    }


    @Override
    public void onAttachedToWindow(RecyclerView view) {
        view.addOnChildAttachStateChangeListener(this);
        mPagerSnapHelper.attachToRecyclerView(view);
        super.onAttachedToWindow(view);
    }

    @Override
    public void onChildViewAttachedToWindow(@NonNull View view) {
        if (onPageChangeListener != null && getChildCount() == 1) {
            currentPosition = getPosition(view);
            onPageChangeListener.onInitComplete(view,currentPosition);
        }
    }

    @Override
    public void onChildViewDetachedFromWindow(@NonNull View view) {
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageRelease(getPosition(view), view);
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            View view = mPagerSnapHelper.findSnapView(this);
            if (view != null && onPageChangeListener != null) {
                int position = getPosition(view);
                if (currentPosition != position) {
                    currentPosition = position;
                    onPageChangeListener.onPageSelected(currentPosition, currentPosition == getItemCount() - 1, view);
                }
            }
        }
        super.onScrollStateChanged(state);
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    public void setOnPageChangeListener(OnViewPagerListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    public interface OnViewPagerListener {
        /**
         * 初始化
         */
        void onInitComplete(View view, int currentPosition);

        /**
         * 释放
         *
         * @param position 离开视图条目索引
         * @param view     离开视图view
         */
        void onPageRelease(int position, View view);

        /**
         * 选中
         *
         * @param position 选中视图索引
         * @param isBottom 是否底部
         * @param view     选中view
         */
        void onPageSelected(int position, boolean isBottom, View view);
    }
}