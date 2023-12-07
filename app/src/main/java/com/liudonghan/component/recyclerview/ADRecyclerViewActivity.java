package com.liudonghan.component.recyclerview;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liudonghan.component.R;
import com.liudonghan.component.adapter.ADRecyclerViewAdapter;
import com.liudonghan.component.adapter.ADRecyclerViewFlowAdapter;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.recycler.ADRecyclerView;
import com.liudonghan.view.recycler.PagerLayoutManager;
import com.liudonghan.view.snackbar.ADSnackBarManager;

import java.util.Arrays;
import java.util.Objects;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADRecyclerViewActivity extends ADBaseActivity<ADRecyeclerViewPresenter> implements ADRecyeclerViewContract.View, BaseQuickAdapter.OnItemClickListener, PagerLayoutManager.OnViewPagerListener {

    private ADRecyclerViewAdapter adRecyclerViewAdapter;
    private ADRecyclerView adRecyclerView, recyclerViewFlow, recyclerViewPage;
    private String[] array = new String[]{
            "验证码输入框", "ADButton", "ADImageView", "ADCircleProgress",
            "ADCityView", "ADIndicatorTab", "ADConstraintLayout", "ADCellTextLayout",
            "ADTextView", "ADVoiceRecorderButton", "日历组件"};
    private int indexPosition;

    @Override
    protected int getLayout() throws RuntimeException {
        return R.layout.activity_a_d_recycler_view;
    }

    @Override
    protected Object initBuilderTitle() throws RuntimeException {
        return null;
    }

    @Override
    protected ADRecyeclerViewPresenter createPresenter() throws RuntimeException {
        return (ADRecyeclerViewPresenter) new ADRecyeclerViewPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        adRecyclerView = (ADRecyclerView) findViewById(R.id.recyclerView);
        adRecyclerViewAdapter = new ADRecyclerViewAdapter(R.layout.item_recyclerview);
        adRecyclerView.setAdapter(adRecyclerViewAdapter);
        adRecyclerViewAdapter.setNewData(Arrays.asList(array));

        recyclerViewFlow = (ADRecyclerView) findViewById(R.id.recyclerView_flow);
        recyclerViewFlow.setAdapter(new ADRecyclerViewFlowAdapter(R.layout.item_recyclerview_flow, Arrays.asList(array)));
//        recyclerViewFlow.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//                outRect.set(10, 10, 10, 10);
//            }
//        });
        recyclerViewPage = (ADRecyclerView) findViewById(R.id.recyclerView_page);
        recyclerViewPage.setAdapter(new ADRecyclerViewFlowAdapter(R.layout.item_recyclerview_page, Arrays.asList(array)));
    }

    @Override
    protected void addListener() throws RuntimeException {
        adRecyclerViewAdapter.setOnItemClickListener(this);
        recyclerViewPage.getPagerLayoutManager().setOnViewPagerListener(this);
    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(ADRecyeclerViewContract.Presenter presenter) {
        mPresenter = (ADRecyeclerViewPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Log.i("Mac_Liu", Objects.requireNonNull(adRecyclerViewAdapter.getItem(position)));
        adRecyclerView.getCenterLayoutManager().smoothScrollToPosition(adRecyclerView, position);
    }

    @Override
    public void onInitComplete(View view) {
        Log.i("Mac_Liu","onInitComplete");
    }

    @Override
    public void onPageRelease(boolean isNext, int position, View view) {
        Log.i("Mac_Liu","onPageRelease：" + isNext + "   position " + position);
    }

    @Override
    public void onPageSelected(int position, boolean isBottom, View view) {
        Log.i("Mac_Liu","onPageSelected：" + isBottom + "   position " + position);
    }
}
