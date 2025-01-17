package com.liudonghan.component.card;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.liudonghan.component.databinding.ActivityCardBinding;
import com.liudonghan.mvp.ADBaseActivity;

import java.io.IOException;

public class CardActivity extends ADBaseActivity<CardPresenter,ActivityCardBinding> implements CardContract.View {

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
        return null;
    }

    @Override
    protected CardPresenter createPresenter() throws RuntimeException {
        return null;
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {

    }

    @Override
    protected void addListener() throws RuntimeException {

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
}
