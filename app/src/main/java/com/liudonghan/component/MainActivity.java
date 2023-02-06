package com.liudonghan.component;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.liudonghan.view.cell.ADCellTextLayout;
import com.liudonghan.view.snackbar.SnackBar;
import com.liudonghan.view.snackbar.ADSnackBarManager;

public class MainActivity extends AppCompatActivity {

    private ADCellTextLayout adCellTextLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adCellTextLayout = findViewById(R.id.activity_main_cg_one);
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
    }
}