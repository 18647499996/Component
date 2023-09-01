package com.liudonghan.view.field;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.liudonghan.view.R;
import com.liudonghan.view.helper.ViewAttr;
import com.liudonghan.view.helper.ViewHelper;
import com.liudonghan.view.radius.ADButton;
import com.liudonghan.view.radius.ADConstraintLayout;

import java.util.Arrays;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:9/1/23
 */
public class ADFieldTextLayout extends ADConstraintLayout implements ViewAttr {

    private TextView textViewRequired, textViewTitle;
    private EditText editTextContent;
    private ADButton buttonGo;
    private ViewHelper viewHelper;
    private boolean isRequired;
    private String titleDesc,editHint;

    public ADFieldTextLayout(@NonNull Context context) {
        super(context, null);
    }


    public ADFieldTextLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        viewHelper = new ViewHelper();
        TypedArray viewTypedArray = viewHelper.initAttrs(context, attrs);
        View inflate = View.inflate(context, R.layout.ad_field_group, this);
        textViewRequired = inflate.findViewById(R.id.view_field_tv_required);
        textViewTitle = inflate.findViewById(R.id.view_field_tv_title);
        editTextContent = inflate.findViewById(R.id.view_field_edt_content);
        buttonGo = inflate.findViewById(R.id.view_field_btn_go);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ADFieldTextLayout);
        isRequired = typedArray.getBoolean(R.styleable.ADFieldTextLayout_liu_is_required, false);
        titleDesc = typedArray.getString(R.styleable.ADFieldTextLayout_liu_title);
        editHint = typedArray.getString(R.styleable.ADFieldTextLayout_liu_hint);
        typedArray.recycle();
        viewTypedArray.recycle();
        initField(context);
    }

    private void initField(Context context) {
        textViewRequired.setVisibility(isRequired ? VISIBLE : INVISIBLE);
        editTextContent.setHint(editHint);
        textViewTitle.setText(titleDesc);
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
