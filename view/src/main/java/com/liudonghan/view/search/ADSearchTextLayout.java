package com.liudonghan.view.search;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.liudonghan.view.R;
import com.liudonghan.view.cell.ADCellTextLayout;
import com.liudonghan.view.radius.ADButton;
import com.liudonghan.view.radius.ADConstraintLayout;
import com.liudonghan.view.radius.ADImageView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:10/7/23
 */
public class ADSearchTextLayout extends ADConstraintLayout implements TextWatcher, View.OnClickListener {

    private Visibility leftVisibility, rightVisibility;
    private int leftDrawable, contentBg, contentCorner, rightBg, rightTextColor;
    private ADImageView imageViewLeft;
    private ImageView imageViewClear;
    private ADConstraintLayout constraintLayoutContent;
    private EditText editTextContent;
    private ADButton buttonRight;
    private String contentHint, rightHint;
    private boolean isFocusable, isClear;
    private OnADSearchTextLayoutListener onADSearchTextLayoutListener;
    private OnADSearchTextLayoutClickListener onADSearchTextLayoutClickListener;

    public ADSearchTextLayout(Context context) {
        super(context, null);
    }

    public ADSearchTextLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = View.inflate(context, R.layout.ad_search_text, this);
        imageViewLeft = inflate.findViewById(R.id.ad_search_text_img_left);
        imageViewClear = inflate.findViewById(R.id.ad_search_text_img_clear);
        constraintLayoutContent = inflate.findViewById(R.id.ad_search_text_cl_content);
        editTextContent = inflate.findViewById(R.id.ad_search_text_edt_content);
        buttonRight = inflate.findViewById(R.id.ad_search_text_btn_right);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ADSearchTextLayout);
        leftDrawable = typedArray.getResourceId(R.styleable.ADSearchTextLayout_liu_search_left_drawable, 0);
        leftVisibility = Visibility.fromInt(typedArray.getInt(R.styleable.ADSearchTextLayout_liu_search_left_visibility, Visibility.Gone.getValue()));
        contentBg = typedArray.getColor(R.styleable.ADSearchTextLayout_liu_content_background_color, Color.parseColor("#f5f5f5"));
        contentCorner = typedArray.getDimensionPixelOffset(R.styleable.ADSearchTextLayout_liu_content_corner, 4);
        contentHint = typedArray.getString(R.styleable.ADSearchTextLayout_liu_content_hint);
        rightBg = typedArray.getColor(R.styleable.ADSearchTextLayout_liu_search_right_background_color, Color.parseColor("#333333"));
        rightTextColor = typedArray.getColor(R.styleable.ADSearchTextLayout_liu_search_right_text_color, Color.parseColor("#ffffff"));
        rightHint = typedArray.getString(R.styleable.ADSearchTextLayout_liu_search_right_hint);
        rightVisibility = Visibility.fromInt(typedArray.getInt(R.styleable.ADSearchTextLayout_liu_search_right_visibility, Visibility.Gone.getValue()));
        isFocusable = typedArray.getBoolean(R.styleable.ADSearchTextLayout_liu_search_focusable, true);
        isClear = typedArray.getBoolean(R.styleable.ADSearchTextLayout_liu_search_clear, false);
        typedArray.recycle();
        initLeft(context);
        initContent(context);
        initRight(context);
    }

    private void initRight(Context context) {
        buttonRight.setText(TextUtils.isEmpty(rightHint) ? "搜索" : rightHint);
        buttonRight.setBackgroundColor(rightBg);
        buttonRight.setTextColor(rightTextColor);
        buttonRight.setVisibility(rightVisibility == Visibility.Visibility ? VISIBLE : GONE);
        buttonRight.setOnClickListener(this);
    }

    private void initContent(Context context) {
        constraintLayoutContent.setBackgroundColor(contentBg);
        constraintLayoutContent.setRadius(contentCorner);
        editTextContent.setHint(TextUtils.isEmpty(contentHint) ? "搜一搜" : contentHint);
        editTextContent.setFocusable(isFocusable);
        editTextContent.addTextChangedListener(this);
        editTextContent.setOnClickListener(this);
    }

    private void initLeft(Context context) {
        imageViewLeft.setVisibility(leftVisibility == Visibility.Visibility ? VISIBLE : GONE);
        imageViewLeft.setImageResource(0 != leftDrawable ? leftDrawable : R.drawable.ad_back_black);
        imageViewLeft.setOnClickListener(this);
        imageViewClear.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (null != onADSearchTextLayoutListener) {
            onADSearchTextLayoutListener.afterTextChanged(s);
        }
        imageViewClear.setVisibility(0 != s.length() ? isClear ? VISIBLE : GONE : GONE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ad_search_text_edt_content) {
            // 点击内容
            if (null != onADSearchTextLayoutClickListener) {
                if (!isFocusable) {
                    onADSearchTextLayoutClickListener.onClickContent();
                } else {
                    Log.i("Mac_Liu", "is focusable true not click");
                }
            }
        } else if (id == R.id.ad_search_text_btn_right) {
            // 点击右侧按钮
            if (null != onADSearchTextLayoutClickListener) {
                onADSearchTextLayoutClickListener.onClickRight();
            }
        } else if (id == R.id.ad_search_text_img_left) {
            // 点击左侧图标
            if (null != onADSearchTextLayoutClickListener) {
                onADSearchTextLayoutClickListener.onClickLeft();
            }
        } else if (id == R.id.ad_search_text_img_clear) {
            editTextContent.setText("");
            if (null != onADSearchTextLayoutClickListener) {
                onADSearchTextLayoutClickListener.onClickClear();
            }
        }
    }

    public enum Visibility {
        Visibility(1),
        Gone(2);

        private int id;

        Visibility(int id) {
            this.id = id;
        }

        public int getValue() {
            return id;
        }

        public static Visibility fromInt(int value) {
            switch (value) {
                case 1:
                    return Visibility;
                case 2:
                    return Gone;
                default:
                    throw new Error("Invalid SourceType");
            }
        }
    }

    public void setOnADSearchTextLayoutListener(OnADSearchTextLayoutListener onADSearchTextLayoutListener) {
        this.onADSearchTextLayoutListener = onADSearchTextLayoutListener;
    }

    public void setOnADSearchTextLayoutClickListener(OnADSearchTextLayoutClickListener onADSearchTextLayoutClickListener) {
        this.onADSearchTextLayoutClickListener = onADSearchTextLayoutClickListener;
    }

    public interface OnADSearchTextLayoutListener {
        /**
         * 文本监听
         *
         * @param editable edt引用
         */
        void afterTextChanged(Editable editable);
    }

    public interface OnADSearchTextLayoutClickListener {

        /**
         * 点击输入框
         */
        void onClickContent();

        /**
         * 点击左侧图标
         */
        void onClickLeft();

        /**
         * 点击右侧图标
         */
        void onClickRight();

        /**
         * 清空输入框
         */
        void onClickClear();
    }
}
