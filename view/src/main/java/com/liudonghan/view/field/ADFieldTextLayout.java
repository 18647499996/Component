package com.liudonghan.view.field;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
public class ADFieldTextLayout extends ADConstraintLayout {

    private TextView textViewRequired, textViewTitle;
    private EditText editTextContent;
    private ADButton buttonGo;
    private View viewDivider;
    private boolean isRequired, isInsert, isDivider, isFocusable;
    private String titleDesc, editHint, insertHint;
    private int dividerMargin, dividerBgColor, dividerHeight, insertRadius, insertHintColor, insertBgColor, centerColor, centerHintColor, titleColor;
    private float titleSize, centerSize;
    private OnADFieldTextLayoutListener onADFieldTextLayoutListener;
    private InputType inputType;

    public ADFieldTextLayout(@NonNull Context context) {
        super(context, null);
    }


    public ADFieldTextLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
        centerSize = typedArray.getDimensionPixelSize(R.styleable.ADFieldTextLayout_liu_center_size, context.getResources().getDimensionPixelOffset(R.dimen.dip_12));
        inputType = InputType.fromInt(typedArray.getInt(R.styleable.ADFieldTextLayout_liu_input_type, InputType.Text.getId()));
        isFocusable = typedArray.getBoolean(R.styleable.ADFieldTextLayout_liu_is_focusable, true);
        // 左侧标签
        titleDesc = typedArray.getString(R.styleable.ADFieldTextLayout_liu_title);
        titleColor = typedArray.getColor(R.styleable.ADFieldTextLayout_liu_title_color, Color.parseColor("#333333"));
        titleSize = typedArray.getDimensionPixelSize(R.styleable.ADFieldTextLayout_liu_title_size, context.getResources().getDimensionPixelOffset(R.dimen.dip_12));
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
        editTextContent.setFocusable(isFocusable);
        switch (inputType) {
            case Text:
                editTextContent.setInputType(EditorInfo.TYPE_CLASS_TEXT);
                break;
            case Number:
                editTextContent.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
                break;
            case Phone:
                editTextContent.setInputType(EditorInfo.TYPE_CLASS_PHONE);
                break;
            case Decimal:
                editTextContent.setInputType(EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            case Empty:
                editTextContent.setInputType(EditorInfo.TYPE_NULL);
                break;
        }
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

    public enum InputType {
        Text(1),
        Number(2),
        Phone(3),
        Decimal(4),
        Empty(5);

        private int id;

        InputType(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static InputType fromInt(int value) {
            switch (value) {
                case 1:
                    return Text;
                case 2:
                    return Number;
                case 3:
                    return Phone;
                case 4:
                    return Decimal;
                case 5:
                    return Empty;
                default:
                    throw new Error("Invalid SourceType");
            }
        }
    }
}
