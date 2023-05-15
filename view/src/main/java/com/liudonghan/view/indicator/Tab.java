package com.liudonghan.view.indicator;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:5/13/23
 */
public class Tab extends LinearLayout implements View.OnClickListener {

    private OnTabTextClickListener onTabTextClickListener;
    private static final String TAG = "Mac_Liu";
    private int tabSize;
    private List<String> tabs = new ArrayList<>();
    private List<Column> columnList = new ArrayList<>();
    private Context context;
    private int selectedColor;
    private int emptyColor;
    private int textHeight;

    public Tab(Context context) {
        super(context);
        this.context = context;
    }

    public Tab(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public Tab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    public List<Column> getColumnList() {
        return columnList;
    }

    public int getTabSize() {
        return tabSize;
    }

    public void setTabSize(int tabSize) {
        this.tabSize = tabSize;
    }

    public void setTabList(List<String> tabList) {
        this.tabs = tabList;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    public int getEmptyColor() {
        return emptyColor;
    }

    public void setEmptyColor(int emptyColor) {
        this.emptyColor = emptyColor;
    }

    public int getTextHeight() {
        return textHeight;
    }

    public void setTextHeight(int textHeight) {
        this.textHeight = textHeight;
    }

    public void builder() {
        for (int i = 0; i < getTabSize(); i++) {
            Column column = newTab(tabs.get(i), 0 == i, i);
            columnList.add(column);
            addView(column);
        }
    }

    /**
     * 得到一个新的tab对象
     */
    private Column newTab(CharSequence text, boolean isSelected, int position) {
        Column column = new Column(context);
        column.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        column.setGravity(Gravity.CENTER);
        column.setPadding(0, 0 == getTextHeight() ? 20 : getTextHeight(), 0, 0 == getTextHeight() ? 20 : getTextHeight());
        column.setTextSize(13);
        column.setTextColor(Color.parseColor("#342e2e"));
        column.setEmptyColor(getEmptyColor());
        column.setSelectedColor(getSelectedColor());
        column.setOnClickListener(this);
        column.setPosition(position);
        column.setSelected(isSelected);
        column.setText(text);
        return column;
    }


    public static class Column extends AppCompatTextView {

        private int position;
        private int selectedColor;
        private int emptyColor;


        public int getSelectedColor() {
            return selectedColor;
        }

        public void setSelectedColor(int textSelectedColor) {
            selectedColor = textSelectedColor;
        }

        public int getEmptyColor() {
            return emptyColor;
        }

        public void setEmptyColor(int emptyColor) {
            this.emptyColor = emptyColor;
        }

        public Column(Context context) {
            super(context);
        }

        public Column(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public Column(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        public void setText(CharSequence text, BufferType type) {
            setTextColor(isSelected() ? getSelectedColor() : getEmptyColor());
            super.setText(text, type);
        }

        @Override
        public void setSelected(boolean selected) {
            super.setSelected(selected);
            setText(getText());
        }


        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }

    @Override
    public void onClick(View v) {
        Column text = (Column) v;
        if (null != getOnTabTextClickListener()) {
            getOnTabTextClickListener().onColumn(text, text.getPosition());
        }
    }

    public OnTabTextClickListener getOnTabTextClickListener() {
        return onTabTextClickListener;
    }

    public void setOnTabTextClickListener(OnTabTextClickListener onTabTextClickListener) {
        this.onTabTextClickListener = onTabTextClickListener;
    }

    public interface OnTabTextClickListener {
        /**
         * @param column   tab信息
         * @param position 索引
         */
        void onColumn(Column column, int position);
    }
}
