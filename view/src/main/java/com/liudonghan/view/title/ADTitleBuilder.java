package com.liudonghan.view.title;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liudonghan.view.R;


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
     * 标题
     */
    private View titleView;

    /**
     * 左边相对布局
     */
    private RelativeLayout mRelLeft;

    /**
     * 左边TextView
     */
    private TextView mTvLeft;

    /**
     * 左边ImageView资源
     */
    private ImageView mImgLeft;

    /**
     * 中间TextView标题
     */
    private TextView mTvContent;

    /**
     * 右边相对布局
     */
    private RelativeLayout mRelRight;

    /**
     * 右边TextView
     */
    private TextView mTvRight;

    /**
     * 右边ImageView资源
     */
    private ImageView mImgRight;
    /**
     * 右边相对布局
     */
    private RelativeLayout mRelRightTwo;

    /**
     * 右边TextView
     */
    private TextView mTvRightTwo;

    /**
     * 右边ImageView资源
     */
    private ImageView mImgRightTwo;

    /**
     * 分割线
     */
    private View mViewLine;

    private Context context;


    /**
     * 第一种  初始化方式
     * 这里是直接引用进文件的初始化方式
     *
     * @param context 上下文
     */
    public ADTitleBuilder(Activity context) {
        this.context = context;
        titleView = context.findViewById(R.id.act_title_bor);

        mTvContent = titleView.findViewById(R.id.act_title_center_tv_title);

        mRelLeft = titleView.findViewById(R.id.act_title_left_rel);
        mImgLeft = titleView.findViewById(R.id.act_title_left_img);
        mTvLeft = titleView.findViewById(R.id.act_title_left_tv);


        mRelRight = titleView.findViewById(R.id.act_title_right_rel);
        mTvRight = titleView.findViewById(R.id.act_title_right_tv);
        mImgRight = titleView.findViewById(R.id.act_title_right_img);

        mRelRightTwo = titleView.findViewById(R.id.act_title_right_rel_two);
        mTvRightTwo = titleView.findViewById(R.id.act_title_right_tv_two);
        mImgRightTwo = titleView.findViewById(R.id.act_title_right_img_two);
        mViewLine = titleView.findViewById(R.id.act_title_view_line);


    }

    /**
     * 初始化Fragment标题引用
     *
     * @param activity 上下文
     * @param view     fragment布局引用
     */
    public ADTitleBuilder(Activity activity, View view) {
        this.context = activity;
        titleView = view.findViewById(R.id.act_title_bor);

        mTvContent = titleView.findViewById(R.id.act_title_center_tv_title);

        mRelLeft = titleView.findViewById(R.id.act_title_left_rel);
        mImgLeft = titleView.findViewById(R.id.act_title_left_img);
        mTvLeft = titleView.findViewById(R.id.act_title_left_tv);


        mRelRight = titleView.findViewById(R.id.act_title_right_rel);
        mTvRight = titleView.findViewById(R.id.act_title_right_tv);
        mImgRight = titleView.findViewById(R.id.act_title_right_img);

        mViewLine = titleView.findViewById(R.id.act_title_view_line);

    }

    /**
     * 隐藏view分割线
     *
     * @return TitleBuilder
     */
    public ADTitleBuilder setViewHide(boolean isHide) {
        mViewLine.setVisibility(isHide ? View.VISIBLE : View.GONE);
        return this;
    }

    public ADTitleBuilder setViewLineColor(int lineColor) {
        if (0 != lineColor) {
            mViewLine.setBackgroundColor(context.getResources().getColor(lineColor));
        }
        return this;
    }

    /**
     * title 的设置
     *
     * @param text 设置文本
     */

    public ADTitleBuilder setMiddleTitleBgRes(String text) {
        setMiddleTitleBgRes(text, R.color.color_342e2e, R.color.white);
        return this;
    }


    /**
     * title 的设置
     *
     * @param text 设置文本
     */

    public ADTitleBuilder setMiddleTitleBgResCover(String text) {
        setMiddleTitleBgRes(text, R.color.white, R.color.black);
        return this;
    }

    /**
     * title
     *
     * @param text       设置文本
     * @param textColor  字体颜色
     * @param titleColor 标题背景颜色
     */
    public ADTitleBuilder setMiddleTitleBgRes(String text, int textColor, int titleColor) {
        mTvContent.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        if (!TextUtils.isEmpty(text)) {
            mTvContent.setText(text);
            mTvContent.setTextColor(context.getResources().getColor(textColor));
            titleView.setBackgroundColor(context.getResources().getColor(titleColor));
        }
        return this;
    }

    /**
     * title
     * 字体加粗
     */
    public ADTitleBuilder setMiddleTitleBgResTypeFace() {
        mTvContent.setTypeface(Typeface.DEFAULT_BOLD);
        return this;
    }

    /**
     * left图片按钮
     *
     * @param resId 资源id
     */
    public ADTitleBuilder setLeftImageRes(int resId) {
        mImgLeft.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        mImgLeft.setImageResource(resId);
        return this;
    }

    /**
     * 设置左边TextView
     *
     * @param text 文本
     * @return
     */
    public ADTitleBuilder setLeftTextRes(String text) {
        setLeftTextRes(text, 14, R.color.white);

        return this;
    }

    /**
     * 设置左边TextView
     *
     * @param text      文本
     * @param textSize  字体大小
     * @param textColor 字体颜色
     */
    public ADTitleBuilder setLeftTextRes(String text, int textSize, int textColor) {
        mTvLeft.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        if (!TextUtils.isEmpty(text)) {
            mTvLeft.setText(text);
            mTvLeft.setTextSize(textSize);
            mTvLeft.setTextColor(context.getResources().getColor(textColor));
        }
        return this;
    }


    /**
     * 设置左边的事件
     *
     * @param activity activity引用
     */
    public ADTitleBuilder setLeftRelativeLayoutListener(final Activity activity) {
        if (mRelLeft.getVisibility() == View.VISIBLE) {
            mRelLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!activity.isFinishing()) {
                        activity.finish();
                    }
                }
            });
        }
        return this;
    }

    /**
     * right右边图片按钮
     *
     * @param resId 资源id
     */
    public ADTitleBuilder setRightImageRes(int resId) {
        mImgRight.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        mImgRight.setImageResource(resId);
        return this;
    }

    /**
     * right右边图片按钮
     *
     * @param resId 资源id
     */
    public ADTitleBuilder setRightImageResTwo(int resId) {
        mImgRightTwo.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        mImgRightTwo.setImageResource(resId);
        return this;
    }

    /**
     * 右边文字按钮
     *
     * @param text 文本
     */
    public ADTitleBuilder setRightText(String text) {
        setRightText(text, 12, R.color.color_342e2e);
        return this;
    }

    /**
     * 右边文本
     *
     * @param text      文本
     * @param textSize  字体大小
     * @param textColor 字体颜色
     */
    public ADTitleBuilder setRightText(String text, int textSize, int textColor) {
        setRightText(text, textSize, textColor, R.color.white);
        return this;
    }

    /**
     * 右边文本
     *
     * @param text      文本
     * @param textSize  字体大小
     * @param textColor 字体颜色
     * @param textBg    文字背景
     */
    public ADTitleBuilder setRightText(String text, int textSize, int textColor, int textBg) {
        mTvRight.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
        if (!TextUtils.isEmpty(text)) {
            mTvRight.setText(text);
            mTvRight.setTextSize(textSize);
            mTvRight.setTextColor(context.getResources().getColor(textColor));
            mTvRight.setBackground(context.getResources().getDrawable(textBg));
        }
        return this;
    }

    /**
     * 设置右边的事件
     */
    public ADTitleBuilder setRightRelativeLayoutListener(View.OnClickListener listener) {
        if (mRelRight.getVisibility() == View.VISIBLE) {
            mRelRight.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 设置左边返回的事件
     */
    public ADTitleBuilder setContentLeftRelativeLayoutListener(View.OnClickListener listener) {
        if (mRelLeft.getVisibility() == View.VISIBLE) {
            mRelLeft.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 设置右边的事件
     */
    public ADTitleBuilder setContentLeftRelativeLayoutTwoListener(View.OnClickListener listener) {
        if (mRelRightTwo.getVisibility() == View.VISIBLE) {
            mRelRightTwo.setOnClickListener(listener);
        }
        return this;
    }
}
