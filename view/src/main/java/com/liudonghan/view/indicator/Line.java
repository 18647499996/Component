package com.liudonghan.view.indicator;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:5/15/23
 */
public class Line extends LinearLayout {

    private Context context;
    private int tabSize;
    private LinearLayout indicator;
    private int lineColor;
    private int duration;
    private int lineWidth;

    public Line(Context context) {
        super(context);
        this.context = context;
    }

    public Line(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public Line(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void setTabSize(int tabSize) {
        this.tabSize = tabSize;
    }

    public int getTabSize() {
        return tabSize;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void builder() {
        LinearLayout indicator = newLine();
        addView(indicator);
    }

    private LinearLayout newLine() {
        indicator = new LinearLayout(context);
        indicator.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        View view = new View(context);
        view.setLayoutParams(new ViewGroup.LayoutParams(0 == getLineWidth() ? ViewGroup.LayoutParams.MATCH_PARENT : getLineWidth(), ViewGroup.LayoutParams.MATCH_PARENT));
        view.setBackgroundColor(getLineColor());
        indicator.setGravity(Gravity.CENTER);
        indicator.addView(view);
        return indicator;
    }

    /**
     * 选中图片数据
     *
     * @param position 索引
     */
    public void selectorLine(int position) {
        int onceWidth = getWidth() / getTabSize();
        ObjectAnimator animator = ObjectAnimator.ofFloat(indicator, "translationX", indicator.getTranslationX(), position * onceWidth);
        animator.setDuration(getDuration());
        animator.start();
    }
}
