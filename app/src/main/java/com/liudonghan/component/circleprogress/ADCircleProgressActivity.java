package com.liudonghan.component.circleprogress;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.component.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.progress.ADCircleProgress;
import com.liudonghan.view.snackbar.ADSnackBarManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADCircleProgressActivity extends ADBaseActivity<ADCircleProgressPresenter> implements ADCircleProgressContract.View {
    private ADCircleProgress adCircleProgress;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_a_d_circle_progress;
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
        adCircleProgress = (ADCircleProgress) findViewById(R.id.circle);
        adCircleProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adCircleProgress.cancelAnimator();
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
