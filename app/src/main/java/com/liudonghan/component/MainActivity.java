package com.liudonghan.component;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.liudonghan.view.snackbar.SnackBar;
import com.liudonghan.view.snackbar.SnackBarManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.activity_main_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SnackBarManager.show(SnackBar
                        .with(MainActivity.this)
                        .position(SnackBar.SnackbarPosition.TOP)
                        .duration(1000)
                        .margin(15, 15)
                        .backgroundDrawable(R.drawable.corners_snack_bar_bg)
                        .text("哈哈哈哈哈"));
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
//                        .backgroundDrawable(R.drawable.corners_snack_bar_bg)
//                        .text("咕咕咕咕咕咕"));
            }
        });
    }
}