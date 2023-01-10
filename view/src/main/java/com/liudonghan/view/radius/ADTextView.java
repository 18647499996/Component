package com.liudonghan.view.radius;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.Arrays;

import com.liudonghan.view.helper.ViewAttr;
import com.liudonghan.view.helper.ViewHelper;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/4/23
 */
@SuppressLint("AppCompatCustomView")
public class ADTextView extends TextView implements ViewAttr {

    private ViewHelper viewHelper;

    public ADTextView(Context context) {
        super(context, null);
    }

    public ADTextView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        viewHelper = new ViewHelper();
        viewHelper.initAttrs(context, attrs);
    }

    @Override
    public void setClipBackground(boolean clipBackground) {
        viewHelper.mClipBackground = clipBackground;
        invalidate();
    }

    @Override
    public void setRoundAsCircle(boolean roundAsCircle) {
        viewHelper.mRoundAsCircle = roundAsCircle;
    }

    @Override
    public void setRadius(int radius) {
        Arrays.fill(viewHelper.radii, radius);
        invalidate();
    }

    public void setTopLeftRadius(int topLeftRadius) {
        viewHelper.radii[0] = topLeftRadius;
        viewHelper.radii[1] = topLeftRadius;
        invalidate();
    }

    public void setTopRightRadius(int topRightRadius) {
        viewHelper.radii[2] = topRightRadius;
        viewHelper.radii[3] = topRightRadius;
        invalidate();
    }

    public void setBottomLeftRadius(int bottomLeftRadius) {
        viewHelper.radii[6] = bottomLeftRadius;
        viewHelper.radii[7] = bottomLeftRadius;
        invalidate();
    }

    public void setBottomRightRadius(int bottomRightRadius) {
        viewHelper.radii[4] = bottomRightRadius;
        viewHelper.radii[5] = bottomRightRadius;
        invalidate();
    }

    public void setStrokeWidth(int strokeWidth) {
        viewHelper.mStrokeWidth = strokeWidth;
        invalidate();
    }

    public void setStrokeColor(int strokeColor) {
        viewHelper.mStrokeColor = strokeColor;
        invalidate();
    }

    public boolean isClipBackground() {
        return viewHelper.mClipBackground;
    }

    public boolean isRoundAsCircle() {
        return viewHelper.mRoundAsCircle;
    }

    public float getTopLeftRadius() {
        return viewHelper.radii[0];
    }

    public float getTopRightRadius() {
        return viewHelper.radii[2];
    }

    public float getBottomLeftRadius() {
        return viewHelper.radii[4];
    }

    public float getBottomRightRadius() {
        return viewHelper.radii[6];
    }

    public int getStrokeWidth() {
        return viewHelper.mStrokeWidth;
    }

    public int getStrokeColor() {
        return viewHelper.mStrokeColor;
    }

    @Override
    public void invalidate() {
        if (null != viewHelper) {
            viewHelper.refreshRegion(this);
        }
        super.invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewHelper.onSizeChanged(this, w, h);
    }

    @Override
    public void draw(Canvas canvas) {
        if (viewHelper.mClipBackground) {
            canvas.save();
            canvas.clipPath(viewHelper.mClipPath);
            super.draw(canvas);
            canvas.restore();
        } else {
            super.draw(canvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.saveLayer(viewHelper.mLayer, null, Canvas.ALL_SAVE_FLAG);
        super.onDraw(canvas);
        viewHelper.onClipDraw(canvas);
        canvas.restore();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN && !viewHelper.mAreaRegion.contains((int) ev.getX(), (int) ev.getY())) {
            return false;
        }
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_UP) {
            refreshDrawableState();
        } else if (action == MotionEvent.ACTION_CANCEL) {
            setPressed(false);
            refreshDrawableState();
        }
        return super.dispatchTouchEvent(ev);
    }
}
