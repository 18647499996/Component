package com.liudonghan.view.time;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatTextView;

import com.liudonghan.view.R;
import com.liudonghan.view.helper.ViewAttr;
import com.liudonghan.view.helper.ViewHelper;

import java.util.Arrays;


/**
 * Description：倒计时按钮
 *
 * @author Created by: Li_Min
 * Time:1/5/23
 */
public class ADCountDownButton extends AppCompatTextView implements ViewAttr {

    private static final long DEFAULT_MILLIS_INFUTURE = 60;
    private ViewHelper viewHelper;

    private long countDownInterval;
    private long millisInFuture;
    private MyCount timer;
    private OnCountListener mListener;
    private String defaultValue = "获取验证码";
    private String postfixValue = "s 重新发送";

    public ADCountDownButton(Context context) {
        this(context, null);
    }

    public ADCountDownButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ADCountDownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        viewHelper = new ViewHelper();
        TypedArray typedArray = viewHelper.initAttrs(context, attrs);
        TypedArray typedViewArray = context.getResources().obtainAttributes(attrs, R.styleable.ADCountDownButton);
        countDownInterval = typedViewArray.getInteger(R.styleable.ADCountDownButton_liu_count_down_interval, 1) * 1000;
        millisInFuture = typedViewArray.getInteger(R.styleable.ADCountDownButton_liu_millis_as_future, 60) * 1000;
        typedViewArray.recycle();
        typedArray.recycle();

    }

    public void setTotalSecond(int second) {
        this.millisInFuture = second * 1000;
        startCountDown();
    }


    public void setTotalSecond(long second) {
        this.millisInFuture = second * 1000;
        startCountDown();
    }

    public String getPostfixValue() {
        return postfixValue;
    }

    public void setPostfixValue(String postfixValue) {
        this.postfixValue = postfixValue;
    }

    public int getTotalSecond() {
        return (int) (millisInFuture / 1000);
    }

    public void startCountDown() {
        if (timer != null && !timer.isFinished()) {
            return;
        }
        if (timer == null) {
            timer = new MyCount(millisInFuture, countDownInterval);
        }
        timer.start();
        setEnabled(false);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancel();
    }

    public void cancel() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void setOnCountListener(OnCountListener mListener) {
        this.mListener = mListener;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public interface OnCountListener {
        void onTick(ADCountDownButton button, String second);

        void onFinish(ADCountDownButton button);
    }

    private class MyCount extends CountDownTimer {

        private static final String TAG = "MyCount";
        private boolean isFinished = true;

        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            setEnabled(false);
            isFinished = false;
            String time = (millisUntilFinished / 1000) + postfixValue;

            if (mListener != null) {
                mListener.onTick(ADCountDownButton.this, time);
            } else {
                ADCountDownButton.this.setText(time);
            }
        }

        @Override
        public void onFinish() {
            setEnabled(true);
            if (mListener != null) {
                mListener.onFinish(ADCountDownButton.this);
            } else {
                ADCountDownButton.this.setText(defaultValue);

            }
            isFinished = true;
        }

        public boolean isFinished() {
            return isFinished;
        }
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