package com.liudonghan.view.title;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;

import com.liudonghan.view.R;
import com.liudonghan.view.radius.ADRelativeLayout;
import com.liudonghan.view.radius.ADTextView;


/**
 * ============================================================
 * <p/>
 * 版 权 ： 诺易(北京)科技服务有限公司
 * <p/>
 * 作 者 ： Li_Min
 * <p/>
 * 版 本 ： 1.0
 * <p/>
 * 创建日期 ：2017/3/17
 * <p/>
 * 描 述 ： 通用标题栏
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 **/
public class ADTitleBuilder {

    /**
     * 父组件
     */
    private final View titleView;

    /**
     * 左边相对布局
     */
    private final ADRelativeLayout relativeLayoutTitleLeft;

    /**
     * 左边TextView
     */
    private final ADTextView textViewTitleLeft;


    /**
     * 中间TextView标题
     */
    private final ADTextView textViewTitleCenter;

    /**
     * 右边相对布局
     */
    private final ADRelativeLayout relativeLayoutTitleRight;

    /**
     * 右边TextView
     */
    private final ADTextView textViewTitleRight;

    /**
     * 分割线
     */
    private final View viewTitleDivider;

    private final Context context;

    public Context getContext() {
        return context;
    }

    public View getTitleView() {
        return titleView;
    }

    public ADRelativeLayout getRelativeLayoutTitleLeft() {
        return relativeLayoutTitleLeft;
    }

    public ADTextView getTextViewTitleLeft() {
        return textViewTitleLeft;
    }

    public ADTextView getTextViewTitleCenter() {
        return textViewTitleCenter;
    }

    public ADRelativeLayout getRelativeLayoutTitleRight() {
        return relativeLayoutTitleRight;
    }

    public ADTextView getTextViewTitleRight() {
        return textViewTitleRight;
    }

    public View getViewTitleDivider() {
        return viewTitleDivider;
    }

    /**
     * 构建标题实例
     * @param view 标题view组件
     */
    public ADTitleBuilder(View view) {
        this.context = view.getContext();
        titleView = view;
        textViewTitleCenter = view.findViewById(R.id.ad_title_center_tv_title);
        relativeLayoutTitleLeft = view.findViewById(R.id.ad_title_left_rel);
        textViewTitleLeft = view.findViewById(R.id.ad_title_left_tv);
        relativeLayoutTitleRight = view.findViewById(R.id.ad_title_right_rel);
        textViewTitleRight = view.findViewById(R.id.ad_title_right_tv);
        viewTitleDivider = view.findViewById(R.id.ad_title_view_divider);
    }

    /**
     * 设置分割线颜色
     * @param isHide 是否隐藏
     * @return ADTitleBuilder
     */
    public ADTitleBuilder setViewDividerAttr(boolean isHide) {
        return setViewDividerAttr(isHide, R.color.color_ebebeb);
    }

    /**
     * 设置分割线颜色
     * @param lineColor 线颜色
     * @return ADTitleBuilder
     */
    public ADTitleBuilder setViewDividerAttr(boolean isHide, int lineColor) {
        viewTitleDivider.setVisibility(isHide ? View.VISIBLE : View.GONE);
        viewTitleDivider.setBackgroundColor(0 == lineColor ? 0 : context.getResources().getColor(lineColor));
        return this;
    }

    /**
     * 设置标题
     * @param text 标题文字
     * @return ADTitleBuilder
     */
    public ADTitleBuilder setMiddleTitleBgRes(String text) {
        return setMiddleTitleBgRes(text, R.color.color_342e2e, R.color.white);
    }

    /**
     * 设置标题
     *
     * @param text       设置文本
     * @param textColor  字体颜色
     * @param titleBgColor 标题背景色
     * @return ADTitleBuilder
     */
    public ADTitleBuilder setMiddleTitleBgRes(String text, int textColor, int titleBgColor) {
        return setMiddleTitleBgRes(text, textColor, titleBgColor, false);
    }

    /**
     * 设置标题
     * @param text 标题文字
     * @param textColor 标题颜色
     * @param titleBgColor 标题背景色
     * @param isBold 标题是否加粗
     * @return ADTitleBuilder
     */
    public ADTitleBuilder setMiddleTitleBgRes(String text, int textColor, int titleBgColor, boolean isBold) {
        if (!TextUtils.isEmpty(text)) {
            textViewTitleCenter.setVisibility(View.VISIBLE);
            textViewTitleCenter.setText(text);
            textViewTitleCenter.setTextColor(context.getResources().getColor(textColor));
            titleView.setBackgroundColor(context.getResources().getColor(titleBgColor));
        } else {
            textViewTitleCenter.setVisibility(View.GONE);
            textViewTitleCenter.setText("");
        }
        if (isBold) {
            textViewTitleCenter.setTypeface(Typeface.DEFAULT_BOLD);
        }
        return this;
    }

