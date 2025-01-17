package com.liudonghan.component.cityview;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.component.R;
import com.liudonghan.component.databinding.ActivityADCityViewBinding;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.snackbar.ADSnackBarManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADCityViewActivity extends ADBaseActivity<ADCityViewPresenter, ActivityADCityViewBinding> implements ADCityViewContract.View {

    @Override
    protected ActivityADCityViewBinding getActivityBinding() throws RuntimeException {
        return ActivityADCityViewBinding.inflate(getLayoutInflater());
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
    protected ADCityViewPresenter createPresenter() throws RuntimeException {
        return (ADCityViewPresenter) new ADCityViewPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        mViewBinding.activityMainCity.getViewSwitcher().setDisplayedChild(0);
        mViewBinding.activityMainCity.getProgressBar().setIndeterminate(true);
        mViewBinding.activityMainCity.setProgressBarBgColor(R.color.color_342e2e);
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
    public void setPresenter(ADCityViewContract.Presenter presenter) {
        mPresenter = (ADCityViewPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }


}
