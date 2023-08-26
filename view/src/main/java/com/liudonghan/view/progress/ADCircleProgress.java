package com.liudonghan.view.progress;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;

import com.liudonghan.view.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:4/18/23
 */
public class ADCircleProgress extends View {


    private Paint innerCirclePaint, finishedPaint, unfinishedPaint;
    private TextPaint textPaint;
    private float textSize;
    private int textColor;
    private int max;
    private float progress;
    private int startingAngle;
    private float unfinishedStrokeWidth;
    private float finishedStrokeWidth;
    private int finishedStrokeColor;
    private int unfinishedStrokeColor;
    private boolean isAutoLoadingAnimation;
    private String text;
    private AnimatorSet animatorSet;

    private RectF finishedOuterRect = new RectF();
    private RectF unfinishedOuterRect = new RectF();
    private boolean clockWise;

    public ADCircleProgress(Context context) {
        this(context, null);
    }

    public ADCircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("WrongConstant")
    public ADCircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ADCircleProgress);
        finishedStrokeColor = typedArray.getColor(R.styleable.ADCircleProgress_liu_finished_color, Color.parseColor("#FF7900"));
        unfinishedStrokeColor = typedArray.getColor(R.styleable.ADCircleProgress_liu_unfinished_color, Color.parseColor("#FFFFFF"));
        finishedStrokeWidth = typedArray.getDimension(R.styleable.ADCircleProgress_liu_finished_stroke_width, 1);
        unfinishedStrokeWidth = typedArray.getDimension(R.styleable.ADCircleProgress_liu_unfinished_stroke_width, 1);
        textColor = typedArray.getColor(R.styleable.ADCircleProgress_liu_text_color, 0);
        textSize = typedArray.getDimension(R.styleable.ADCircleProgress_liu_text_size, 12);
        text = typedArray.getString(R.styleable.ADCircleProgress_liu_text);
        clockWise = typedArray.getBoolean(R.styleable.ADCircleProgress_liu_clock_wise, true);
        max = typedArray.getInt(R.styleable.ADCircleProgress_liu_max, 100);
        progress = typedArray.getFloat(R.styleable.ADCircleProgress_liu_progress, 0);
        startingAngle = typedArray.getInt(R.styleable.ADCircleProgress_liu_starting_angle, 270);
        isAutoLoadingAnimation = typedArray.getBoolean(R.styleable.ADCircleProgress_liu_is_auto_loading_animation, false);
        typedArray.recycle();

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.TRANSPARENT);
        innerCirclePaint.setAntiAlias(true);

        textPaint = new TextPaint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setAntiAlias(true);

        finishedPaint = new Paint();
        finishedPaint.setColor(finishedStrokeColor);
        finishedPaint.setStyle(Paint.Style.STROKE);
        finishedPaint.setAntiAlias(true);
        finishedPaint.setStrokeWidth(finishedStrokeWidth);

        unfinishedPaint = new Paint();
        unfinishedPaint.setColor(unfinishedStrokeColor);
        unfinishedPaint.setStyle(Paint.Style.STROKE);
        unfinishedPaint.setAntiAlias(true);
        unfinishedPaint.setStrokeWidth(unfinishedStrokeWidth);
        if (isAutoLoadingAnimation) {
            List<Animator> animationList = new ArrayList<>();
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "progress", 0f, max);
            objectAnimator.setDuration(1500);
            objectAnimator.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            objectAnimator.setRepeatMode(ValueAnimator.INFINITE);
            animationList.add(objectAnimator);

            ObjectAnimator objectAnimatorAlpha = ObjectAnimator.ofFloat(this, "alpha", 0.5f, 1f);
            objectAnimatorAlpha.setDuration(1500);
            objectAnimatorAlpha.setRepeatCount(ValueAnimator.INFINITE);//无限循环
            objectAnimatorAlpha.setRepeatMode(ValueAnimator.INFINITE);
            animationList.add(objectAnimatorAlpha);
            animatorSet = new AnimatorSet();
            animatorSet.setInterpolator(new DecelerateInterpolator());
            animatorSet.playTogether(animationList);
            animatorSet.start();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int measureSpec) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 100;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float delta = Math.max(finishedStrokeWidth, unfinishedStrokeWidth);
        finishedOuterRect.set(delta, delta, getWidth() - delta, getHeight() - delta);
        unfinishedOuterRect.set(delta, delta, getWidth() - delta, getHeight() - delta);
        float innerCircleRadius = (getWidth() - Math.min(finishedStrokeWidth, unfinishedStrokeWidth) + Math.abs(finishedStrokeWidth - unfinishedStrokeWidth)) / 2f;
        canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, innerCircleRadius, innerCirclePaint);
        if (!clockWise) {
            canvas.drawArc(finishedOuterRect, -(360f - getStartingAngle()), -(getProgressAngle()), false, finishedPaint);
            canvas.drawArc(unfinishedOuterRect, -(360f - getStartingAngle()) - getProgressAngle(), -(360f - getProgressAngle()), false, unfinishedPaint);
        } else {
            canvas.drawArc(finishedOuterRect, getStartingAngle(), getProgressAngle(), false, finishedPaint);
            canvas.drawArc(unfinishedOuterRect, getStartingAngle() + getProgressAngle(), 360 - getProgressAngle(), false, unfinishedPaint);
        }

        if (!TextUtils.isEmpty(text)) {
            float textHeight = textPaint.descent() + textPaint.ascent();
            canvas.drawText(text, (getWidth() - textPaint.measureText(text)) / 2.0f, (getWidth() - textHeight) / 2.0f, textPaint);
        }
    }

    public int getStartingAngle() {
        return startingAngle;
    }

    public void setStartingAngle(int startingAngle) {
        this.startingAngle = startingAngle;
        this.invalidate();
    }

    private float getProgressAngle() {
        return getProgress() / (float) max * 360f;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        this.invalidate();
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        if (this.progress > getMax()) {
            this.progress %= getMax();
        }
        this.invalidate();
    }

    public void cancelAnimator() {
        if (null != animatorSet) {
            animatorSet.cancel();
        }
    }
}
