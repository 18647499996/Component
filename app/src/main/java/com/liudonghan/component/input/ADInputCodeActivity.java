package com.liudonghan.component.input;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.component.R;
import com.liudonghan.component.databinding.ActivityADInputCodeBinding;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.snackbar.ADSnackBarManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADInputCodeActivity extends ADBaseActivity<ADInputCodePresenter, ActivityADInputCodeBinding> implements ADInputCodeContract.View {

    @Override
    protected ActivityADInputCodeBinding getActivityBinding() throws RuntimeException {
        return ActivityADInputCodeBinding.inflate(getLayoutInflater());
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
