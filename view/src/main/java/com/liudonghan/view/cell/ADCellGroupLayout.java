package com.liudonghan.view.cell;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
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
public class ADCellGroupLayout extends ADConstraintLayout {

    private String leftText, rightText;
    private int leftTextColor, leftDrawableWidth, leftDrawableHeight, leftDrawablePadding, leftDrawable;
    private int rightTextColor, rightDrawableWidth, rightDrawableHeight, rightDrawablePadding, rightDrawable;
    private TextView textViewLeft, textViewRight;
    private float leftTextSize, rightTextSize;

    private Direction leftDrawableDirection, rightDrawableDirection;

    public ADCellGroupLayout(@NonNull Context context) {
        super(context, null);
    }

    public ADCellGroupLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View inflate = View.inflate(context, R.layout.view_cell_group, this);
        textViewLeft = inflate.findViewById(R.id.view_cell_group_tv_left);
        textViewRight = inflate.findViewById(R.id.view_cell_group_tv_right);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ADCellGroupLayout);
        // 左侧标签
        leftText = typedArray.getString(R.styleable.ADCellGroupLayout_liu_left_text);
        leftTextSize = typedArray.getFloat(R.styleable.ADCellGroupLayout_liu_left_text_size, 14);
        leftTextColor = typedArray.getColor(R.styleable.ADCellGroupLayout_liu_left_text_color, Color.parseColor("#342e2e"));
        leftDrawable = typedArray.getResourceId(R.styleable.ADCellGroupLayout_liu_left_drawable, 0);
        leftDrawableDirection = Direction.fromInt(typedArray.getInt(R.styleable.ADCellGroupLayout_liu_left_drawable_direction, Direction.left.getValue()));
        leftDrawablePadding = typedArray.getDimensionPixelOffset(R.styleable.ADCellGroupLayout_liu_left_drawable_padding, 0);
        leftDrawableWidth = typedArray.getDimensionPixelOffset(R.styleable.ADCellGroupLayout_liu_left_drawable_width, 0);
        leftDrawableHeight = typedArray.getDimensionPixelOffset(R.styleable.ADCellGroupLayout_liu_left_drawable_height, 0);

        // 右侧标签
        rightText = typedArray.getString(R.styleable.ADCellGroupLayout_liu_right_text);
        rightTextSize = typedArray.getFloat(R.styleable.ADCellGroupLayout_liu_right_text_size, 12);
        rightTextColor = typedArray.getColor(R.styleable.ADCellGroupLayout_liu_right_text_color, Color.parseColor("#999999"));
        rightDrawable = typedArray.getResourceId(R.styleable.ADCellGroupLayout_liu_right_drawable, 0);
        rightDrawableDirection = Direction.fromInt(typedArray.getInt(R.styleable.ADCellGroupLayout_liu_right_drawable_direction, Direction.right.getValue()));
        rightDrawableWidth = typedArray.getDimensionPixelOffset(R.styleable.ADCellGroupLayout_liu_right_drawable_width, 0);
        rightDrawableHeight = typedArray.getDimensionPixelOffset(R.styleable.ADCellGroupLayout_liu_right_drawable_height, 0);
        rightDrawablePadding = typedArray.getDimensionPixelOffset(R.styleable.ADCellGroupLayout_liu_right_drawable_padding, 0);

        typedArray.recycle();

        initCell(context);
    }

    /**
     * 初始化cell
     *
     * @param context 上下文
     */
    private void initCell(Context context) {
        textViewLeft.setText(leftText);
        textViewLeft.setTextColor(leftTextColor);
        textViewLeft.setTextSize(leftTextSize);
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

        textViewRight.setText(rightText);
        textViewRight.setTextColor(rightTextColor);
        textViewRight.setTextSize(rightTextSize);
        if (0 != rightDrawable) {
            textViewRight.setCompoundDrawablePadding(rightDrawablePadding);
            Drawable drawable = context.getResources().getDrawable(rightDrawable);
            drawable.setBounds(0, 0, 0 == rightDrawableWidth ? textViewRight.getWidth() : rightDrawableWidth, 0 == rightDrawableHeight ? textViewRight.getHeight() : rightDrawableHeight);
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
    }

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public float getLeftTextSize() {
        return leftTextSize;
    }

    public void setLeftTextSize(int leftTextSize) {
        this.leftTextSize = leftTextSize;
    }

    public int getLeftTextColor() {
        return leftTextColor;
    }

    public void setLeftTextColor(int leftTextColor) {
        this.leftTextColor = leftTextColor;
    }

    public int getLeftDrawableWith() {
        return leftDrawableWidth;
    }

    public void setLeftDrawableWidth(int leftDrawableWith) {
        this.leftDrawableWidth = leftDrawableWith;
    }

    public int getLeftDrawableHeight() {
        return leftDrawableHeight;
    }

    public void setLeftDrawableHeight(int leftDrawableHeight) {
        this.leftDrawableHeight = leftDrawableHeight;
    }

    public int getLeftDrawablePadding() {
        return leftDrawablePadding;
    }

    public void setLeftDrawablePadding(int leftDrawablePadding) {
        this.leftDrawablePadding = leftDrawablePadding;
    }

    public float getRightTextSize() {
        return rightTextSize;
    }

    public void setRightTextSize(int rightTextSize) {
        this.rightTextSize = rightTextSize;
    }

    public int getRightTextColor() {
        return rightTextColor;
    }

    public void setRightTextColor(int rightTextColor) {
        this.rightTextColor = rightTextColor;
    }

    public int getRightDrawablePadding() {
        return rightDrawablePadding;
    }

    public void setRightDrawablePadding(int rightDrawablePadding) {
        this.rightDrawablePadding = rightDrawablePadding;
    }

    public int getRightDrawableWidth() {
        return rightDrawableWidth;
    }

    public void setRightDrawableWidth(int rightDrawableWidth) {
        this.rightDrawableWidth = rightDrawableWidth;
    }

    public int getRightDrawableHeight() {
        return rightDrawableHeight;
    }

    public void setRightDrawableHeight(int rightDrawableHeight) {
        this.rightDrawableHeight = rightDrawableHeight;
    }

    public int getLeftDrawable() {
        return leftDrawable;
    }

    public void setLeftDrawable(int leftDrawable) {
        this.leftDrawable = leftDrawable;
    }

    public int getRightDrawable() {
        return rightDrawable;
    }

    public void setRightDrawable(int rightDrawable) {
        this.rightDrawable = rightDrawable;
    }

    public TextView getTextViewLeft() {
        return textViewLeft;
    }

    public void setTextViewLeft(TextView textViewLeft) {
        this.textViewLeft = textViewLeft;
    }

    public TextView getTextViewRight() {
        return textViewRight;
    }

    public void setTextViewRight(TextView textViewRight) {
        this.textViewRight = textViewRight;
    }

    public Direction getLeftDrawableDirection() {
        return leftDrawableDirection;
    }

    public void setLeftDrawableDirection(Direction leftDrawableDirection) {
        this.leftDrawableDirection = leftDrawableDirection;
    }

    public Direction getRightDrawableDirection() {
        return rightDrawableDirection;
    }

    public void setRightDrawableDirection(Direction rightDrawableDirection) {
        this.rightDrawableDirection = rightDrawableDirection;
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
}
