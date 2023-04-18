package com.liudonghan.component;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.liudonghan.view.cell.ADCellTextLayout;
import com.liudonghan.view.city.ADCityView;
import com.liudonghan.view.progress.ADCircleProgress;
import com.liudonghan.view.snackbar.SnackBar;
import com.liudonghan.view.snackbar.ADSnackBarManager;
import com.liudonghan.view.voice.ADVoiceRecorderButton;

public class MainActivity extends AppCompatActivity implements ADVoiceRecorderButton.OnADVoiceRecorderButtonListener {

    private ADCellTextLayout adCellTextLayout;
    private ADVoiceRecorderButton adVoiceRecorderButton;
    private ADCircleProgress adCircleProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adCellTextLayout = findViewById(R.id.activity_main_cg_one);
        ADCityView adCityView = findViewById(R.id.activity_main_city);
        adVoiceRecorderButton = findViewById(R.id.btn_1);
        adCityView.getViewSwitcher().setDisplayedChild(0);
        adCityView.getProgressBar().setIndeterminate(true);
        adCityView.setProgressBarBgColor(R.color.color_342e2e);
        adCircleProgress = findViewById(R.id.circle);
        adCircleProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adCircleProgress.cancelAnimator();
            }
        });
        adCellTextLayout.setLeftText("动态设置");
        findViewById(R.id.activity_main_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ADSnackBarManager.show(SnackBar
                        .with(MainActivity.this)
                        .position(SnackBar.SnackbarPosition.TOP)
                        .duration(1000)
                        .margin(15, 15)
                        .backgroundDrawable(R.drawable.ad_snack_bar_bg)
                        .text("哈哈哈哈哈"));
            }
        });
        findViewById(R.id.activity_main_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PhotoActivity.class));
            }
        });

        findViewById(R.id.activity_main_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SnackbarManager.show(Snackbar
//                        .with(MainActivity.this)
//                        .position(Snackbar.SnackbarPosition.TOP)
//                        .duration(1000)
//                        .margin(15, 15)
//                        .backgroundDrawable(R.drawable.ad_snack_bar_bg)
//                        .text("咕咕咕咕咕咕"));
            }
        });
        adVoiceRecorderButton.setOnADVoiceRecorderButtonListener(this);
    }

    @Override
    public void onShortLimit() {
        ADSnackBarManager.getInstance().showWarn(this,"录音时间太短");
    }

    @SuppressLint("ShowToast")
    @Override
    public void onRangeLimit() {
        ADSnackBarManager.getInstance().showWarn(this,"取消录音发送");
    }

    @Override
    public void onAudioSucceed(String filePath, long duration) {
        Log.i("Mac_Liu","voice file path ： " + filePath);
    }
}