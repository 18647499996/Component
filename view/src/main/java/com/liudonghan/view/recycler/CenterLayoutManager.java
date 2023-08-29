package com.liudonghan.view.recycler;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Description：居中滚动（ 条目 ）
 *
 * @author Created by: Li_Min
 * Time:8/14/23
 */
public class CenterLayoutManager extends LinearLayoutManager {

    static int targetPosition = 0;
    static float duration = 350f;

    public CenterLayoutManager(Context context) {
        super(context);
    }

    public CenterLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CenterLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        CenterSmoothScroller smoothScroller = new CenterSmoothScroller(recyclerView.getContext());
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, int position) {
        smoothScrollToPosition(recyclerView, new RecyclerView.State(), 300, position);
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, int position, int duration) {
        smoothScrollToPosition(recyclerView, new RecyclerView.State(), duration, position);
    }


    private void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int duration, int position) {
        CenterLayoutManager.duration = duration;
        CenterLayoutManager.targetPosition = position;
        smoothScrollToPosition(recyclerView, state, position);
    }

    public static class CenterSmoothScroller extends LinearSmoothScroller {


        public CenterSmoothScroller(Context context) {
            super(context);
        }

        @Override
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
            return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
        }

        @Override
        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            float newDuration = duration / (Math.abs(targetPosition));
            return newDuration / displayMetrics.densityDpi;
        }

        @Override
        protected int calculateTimeForScrolling(int dx) {
            return super.calculateTimeForScrolling(dx);
        }
    }
}