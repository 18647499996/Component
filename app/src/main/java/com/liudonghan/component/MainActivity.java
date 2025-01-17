package com.liudonghan.component;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liudonghan.component.button.ADButtonActivity;
import com.liudonghan.component.calendar.CalendarActivity;
import com.liudonghan.component.card.CardActivity;
import com.liudonghan.component.cell.ADCellTextLayoutActivity;
import com.liudonghan.component.circleprogress.ADCircleProgressActivity;
import com.liudonghan.component.cityview.ADCityViewActivity;
import com.liudonghan.component.constraintLayout.ADConstraintLayoutActivity;
import com.liudonghan.component.field.FieldActivity;
import com.liudonghan.component.imageview.ADImageViewActivity;
import com.liudonghan.component.indicatortab.ADIndicatorTabActivity;
import com.liudonghan.component.input.ADInputCodeActivity;
import com.liudonghan.component.recyclerview.ADRecyclerViewActivity;
import com.liudonghan.component.search.SearchTextLayoutActivity;
import com.liudonghan.component.tabhost.FragmentTabHostActivity;
import com.liudonghan.component.textview.ADTextViewActivity;
import com.liudonghan.component.voicerecorder.ADVoiceRecorderButtonActivity;
import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.view.calendar.ADCalendarEntity;
import com.liudonghan.view.recycler.ADRecyclerView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {

    public static ADCalendarEntity.Day startDay;
    public static ADCalendarEntity.Day endDay;

    private String[] array = new String[]{
            "验证码输入框", "ADButton", "ADImageView", "ADCircleProgress",
            "ADCityView", "ADIndicatorTab", "ADConstraintLayout", "ADCellTextLayout",
            "ADTextView", "ADVoiceRecorderButton", "日历组件", "ADRecyclerView",
            "ADFieldTextLayout", "ADFragmentTabHost", "ADSearchTextLayout", "ViewBinding"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlowAdapter flowAdapter = new FlowAdapter(R.layout.item_flow);
        ADRecyclerView adRecyclerView = findViewById(R.id.recyclerView);
        adRecyclerView.setAdapter(flowAdapter);
        flowAdapter.setNewData(Arrays.asList(array));
        flowAdapter.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position) {
            case 0:
                // todo 验证码输入框
                ADInputCodeActivity.startActivity(this, ADInputCodeActivity.class);
                break;
            case 1:
                // todo 自定义Button按钮
                ADButtonActivity.startActivity(this, ADButtonActivity.class);
                break;
            case 2:
                // todo 自定义ImageView
                ADImageViewActivity.startActivity(this, ADImageViewActivity.class);
                break;
            case 3:
                // todo 自定义进度加载框
                ADCircleProgressActivity.startActivity(this, ADCircleProgressActivity.class);
                break;
            case 4:
                // todo 自定义城市选择器
                ADCityViewActivity.startActivity(this, ADCityViewActivity.class);
                break;
            case 5:
                // todo 自定义tab指示器
                ADIndicatorTabActivity.startActivity(this, ADIndicatorTabActivity.class);
                break;
            case 6:
                // todo 约束性布局
                ADConstraintLayoutActivity.startActivity(this, ADConstraintLayoutActivity.class);
                break;
            case 7:
                // todo 单元格组件
                ADCellTextLayoutActivity.startActivity(this, ADCellTextLayoutActivity.class);
                break;
            case 8:
                // todo 自定义TextView
                ADTextViewActivity.startActivity(this, ADTextViewActivity.class);
                break;
            case 9:
                // todo 语音录制按钮组件
                ADVoiceRecorderButtonActivity.startActivity(this, ADVoiceRecorderButtonActivity.class);
                break;
            case 10:
                // todo 日历选择器（ 开始、结束 ）
                CalendarActivity.startActivity(this, CalendarActivity.class);
                break;
            case 11:
                // todo RecyclerView
                ADRecyclerViewActivity.startActivity(this, ADRecyclerViewActivity.class);
                break;
            case 12:
                // todo 自定义输入框
                FieldActivity.startActivity(this, FieldActivity.class);
                break;
            case 13:
                FragmentTabHostActivity.startActivity(this, FragmentTabHostActivity.class);
                break;
            case 14:
                // todo 自定义搜索框
                SearchTextLayoutActivity.startActivity(this, SearchTextLayoutActivity.class);
                break;
            case 15:
                ADBaseActivity.startActivity(this, CardActivity.class);
                break;
            default:
                break;
        }
    }
}