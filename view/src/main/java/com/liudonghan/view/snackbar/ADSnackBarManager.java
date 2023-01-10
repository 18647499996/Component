package com.liudonghan.view.snackbar;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;


import com.liudonghan.view.R;

import java.lang.ref.WeakReference;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/6/23
 */
public class ADSnackBarManager {

    private static volatile ADSnackBarManager instance = null;

    private ADSnackBarManager() {
    }

    public static ADSnackBarManager getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (ADSnackBarManager.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADSnackBarManager();
                }
            }
        }
        return instance;
    }

    private static final String TAG = ADSnackBarManager.class.getSimpleName();
    private static final Handler MAIN_THREAD = new Handler(Looper.getMainLooper());

    private static WeakReference<SnackBar> snackbarReference;


    public static void show(@NonNull SnackBar snackbar) {
        try {
            show(snackbar, (Activity) snackbar.getContext());
        } catch (ClassCastException e) {
            Log.e(TAG, "Couldn't get Activity from the Snackbar's Context. Try calling " +
                    "#show(Snackbar, Activity) instead", e);
        }
    }


    public static void show(@NonNull final SnackBar snackbar, @NonNull final Activity activity) {
        MAIN_THREAD.post(new Runnable() {
            @Override
            public void run() {
                SnackBar currentSnackbar = getCurrentSnackbar();
                if (currentSnackbar != null) {
                    if (currentSnackbar.isShowing() && !currentSnackbar.isDimissing()) {
                        currentSnackbar.dismissAnimation(false);
                        currentSnackbar.dismissByReplace();
                        snackbarReference = new WeakReference<>(snackbar);
                        snackbar.showAnimation(false);
                        snackbar.showByReplace(activity);
                        return;
                    }
                    currentSnackbar.dismiss();
                }
                snackbarReference = new WeakReference<>(snackbar);
                snackbar.show(activity);
            }
        });
    }


    public static void show(@NonNull SnackBar snackbar, @NonNull ViewGroup parent) {
        show(snackbar, parent, SnackBar.shouldUsePhoneLayout(snackbar.getContext()));
    }


    public static void show(@NonNull final SnackBar snackbar, @NonNull final ViewGroup parent,
                            final boolean usePhoneLayout) {
        MAIN_THREAD.post(new Runnable() {
            @Override
            public void run() {
                SnackBar currentSnackbar = getCurrentSnackbar();
                if (currentSnackbar != null) {
                    if (currentSnackbar.isShowing() && !currentSnackbar.isDimissing()) {
                        currentSnackbar.dismissAnimation(false);
                        currentSnackbar.dismissByReplace();
                        snackbarReference = new WeakReference<>(snackbar);
                        snackbar.showAnimation(false);
                        snackbar.showByReplace(parent, usePhoneLayout);
                        return;
                    }
                    currentSnackbar.dismiss();
                }
                snackbarReference = new WeakReference<>(snackbar);
                snackbar.show(parent, usePhoneLayout);
            }
        });
    }


    public static void dismiss() {
        final SnackBar currentSnackbar = getCurrentSnackbar();
        if (currentSnackbar != null) {
            MAIN_THREAD.post(new Runnable() {
                @Override
                public void run() {
                    currentSnackbar.dismiss();
                }
            });
        }
    }

    /**
     * Return the current Snackbar
     */
    public static SnackBar getCurrentSnackbar() {
        if (snackbarReference != null) {
            return snackbarReference.get();
        }
        return null;
    }

    /**
     * 显示悬浮提示框（ 异常 ）
     *
     * @param context 上下文
     * @param msg     提示语
     */
    public void showError(Context context, String msg) {
        show(context, msg, R.drawable.corners_bg_bar_error);
    }

    /**
     * 显示悬浮提示框（ 成功）
     *
     * @param context 上下文
     * @param msg     提示语
     */
    public void showSucceed(Context context, String msg) {
        show(context, msg, R.drawable.corners_bg_bar_succeed);
    }

    /**
     * 显示悬浮提示框（ 警告 ）
     *
     * @param context 上下文
     * @param msg     提示语
     */
    public void showWarn(Context context, String msg) {
        show(context, msg, R.drawable.corners_bg_bar_warn);
    }

    /**
     * 显示悬浮提示框
     *
     * @param context 上下文
     * @param msg     提示语
     * @param resId   设置背景
     */
    public void show(Context context, String msg, int resId) {
        show(context, msg, resId, 15, 220);
    }

    /**
     * 显示悬浮提示框
     *
     * @param context 上下文
     * @param msg     提示语
     * @param resId   设置背景
     */
    public void show(Context context, String msg, int resId, int marginLeft, int marginTop) {
        show(context, msg, resId, marginLeft, marginTop, SnackBar.SnackbarPosition.TOP);
    }

    /**
     * 显示悬浮提示框
     *
     * @param context 上下文
     * @param msg     提示语
     * @param resId   设置背景
     */
    public void show(Context context, String msg, int resId, int marginLeft, int marginTop, SnackBar.SnackbarPosition position) {
        if (null == context) {
            return;
        }
        show(SnackBar
                .with(context)
                .position(position)
                .duration(1000)
                .margin(marginLeft, marginTop)
                .backgroundDrawable(resId)
                .text(msg));
    }
}