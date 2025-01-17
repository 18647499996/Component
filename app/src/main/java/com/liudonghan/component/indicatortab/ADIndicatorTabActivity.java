package com.liudonghan.component.indicatortab;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liudonghan.component.databinding.ActivityADIndicatorTabBinding;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.snackbar.ADSnackBarManager;

import java.util.Arrays;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADIndicatorTabActivity extends ADBaseActivity<ADIndicatorTabPresenter, ActivityADIndicatorTabBinding> implements ADIndicatorTabContract.View {

    @Override
    protected ActivityADIndicatorTabBinding getActivityBinding() throws RuntimeException {
        return ActivityADIndicatorTabBinding.inflate(getLayoutInflater());
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
    protected ADIndicatorTabPresenter createPresenter() throws RuntimeException {
        return (ADIndicatorTabPresenter) new ADIndicatorTabPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        mViewBinding.indicatorTab.setData(Arrays.asList("tab1", "tab2", "tab3"));

        mViewBinding.indicatorTab.setOnADIndicatorTabItemClickListener((text, position) -> {
            Log.i("Mac_Liu", "点击条目：" + text.getText());
            mViewBinding.indicatorTab.setDefaultPosition(1);
        });
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
    public void setPresenter(ADIndicatorTabContract.Presenter presenter) {
        mPresenter = (ADIndicatorTabPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }


}
