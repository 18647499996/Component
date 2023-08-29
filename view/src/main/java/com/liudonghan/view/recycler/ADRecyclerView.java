package com.liudonghan.view.recycler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.liudonghan.view.R;
import com.liudonghan.view.helper.ViewAttr;
import com.liudonghan.view.helper.ViewHelper;

import java.util.Arrays;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:8/28/23
 */
public class ADRecyclerView extends RecyclerView implements ViewAttr {

    private ViewHelper viewHelper;
    private LayoutManagerMode layoutMode;
    private Orientation orientation;
    private int spanCount;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private CenterLayoutManager centerLayoutManager;
    private FlowLayoutManager flowLayoutManager;

    public ADRecyclerView(@NonNull Context context) {
        super(context, null);
    }

    public ADRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        viewHelper = new ViewHelper();
        TypedArray viewTypeArray = viewHelper.initAttrs(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ADRecyclerView);
        layoutMode = LayoutManagerMode.fromInt(typedArray.getInt(R.styleable.ADRecyclerView_liu_layout_manager, LayoutManagerMode.LinearLayoutManager.getValue()));
        orientation = Orientation.fromInt(typedArray.getInt(R.styleable.ADRecyclerView_liu_orientation, Orientation.Vertical.getId()));
        spanCount = typedArray.getInt(R.styleable.ADRecyclerView_liu_span_count, 2);
        typedArray.recycle();
        viewTypeArray.recycle();
        initAttrs(context);
    }

    /**
     * 初始化RecyclerView
     *
     * @param context 上下文
     */
    private void initAttrs(Context context) {
        switch (layoutMode) {
            case LinearLayoutManager:
                linearLayoutManager = new LinearLayoutManager(context, orientation.getId(), false);
                setLayoutManager(linearLayoutManager);
                break;
            case GridLayoutManager:
                gridLayoutManager = new GridLayoutManager(context, spanCount);
                setLayoutManager(gridLayoutManager);
                break;
            case StorageManager:
                staggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
                setLayoutManager(staggeredGridLayoutManager);
                break;
            case CenterLayoutManager:
                centerLayoutManager = new CenterLayoutManager(context, orientation.getId(), false);
                setLayoutManager(centerLayoutManager);
                break;
            case FlowLayoutManager:
                flowLayoutManager = new FlowLayoutManager();
                setLayoutManager(flowLayoutManager);
                break;
        }
    }

    public LinearLayoutManager getLinearLayoutManager() {
        return linearLayoutManager;
    }

    public GridLayoutManager getGridLayoutManager() {
        return gridLayoutManager;
    }

    public StaggeredGridLayoutManager getStaggeredGridLayoutManager() {
        return staggeredGridLayoutManager;
    }

    public CenterLayoutManager getCenterLayoutManager() {
        return centerLayoutManager;
    }

    public FlowLayoutManager getFlowLayoutManager() {
        return flowLayoutManager;
    }

    public enum Orientation {
        Vertical(1),
        Horizontal(0);

        private int id;

        Orientation(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static Orientation fromInt(int id) {
            switch (id) {
                case 1:
                    return Vertical;
                case 0:
                    return Horizontal;
                default:
                    throw new Error("Invalid SourceType");
            }
        }
    }

    public enum LayoutManagerMode {
        // todo 线性排版
        LinearLayoutManager(1),
        // todo 网格排版
        GridLayoutManager(2),
        // todo 瀑布流排版
        StorageManager(3),
        // todo 居中滑动
        CenterLayoutManager(4),
        // todo 流式排版
        FlowLayoutManager(5);

        private int id;

        LayoutManagerMode(int id) {
            this.id = id;
        }

        public int getValue() {
            return id;
        }

        public static LayoutManagerMode fromInt(int value) {
            switch (value) {
                case 1:
                    return LinearLayoutManager;
                case 2:
                    return GridLayoutManager;
                case 3:
                    return StorageManager;
                case 4:
                    return CenterLayoutManager;
                case 5:
                    return FlowLayoutManager;
                default:
                    throw new Error("Invalid SourceType");
            }
        }
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
    public void onDraw(Canvas canvas) {
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
