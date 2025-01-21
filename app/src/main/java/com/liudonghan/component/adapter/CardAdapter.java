package com.liudonghan.component.adapter;

import android.content.Context;
import android.view.View;

import com.liudonghan.component.R;
import com.liudonghan.component.card.ADBaseListAdapter;

public class CardAdapter extends ADBaseListAdapter<String> {

    public CardAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemLayout() {
        return R.layout.item_card;
    }

    @Override
    public View getView(int position, View convertView, BaseViewHolder<String> viewHolder, String item) {
        viewHolder.setText(R.id.item_card_tv_name, item)
                .addOnClickListener(position, R.id.item_card_tv_name);
        return convertView;
    }
}
