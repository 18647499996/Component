package com.liudonghan.view.snackbar;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/6/23
 */
public class RecyclerUtil {

    static void setScrollListener(final SnackBar snackbar, View view) {
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                snackbar.dismiss();
            }
        });
    }
}