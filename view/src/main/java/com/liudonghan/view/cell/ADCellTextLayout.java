package com.liudonghan.view.cell;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.liudonghan.view.R;
import com.liudonghan.view.radius.ADConstraintLayout;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/16/23
 */
public class ADCellTextLayout extends ADConstraintLayout {

    private Context context;
    private String leftText, rightText, subText;
    private int leftTextColor, leftDrawableWidth, leftDrawableHeight, leftDrawablePadding, leftDrawable;
    private int rightTextColor, rightDrawableWidth, rightDrawableHeight, rightDrawablePadding, rightDrawable;
    private int subTextColor, subDrawableWidth, subDrawableHeight, subDrawablePadding, subDrawable, subMarginRight, subMarginLeft;
    private TextView textViewLeft, textViewRight, textViewSub;
    private float leftTextSize, rightTextSize, subTextSize;
    private View lineView;
    private int lineMarginRight, lineMarginLeft, lineBgColor, lineHeight;
    private boolean leftBold, rightBold, subBold;

    private Direction leftDrawableDirection, rightDrawableDirection, subDrawableDirection;
    private Visibility lineVisibility;

    public ADCellTextLayout(@NonNull Context context) {
        super(context, null);
    }

    public ADCellTextLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View inflate = View.inflate(context, R.layout.ad_cell_group, this);
        textViewLeft = inflate.findViewById(R.id.view_cell_group_tv_left);
        textViewSub = inflate.findViewById(R.id.view_cell_group_tv_sub);
        textViewRight = inflate.findViewById(R.id.view_cell_group_tv_right);
        lineView = inflate.findViewById(R.id.view_cell_group_view_line);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ADCellTextLayout);
        // 左侧标签
        leftText = typedArray.getString(R.styleable.ADCellTextLayout_liu_left_text);
        leftTextSize = typedArray.getDimensionPixelSize(R.styleable.ADCellTextLayout_liu_left_text_size, 40);
        leftTextColor = typedArray.getColor(R.styleable.ADCellTextLayout_liu_left_text_color, Color.parseColor("#342e2e"));
        leftDrawable = typedArray.getResourceId(R.styleable.ADCellTextLayout_liu_left_drawable, 0);
        leftDrawableDirection = Direction.fromInt(typedArray.getInt(R.styleable.ADCellTextLayout_liu_left_drawable_direction, Direction.left.getValue()));
        leftBold = typedArray.getBoolean(R.styleable.ADCellTextLayout_liu_left_text_bold, false);
        leftDrawablePadding = typedArray.getDimensionPixelOffset(R.styleable.ADCellTextLayout_liu_left_drawable_padding, 0);
        leftDrawableWidth = typedArray.getDimensionPixelOffset(R.styleable.ADCellTextLayout_liu_left_drawable_width, 0);
        leftDrawableHeight = typedArray.getDimensionPixelOffset(R.styleable.ADCellTextLayout_liu_left_drawable_height, 0);

        // 副标签
        subText = typedArray.getString(R.styleable.ADCellTextLayout_liu_sub_text);
        subTextSize = typedArray.getDimensionPixelSize(R.styleable.ADCellTextLayout_liu_sub_text_size, 40);
        subTextColor = typedArray.getColor(R.styleable.ADCellTextLayout_liu_sub_text_color, Color.parseColor("#342e2e"));
        subDrawable = typedArray.getResourceId(R.styleable.ADCellTextLayout_liu_sub_drawable, 0);
        subDrawableDirection = Direction.fromInt(typedArray.getInt(R.styleable.ADCellTextLayout_liu_sub_drawable_direction, Direction.left.getValue()));
        subBold = typedArray.getBoolean(R.styleable.ADCellTextLayout_liu_sub_text_bold, false);
        subDrawablePadding = typedArray.getDimensionPixelOffset(R.styleable.ADCellTextLayout_liu_sub_drawable_padding, 0);
        subDrawableWidth = typedArray.getDimensionPixelOffset(R.styleable.ADCellTextLayout_liu_sub_drawable_width, 0);
        subDrawableHeight = typedArray.getDimensionPixelOffset(R.styleable.ADCellTextLayout_liu_sub_drawable_height, 0);
        subMarginRight = typedArray.getDimensionPixelOffset(R.styleable.ADCellTextLayout_liu_sub_right_margin, 0);
        subMarginLeft = typedArray.getDimensionPixelOffset(R.styleable.ADCellTextLayout_liu_sub_left_margin, 0);

        // 右侧标签
        rightText = typedArray.getString(R.styleable.ADCellTextLayout_liu_right_text);
        rightTextSize = typedArray.getDimensionPixelSize(R.styleable.ADCellTextLayout_liu_right_text_size, 40);
        rightTextColor = typedArray.getColor(R.styleable.ADCellTextLayout_liu_right_text_color, Color.parseColor("#999999"));
        rightDrawable = typedArray.getResourceId(R.styleable.ADCellTextLayout_liu_right_drawable, 0);
        rightDrawableDirection = Direction.fromInt(typedArray.getInt(R.styleable.ADCellTextLayout_liu_right_drawable_direction, Direction.right.getValue()));
        rightBold = typedArray.getBoolean(R.styleable.ADCellTextLayout_liu_right_text_bold, false);
        rightDrawableWidth = typedArray.getDimensionPixelOffset(R.styleable.ADCellTextLayout_liu_right_drawable_width, 0);
        rightDrawableHeight = typedArray.getDimensionPixelOffset(R.styleable.ADCellTextLayout_liu_right_drawable_height, 0);
        rightDrawablePadding = typedArray.getDimensionPixelOffset(R.styleable.ADCellTextLayout_liu_right_drawable_padding, 0);

        // 分割线
        lineMarginRight = typedArray.getDimensionPixelOffset(R.styleable.ADCellTextLayout_liu_line_right_margin, 0);
        lineMarginLeft = typedArray.getDimensionPixelOffset(R.styleable.ADCellTextLayout_liu_line_left_margin, 0);
        lineBgColor = typedArray.getColor(R.styleable.ADCellTextLayout_liu_line_background_color, Color.parseColor("#ebebeb"));
        lineVisibility = Visibility.fromInt(typedArray.getInt(R.styleable.ADCellTextLayout_liu_line_visibility, Visibility.Visibility.getValue()));
        lineHeight = typedArray.getDimensionPixelOffset(R.styleable.ADCellTextLayout_liu_line_height, context.getResources().getDimensionPixelOffset(R.dimen.ad_0_67));

        typedArray.recycle();
        initCell(context);
    }

    /**
     * 初始化cell
     *
     * @param context 上下文
     */
    private void initCell(Context context) {
        // 设置左侧view
        settingLeftTextCell(context);
        // 副标签
        settingSubTextCell(context);
        // 设置右侧view
        settingRightTextCell(context);
        // 设置line分割线
        settingLineView(context);

    }

    private void settingLineView(Context context) {
        lineView.setVisibility(lineVisibility == Visibility.Visibility ? VISIBLE : GONE);
        lineView.setBackgroundColor(lineBgColor);
        ConstraintLayout.LayoutParams layoutParams = (LayoutParams) lineView.getLayoutParams();
        layoutParams.leftMargin = lineMarginLeft;
        layoutParams.rightMargin = lineMarginRight;
        layoutParams.height = lineHeight;
    }

    /**
     * 右侧标签
     *
     * @param context 上下文`
     */
    private void settingRightTextCell(Context context) {
        textViewRight.setText(rightText);
        textViewRight.setTextColor(rightTextColor);
        textViewRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
        if (rightBold) {
            textViewRight.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            textViewRight.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
        if (0 != rightDrawable) {
            textViewRight.setCompoundDrawablePadding(rightDrawablePadding);
            Drawable drawable = context.getResources().getDrawable(rightDrawable);
            int height = drawable.getMinimumHeight();
            int width = drawable.getMinimumWidth();
            drawable.setBounds(0, 0, 0 == rightDrawableWidth ? width : rightDrawableWidth, 0 == rightDrawableHeight ? height : rightDrawableHeight);
            switch (rightDrawableDirection) {
                case left:
                    textViewRight.setCompoundDrawables(drawable, null, null, null);
                    break;
                case top:
                    textViewRight.setCompoundDrawables(null, drawable, null, null);
                    break;
                case right:
                    textViewRight.setCompoundDrawables(null, null, drawable, null);
                    break;
                case bottom:
                    textViewRight.setCompoundDrawables(null, null, null, drawable);
                    break;
            }
        }
        ConstraintLayout.LayoutParams layoutParams = (LayoutParams) textViewSub.getLayoutParams();
        layoutParams.leftMargin = subMarginLeft;
        layoutParams.rightMargin = subMarginRight;
    }

    /**
     * 副标签
     *
     * @param context 上下文
     */
    private void settingSubTextCell(Context context) {
        textViewSub.setText(subText);
        textViewSub.setTextColor(subTextColor);
        textViewSub.setTextSize(TypedValue.COMPLEX_UNIT_PX, subTextSize);
        if (subBold) {
            textViewSub.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            textViewSub.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
        if (0 != subDrawable) {
            textViewSub.setCompoundDrawablePadding(subDrawablePadding);
            Drawable drawable = context.getResources().getDrawable(leftDrawable);
            int height = drawable.getMinimumHeight();
            drawable.setBounds(0, 0, 0 == subDrawableWidth ? height : subDrawableWidth, 0 == subDrawableHeight ? height : subDrawableHeight);
            switch (subDrawableDirection) {
                case left:
                    textViewSub.setCompoundDrawables(drawable, null, null, null);
                    break;
                case top:
                    textViewSub.setCompoundDrawables(null, drawable, null, null);
                    break;
                case right:
                    textViewSub.setCompoundDrawables(null, null, drawable, null);
                    break;
                case bottom:
                    textViewSub.setCompoundDrawables(null, null, null, drawable);
                    break;
            }
        }
    }

    /**
     * 左侧标签
     *
     * @param context 上下文
     */
    private void settingLeftTextCell(Context context) {
        textViewLeft.setText(leftText);
        textViewLeft.setTextColor(leftTextColor);
        textViewLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
        if (leftBold) {
            textViewLeft.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            textViewLeft.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
        if (0 != leftDrawable) {
            textViewLeft.setCompoundDrawablePadding(leftDrawablePadding);
            Drawable drawable = context.getResources().getDrawable(leftDrawable);
            int height = drawable.getMinimumHeight();
            drawable.setBounds(0, 0, 0 == leftDrawableWidth ? height : leftDrawableWidth, 0 == leftDrawableHeight ? height : leftDrawableHeight);
            switch (leftDrawableDirection) {
                case left:
                    textViewLeft.setCompoundDrawables(drawable, null, null, null);
                    break;
                case top:
                    textViewLeft.setCompoundDrawables(null, drawable, null, null);
                    break;
                case right:
                    textViewLeft.setCompoundDrawables(null, null, drawable, null);
                    break;
                case bottom:
                    textViewLeft.setCompoundDrawables(null, null, null, drawable);
                    break;
            }
        }
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
        initCell(context);
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
        initCell(context);
    }

    public void setSubText(String subText) {
        this.subText = subText;
        initCell(context);
    }

    public void setSubTextColor(int subTextColor) {
        this.subTextColor = subTextColor;
        initCell(context);
    }

    public void setSubTextSize(float subTextSize) {
        this.subTextSize = subTextSize;
        initCell(context);
    }

    public void setSubBold(boolean subBold) {
        this.subBold = subBold;
        initCell(context);
    }

    public void setSubDrawable(int subDrawable) {
        this.subDrawable = subDrawable;
        initCell(context);
    }

    public void setSubDrawableHeight(int subDrawableHeight) {
        this.subDrawableHeight = subDrawableHeight;
        initCell(context);
    }

    public void setSubDrawablePadding(int subDrawablePadding) {
        this.subDrawablePadding = subDrawablePadding;
        initCell(context);
    }

    public void setSubDrawableWidth(int subDrawableWidth) {
        this.subDrawableWidth = subDrawableWidth;
        initCell(context);
    }

    public void setSubMarginLeft(int subMarginLeft) {
        this.subMarginLeft = subMarginLeft;
        initCell(context);
    }

    public void setSubMarginRight(int subMarginRight) {
        this.subMarginRight = subMarginRight;
        initCell(context);
    }

    public void setTextViewSub(TextView textViewSub) {
        this.textViewSub = textViewSub;
        initCell(context);
    }

    public void setLeftTextColor(int leftTextColor) {
        this.leftTextColor = leftTextColor;
        initCell(context);
    }

    public void setLeftDrawableWidth(int leftDrawableWidth) {
        this.leftDrawableWidth = leftDrawableWidth;
        initCell(context);
    }

    public void setLeftDrawableHeight(int leftDrawableHeight) {
        this.leftDrawableHeight = leftDrawableHeight;
        initCell(context);
    }

    public void setLeftDrawablePadding(int leftDrawablePadding) {
        this.leftDrawablePadding = leftDrawablePadding;
        initCell(context);
    }

    public void setLeftDrawable(int leftDrawable) {
        this.leftDrawable = leftDrawable;
        initCell(context);
    }

    public void setRightTextColor(int rightTextColor) {
        this.rightTextColor = rightTextColor;
        initCell(context);
    }

    public void setRightDrawableWidth(int rightDrawableWidth) {
        this.rightDrawableWidth = rightDrawableWidth;
        initCell(context);
    }

    public void setRightDrawableHeight(int rightDrawableHeight) {
        this.rightDrawableHeight = rightDrawableHeight;
        initCell(context);
    }

    public void setRightDrawablePadding(int rightDrawablePadding) {
        this.rightDrawablePadding = rightDrawablePadding;
        initCell(context);
    }

    public void setRightDrawable(int rightDrawable) {
        this.rightDrawable = rightDrawable;
        initCell(context);
    }

    public void setTextViewLeft(TextView textViewLeft) {
        this.textViewLeft = textViewLeft;
        initCell(context);
    }

    public void setTextViewRight(TextView textViewRight) {
        this.textViewRight = textViewRight;
        initCell(context);
    }

    public void setLeftTextSize(float leftTextSize) {
        this.leftTextSize = leftTextSize;
        initCell(context);
    }

    public void setRightTextSize(float rightTextSize) {
        this.rightTextSize = rightTextSize;
        initCell(context);
    }

    public void setLeftDrawableDirection(Direction leftDrawableDirection) {
        this.leftDrawableDirection = leftDrawableDirection;
        initCell(context);
    }

    public void setRightDrawableDirection(Direction rightDrawableDirection) {
        this.rightDrawableDirection = rightDrawableDirection;
        initCell(context);
    }

    public void setSubDrawableDirection(Direction subDrawableDirection) {
        this.subDrawableDirection = subDrawableDirection;
        initCell(context);
    }

    public void setLineBgColor(int lineBgColor) {
        this.lineBgColor = lineBgColor;
        initCell(context);
    }

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
        initCell(context);
    }

    public void setLineMarginLeft(int lineMarginLeft) {
        this.lineMarginLeft = lineMarginLeft;
        initCell(context);
    }

    public void setLineMarginRight(int lineMarginRight) {
        this.lineMarginRight = lineMarginRight;
        initCell(context);
    }

    public void setLineVisibility(Visibility lineVisibility) {
        this.lineVisibility = lineVisibility;
        initCell(context);
    }

    public void setLeftBold(boolean leftBold) {
        this.leftBold = leftBold;
        initCell(context);
    }

    public void setRightBold(boolean rightBold) {
        this.rightBold = rightBold;
        initCell(context);
    }

    public boolean isLeftBold() {
        return leftBold;
    }

    public boolean isRightBold() {
        return rightBold;
    }

    public int getLineMarginRight() {
        return lineMarginRight;
    }

    public int getLineMarginLeft() {
        return lineMarginLeft;
    }

    public int getLineBgColor() {
        return lineBgColor;
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public Visibility getLineVisibility() {
        return lineVisibility;
    }

    public String getLeftText() {
        return leftText;
    }

    public String getRightText() {
        return rightText;
    }

    public int getLeftTextColor() {
        return leftTextColor;
    }

    public int getLeftDrawableWidth() {
        return leftDrawableWidth;
    }

    public int getLeftDrawableHeight() {
        return leftDrawableHeight;
    }

    public int getLeftDrawablePadding() {
        return leftDrawablePadding;
    }

    public int getLeftDrawable() {
        return leftDrawable;
    }

    public int getRightTextColor() {
        return rightTextColor;
    }

    public int getRightDrawableWidth() {
        return rightDrawableWidth;
    }

    public int getRightDrawableHeight() {
        return rightDrawableHeight;
    }

    public int getRightDrawablePadding() {
        return rightDrawablePadding;
    }

    public int getRightDrawable() {
        return rightDrawable;
    }

    public TextView getTextViewLeft() {
        return textViewLeft;
    }

    public TextView getTextViewRight() {
        return textViewRight;
    }

    public TextView getTextViewSub() {
        return textViewSub;
    }

    public String getSubText() {
        return subText;
    }

    public int getSubDrawable() {
        return subDrawable;
    }

    public int getSubTextColor() {
        return subTextColor;
    }

    public int getSubDrawableHeight() {
        return subDrawableHeight;
    }

    public int getSubDrawablePadding() {
        return subDrawablePadding;
    }

    public int getSubDrawableWidth() {
        return subDrawableWidth;
    }

    public int getSubMarginLeft() {
        return subMarginLeft;
    }

    public int getSubMarginRight() {
        return subMarginRight;
    }

    public float getLeftTextSize() {
        return leftTextSize;
    }

    public float getRightTextSize() {
        return rightTextSize;
    }

    public float getSubTextSize() {
        return subTextSize;
    }

    public Direction getLeftDrawableDirection() {
        return leftDrawableDirection;
    }

    public Direction getRightDrawableDirection() {
        return rightDrawableDirection;
    }

    public Direction getSubDrawableDirection() {
        return subDrawableDirection;
    }

    public enum Direction {
        left(1),
        top(2),
        right(3),
        bottom(4);

        private int id;

        Direction(int id) {
            this.id = id;
        }

        public int getValue() {
            return id;
        }

        public static Direction fromInt(int value) {
            switch (value) {
                case 1:
                    return left;
                case 2:
                    return top;
                case 3:
                    return right;
                case 4:
                    return bottom;
                default:
                    throw new Error("Invalid SourceType");
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

    public enum TextStyle {
        Bold(1),
        Default(0);

        private int id;

        TextStyle(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static TextStyle fromInt(int value) {
            switch (value) {
                case 0:
                    return Default;
                case 1:
                    return Bold;
                default:
                    throw new Error("Invalid SourceType");
            }
        }
    }
}
