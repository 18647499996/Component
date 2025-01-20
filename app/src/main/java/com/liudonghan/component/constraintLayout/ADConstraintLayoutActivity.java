package com.liudonghan.component.constraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.liudonghan.component.R;
import com.liudonghan.component.databinding.ActivityADConstraintLayoutBinding;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.snackbar.ADSnackBarManager;
import com.liudonghan.view.title.ADTitleBuilder;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADConstraintLayoutActivity extends ADBaseActivity<ADConstraintLayoutPresenter, ActivityADConstraintLayoutBinding> implements ADConstraintLayoutContract.View {

    private ADTitleBuilder titleBuilder;

    @Override
    protected ActivityADConstraintLayoutBinding getActivityBinding() throws RuntimeException {
        return ActivityADConstraintLayoutBinding.inflate(getLayoutInflater());
    }

    @Override
    protected View getViewBindingLayout() throws RuntimeException {
        return mViewBinding.getRoot();
    }

    @Override
    protected ADTitleBuilder initBuilderTitle() throws RuntimeException {
        titleBuilder = new ADTitleBuilder(mViewBinding.activityTitleInc)
                .setMiddleTitleBgRes("ADConstraintLayout")
                .setLeftBack(R.drawable.ad_back_black)
                .setRightSub("刷新模式", R.color.white, R.color.color_eb2525)
                .setLeftRelativeLayoutFinish(this);
        return titleBuilder;
    }

    @Override
    protected ADConstraintLayoutPresenter createPresenter() throws RuntimeException {
        return (ADConstraintLayoutPresenter) new ADConstraintLayoutPresenter(this).builder(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void addListener() throws RuntimeException {
        mViewBinding.activityMainLayout.setOnClickListener(view -> titleBuilder.getTextViewTitleCenter().setText("我是动态ViewBinding标题"));
    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(ADConstraintLayoutContract.Presenter presenter) {
        mPresenter = (ADConstraintLayoutPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }


}
