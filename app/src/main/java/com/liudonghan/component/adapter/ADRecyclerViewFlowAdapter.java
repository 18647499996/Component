package com.liudonghan.component.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liudonghan.component.R;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:11/23/23
 */
public class ADRecyclerViewFlowAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ADRecyclerViewFlowAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_find_food_child_tv_name, item);
    }
}
