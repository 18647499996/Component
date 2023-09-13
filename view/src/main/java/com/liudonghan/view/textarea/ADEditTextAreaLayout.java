package com.liudonghan.view.textarea;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.liudonghan.view.R;
import com.liudonghan.view.radius.ADConstraintLayout;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:9/8/23
 */
public class ADEditTextAreaLayout extends ADConstraintLayout implements TextWatcher {

    private EditText editText;
    private TextView textView;
    private int maxLength, editColor, editHintColor, editTextSize, hintColor, hintTextSize;
    private String hintLast,editHint;
    private OnADEditTextAreaLayoutListener onADEditTextAreaLayoutListener;

    public ADEditTextAreaLayout(Context context) {
        super(context, null);
    }

    public ADEditTextAreaLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View inflate = View.inflate(context, R.layout.ad_edit_text_area, this);
        editText = inflate.findViewById(R.id.ad_edit_text_area_edt_content);
        textView = inflate.findViewById(R.id.ad_edit_text_area_tv_hint);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ADEditTextAreaLayout);
        maxLength = typedArray.getInt(R.styleable.ADEditTextAreaLayout_liu_max_length, 50);
        editColor = typedArray.getColor(R.styleable.ADEditTextAreaLayout_liu_edit_color, Color.parseColor("#342e2e"));
        editHintColor = typedArray.getColor(R.styleable.ADEditTextAreaLayout_liu_edit_hint_color, Color.parseColor("#cccccc"));
        editTextSize = typedArray.getDimensionPixelOffset(R.styleable.ADEditTextAreaLayout_liu_edit_text_size, context.getResources().getDimensionPixelOffset(R.dimen.dip_12));
        hintColor = typedArray.getColor(R.styleable.ADEditTextAreaLayout_liu_hint_color, Color.parseColor("#aaaaaa"));
        hintTextSize = typedArray.getDimensionPixelOffset(R.styleable.ADEditTextAreaLayout_liu_hint_text_size, context.getResources().getDimensionPixelOffset(R.dimen.dip_12));
        editHint = typedArray.getString(R.styleable.ADEditTextAreaLayout_liu_edit_hint);
        hintLast = typedArray.getString(R.styleable.ADEditTextAreaLayout_liu_last);
        typedArray.recycle();
        initEdit(context);
        initListener();
    }

    private void initListener() {
        editText.addTextChangedListener(this);
    }

    @SuppressLint("SetTextI18n")
    private void initEdit(Context context) {
        editText.setTextColor(editColor);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, editTextSize);
        editText.setHintTextColor(editHintColor);
        editText.setHint(editHint);
        textView.setTextColor(hintColor);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, hintTextSize);
        textView.setText("0/" + maxLength + " " + (TextUtils.isEmpty(hintLast) ? "字" : hintLast));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > maxLength) {
            editText.setText(s.subSequence(0, maxLength));
            Selection.setSelection(editText.getText(), maxLength);
            if (null != onADEditTextAreaLayoutListener) {
                onADEditTextAreaLayoutListener.onExceed(s);
            }
        } else {
            textView.setText(editText.getText().toString().trim().length() + "/" + maxLength + " " + (TextUtils.isEmpty(hintLast) ? "字" : hintLast));
            if (null != onADEditTextAreaLayoutListener) {
                onADEditTextAreaLayoutListener.onAfter(s);
            }
        }
    }

    public void setOnADEditTextAreaLayoutListener(OnADEditTextAreaLayoutListener onADEditTextAreaLayoutListener) {
        this.onADEditTextAreaLayoutListener = onADEditTextAreaLayoutListener;
    }

    public EditText getEditText() {
        return editText;
    }

    public TextView getTextView() {
        return textView;
    }

    public String getContent() {
        return editText.getText().toString().trim();
    }

    public interface OnADEditTextAreaLayoutListener {

        /**
         * 超出范围
         *
         * @param editable edit引用
         */
        void onExceed(Editable editable);

        /**
         * 输入监听
         *
         * @param editable edit引用
         */
        void onAfter(Editable editable);
    }
}
