package com.liudonghan.component.textview;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.component.MainActivity;
import com.liudonghan.component.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.cell.ADCellTextLayout;
import com.liudonghan.view.snackbar.ADSnackBarManager;
import com.liudonghan.view.snackbar.SnackBar;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADTextViewActivity extends ADBaseActivity<ADTextViewPresenter> implements ADTextViewContract.View {



    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_a_d_text_view;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected ADTextViewPresenter createPresenter() throws RuntimeException {
        return (ADTextViewPresenter) new ADTextViewPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        findViewById(R.id.activity_main_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ADSnackBarManager.show(SnackBar
                        .with(ADTextViewActivity.this)
                        .position(SnackBar.SnackbarPosition.TOP)
                        .duration(1000)
                        .margin(15, 15)
                        .backgroundDrawable(R.drawable.ad_snack_bar_bg)
                        .text("哈哈哈哈哈"));
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
    public void setPresenter(ADTextViewContract.Presenter presenter) {
        mPresenter = (ADTextViewPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }


}
