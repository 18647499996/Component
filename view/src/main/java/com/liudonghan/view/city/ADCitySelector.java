package com.liudonghan.view.city;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.liudonghan.view.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:11/30/21
 */
public class ADCitySelector extends LinearLayout implements View.OnClickListener {
    private int TextSelectedColor = Color.parseColor("#FF7900");
    private int TextEmptyColor = Color.parseColor("#333333");
    //顶部的tab集合
    private ArrayList<Tab> tabs;
    //列表的适配器
    private ADCitySelector.AddressAdapter addressAdapter;

    private OnADItemClickListener onItemClickListener;
    private OnADTabSelectedListener onTabSelectedListener;
    private RecyclerView list;
    //tabs的外层layout
    private LinearLayout tabs_layout;
    //会移动的横线布局
    private ADCitySelector.Line line;
    private Context mContext;
    //总共tab的数量
    private int tabAmount = 4;
    //当前tab的位置
    private int tabIndex = 0;
    //分隔线
    private View grayLine;
    private List<ADCityModel> cities;

    public ADCitySelector(Context context) {
        super(context);
        init(context);
    }

    public ADCitySelector(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ADCitySelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        removeAllViews();
        this.mContext = context;
        setOrientation(VERTICAL);
        tabs_layout = new LinearLayout(mContext);
        tabs_layout.setWeightSum(tabAmount);
        tabs_layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        tabs_layout.setOrientation(HORIZONTAL);
        addView(tabs_layout);
        tabs = new ArrayList<>();
        ADCitySelector.Tab tab = newTab("请选择", true);
        tabs_layout.addView(tab);
        tabs.add(tab);
        for (int i = 1; i < tabAmount; i++) {
            ADCitySelector.Tab tabCount = newTab("", false);
            tabCount.setIndex(i);
            tabs_layout.addView(tabCount);
            tabs.add(tabCount);
        }
        line = new ADCitySelector.Line(mContext);
        line.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 1));
        line.setSum(tabAmount);
        addView(line);
        grayLine = new View(mContext);
        grayLine.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 1));
        grayLine.setBackgroundColor(mContext.getResources().getColor(R.color.color_dddddd));
        addView(grayLine);
        list = new RecyclerView(mContext);
        list.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        list.setLayoutManager(new LinearLayoutManager(mContext));
        addView(list);
    }

    /**
     * 得到一个新的tab对象
     */
    private ADCitySelector.Tab newTab(CharSequence text, boolean isSelected) {
        ADCitySelector.Tab tab = new Tab(mContext);
        tab.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        tab.setGravity(Gravity.CENTER);
        tab.setPadding(0, 20, 0, 20);
        tab.setSelected(isSelected);
        tab.setText(text);
        tab.setTextEmptyColor(TextEmptyColor);
        tab.setTextSelectedColor(TextSelectedColor);
        tab.setOnClickListener(this);
        return tab;
    }

    /**
     * 设置tab的数量，默认3个,不小于2个
     *
     * @param tabAmount tab的数量
     */
    public void setTabAmount(int tabAmount) {
        if (tabAmount >= 2) {
            this.tabAmount = tabAmount;
            init(mContext);
        } else
            throw new RuntimeException("AddressSelector tabAmount can not less-than 2 !");
    }

    /**
     * 设置列表的点击事件回调接口
     */
    public void setOnItemClickListener(OnADItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 设置列表的数据源，设置后立即生效
     */
    public void setCities(List<ADCityModel> cities) {
        this.cities = cities;
        if (cities == null || cities.size() <= 0)
            return;
        if (addressAdapter == null) {
            addressAdapter = new ADCitySelector.AddressAdapter();
            list.setAdapter(addressAdapter);
        }
        addressAdapter.notifyDataSetChanged();
    }

    /**
     * 设置顶部tab的点击事件回调
     */
    public void setOnTabSelectedListener(OnADTabSelectedListener onTabSelectedListener) {
        this.onTabSelectedListener = onTabSelectedListener;
    }

    @Override
    public void onClick(View v) {
        ADCitySelector.Tab tab = (ADCitySelector.Tab) v;
        //如果点击的tab大于index则直接跳出
        if (tab.index > tabIndex)
            return;
        tabIndex = tab.index;
        if (onTabSelectedListener != null) {
            if (tab.isSelected)
                onTabSelectedListener.onTabReselected(ADCitySelector.this, tab);
            else
                onTabSelectedListener.onTabSelected(ADCitySelector.this, tab);
        }
        resetAllTabs(tabIndex);
        line.setIndex(tabIndex);
        tab.setSelected(true);
    }

    private void resetAllTabs(int tabIndex) {
        if (tabs != null)
            for (int i = 0; i < tabs.size(); i++) {
                tabs.get(i).resetState();
                if (i > tabIndex) {
                    tabs.get(i).setText("");
                }
            }
    }

    /**
     * 标签控件
     */
    @SuppressLint("AppCompatCustomView")
    public static class Tab extends TextView {
        private int index = 0;
        private int TextSelectedColor = Color.parseColor("#FF7900");
        private int TextEmptyColor = Color.parseColor("#333333");
        /**
         * 是否选中状态
         */
        private boolean isSelected = false;

        public Tab(Context context) {
            super(context);
            init();
        }

        public Tab(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public Tab(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private void init() {
            setTextSize(13);
        }

        @Override
        public void setText(CharSequence text, BufferType type) {
            if (isSelected)
                setTextColor(TextSelectedColor);
            else
                setTextColor(TextEmptyColor);
            super.setText(text, type);
        }

        @Override
        public void setSelected(boolean selected) {
            isSelected = selected;
            setText(getText());
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void resetState() {
            isSelected = false;
            setText(getText());
        }

        public void setTextSelectedColor(int textSelectedColor) {
            TextSelectedColor = textSelectedColor;
        }

        public void setTextEmptyColor(int textEmptyColor) {
            TextEmptyColor = textEmptyColor;
        }
    }

    /**
     * 横线控件
     */
    private class Line extends LinearLayout {
        private int sum = 3;
        private int oldIndex = 0;
        private int nowIndex = 0;
        private View indicator;
        private int SelectedColor = Color.parseColor("#FF7900");

        public Line(Context context) {
            super(context);
            init(context);
        }

        public Line(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }

        public Line(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context);
        }

        private void init(Context context) {
            setOrientation(HORIZONTAL);
            setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 1));
            setWeightSum(tabAmount);
            indicator = new View(context);
            indicator.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 1));
            indicator.setBackgroundColor(SelectedColor);
            addView(indicator);
        }

        public void setIndex(int index) {
            int onceWidth = getWidth() / sum;
            this.nowIndex = index;
            ObjectAnimator animator = ObjectAnimator.ofFloat(indicator, "translationX", indicator.getTranslationX(), (nowIndex - oldIndex) * onceWidth);
            animator.setDuration(300);
            animator.start();
        }

        public void setSum(int sum) {
            this.sum = sum;
        }

        public void setSelectedColor(int selectedColor) {
            SelectedColor = selectedColor;
        }
    }

    private class AddressAdapter extends RecyclerView.Adapter<ADCitySelector.AddressAdapter.MyViewHolder> {
        @Override
        public ADCitySelector.AddressAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ADCitySelector.AddressAdapter.MyViewHolder viewHolder = new ADCitySelector.AddressAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_address, parent, false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ADCitySelector.AddressAdapter.MyViewHolder holder, final int position) {
            if (cities.get(position) instanceof ADCityModel){
                final ADCityModel adCityModel = (ADCityModel) cities.get(position);
                holder.tv.setText(adCityModel.getCityName());
                holder.itemView.setTag(cities.get(position));
                holder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener != null) {
                            onItemClickListener.itemClick(ADCitySelector.this, adCityModel, tabIndex, position);
                            tabs.get(tabIndex).setText(adCityModel.getCityName());
                            tabs.get(tabIndex).setTag(v.getTag());
                            if (tabIndex + 1 < tabs.size()) {
                                tabIndex++;
                                resetAllTabs(tabIndex);
                                line.setIndex(tabIndex);
                                tabs.get(tabIndex).setText("请选择");
                                tabs.get(tabIndex).setSelected(true);
                            }
                        }
                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            return cities.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView tv;
            public View itemView;

            public MyViewHolder(View itemView) {
                super(itemView);
                this.itemView = itemView;
                tv = (TextView) itemView.findViewById(R.id.item_address_tv);
            }
        }
    }
}