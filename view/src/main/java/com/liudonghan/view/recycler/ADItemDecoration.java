package com.liudonghan.view.recycler;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:11/23/23
 */
public class ADItemDecoration extends RecyclerView.ItemDecoration {

    private int margin = 8;

    public ADItemDecoration() {

    }

    public ADItemDecoration(int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(margin, margin, margin, margin);
    }
}
