package com.liudonghan.component.tabhost;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.component.R;
import com.liudonghan.component.databinding.ActivityFragmentTabHostBinding;
import com.liudonghan.component.fragment.home.HomeFragment;
import com.liudonghan.component.fragment.mine.MineFragment;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.snackbar.ADSnackBarManager;
import com.liudonghan.view.tabhost.ADFragmentTabHost;
import com.liudonghan.view.tabhost.ADNavigationEntity;
import com.liudonghan.view.tabhost.FragmentTabHost;
import com.liudonghan.view.tabhost.TabHostAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class FragmentTabHostActivity extends ADBaseActivity<FragmentTabHostPresenter, ActivityFragmentTabHostBinding> implements FragmentTabHostContract.View, ADFragmentTabHost.OnADFragmentTabHostListener {

    @Override
    protected ActivityFragmentTabHostBinding getActivityBinding() throws RuntimeException {
        return ActivityFragmentTabHostBinding.inflate(getLayoutInflater());
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
    protected FragmentTabHostPresenter createPresenter() throws RuntimeException {
        return (FragmentTabHostPresenter) new FragmentTabHostPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        List<ADNavigationEntity> list = new ArrayList<>();
        list.add(new ADNavigationEntity(R.drawable.icon_home_off, R.drawable.icon_home_on, new HomeFragment(), true));
        list.add(new ADNavigationEntity(R.drawable.icon_like_off, R.drawable.icon_like_on, new MineFragment(), false));
        list.add(new ADNavigationEntity(R.drawable.icon_faxian_off, R.drawable.icon_faxian_on, new HomeFragment(), false));
        mViewBinding.activityTabHost.setData(list);
    }

    @Override
    protected void addListener() throws RuntimeException {
        mViewBinding.activityTabHost.setOnADFragmentTabHostListener(this);
        mViewBinding.activityTabHost.setUnreadCount(1, 20);
    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(FragmentTabHostContract.Presenter presenter) {
        mPresenter = (FragmentTabHostPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }

    @Override
    public void onTabHost(ADNavigationEntity item, int position, FragmentTabHost fragmentTabHost, TabHostAdapter tabHostAdapter) {

    }
}
