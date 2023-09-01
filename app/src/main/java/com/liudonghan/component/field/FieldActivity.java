package com.liudonghan.component.field;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.component.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.snackbar.ADSnackBarManager;
import com.liudonghan.view.title.ADTitleBuilder;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class FieldActivity extends ADBaseActivity<FieldPresenter> implements FieldContract.View {

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_field;
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

    }

    @Override
    protected void addListener() throws RuntimeException {

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


}
