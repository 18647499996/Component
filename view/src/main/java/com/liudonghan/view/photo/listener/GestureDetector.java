package com.liudonghan.view.photo.listener;

import android.view.MotionEvent;

import com.liudonghan.view.photo.listener.OnGestureListener;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/11/23
 */
public interface GestureDetector {
     boolean onTouchEvent(MotionEvent ev);

     boolean isScaling();

     boolean isDragging();

     void setOnGestureListener(OnGestureListener listener);
}
