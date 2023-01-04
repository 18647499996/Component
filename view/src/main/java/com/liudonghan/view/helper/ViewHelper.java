package com.liudonghan.view.helper;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.liudonghan.view.R;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/4/23
 */
public class ViewHelper {

    /**
     * top-left, top-right, bottom-right, bottom-left
     */
    public float[] radii = new float[8];

    /**
     * 剪裁区域路径
     */
    public Path mClipPath;

    /**
     * 画笔
     */
    public Paint mPaint;

    /**
     * 圆形
     */
    public boolean mRoundAsCircle = false;

    /**
     * 默认描边颜色
     */
    public int mDefaultStrokeColor;

    /**
     * 描边颜色
     */
    public int mStrokeColor;

    /**
     * 描边颜色的状态
     */
    public ColorStateList mStrokeColorStateList;

    /**
     * 描边半径
     */
    public int mStrokeWidth;

    /**
     * 是否剪裁背景
     */
    public boolean mClipBackground;

    /**
     * 内容区域
     */
    public Region mAreaRegion;

    /**
     * 画布图层大小
     */
    public RectF mLayer;

    /**
     * 初始化view属性
     *
     * @param context 上下文
     * @param attrs   属性
     */
    public void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Liu_Attrs);
        mRoundAsCircle = ta.getBoolean(R.styleable.Liu_Attrs_liu_is_circle, false);
        mStrokeColorStateList = ta.getColorStateList(R.styleable.Liu_Attrs_liu_stroke_color);
        if (null != mStrokeColorStateList) {
            mStrokeColor = mStrokeColorStateList.getDefaultColor();
            mDefaultStrokeColor = mStrokeColorStateList.getDefaultColor();
        } else {
            mStrokeColor = Color.WHITE;
            mDefaultStrokeColor = Color.WHITE;
        }
        mStrokeWidth = ta.getDimensionPixelSize(R.styleable.Liu_Attrs_liu_stroke_width, 0);
        mClipBackground = ta.getBoolean(R.styleable.Liu_Attrs_liu_clip_background, true);
        int roundCorner = ta.getDimensionPixelSize(R.styleable.Liu_Attrs_liu_round_corner, 0);
        int roundCornerTopLeft = ta.getDimensionPixelSize(R.styleable.Liu_Attrs_liu_round_corner_top_left, roundCorner);
        int roundCornerTopRight = ta.getDimensionPixelSize(R.styleable.Liu_Attrs_liu_round_corner_top_right, roundCorner);
        int roundCornerBottomLeft = ta.getDimensionPixelSize(R.styleable.Liu_Attrs_liu_round_corner_bottom_left, roundCorner);
        int roundCornerBottomRight = ta.getDimensionPixelSize(R.styleable.Liu_Attrs_liu_round_corner_bottom_right, roundCorner);
        ta.recycle();

        radii[0] = roundCornerTopLeft;
        radii[1] = roundCornerTopLeft;

        radii[2] = roundCornerTopRight;
        radii[3] = roundCornerTopRight;

        radii[4] = roundCornerBottomRight;
        radii[5] = roundCornerBottomRight;

        radii[6] = roundCornerBottomLeft;
        radii[7] = roundCornerBottomLeft;

        mLayer = new RectF();
        mClipPath = new Path();
        mAreaRegion = new Region();
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
    }

    public void onSizeChanged(View view, int w, int h) {
        Log.d("'onSizeChanged：", "宽：" + w + "    --------   高：" + h);
        Log.d("view内边距：", "左：" + view.getPaddingLeft() + "\n上：" + view.getPaddingTop() + "\n右：" + view.getPaddingRight() + "\n下：" + view.getPaddingBottom());
        mLayer.set(0, 0, w, h);
        refreshRegion(view);
    }

    public void refreshRegion(View view) {
        int w = (int) mLayer.width();
        int h = (int) mLayer.height();
        RectF areas = new RectF();
        areas.left = view.getPaddingLeft();
        areas.top = view.getPaddingTop();
        areas.right = w - view.getPaddingRight();
        areas.bottom = h - view.getPaddingBottom();
        mClipPath.reset();
        if (mRoundAsCircle) {
            float d = areas.width() >= areas.height() ? areas.height() : areas.width();
            float r = d / 2;
            PointF center = new PointF(w / 2, h / 2);
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
                mClipPath.addCircle(center.x, center.y, r, Path.Direction.CW);

                mClipPath.moveTo(0, 0);  // 通过空操作让Path区域占满画布
                mClipPath.moveTo(w, h);
            } else {
                float y = h / 2 - r;
                mClipPath.moveTo(areas.left, y);
                mClipPath.addCircle(center.x, y + r, r, Path.Direction.CW);
            }
        } else {
            mClipPath.addRoundRect(new RectF(0, 0, w, h), radii, Path.Direction.CW);
        }
//        Region clip = new Region((int) areas.left, (int) areas.top,
//                (int) areas.right, (int) areas.bottom);
//        mAreaRegion.setPath(mClipPath, clip);
    }

    public void onClipDraw(Canvas canvas) {
        if (mStrokeWidth > 0) {
            // 支持半透明描边，将与描边区域重叠的内容裁剪掉
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            mPaint.setColor(Color.WHITE);
            mPaint.setStrokeWidth(mStrokeWidth * 2);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(mClipPath, mPaint);
            // 绘制描边
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            mPaint.setColor(mStrokeColor);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(mClipPath, mPaint);
        }
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O_MR1) {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawPath(mClipPath, mPaint);
        } else {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

            final Path path = new Path();
            path.addRect(0, 0, (int) mLayer.width(), (int) mLayer.height(), Path.Direction.CW);
            path.op(mClipPath, Path.Op.DIFFERENCE);
            canvas.drawPath(path, mPaint);
        }
    }
}
