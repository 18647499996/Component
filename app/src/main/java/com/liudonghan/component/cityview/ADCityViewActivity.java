package com.liudonghan.component.cityview;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.component.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.city.ADCityView;
import com.liudonghan.view.snackbar.ADSnackBarManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADCityViewActivity extends ADBaseActivity<ADCityViewPresenter> implements ADCityViewContract.View {

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_a_d_city_view;
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
        ADCityView adCityView = (ADCityView) findViewById(R.id.activity_main_city);
        adCityView.getViewSwitcher().setDisplayedChild(0);
        adCityView.getProgressBar().setIndeterminate(true);
        adCityView.setProgressBarBgColor(R.color.color_342e2e);
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