    /**
     * 设置返回键
     * @param textBack 返回键文字
     * @return ADTitleBuilder
     */
    public ADTitleBuilder setLeftBack(String textBack) {
        return setLeftBack(textBack, 0);
    }

    /**
     * 设置返回键（ 左侧 ）
     * @param resId 返回键图标
     * @return ADTitleBuilder
     */
    public ADTitleBuilder setLeftBack(int resId) {
        return setLeftBack("", resId);
    }

    /**
     * 设置返回键（ 左侧 ）
     * @param textBack 返回键文字
     * @param resId 返回键图标
     * @return ADTitleBuilder
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    private ADTitleBuilder setLeftBack(String textBack, int resId) {
        textViewTitleLeft.setText(textBack);
        if (resId != 0) {
            Drawable drawable = context.getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textViewTitleLeft.setCompoundDrawables(drawable, null, null, null);
        } else {
            textViewTitleLeft.setCompoundDrawables(null, null, null, null);
        }
        return this;
    }

    /**
     * 设置左边的事件
     *
     * @param activity activity引用
     * @return ADTitleBuilder
     */
    public ADTitleBuilder setLeftRelativeLayoutFinish(final Activity activity) {
        if (relativeLayoutTitleLeft.getVisibility() == View.VISIBLE) {
            relativeLayoutTitleLeft.setOnClickListener(view -> {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            });
        }
        return this;
    }

    /**
     * 设置左边返回的事件
     * @param listener 回调监听
     * @return ADTitleBuilder
     */
    public ADTitleBuilder setLeftRelativeLayoutListener(View.OnClickListener listener) {
        if (relativeLayoutTitleLeft.getVisibility() == View.VISIBLE) {
            relativeLayoutTitleLeft.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 设置副标题（ 右侧 ）
     * @param text 副标题文字
     * @return ADTitleBuilder
     */
    public ADTitleBuilder setRightSub(String text) {
        return setRightSub(text, R.color.color_7c7c7c);
    }

    /**
     * 设置副标题（ 右侧 ）
     * @param resId 资源图片
     * @return ADTitleBuilder
     */
    public ADTitleBuilder setRightSub(int resId) {
        return setRightSub("", 0, 0, 0, resId);
    }

    /**
     * 设置副标题（ 右侧 ）
     * @param text 副标题文字
     * @param textColor 文字颜色
     * @return ADTitleBuilder
     */
    public ADTitleBuilder setRightSub(String text, int textColor) {
        return setRightSub(text, textColor, R.color.white);
    }

    /**
     * 设置副标题（ 右侧 ）
     * @param text 副标题文字
     * @param textColor 文字颜色
     * @param textBgColor 副标题背景色
     * @return ADTitleBuilder
     */
    public ADTitleBuilder setRightSub(String text, int textColor, int textBgColor) {
        return setRightSub(text, textColor, textBgColor, 5, 0);
    }

    /**
     * 设置副标题（ 右侧 ）
     * @param text 副标题文字
     * @param textColor 文字颜色
     * @param textBgColor 副标题背景色
     * @param radius 副标题背景圆角
     * @return ADTitleBuilder
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public ADTitleBuilder setRightSub(String text, int textColor, int textBgColor, int radius) {
        return setRightSub(text, textColor, textBgColor, radius, 0);
    }

    /**
     * 设置副标题（ 右侧 ）
     * @param text 副标题文字
     * @param textColor 文字颜色
     * @param textBgColor 副标题背景色
     * @param radius 副标题背景圆角
     * @param resId 资源图片
     * @return ADTitleBuilder
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public ADTitleBuilder setRightSub(String text, int textColor, int textBgColor, int radius, int resId) {
        textViewTitleRight.setText(text);
        textViewTitleRight.setTextColor(textColor == 0 ? 0 : context.getResources().getColor(textColor));
        textViewTitleRight.setBackground(textBgColor == 0 ? null : context.getResources().getDrawable(textBgColor));
        textViewTitleRight.setRadius(radius);
        if (resId != 0) {
            Drawable drawable = context.getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textViewTitleRight.setCompoundDrawables(null, null, drawable, null);
        } else {
            textViewTitleRight.setCompoundDrawables(null, null, null, null);
        }
        return this;
    }

    /**
     * 设置右边的事件
     */
    public ADTitleBuilder setRightRelativeLayoutListener(View.OnClickListener listener) {
        if (relativeLayoutTitleRight.getVisibility() == View.VISIBLE) {
            relativeLayoutTitleRight.setOnClickListener(listener);
        }
        return this;
    }
}
