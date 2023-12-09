package com.liudonghan.component.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liudonghan.component.R;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:8/29/23
 */
public class ADRecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public ADRecyclerViewAdapter(int layoutResId) {
        super(layoutResId);
    }

    public ADRecyclerViewAdapter(int layoutResId, List<String> stringList) {
        super(layoutResId, stringList);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_text, item);
    }
}
