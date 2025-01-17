package com.liudonghan.component.card;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.liudonghan.component.databinding.ActivityCardBinding;
import com.liudonghan.mvp.ADBaseActivity;

import java.io.IOException;

public class CardActivity extends ADBaseActivity<CardPresenter> implements CardContract.View {
    private ActivityCardBinding activityCardBinding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCardBinding = ActivityCardBinding.inflate(getLayoutInflater());
        setContentView(activityCardBinding.getRoot());
        activityCardBinding.activityTvHint.setText("viewBinding组件");
    }

    @Override
    protected int getLayout() throws RuntimeException {
        return 0;
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
