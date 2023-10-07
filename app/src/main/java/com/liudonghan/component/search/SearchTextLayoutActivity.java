package com.liudonghan.component.search;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import com.liudonghan.component.R;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.search.ADSearchTextLayout;
import com.liudonghan.view.snackbar.ADSnackBarManager;

import butterknife.BindView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class SearchTextLayoutActivity extends ADBaseActivity<SearchTextLayoutPresenter> implements SearchTextLayoutContract.View, ADSearchTextLayout.OnADSearchTextLayoutListener, ADSearchTextLayout.OnADSearchTextLayoutClickListener {

    @BindView(R.id.search_text_layout)
    ADSearchTextLayout searchTextLayout;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_search_text_layout;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected SearchTextLayoutPresenter createPresenter() throws RuntimeException {
        return (SearchTextLayoutPresenter) new SearchTextLayoutPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {

    }

    @Override
    protected void addListener() throws RuntimeException {
        searchTextLayout.setOnADSearchTextLayoutListener(this);
        searchTextLayout.setOnADSearchTextLayoutClickListener(this);
    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(SearchTextLayoutContract.Presenter presenter) {
        mPresenter = (SearchTextLayoutPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClickContent() {
        ADSnackBarManager.getInstance().showWarn(this, "点击跳转搜索详情");
    }

    @Override
    public void onClickLeft() {
        SearchTextLayoutActivity.this.finish();
    }

    @Override
    public void onClickRight() {
        ADSnackBarManager.getInstance().showWarn(this, "点击进入");
    }

    @Override
    public void onClickClear() {
        ADSnackBarManager.getInstance().showWarn(this, "清空输入框");
    }
}
