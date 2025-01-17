package com.liudonghan.component.cell;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.component.databinding.ActivityADCellTextLayoutBinding;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.snackbar.ADSnackBarManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADCellTextLayoutActivity extends ADBaseActivity<ADCellTextLayoutPresenter, ActivityADCellTextLayoutBinding> implements ADCellTextLayoutContract.View {

    @Override
    protected ActivityADCellTextLayoutBinding getActivityBinding() throws RuntimeException {
        return ActivityADCellTextLayoutBinding.inflate(getLayoutInflater());
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
    protected ADCellTextLayoutPresenter createPresenter() throws RuntimeException {
        return (ADCellTextLayoutPresenter) new ADCellTextLayoutPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        mViewBinding.activityMainCgOne.setLeftText("动态设置");
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
    public void setPresenter(ADCellTextLayoutContract.Presenter presenter) {
        mPresenter = (ADCellTextLayoutPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }


}
