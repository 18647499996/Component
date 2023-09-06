package com.liudonghan.component.field;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liudonghan.component.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.field.ADFieldTextLayout;
import com.liudonghan.view.snackbar.ADSnackBarManager;
import com.liudonghan.view.title.ADTitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class FieldActivity extends ADBaseActivity<FieldPresenter> implements FieldContract.View, ADFieldTextLayout.OnADFieldTextLayoutListener {

    @BindView(R.id.field)
    ADFieldTextLayout field;
    @BindView(R.id.field1)
    ADFieldTextLayout field1;
    @BindView(R.id.field2)
    ADFieldTextLayout field2;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_field;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return new ADTitleBuilder(this).setMiddleTitleBgRes("Field").setLeftImageRes(R.drawable.ad_back_black).setLeftRelativeLayoutListener(this);
    }

    @Override
    protected FieldPresenter createPresenter() throws RuntimeException {
        return (FieldPresenter) new FieldPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        field.getTextViewTitle().setText("开户行信息");
    }

    @Override
    protected void addListener() throws RuntimeException {
        field.setOnADFieldTextLayoutListener(this);
        field1.setOnADFieldTextLayoutListener(this);
        field2.setOnADFieldTextLayoutListener(this);
    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(FieldContract.Presenter presenter) {
        mPresenter = (FieldPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }

    @Override
    public void onField(View view, String title) {
        Log.i("Mac_Liu", "点击按钮：" + title);
    }

}
