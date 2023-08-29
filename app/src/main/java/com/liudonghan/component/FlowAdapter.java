package com.liudonghan.component;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:8/29/23
 */
public class FlowAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public FlowAdapter(int layoutRes) {
        super(layoutRes);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.btn, item);
    }
}
