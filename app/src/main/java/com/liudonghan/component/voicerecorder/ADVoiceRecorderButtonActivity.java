package com.liudonghan.component.voicerecorder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liudonghan.component.R;
import com.liudonghan.component.databinding.ActivityADVoiceRecorderButtonBinding;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.snackbar.ADSnackBarManager;
import com.liudonghan.view.voice.ADVoiceRecorderButton;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADVoiceRecorderButtonActivity extends ADBaseActivity<ADVoiceRecorderButtonPresenter, ActivityADVoiceRecorderButtonBinding> implements ADVoiceRecorderButtonContract.View, ADVoiceRecorderButton.OnADVoiceRecorderButtonListener {

    @Override
    protected ActivityADVoiceRecorderButtonBinding getActivityBinding() throws RuntimeException {
        return ActivityADVoiceRecorderButtonBinding.inflate(getLayoutInflater());
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
    protected ADVoiceRecorderButtonPresenter createPresenter() throws RuntimeException {
        return (ADVoiceRecorderButtonPresenter) new ADVoiceRecorderButtonPresenter(this).builder(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) throws RuntimeException {

    }

    @Override
    protected void addListener() throws RuntimeException {
        mViewBinding.btn1.setOnADVoiceRecorderButtonListener(this);
    }

    @Override
    protected void onClickDoubleListener(View view) throws RuntimeException {

    }

    @Override
    protected void onDestroys() throws RuntimeException {

    }

    @Override
    public void setPresenter(ADVoiceRecorderButtonContract.Presenter presenter) {
        mPresenter = (ADVoiceRecorderButtonPresenter) checkNotNull(presenter);
    }

    @Override
    public void showErrorMessage(String msg) {
        ADSnackBarManager.getInstance().showError(this, msg);
    }


    @Override
    public void onShortLimit() {
        ADSnackBarManager.getInstance().showWarn(this, "录音时间太短");
    }

    @Override
    public void onRangeLimit() {
        ADSnackBarManager.getInstance().showWarn(this, "取消录音发送");
    }

    @Override
    public void onAudioSucceed(String filePath, long duration) {
        Log.i("Mac_Liu", "voice file path ： " + filePath);
    }
}
