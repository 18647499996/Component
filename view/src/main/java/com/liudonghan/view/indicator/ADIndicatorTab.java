package com.liudonghan.view.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.liudonghan.view.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:5/13/23
 */
public class ADIndicatorTab extends LinearLayout implements Tab.OnTabTextClickListener {

    private static final String TAG = "Mac_Liu";
    private Context context;
    /**
     * tab数据源
     */
    public List<String> tabList = new ArrayList<>();
    /**
     * tab大小
     */
    public int tabSize;
    /**
     *
     */
    private int textHeight;
    /**
     * 选中字体颜色
     */
    private int selectedColor;
    /**
     * 默认字体颜色
     */
    private int emptyColor;
    /**
     * 字体大小
     */
    private int textSize;
    /**
     * 指示器（ 线 ）高
     */
    private int lineHeight;
    /**
     * 指示器（ 线 ）宽
     */
    private int lineWidth;
    /**
     * 指示器颜色
     */
    private int lineColor;
    /**
     * 平移动画时长
     */
    private int duration;

    private Line line;
    private Tab tab;
    private OnADIndicatorTabItemClickListener onADIndicatorTabItemClickListener;

    public ADIndicatorTab(Context context) {
        super(context, null);
    }

    public ADIndicatorTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ADIndicatorTab);
        textSize = typedArray.getDimensionPixelSize(R.styleable.ADIndicatorTab_liu_indicator_text_size, 40);
        textHeight = typedArray.getDimensionPixelOffset(R.styleable.ADIndicatorTab_liu_indicator_text_height, 0);
        emptyColor = typedArray.getColor(R.styleable.ADIndicatorTab_liu_indicator_empty_color, Color.parseColor("#342e2e"));
        selectedColor = typedArray.getColor(R.styleable.ADIndicatorTab_liu_indicator_selected_color, Color.parseColor("#ff7900"));
        lineHeight = typedArray.getDimensionPixelOffset(R.styleable.ADIndicatorTab_liu_indicator_line_height, context.getResources().getDimensionPixelOffset(R.dimen.ad_0_67));
        lineColor = typedArray.getColor(R.styleable.ADIndicatorTab_liu_indicator_line_color, Color.parseColor("#ff7900"));
        lineWidth = typedArray.getDimensionPixelOffset(R.styleable.ADIndicatorTab_liu_indicator_line_width, 0);
        duration = typedArray.getInt(R.styleable.ADIndicatorTab_liu_indicator_line_duration, 200);
        typedArray.recycle();
    }

    /**
     * 设置指示器数据
     *
     * @param indicatorTabList 数据源
     */
    public void setData(List<String> indicatorTabList) {
        if (null == indicatorTabList || 0 == indicatorTabList.size()) {
            Log.i(TAG, "indicator tab error is not empty");
            return;
        }
        removeAllViews();
        this.tabList = indicatorTabList;
        this.tabSize = indicatorTabList.size();
        newTab();
        newLine();
    }

    /**
     * 得到一个新的对象（ 文本 ）
     */
    private void newTab() {
        tab = new Tab(context);
        tab.setOrientation(HORIZONTAL);
        tab.setGravity(Gravity.CENTER);
        tab.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        tab.setWeightSum(tabSize);
        tab.setTabSize(tabSize);
        tab.setTabList(tabList);
        tab.setTextHeight(getTextHeight());
        tab.setEmptyColor(getEmptyColor());
        tab.setSelectedColor(getSelectedColor());
        tab.setOnTabTextClickListener(this);
        tab.builder();
        addView(tab);
    }

    /**
     * 得到新的指示器（ 线 ）
     */
    private void newLine() {
        line = new Line(context);
        line.setOrientation(HORIZONTAL);
        line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, getLineHeight()));
        line.setWeightSum(tabSize);
        line.setTabSize(tabSize);
        line.setLineColor(getLineColor());
        line.setDuration(getDuration());
        line.setLineWidth(getLineWidth());
        line.builder();
        addView(line);
    }


    @Override
    public void onColumn(Tab.Column column, int position) {
        Log.i(TAG, "click column info ：" + column.getText());
        line.selectorLine(position);
        for (int i = 0; i < tabSize; i++) {
            tab.getColumnList().get(i).setSelected(position == tab.getColumnList().get(i).getPosition());
        }
        if (null != getOnADIndicatorTabItemClickListener()) {
            getOnADIndicatorTabItemClickListener().onItemClick(column, position);
        }
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
        setData(tabList);
    }

    public int getEmptyColor() {
        return emptyColor;
    }

    public void setEmptyColor(int emptyColor) {
        this.emptyColor = emptyColor;
        setData(tabList);
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        setData(tabList);
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
        setData(tabList);
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        setData(tabList);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
        setData(tabList);
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
        setData(tabList);
    }

    public int getTextHeight() {
        return textHeight;
    }

    public void setTextHeight(int textHeight) {
        this.textHeight = textHeight;
    }

    public OnADIndicatorTabItemClickListener getOnADIndicatorTabItemClickListener() {
        return onADIndicatorTabItemClickListener;
    }

    public void setOnADIndicatorTabItemClickListener(OnADIndicatorTabItemClickListener onADIndicatorTabItemClickListener) {
        this.onADIndicatorTabItemClickListener = onADIndicatorTabItemClickListener;
    }

    public interface OnADIndicatorTabItemClickListener {
        void onItemClick(Tab.Column text, int position);
    }
}
