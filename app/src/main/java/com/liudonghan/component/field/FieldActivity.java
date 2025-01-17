package com.liudonghan.component.field;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liudonghan.component.R;
import com.liudonghan.component.databinding.ActivityFieldBinding;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.field.ADFieldTextLayout;
import com.liudonghan.view.snackbar.ADSnackBarManager;
import com.liudonghan.view.title.ADTitleBuilder;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class FieldActivity extends ADBaseActivity<FieldPresenter, ActivityFieldBinding> implements FieldContract.View, ADFieldTextLayout.OnADFieldTextLayoutListener {

    @Override
    protected ActivityFieldBinding getActivityBinding() throws RuntimeException {
        return ActivityFieldBinding.inflate(getLayoutInflater());
    }

    @Override
    protected View getViewBindingLayout() throws RuntimeException {
        return mViewBinding.getRoot();
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return new ADTitleBuilder(this).setMiddleTitleBgRes("Field").setLeftImageRes(R.drawable.ad_back_black).setLeftRelativeLayoutListener(this);
    }

    @Override
    protected FieldPresenter createPresenter() throws RuntimeException {
        return (FieldPresenter) new FieldPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        mViewBinding.field.getTextViewTitle().setText("开户行信息");
    }

    @Override
    protected void addListener() throws RuntimeException {
        mViewBinding.field.setOnADFieldTextLayoutListener(this);
        mViewBinding.field1.setOnADFieldTextLayoutListener(this);
        mViewBinding.field2.setOnADFieldTextLayoutListener(this);
    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(FieldContract.Presenter presenter) {
        mPresenter = (FieldPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }

    @Override
    public void onField(View view, String title) {
        Log.i("Mac_Liu", "点击按钮：" + title);
    }

}
