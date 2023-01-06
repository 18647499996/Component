package com.liudonghan.view.snackbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/6/23
 */
public class SnackBarLayout extends LinearLayout {
    private int mMaxWidth = Integer.MAX_VALUE;
    private int mMaxHeight = Integer.MAX_VALUE;

    public SnackBarLayout(Context context) {
        super(context);
    }

    public SnackBarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SnackBarLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Adjust width as necessary
        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (mMaxWidth < width) {
            int mode = MeasureSpec.getMode(widthMeasureSpec);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, mode);
        }
        // Adjust height as necessary
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (mMaxHeight < height) {
            int mode = MeasureSpec.getMode(heightMeasureSpec);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, mode);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setMaxWidth(int maxWidth) {
        mMaxWidth = maxWidth;
        requestLayout();
    }

    public void setMaxHeight(int maxHeight) {
        mMaxHeight = maxHeight;
        requestLayout();
    }

}
