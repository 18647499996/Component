package com.liudonghan.component.constraintLayout;

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
public class ADConstraintLayoutActivity extends ADBaseActivity<ADConstraintLayoutPresenter> implements ADConstraintLayoutContract.View {

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_a_d_constraint_layout;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected ADConstraintLayoutPresenter createPresenter() throws RuntimeException {
        return (ADConstraintLayoutPresenter) new ADConstraintLayoutPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        findViewById(R.id.activity_main_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SnackbarManager.show(Snackbar
//                        .with(MainActivity.this)
//                        .position(Snackbar.SnackbarPosition.TOP)
//                        .duration(1000)
//                        .margin(15, 15)
//                        .backgroundDrawable(R.drawable.ad_snack_bar_bg)
//                        .text("咕咕咕咕咕咕"));
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
    public void setPresenter(ADConstraintLayoutContract.Presenter presenter) {
        mPresenter = (ADConstraintLayoutPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }


}
