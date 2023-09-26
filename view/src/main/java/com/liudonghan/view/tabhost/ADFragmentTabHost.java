package com.liudonghan.view.tabhost;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liudonghan.view.R;
import com.liudonghan.view.radius.ADConstraintLayout;
import com.liudonghan.view.recycler.ADRecyclerView;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:9/25/23
 */
public class ADFragmentTabHost extends ADConstraintLayout implements BaseQuickAdapter.OnItemClickListener {

    private static final String MAC_LIU = "Mac_Liu";
    private Context context;
    private ADRecyclerView recyclerView;
    private FragmentActivity fragmentActivity;
    private FragmentTabHost fragmentTabHost;
    private View viewDivider;
    private TabHostAdapter tabHostAdapter;
    private OnADFragmentTabHostListener onADFragmentTabHostListener;
    private int tabHeight, tabBgColor;
    private boolean isDivider;

    public ADFragmentTabHost(Context context) {
        super(context, null);
    }

    public ADFragmentTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.fragmentActivity = (FragmentActivity) context;
        this.context = context;
        View inflate = View.inflate(context, R.layout.ad_fragment_tab_host, this);
        recyclerView = inflate.findViewById(R.id.ad_fragment_tab_host_rv);
        fragmentTabHost = inflate.findViewById(android.R.id.tabhost);
        viewDivider = inflate.findViewById(R.id.ad_fragment_tab_view_divider);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ADFragmentTabHost);
        tabHeight = typedArray.getDimensionPixelOffset(R.styleable.ADFragmentTabHost_liu_tab_height, context.getResources().getDimensionPixelOffset(R.dimen.dip_52));
        tabBgColor = typedArray.getColor(R.styleable.ADFragmentTabHost_liu_tab_bg_color, Color.parseColor("#ffffff"));
        isDivider = typedArray.getBoolean(R.styleable.ADFragmentTabHost_liu_tab_divider, false);
        typedArray.recycle();
        init(context);
    }

    private void init(Context context) {
        ConstraintLayout.LayoutParams params = (LayoutParams) recyclerView.getLayoutParams();
        params.height = tabHeight;
        recyclerView.setLayoutParams(params);
        recyclerView.setBackgroundColor(tabBgColor);
        viewDivider.setVisibility(isDivider ? VISIBLE : GONE);
        fragmentTabHost.setup(context, fragmentActivity.getSupportFragmentManager(), android.R.id.tabcontent);
        tabHostAdapter = new TabHostAdapter(R.layout.item_ad_tab_host);
        recyclerView.setAdapter(tabHostAdapter);
        tabHostAdapter.setOnItemClickListener(this);
    }

    public void setData(List<ADNavigationEntity> tab) {
        if (null == tab) {
            Log.i(MAC_LIU, "tab navigation list is not empty");
            return;
        }
        recyclerView.setSpanCount(tab.size());
        tabHostAdapter.setNewData(tab);
        for (int i = 0; i < tab.size(); i++) {
            View view = View.inflate(context, R.layout.item_ad_tab_host, null);
            fragmentTabHost.addTab(fragmentTabHost.newTabSpec(String.valueOf(i)).setIndicator(view), tab.get(i).getFragment().getClass(), null);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ADNavigationEntity item = tabHostAdapter.getItem(position);
        fragmentTabHost.setCurrentTabByTag(String.valueOf(position));
        tabHostAdapter.selector(position);
        if (null != onADFragmentTabHostListener) {
            onADFragmentTabHostListener.onTabHost(item, position, fragmentTabHost, tabHostAdapter);
        }
    }

    public void setOnADFragmentTabHostListener(OnADFragmentTabHostListener onADFragmentTabHostListener) {
        this.onADFragmentTabHostListener = onADFragmentTabHostListener;
    }

    public ADRecyclerView getRecyclerView() {
        return recyclerView;
    }

    public FragmentTabHost getFragmentTabHost() {
        return fragmentTabHost;
    }

    public TabHostAdapter getTabHostAdapter() {
        return tabHostAdapter;
    }

    public void setUnreadCount(int position, int count) {
        tabHostAdapter.setUnreadCount(position, count);
    }

    public interface OnADFragmentTabHostListener {
        /**
         * 切换tab
         *
         * @param item            tab条目
         * @param position        索引
         * @param fragmentTabHost fragmentTabHost组件
         * @param tabHostAdapter  配适器引用
         */
        void onTabHost(ADNavigationEntity item, int position, FragmentTabHost fragmentTabHost, TabHostAdapter tabHostAdapter);
    }
}
