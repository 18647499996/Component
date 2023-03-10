package com.liudonghan.view.city;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;

import com.liudonghan.view.R;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2/3/23
 */
public class ADCityView extends RelativeLayout {

    private ViewSwitcher viewSwitcher;
    private ProgressBar progressBar;
    private ADCitySelector adCitySelector;
    private TextView textView;
    private ImageView imageView;

    public ViewSwitcher getViewSwitcher() {
        return viewSwitcher;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public ADCitySelector getAdCitySelector() {
        return adCitySelector;
    }

    public TextView getTextView() {
        return textView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public ADCityView(Context context) {
        this(context, null);
    }

    public ADCityView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ADCityView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View inflate = View.inflate(context, R.layout.ad_multi_city, this);
        viewSwitcher = inflate.findViewById(R.id.view_multi_city_switcher);
        progressBar = inflate.findViewById(R.id.view_multi_city_progress_bar);
        adCitySelector = inflate.findViewById(R.id.dialog_city_wheel_address);
        textView = inflate.findViewById(R.id.view_multi_city_tv_title);
        imageView = inflate.findViewById(R.id.view_multi_city_img_close);
    }

    public void setData(List<ADCityModel> cityModelList) {
        adCitySelector.setCities(cityModelList);
        viewSwitcher.setDisplayedChild(1);
    }

    @SuppressLint("NewApi")
    public void setProgressBarBgColor(@ColorRes int colorId) {
        progressBar.setIndeterminateTintList(ColorStateList.valueOf(getResources().getColor(colorId)));
    }
}
