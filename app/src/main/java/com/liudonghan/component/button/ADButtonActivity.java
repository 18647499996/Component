package com.liudonghan.component.button;

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
public class ADButtonActivity extends ADBaseActivity<ADButtonPresenter> implements ADButtonContract.View {

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_a_d_button;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected ADButtonPresenter createPresenter() throws RuntimeException {
        return (ADButtonPresenter) new ADButtonPresenter(this).builder(this);
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
    public void setPresenter(ADButtonContract.Presenter presenter) {
        mPresenter = (ADButtonPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }


}
