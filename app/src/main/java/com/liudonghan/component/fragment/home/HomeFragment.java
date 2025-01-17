package com.liudonghan.component.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liudonghan.component.MainActivity;
import com.liudonghan.component.R;
import com.liudonghan.component.databinding.FragmentHomeBinding;
import com.liudonghan.mvp.ADBaseFragment;
import com.liudonghan.view.snackbar.ADSnackBarManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class HomeFragment extends ADBaseFragment<HomePresenter, FragmentHomeBinding> implements HomeContract.View {

    @Override
    protected View getViewBindingLayout() {
        return mViewBinding.getRoot();
    }

    @Override
    protected FragmentHomeBinding getFragmentViewBinding() {
        return FragmentHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Object initBuilderTitle(View view) throws RuntimeException {
        return null;
    }

    @Override
    protected HomePresenter createPresenter() {
        return (HomePresenter) new HomePresenter(this).builder(getActivity());
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        Log.i("Mac_Liu","创建Home");
    }

    @Override
    protected void onClickDoubleListener(View paramView) throws RuntimeException {

    }

    @Override
    protected void setListener() throws RuntimeException {

    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = (HomePresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(getActivity(), msg);
    }
}