package com.liudonghan.view.time;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.liudonghan.view.R;


/**
 * Description：倒计时按钮
 *
 * @author Created by: Li_Min
 * Time:1/5/23
 */
public class CountDownButton extends AppCompatTextView {

    private static final long DEFAULT_MILLIS_INFUTURE = 60;

    private long countDownInterval;
    private long millisInFuture;
    private MyCount timer;
    private OnCountListener mListener;
    private String defaultValue = "获取验证码";

    public CountDownButton(Context context) {
        this(context, null);
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public CountDownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getResources().obtainAttributes(attrs, R.styleable.LiuCountDown);
        countDownInterval = typedArray.getInteger(R.styleable.LiuCountDown_liu_count_down_interval, 1) * 1000;
        millisInFuture = typedArray.getInteger(R.styleable.LiuCountDown_liu_millis_as_future, 60) * 1000;
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
        void onTick(CountDownButton button, String second);

        void onFinish(CountDownButton button);
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
            String time = (millisUntilFinished / 1000) + "s 重新发送";

            if (mListener != null) {
                mListener.onTick(CountDownButton.this, time);
            } else {
                CountDownButton.this.setText(time);
            }
        }

        @Override
        public void onFinish() {
            setEnabled(true);
            if (mListener != null) {
                mListener.onFinish(CountDownButton.this);
            } else {
                CountDownButton.this.setText(defaultValue);

            }
            isFinished = true;
        }

        public boolean isFinished() {
            return isFinished;
        }
    }


}