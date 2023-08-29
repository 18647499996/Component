package com.liudonghan.component.indicatortab;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liudonghan.component.MainActivity;
import com.liudonghan.component.R;
import com.liudonghan.component.calendar.CalendarActivity;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.indicator.ADIndicatorTab;
import com.liudonghan.view.indicator.Tab;
import com.liudonghan.view.snackbar.ADSnackBarManager;

import java.util.Arrays;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADIndicatorTabActivity extends ADBaseActivity<ADIndicatorTabPresenter> implements ADIndicatorTabContract.View {

    private ADIndicatorTab adIndicatorTab;


    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_a_d_indicator_tab;
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
        adIndicatorTab = (ADIndicatorTab) findViewById(R.id.indicator_tab);
        adIndicatorTab.setData(Arrays.asList("tab1", "tab2", "tab3"));

        adIndicatorTab.setOnADIndicatorTabItemClickListener(new ADIndicatorTab.OnADIndicatorTabItemClickListener() {
            @Override
            public void onTabItemClick(Tab.Column text, int position) {
                Log.i("Mac_Liu", "点击条目：" + text.getText());
                adIndicatorTab.setDefaultPosition(1);
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
    public void setPresenter(ADIndicatorTabContract.Presenter presenter) {
        mPresenter = (ADIndicatorTabPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }


}
