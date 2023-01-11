package com.liudonghan.view.photo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/11/23
 */
public class ADPhotoViewPager extends ViewPager {


    public ADPhotoViewPager(Context context) {
        super(context);
    }

    public ADPhotoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
