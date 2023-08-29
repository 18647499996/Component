package com.liudonghan.component.imageview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.liudonghan.component.MainActivity;
import com.liudonghan.component.PhotoActivity;
import com.liudonghan.component.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.radius.ADImageView;
import com.liudonghan.view.snackbar.ADSnackBarManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADImageViewActivity extends ADBaseActivity<ADImageViewPresenter> implements ADImageViewContract.View {

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_a_d_image_view;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected ADImageViewPresenter createPresenter() throws RuntimeException {
        return (ADImageViewPresenter) new ADImageViewPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        findViewById(R.id.activity_main_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ADImageViewActivity.this, PhotoActivity.class));
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
    public void setPresenter(ADImageViewContract.Presenter presenter) {
        mPresenter = (ADImageViewPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }


}
