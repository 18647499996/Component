package com.liudonghan.component.card;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.liudonghan.component.R;
import com.liudonghan.component.adapter.CardAdapter;
import com.liudonghan.component.databinding.ActivityCardBinding;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.fling.ADBaseListAdapter;
import com.liudonghan.view.fling.ADSwipeFlingAdapterView;
import com.liudonghan.view.title.ADTitleBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardActivity extends ADBaseActivity<CardPresenter, ActivityCardBinding> implements CardContract.View, ADSwipeFlingAdapterView.onFlingListener, ADBaseListAdapter.OnItemChildClickAdapterListener<String>, ADBaseListAdapter.OnItemClickAdapterListener<String> {

    private CardAdapter cardAdapter;

    @Override
    protected ActivityCardBinding getActivityBinding() throws RuntimeException {
        return ActivityCardBinding.inflate(getLayoutInflater());
    }

    @Override
    protected View getViewBindingLayout() throws RuntimeException {
        return mViewBinding.getRoot();
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return new ADTitleBuilder(mViewBinding.activityCardTitle)
                .setMiddleTitleBgRes("左右滑动")
                .setLeftBack(R.drawable.ad_back_black);
    }

    @Override
    protected CardPresenter createPresenter() throws RuntimeException {
        return (CardPresenter) new CardPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        cardAdapter = new CardAdapter(this, getData());
        mViewBinding.activityCardSwipe.setAdapter(cardAdapter);
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        data.add("小姐姐");
        data.add("小哥哥");
        data.add("小妹妹");
        data.add("小弟弟");
        return data;
    }

    @Override
    protected void addListener() throws RuntimeException {
        mViewBinding.activityCardSwipe.setFlingListener(this);
        cardAdapter.setOnItemChildClickAdapterListener(this);
        cardAdapter.setOnItemClickAdapterListener(this);
    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException, IOException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(CardContract.Presenter presenter) {

    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void removeFirstObjectInAdapter() {
        cardAdapter.remove(0);
    }

    @Override
    public void onLeftCardExit(Object dataObject) {
        String data = (String) dataObject;
        Log.e("Mac_Liu", "向左滑动：" + data);
    }

    @Override
    public void onRightCardExit(Object dataObject) {
        String data = (String) dataObject;
        Log.e("Mac_Liu", "向右滑动：" + data);
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        if (itemsInAdapter == 2) {
            Log.e("Mac_Liu", "onAdapterAboutToEmpty：" + itemsInAdapter + "  加载更多数据");
            cardAdapter.addAll(getData());
        }
    }

    @Override
    public void onScroll(float progress, float scrollXProgress) {

    }

    @Override
    public void onItemChildClick(ADBaseListAdapter<String> baseListAdapter, View v, int position) {
        Log.e("Mac_Liu", "view点击：" + baseListAdapter.getItem(position));
    }

    @Override
    public void onItemClick(ADBaseListAdapter<String> baseListAdapter, int position, String item) {
        Log.e("Mac_Liu", "条目点击：" + item);
    }
}
