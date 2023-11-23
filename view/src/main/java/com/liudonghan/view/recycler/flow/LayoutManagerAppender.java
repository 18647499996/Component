package com.liudonghan.view.recycler.flow;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:11/23/23
 */
public class LayoutManagerAppender {
    View mView;
    RecyclerView.LayoutManager mLayoutManager;
    Rect mRect;
    FlowLayoutOptions.Alignment alignment;

    public LayoutManagerAppender(View view, RecyclerView.LayoutManager layoutManager, Rect rect, FlowLayoutOptions.Alignment alignment) {
        mView = view;
        mLayoutManager = layoutManager;
        this.mRect = new Rect(rect);
        this.alignment = alignment;
    }

    public void layout(int addition) {
        if (alignment == FlowLayoutOptions.Alignment.CENTER)
            mLayoutManager.layoutDecorated(mView, mRect.left + addition, mRect.top, mRect.right + addition, mRect.bottom);
        else
            mLayoutManager.layoutDecorated(mView, mRect.left, mRect.top, mRect.right, mRect.bottom);

    }
}
