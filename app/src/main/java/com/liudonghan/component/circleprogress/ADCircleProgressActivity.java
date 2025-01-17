package com.liudonghan.component.circleprogress;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.component.databinding.ActivityADCircleProgressBinding;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.snackbar.ADSnackBarManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADCircleProgressActivity extends ADBaseActivity<ADCircleProgressPresenter, ActivityADCircleProgressBinding> implements ADCircleProgressContract.View {

    @Override
    protected ActivityADCircleProgressBinding getActivityBinding() throws RuntimeException {
        return ActivityADCircleProgressBinding.inflate(getLayoutInflater());
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
    protected ADCircleProgressPresenter createPresenter() throws RuntimeException {
        return (ADCircleProgressPresenter) new ADCircleProgressPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        mViewBinding.circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewBinding.circle.cancelAnimator();
            }
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
    public void setPresenter(ADCircleProgressContract.Presenter presenter) {
        mPresenter = (ADCircleProgressPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }


}
