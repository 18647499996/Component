package com.liudonghan.component.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:11/23/23
 */
public class ADRecyclerViewPageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ADRecyclerViewPageAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }
}
