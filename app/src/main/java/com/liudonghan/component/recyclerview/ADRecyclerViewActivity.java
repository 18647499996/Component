package com.liudonghan.component.recyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liudonghan.component.R;
import com.liudonghan.component.adapter.ADRecyclerViewAdapter;
import com.liudonghan.component.adapter.ADRecyclerViewPageAdapter;
import com.liudonghan.component.databinding.ActivityADRecyclerViewBinding;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.recycler.ADRecyclerView;
import com.liudonghan.view.recycler.PagerLayoutManager;
import com.liudonghan.view.snackbar.ADSnackBarManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADRecyclerViewActivity extends ADBaseActivity<ADRecyeclerViewPresenter, ActivityADRecyclerViewBinding> implements ADRecyeclerViewContract.View, BaseQuickAdapter.OnItemClickListener, PagerLayoutManager.OnViewPagerListener {

    private ADRecyclerViewAdapter adRecyclerViewAdapter;
    private ADRecyclerViewPageAdapter adRecyclerViewPageAdapter;
    private ADRecyclerView adRecyclerView, recyclerViewFlow, recyclerViewPage;
    private String[] array = new String[]{
            "验证码输入框", "ADButton", "ADImageView", "ADCircleProgress",
            "ADCityView", "ADIndicatorTab", "ADConstraintLayout", "ADCellTextLayout",
            "ADTextView", "ADVoiceRecorderButton", "日历组件"};
    private List<String> play = new ArrayList<>();
    private int currentPosition;

    @Override
    protected ActivityADRecyclerViewBinding getActivityBinding() throws RuntimeException {
        return ActivityADRecyclerViewBinding.inflate(getLayoutInflater());
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
    protected ADRecyeclerViewPresenter createPresenter() throws RuntimeException {
        return (ADRecyeclerViewPresenter) new ADRecyeclerViewPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {
        immersionBar.transparentStatusBar().statusBarDarkFont(false).init();
        play.add("https://recordcdn.quklive.com/upload/vod/user1462960877450854/1702396014865166/8/video.m3u8");
        play.add("https://findermp.video.qq.com/251/20302/stodownload?encfilekey=oibeqyX228riaCwo9STVsGLPj9UYCicgttv2KWPY60smYM2qLOqwGQZqLkjGSPFc8AoL29xOOoUksTNeGtfTV6KBySEMaKwl6RyOUzp1714kbopib8RvqianG0fhJYVaeSibzs1ugiau02k9Bw&a=1&bizid=1023&dotrans=0&hy=SH&idx=1&m=5fadd3e3712e860e8f1d948d047fab3a&upid=500170&token=ic1n0xDG6awicz0rLxFEoGHPzOhDrIjZTic5RdOZTpaVwrhRDVwfXiaJZLrrxQaTFBzCne7Ls7pQAh4&X-snsvideoflag=xV1");
        play.add("https://findermp.video.qq.com/251/20302/stodownload?encfilekey=6xykWLEnztKcKCJZcV0rWCM8ua7DibZkibPSZaIgeFjxGjtP618ibg8Qa0VpyhR97XiaPOWaYs9f8ic6lwCg6nMdDcpdmcHJ5IqiaIJ5kK7aHRxRGBAdg5FIBzj8v5fqYx33l2pFRFburvK3jF8dSlbbVuGxjezibAFBRFGG6tLAjp1rkU&a=1&bizid=1023&dotrans=0&hy=SZ&idx=1&m=67812ad41291c216ba9c232ce80ec3d8&upid=500100&token=AxricY7RBHdW6neDJ09QweDVYdhsMiaKIu36AcK9vygMZZbExtlMNVaqZS43NB0GubNpgIyibUomTg&X-snsvideoflag=xV1");
        play.add("https://findermp.video.qq.com/251/20302/stodownload?encfilekey=Cvvj5Ix3eewK0tHtibORqcsqchXNh0Gf3sJcaYqC2rQDHlnqrsme71ydWRL3IxkpZeA0M1ex5bhQVkhjtqYRdNFScXnLKyyYa2DarpqJCJTLJiaiciboG81thhkge82RD7aV&bizid=1023&dotrans=0&hy=SH&idx=1&m=&token=AxricY7RBHdVoLVOwMyFZDTBjbNnpdowV9f9Jrl6NBR9hR9l1ic655qiabEhRIibTeYcMra4yJs0Ahk&X-snsvideoflag=xV1");
        play.add("https://findermp.video.qq.com/251/20302/stodownload?encfilekey=6xykWLEnztKcKCJZcV0rWCM8ua7DibZkibPSZaIgeFjxF3lNXAST3ocicLbUtHNa8Mv1u33bTAfqxmK8RibYwViaeA8pWWw2pVgJFXcBJXB2DLzASrpvNOV7PD3aic0mlNKdCUg1Vic44u18wiba34HwfEt7Ghzwia9b1m6LFNibnj53CNK8c&a=1&bizid=1023&dotrans=0&hy=SZ&idx=1&m=839fbbbb5aff00f88a4326359f5c4303&upid=500200&token=ic1n0xDG6aw8t2OZBicR8d2UeaQRznqwj9gicrWKvnx26B7Mwtgfps1KkZup2GvIsWHPh6oUibYpq4Y&X-snsvideoflag=xV1");
        play.add("https://findermp.video.qq.com/251/20302/stodownload?encfilekey=Cvvj5Ix3eez3Y79SxtvVL0L7CkPM6dFibFeI6caGYwFGJUnNY5spQttL4dv0SVkxuL7KpfaYHR9yrN0WxLnBjObRKzPfBpjCEPOHr8Y8Cg2Ayv6FCEPU92rKUbuYhbzJsayZQR79N4MzfZcY8y09H3g&a=1&bizid=1023&dotrans=0&hy=SH&idx=1&m=f89d8bbedfaacedaa4591dfed9b0a524&upid=500100&token=x5Y29zUxcibAOs948GpcXxv2tibY4v815lyYqNicObCnn7IqmKL4aibOlNibfos4g5hGvCKwKSO9fMrk&X-snsvideoflag=xV1");
        play.add("https://findermp.video.qq.com/251/20302/stodownload?encfilekey=6xykWLEnztKcKCJZcV0rWCM8ua7DibZkibqXGfPxf5lrqqhEVZAKAW0stWNRgACXYbh12ibbuU4ic6Fpttu6dpbkAtVgBQ9mc5dCqKI1U63WtpzB9SHzpia2SDWMAwvP6SwicL4icZpFv0Mziawn9bC6oanNibkKjuU6JJjGia5QEmawKjFMU&a=1&bizid=1023&dotrans=0&hy=SH&idx=1&m=7399c3195c3b482512382ffa9d520265&upid=290150&token=AxricY7RBHdW8icNhSyia8XB0EezR01qvRARib2A1WcUDfMhBS2VUCsAI1eCdOGfsibuiaibUvHibkaMHaY&X-snsvideoflag=xV1");
        adRecyclerView = (ADRecyclerView) findViewById(R.id.recyclerView);
        adRecyclerViewAdapter = new ADRecyclerViewAdapter(R.layout.item_recyclerview);
        adRecyclerView.setAdapter(adRecyclerViewAdapter);
        adRecyclerViewAdapter.setNewData(Arrays.asList(array));

        recyclerViewFlow = (ADRecyclerView) findViewById(R.id.recyclerView_flow);
        recyclerViewFlow.setAdapter(new ADRecyclerViewAdapter(R.layout.item_recyclerview_flow, Arrays.asList(array)));
//        recyclerViewFlow.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//                outRect.set(10, 10, 10, 10);
//            }
//        });
        recyclerViewPage = (ADRecyclerView) findViewById(R.id.recyclerView_page);
        adRecyclerViewPageAdapter = new ADRecyclerViewPageAdapter(R.layout.item_recyclerview_page, play);
        recyclerViewPage.setAdapter(adRecyclerViewPageAdapter);
    }

    @Override
    protected void addListener() throws RuntimeException {
        adRecyclerViewAdapter.setOnItemClickListener(this);
        recyclerViewPage.getPagerLayoutManager().setOnPageChangeListener(this);
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
    public void onInitComplete(View view, int currentPosition) {
        Log.i("Mac_Liu", "onInitComplete");
        mPresenter.startPlay(currentPosition, view, adRecyclerViewPageAdapter, null, false);
    }

    @Override
    public void onPageRelease(int currentPosition, View view) {
        Log.i("Mac_Liu", "onPageRelease： position " + currentPosition);

    }

    @Override
    public void onPageSelected(int currentPosition, boolean isBottom, View view) {
        this.currentPosition = currentPosition;
        Log.i("Mac_Liu", "onPageSelected：" + isBottom + "   position " + currentPosition);
        mPresenter.startPlay(currentPosition, view, adRecyclerViewPageAdapter, null, isBottom);
    }

}
