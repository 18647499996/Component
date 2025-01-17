package com.liudonghan.component.search;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import com.liudonghan.component.databinding.ActivitySearchTextLayoutBinding;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.search.ADSearchTextLayout;
import com.liudonghan.view.snackbar.ADSnackBarManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class SearchTextLayoutActivity extends ADBaseActivity<SearchTextLayoutPresenter, ActivitySearchTextLayoutBinding> implements SearchTextLayoutContract.View, ADSearchTextLayout.OnADSearchTextLayoutListener, ADSearchTextLayout.OnADSearchTextLayoutClickListener {

    @Override
    protected ActivitySearchTextLayoutBinding getActivityBinding() throws RuntimeException {
        return ActivitySearchTextLayoutBinding.inflate(getLayoutInflater());
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
    protected SearchTextLayoutPresenter createPresenter() throws RuntimeException {
        return (SearchTextLayoutPresenter) new SearchTextLayoutPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {

    }

    @Override
    protected void addListener() throws RuntimeException {
        mViewBinding.searchTextLayout.setOnADSearchTextLayoutListener(this);
        mViewBinding.searchTextLayout.setOnADSearchTextLayoutClickListener(this);
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
