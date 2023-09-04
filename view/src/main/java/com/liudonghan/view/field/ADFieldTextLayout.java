package com.liudonghan.view.field;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

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
    private View viewDivider;
    private ViewHelper viewHelper;
    private boolean isRequired, isInsert, isDivider;
    private String titleDesc, editHint, insertHint;
    private int dividerMargin, dividerBgColor, dividerHeight, insertRadius, insertHintColor, insertBgColor, centerColor, centerHintColor, titleColor;
    private float titleSize, centerSize;
    private OnADFieldTextLayoutListener onADFieldTextLayoutListener;

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
        viewDivider = inflate.findViewById(R.id.view_field_view_divider);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ADFieldTextLayout);
        isRequired = typedArray.getBoolean(R.styleable.ADFieldTextLayout_liu_is_required, false);
        isInsert = typedArray.getBoolean(R.styleable.ADFieldTextLayout_liu_is_insert, false);
        isDivider = typedArray.getBoolean(R.styleable.ADFieldTextLayout_liu_is_divider, false);
        // 输入框
        editHint = typedArray.getString(R.styleable.ADFieldTextLayout_liu_hint);
        centerColor = typedArray.getColor(R.styleable.ADFieldTextLayout_liu_center_color, Color.parseColor("#333333"));
        centerHintColor = typedArray.getColor(R.styleable.ADFieldTextLayout_liu_center_hint_color, Color.parseColor("#cccccc"));
        centerSize = typedArray.getDimensionPixelSize(R.styleable.ADFieldTextLayout_liu_center_size, context.getResources().getDimensionPixelOffset(R.dimen.dip_13));
        // 左侧标签
        titleDesc = typedArray.getString(R.styleable.ADFieldTextLayout_liu_title);
        titleColor = typedArray.getColor(R.styleable.ADFieldTextLayout_liu_title_color, Color.parseColor("#333333"));
        titleSize = typedArray.getDimensionPixelSize(R.styleable.ADFieldTextLayout_liu_title_size, context.getResources().getDimensionPixelOffset(R.dimen.dip_13));
        // 分割线
        dividerMargin = typedArray.getDimensionPixelOffset(R.styleable.ADFieldTextLayout_liu_divider_margin, 0);
        dividerBgColor = typedArray.getColor(R.styleable.ADFieldTextLayout_liu_divider_background_color, Color.parseColor("#ebebeb"));
        dividerHeight = typedArray.getDimensionPixelOffset(R.styleable.ADFieldTextLayout_liu_divider_height, context.getResources().getDimensionPixelOffset(R.dimen.ad_0_67));
        // 插入按钮
        insertRadius = typedArray.getDimensionPixelOffset(R.styleable.ADFieldTextLayout_liu_insert_radius, context.getResources().getDimensionPixelOffset(R.dimen.dp_4));
        insertHint = typedArray.getString(R.styleable.ADFieldTextLayout_liu_insert_hint);
        insertHintColor = typedArray.getColor(R.styleable.ADFieldTextLayout_liu_insert_hint_color, Color.parseColor("#505257"));
        insertBgColor = typedArray.getColor(R.styleable.ADFieldTextLayout_liu_insert_background_color, Color.parseColor("#eeeeee"));
        typedArray.recycle();
        viewTypedArray.recycle();
        initField(context);
        initDivider(context);
        initListener();
        initInsert(context);
    }

    /**
     * todo 初始化右侧按钮
     *
     * @param context 上下文
     */
    private void initInsert(Context context) {
        buttonGo.setVisibility(isInsert ? VISIBLE : GONE);
        buttonGo.setRadius(insertRadius);
        buttonGo.setText(insertHint);
        buttonGo.setTextColor(insertHintColor);
        buttonGo.setBackgroundColor(insertBgColor);
        buttonGo.setPadding(
                context.getResources().getDimensionPixelOffset(buttonGo.getText().toString().trim().length() < 4 ? R.dimen.dip_18 : R.dimen.dip_12),
                context.getResources().getDimensionPixelOffset(R.dimen.dip_7),
                context.getResources().getDimensionPixelOffset(buttonGo.getText().toString().trim().length() < 4 ? R.dimen.dip_18 : R.dimen.dip_12),
                context.getResources().getDimensionPixelOffset(R.dimen.dip_7));

    }

    /**
     * todo 初始化
     *
     * @param context 上下文
     */
    private void initField(Context context) {
        textViewRequired.setVisibility(isRequired ? VISIBLE : INVISIBLE);
        textViewTitle.setText(titleDesc);
        textViewTitle.setTextColor(titleColor);
        textViewTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
        editTextContent.setHint(editHint);
        editTextContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, centerSize);
        editTextContent.setTextColor(centerColor);
        editTextContent.setHintTextColor(centerHintColor);
    }

    /**
     * todo 初始化分割线
     *
     * @param context 上下文
     */
    private void initDivider(Context context) {
        viewDivider.setVisibility(isDivider ? VISIBLE : GONE);
        viewDivider.setBackgroundColor(dividerBgColor);
        ConstraintLayout.LayoutParams layoutParams = (LayoutParams) viewDivider.getLayoutParams();
        layoutParams.leftMargin = dividerMargin;
        layoutParams.height = dividerHeight;
    }

    private void initListener() {
        buttonGo.setOnClickListener(v -> {
            if (null != onADFieldTextLayoutListener) {
                onADFieldTextLayoutListener.onField(v, textViewTitle.getText().toString().trim());
            }
        });
    }


    public TextView getTextViewRequired() {
        return textViewRequired;
    }

    public TextView getTextViewTitle() {
        return textViewTitle;
    }

    public EditText getEditTextContent() {
        return editTextContent;
    }

    public ADButton getButtonGo() {
        return buttonGo;
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

    public void setOnADFieldTextLayoutListener(OnADFieldTextLayoutListener onADFieldTextLayoutListener) {
        this.onADFieldTextLayoutListener = onADFieldTextLayoutListener;
    }

    public interface OnADFieldTextLayoutListener {

        /**
         * 数据框点击
         *
         * @param view  view组件
         * @param title field标题
         */
        void onField(View view, String title);
    }
}
