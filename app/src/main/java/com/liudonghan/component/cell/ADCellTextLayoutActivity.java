package com.liudonghan.component.cell;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.component.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.cell.ADCellTextLayout;
import com.liudonghan.view.snackbar.ADSnackBarManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADCellTextLayoutActivity extends ADBaseActivity<ADCellTextLayoutPresenter> implements ADCellTextLayoutContract.View {
    private ADCellTextLayout adCellTextLayout;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_a_d_cell_text_layout;
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
        adCellTextLayout = (ADCellTextLayout) findViewById(R.id.activity_main_cg_one);
        adCellTextLayout.setLeftText("动态设置");
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
