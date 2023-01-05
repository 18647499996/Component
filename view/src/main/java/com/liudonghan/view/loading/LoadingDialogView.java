package com.liudonghan.view.loading;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.liudonghan.view.R;

import java.util.Objects;


/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/27
 */
public class LoadingDialogView {
    private Dialog popupWindow;
    private View contentView;
    private Activity activity;

    @SuppressLint("StaticFieldLeak")
    private static volatile LoadingDialogView instance = null;

    private LoadingDialogView() {
    }

    public static LoadingDialogView getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (LoadingDialogView.class) {
                // double checkout
                if (null == instance) {
                    instance = new LoadingDialogView();
                }
            }
        }
        return instance;
    }

    /**
     * 创建PopupWindow
     */
    public void init(Activity activity, String tip, boolean isLogin) {
        this.activity = activity;
        if (null != popupWindow) {
            if (popupWindow.isShowing()) {
                return;
            }
        }
        contentView = View.inflate(activity, R.layout.dialog_loading, null);
        popupWindow = new Dialog(activity, R.style.Base_Dialog);
        // 去除标题
        popupWindow.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 添加一个自定义布局
        popupWindow.setContentView(contentView);
        TextView textView = contentView.findViewById(R.id.tipTextView);
        ImageView spaceshipImage = contentView.findViewById(R.id.img);
        spaceshipImage.setBackgroundResource(R.drawable.progress_pull);
        AnimationDrawable animationDrawable = (AnimationDrawable) spaceshipImage.getBackground();
        animationDrawable.start();
        if (TextUtils.isEmpty(tip)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(tip);
            textView.setVisibility(View.VISIBLE);
        }
        // 设置对话框的大小
        WindowManager wm = activity.getWindowManager();
        WindowManager.LayoutParams params = Objects.requireNonNull(popupWindow.getWindow()).getAttributes();
        // 获取屏幕宽高
        Display d = wm.getDefaultDisplay();
        // 设置高度为屏幕的0.6
        params.width = (int) d.getWidth();
        popupWindow.getWindow().setAttributes(params);
        popupWindow.getWindow().setDimAmount(0f);
        popupWindow.setCanceledOnTouchOutside(false);

        show();
    }


    private void show() {
        if (null != popupWindow) {
            if (popupWindow.isShowing()) {
                return;
            }
            popupWindow.show();
        }
    }

    public void dismiss() {
        try {
            if (null != popupWindow) {
                popupWindow.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 销毁ac引用
     */
    public void destroy() {
        if (null != popupWindow && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
        activity = null;
        contentView = null;
        popupWindow = null;
    }
}
