package com.liudonghan.component.fragment.mine;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.component.MainActivity;
import com.liudonghan.component.R;
import com.liudonghan.mvp.ADBaseFragment;
import com.liudonghan.view.snackbar.ADSnackBarManager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class MineFragment extends ADBaseFragment<MinePresenter, MainActivity> implements MineContract.View {
    @Override
    protected int loadViewLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected Object initBuilderTitle(View view) throws RuntimeException {
        return null;
    }

    @Override
    protected MinePresenter createPresenter() {
        return (MinePresenter) new MinePresenter(this).builder(getActivity());
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {

    }

    @Override
    protected void onClickDoubleListener(View paramView) throws RuntimeException {

    }

    @Override
    protected void setListener() throws RuntimeException {

    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        mPresenter = (MinePresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(getActivity(), msg);
    }
}