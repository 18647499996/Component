package com.liudonghan.component.input;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.component.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.snackbar.ADSnackBarManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADInputCodeActivity extends ADBaseActivity<ADInputCodePresenter> implements ADInputCodeContract.View {

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_a_d_input_code;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected ADInputCodePresenter createPresenter() throws RuntimeException {
        return (ADInputCodePresenter) new ADInputCodePresenter(this).builder(this);
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
    public void setPresenter(ADInputCodeContract.Presenter presenter) {
        mPresenter = (ADInputCodePresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }


}
