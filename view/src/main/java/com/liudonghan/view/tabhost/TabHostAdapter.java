package com.liudonghan.view.tabhost;

import android.text.TextUtils;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liudonghan.view.R;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:9/25/23
 */
public class TabHostAdapter extends BaseQuickAdapter<ADNavigationEntity, BaseViewHolder> {

    public TabHostAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ADNavigationEntity item) {
        helper.setText(R.id.item_ad_tab_host_tv_title, item.getTitle())
                .setText(R.id.item_ad_tab_host_tv_count, 99 > item.getCount() ? String.valueOf(item.getCount()) : "..")
                .setTextColor(R.id.item_ad_tab_host_tv_title, mContext.getResources().getColor(item.isSelector() ? item.getSelectorTextColor() : item.getUnTextColor()))
                .setImageResource(R.id.item_ad_tab_host_img_icon, item.isSelector() ? item.getSelectorResId() : item.getUnResId())
                .setGone(R.id.item_ad_tab_host_tv_count, 0 != item.getCount())
                .setGone(R.id.item_ad_tab_host_tv_title, !TextUtils.isEmpty(item.getTitle()))
                .setGone(R.id.item_ad_tab_host_img_icon, 0 != item.getSelectorResId() && 0 != item.getUnResId());


    }

    /**
     * 选中tab
     *
     * @param position 索引
     */
    public void selector(int position) {
        for (int i = 0; i < getData().size(); i++) {
            getData().get(i).setSelector(i == position);
        }
        notifyDataSetChanged();
    }

    public void setUnreadCount(int position, int count) {
        if (position > getData().size() - 1) {
            Log.i("Mac_Liu", "position super range ADNavigationEntity is not null");
            return;
        }
        getData().get(position).setCount(count);
        notifyItemChanged(position);
    }
}
