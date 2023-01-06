package com.liudonghan.view.snackbar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.AnimRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.core.view.accessibility.AccessibilityEventCompat;

import com.liudonghan.view.R;
import com.liudonghan.view.snackbar.display.DisplayCompat;
import com.liudonghan.view.snackbar.listener.ActionClickListener;
import com.liudonghan.view.snackbar.listener.ActionSwipeListener;
import com.liudonghan.view.snackbar.listener.EventListener;
import com.liudonghan.view.snackbar.listener.SwipeDismissTouchListener;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/6/23
 */
public class SnackBar extends SnackBarLayout {

    public enum SnackbarDuration {
        LENGTH_SHORT(2000), LENGTH_LONG(3500), LENGTH_INDEFINITE(-1);

        private long duration;

        SnackbarDuration(long duration) {
            this.duration = duration;
        }

        public long getDuration() {
            return duration;
        }
    }

    public static enum SnackbarPosition {
        TOP(Gravity.TOP), BOTTOM(Gravity.BOTTOM), BOTTOM_CENTER(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);

        private int layoutGravity;

        SnackbarPosition(int layoutGravity) {
            this.layoutGravity = layoutGravity;
        }

        public int getLayoutGravity() {
            return layoutGravity;
        }
    }

    private int mUndefinedColor = -10000;
    private int mUndefinedDrawable = -10000;

    private SnackBarType mType = SnackBarType.SINGLE_LINE;
    private SnackbarDuration mDuration = SnackbarDuration.LENGTH_LONG;
    private CharSequence mText;
    private TextView snackbarText;
    private TextView snackbarAction;
    private int mColor = mUndefinedColor;
    private int mTextColor = mUndefinedColor;
    private int mOffset;
    private Integer mLineColor;
    private SnackbarPosition mPhonePosition = SnackbarPosition.BOTTOM;
    private SnackbarPosition mWidePosition = SnackbarPosition.BOTTOM_CENTER;
    private int mDrawable = mUndefinedDrawable;
    private int mMarginTop = 0;
    private int mMarginBottom = 0;
    private int mMarginLeft = 0;
    private int mMarginRight = 0;
    private long mSnackbarStart;
    private long mSnackbarFinish;
    private long mTimeRemaining = -1;
    private CharSequence mActionLabel;
    private int mActionColor = mUndefinedColor;
    private boolean mShowAnimated = true;
    private boolean mDismissAnimated = true;
    private boolean mIsReplacePending = false;
    private boolean mIsShowingByReplace = false;
    private long mCustomDuration = -1;
    private ActionClickListener mActionClickListener;
    private ActionSwipeListener mActionSwipeListener;
    private boolean mShouldAllowMultipleActionClicks;
    private boolean mActionClicked;
    private boolean mShouldDismissOnActionClicked = true;
    private EventListener mEventListener;
    private Typeface mTextTypeface;
    private Typeface mActionTypeface;
    private boolean mIsShowing = false;
    private boolean mCanSwipeToDismiss = true;
    private boolean mIsDismissing = false;
    private Rect mWindowInsets = new Rect();
    private Rect mDisplayFrame = new Rect();
    private Point mDisplaySize = new Point();
    private Point mRealDisplaySize = new Point();
    private Activity mTargetActivity;
    private Float mMaxWidthPercentage = null;
    private boolean mUsePhoneLayout;
    private Runnable mDismissRunnable = new Runnable() {
        @Override
        public void run() {
            dismiss();
        }
    };
    private Runnable mRefreshLayoutParamsMarginsRunnable = new Runnable() {
        @Override
        public void run() {
            refreshLayoutParamsMargins();
        }
    };

    @SuppressLint("ObsoleteSdkInt")
    private SnackBar(Context context) {
        super(context);

        // inject helper view to use onWindowSystemUiVisibilityChangedCompat() event
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            addView(new SnackBarHelperChildView(getContext()));
        }
    }

    public static SnackBar with(Context context) {
        return new SnackBar(context);
    }

    /**
     * Sets the type of {@link Snackbar} to be displayed.
     *
     * @param type the {@link SnackBarType} of this instance
     * @return
     */
    public SnackBar type(SnackBarType type) {
        mType = type;
        return this;
    }

    /**
     * Sets the text to be displayed in this {@link Snackbar}
     *
     * @param text
     * @return
     */
    public SnackBar text(CharSequence text) {
        mText = text;
        if (snackbarText != null) {
            snackbarText.setText(mText);
        }
        return this;
    }

    /**
     * Sets the text to be displayed in this {@link Snackbar}
     *
     * @param resId
     * @return
     */
    public SnackBar text(@StringRes int resId) {
        return text(getContext().getText(resId));
    }

    /**
     * Sets the background color of this {@link Snackbar}
     *
     * @param color
     * @return
     */
    public SnackBar color(int color) {
        mColor = color;
        return this;
    }

    /**
     * Sets the background color of this {@link Snackbar}
     *
     * @param resId
     * @return
     */
    public SnackBar colorResource(@ColorRes int resId) {
        return color(getResources().getColor(resId));
    }

    /**
     * Sets the background drawable of this {@link Snackbar}
     *
     * @param resId
     * @return
     */
    public SnackBar backgroundDrawable(@DrawableRes int resId) {
        mDrawable = resId;
        return this;
    }

    /**
     * Sets the text color of this {@link Snackbar}
     *
     * @param textColor
     * @return
     */
    public SnackBar textColor(int textColor) {
        mTextColor = textColor;
        return this;
    }

    /**
     * Sets the text color of this {@link Snackbar}
     *
     * @param resId
     * @return
     */
    public SnackBar textColorResource(@ColorRes int resId) {
        return textColor(getResources().getColor(resId));
    }

    /**
     * Sets the text color of this {@link Snackbar}'s top line, or null for none
     *
     * @param lineColor
     * @return
     */
    public SnackBar lineColor(Integer lineColor) {
        mLineColor = lineColor;
        return this;
    }

    /**
     * Sets the text color of this {@link Snackbar}'s top line
     *
     * @param resId
     * @return
     */
    public SnackBar lineColorResource(@ColorRes int resId) {
        return lineColor(getResources().getColor(resId));
    }

    /**
     * Sets the action label to be displayed, if any. Note that if this is not set, the action
     * button will not be displayed
     *
     * @param actionButtonLabel
     * @return
     */
    public SnackBar actionLabel(CharSequence actionButtonLabel) {
        mActionLabel = actionButtonLabel;
        if (snackbarAction != null) {
            snackbarAction.setText(mActionLabel);
        }
        return this;
    }

    /**
     * Sets the action label to be displayed, if any. Note that if this is not set, the action
     * button will not be displayed
     *
     * @param resId
     * @return
     */
    public SnackBar actionLabel(@StringRes int resId) {
        return actionLabel(getContext().getString(resId));
    }

    /**
     * Set the position of the {@link Snackbar}. Note that if this is not set, the default is to
     * show the snackbar to the bottom of the screen.
     *
     * @param position
     * @return
     */
    public SnackBar position(SnackbarPosition position) {
        mPhonePosition = position;
        return this;
    }

    /**
     * Set the position for wide screen (tablets | desktop) of the {@link Snackbar}. Note that if this is not set, the default is to
     * show the snackbar to the bottom | center of the screen.
     *
     * @param position A {@link com.nispok.snackbar.Snackbar.SnackbarPosition}
     * @return A {@link Snackbar} instance to make changing
     */
    public SnackBar widePosition(SnackbarPosition position){
        mWidePosition = position;
        return this;
    }

    /**
     * Sets all the margins of the {@link Snackbar} to the same value, in pixels
     *
     * @param margin
     * @return
     */
    public SnackBar margin(int margin) {
        return margin(margin, margin, margin, margin);
    }

    /**
     * Sets the margins of the {@link Snackbar} in pixels such that the left and right are equal, and the top and bottom are equal
     *
     * @param marginLR
     * @param marginTB
     * @return
     */
    public SnackBar margin(int marginLR, int marginTB) {
        return margin(marginLR, marginTB, marginLR, marginTB);
    }

    /**
     * Sets all the margin of the {@link Snackbar} individually, in pixels
     *
     * @param marginLeft
     * @param marginTop
     * @param marginRight
     * @param marginBottom
     * @return
     */
    public SnackBar margin(int marginLeft, int marginTop, int marginRight, int marginBottom) {
        mMarginLeft = marginLeft;
        mMarginTop = marginTop;
        mMarginBottom = marginBottom;
        mMarginRight = marginRight;

        return this;
    }

    /**
     * Sets the color of the action button label. Note that you must set a button label with
     * {@link Snackbar#actionLabel(CharSequence)} for this button to be displayed
     *
     * @param actionColor
     * @return
     */
    public SnackBar actionColor(int actionColor) {
        mActionColor = actionColor;
        return this;
    }

    /**
     * Sets the color of the action button label. Note that you must set a button label with
     * {@link Snackbar#actionLabel(CharSequence)} for this button to be displayed
     *
     * @param resId
     * @return
     */
    public SnackBar actionColorResource(@ColorRes int resId) {
        return actionColor(getResources().getColor(resId));
    }

    /**
     * Determines whether this {@link Snackbar} should dismiss when the action button is touched
     *
     * @param shouldDismiss
     * @return
     */
    public SnackBar dismissOnActionClicked(boolean shouldDismiss) {
        mShouldDismissOnActionClicked = shouldDismiss;
        return this;
    }

    /**
     * Sets the listener to be called when the {@link Snackbar} action is
     * selected. Note that you must set a button label with
     * {@link Snackbar#actionLabel(CharSequence)} for this button to be displayed
     *
     * @param listener
     * @return
     */
    public SnackBar actionListener(ActionClickListener listener) {
        mActionClickListener = listener;
        return this;
    }


    /**
     * Sets the listener to be called when the {@link Snackbar} is dismissed by swipe.
     *
     * @param listener
     * @return
     */
    public SnackBar swipeListener(ActionSwipeListener listener) {
        mActionSwipeListener = listener;
        return this;
    }

    /**
     * Determines whether this {@link Snackbar} should allow the action button to be
     * clicked multiple times
     *
     * @param shouldAllow
     * @return
     */
    public SnackBar allowMultipleActionClicks(boolean shouldAllow) {

        mShouldAllowMultipleActionClicks = shouldAllow;
        return this;
    }

    /**
     * Sets the listener to be called when the {@link Snackbar} is dismissed.
     *
     * @param listener
     * @return
     */
    public SnackBar eventListener(EventListener listener) {
        mEventListener = listener;
        return this;
    }

    /**
     * Sets on/off both show and dismiss animations for this {@link Snackbar}
     *
     * @param withAnimation
     * @return
     */
    public SnackBar animation(boolean withAnimation) {
        mShowAnimated = withAnimation;
        mDismissAnimated = withAnimation;
        return this;
    }

    /**
     * Sets on/off show animation for this {@link Snackbar}
     *
     * @param withAnimation
     * @return
     */
    public SnackBar showAnimation(boolean withAnimation) {
        mShowAnimated = withAnimation;
        return this;
    }

    /**
     * Sets on/off dismiss animation for this {@link Snackbar}
     *
     * @param withAnimation
     * @return
     */
    public SnackBar dismissAnimation(boolean withAnimation) {
        mDismissAnimated = withAnimation;
        return this;
    }

    /**
     * Determines whether this {@link com.nispok.snackbar.Snackbar} can be swiped off from the screen
     *
     * @param canSwipeToDismiss
     * @return
     */
    public SnackBar swipeToDismiss(boolean canSwipeToDismiss) {
        mCanSwipeToDismiss = canSwipeToDismiss;
        return this;
    }

    /**
     * Sets the duration of this {@link Snackbar}. See
     * {@link Snackbar.SnackbarDuration} for available options
     *
     * @param duration
     * @return
     */
    public SnackBar duration(SnackbarDuration duration) {
        mDuration = duration;
        return this;
    }

    /**
     * Sets a custom duration of this {@link Snackbar}
     *
     * @param duration custom duration. Value must be greater than 0 or it will be ignored
     * @return
     */
    public SnackBar duration(long duration) {
        mCustomDuration = duration > 0 ? duration : mCustomDuration;
        return this;
    }

    /**
     * Attaches this {@link Snackbar} to an AbsListView (ListView, GridView, ExpandableListView) so
     * it dismisses when the list is scrolled
     *
     * @param absListView
     * @return
     */
    public SnackBar attachToAbsListView(AbsListView absListView) {
        absListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                dismiss();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
            }
        });

        return this;
    }

    /**
     * Attaches this {@link Snackbar} to a RecyclerView so it dismisses when the list is scrolled
     *
     * @param recyclerView The RecyclerView instance to attach to.
     * @return
     */
    public SnackBar attachToRecyclerView(View recyclerView) {

        try {
            Class.forName("android.support.v7.widget.RecyclerView");

            // We got here, so now we can safely check
            RecyclerUtil.setScrollListener(this, recyclerView);
        } catch (ClassNotFoundException ignored) {
            throw new IllegalArgumentException("RecyclerView not found. Did you add it to your dependencies?");
        }

        return this;
    }

    /**
     * Use a custom typeface for this Snackbar's text
     *
     * @param typeface
     * @return
     */
    public SnackBar textTypeface(Typeface typeface) {
        mTextTypeface = typeface;
        return this;
    }

    /**
     * Use a custom typeface for this Snackbar's action label
     *
     * @param typeface
     * @return
     */
    public SnackBar actionLabelTypeface(Typeface typeface) {
        mActionTypeface = typeface;
        return this;
    }

    private static ViewGroup.MarginLayoutParams createMarginLayoutParams(ViewGroup viewGroup, int width, int height, SnackbarPosition position) {
        if (viewGroup instanceof FrameLayout) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width, height);
            params.gravity = position.getLayoutGravity();
            return params;
        } else if (viewGroup instanceof RelativeLayout) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);

            if (position == SnackbarPosition.TOP)
                params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
            else
                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

            return params;
        } else if (viewGroup instanceof LinearLayout) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            params.gravity = position.getLayoutGravity();
            return params;
        } else {
            throw new IllegalStateException("Requires FrameLayout or RelativeLayout for the parent of Snackbar");
        }
    }

    static boolean shouldUsePhoneLayout(Context context) {
        if (context == null) {
            return true;
        } else {
            return true;
        }
    }

    private ViewGroup.MarginLayoutParams init(Context context, Activity targetActivity, ViewGroup parent, boolean usePhoneLayout) {
        SnackBarLayout layout = (SnackBarLayout) LayoutInflater.from(context)
                .inflate(R.layout.view_snack_bar, this, true);
        layout.setOrientation(LinearLayout.VERTICAL);

        Resources res = getResources();
        mColor = mColor != mUndefinedColor ? mColor : res.getColor(R.color.color_ff323232);
        mOffset = res.getDimensionPixelOffset(R.dimen.dip_0);
        mUsePhoneLayout = usePhoneLayout;
        float scale = res.getDisplayMetrics().density;

        View divider = layout.findViewById(R.id.view_snack_bar_divider);

        ViewGroup.MarginLayoutParams params;
        if (mUsePhoneLayout) {
            // Phone
            layout.setMinimumHeight(dpToPx(mType.getMinHeight(), scale));
            layout.setMaxHeight(dpToPx(mType.getMaxHeight(), scale));
            layout.setBackgroundColor(mColor);
            params = createMarginLayoutParams(
                    parent, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT, mPhonePosition);

            if (mLineColor != null) {
                divider.setBackgroundColor(mLineColor);
            } else {
                divider.setVisibility(View.GONE);
            }
        } else {
            // Tablet/desktop
            mType = SnackBarType.SINGLE_LINE; // Force single-line
            layout.setMinimumWidth(res.getDimensionPixelSize(R.dimen.dip_288));
            layout.setMaxWidth(
                    mMaxWidthPercentage == null
                            ? res.getDimensionPixelSize(R.dimen.dip_568)
                            : DisplayCompat.getWidthFromPercentage(targetActivity, mMaxWidthPercentage));
            layout.setBackgroundResource(R.drawable.corners_snack_bar_bg);
            GradientDrawable bg = (GradientDrawable) layout.getBackground();
            bg.setColor(mColor);

            params = createMarginLayoutParams(
                    parent, FrameLayout.LayoutParams.WRAP_CONTENT, dpToPx(mType.getMaxHeight(), scale), mWidePosition);

            if (mLineColor != null) {
                divider.setBackgroundResource(R.drawable.corners_snack_bar_divder_bg);
                GradientDrawable dbg = (GradientDrawable) divider.getBackground();
                dbg.setColor(mLineColor);
            } else {
                divider.setVisibility(View.GONE);
            }
        }

        if (mDrawable != mUndefinedDrawable)
            setBackgroundDrawable(layout, res.getDrawable(mDrawable));

        snackbarText = (TextView) layout.findViewById(R.id.view_snack_bar_tv);
        snackbarText.setText(mText);
        snackbarText.setTypeface(mTextTypeface);

        if (mTextColor != mUndefinedColor) {
            snackbarText.setTextColor(mTextColor);
        }

        snackbarText.setMaxLines(mType.getMaxLines());

        snackbarAction = (TextView) layout.findViewById(R.id.view_snack_bar_tv_action);
        if (!TextUtils.isEmpty(mActionLabel)) {
            requestLayout();
            snackbarAction.setText(mActionLabel);
            snackbarAction.setTypeface(mActionTypeface);

            if (mActionColor != mUndefinedColor) {
                snackbarAction.setTextColor(mActionColor);
            }

            snackbarAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mActionClickListener != null) {

                        // Before calling the onActionClicked() callback, make sure:
                        // 1) The snackbar is not dismissing
                        // 2) If we aren't allowing multiple clicks, that this is the first click
                        if (!mIsDismissing && (!mActionClicked || mShouldAllowMultipleActionClicks)) {

                            mActionClickListener.onActionClicked(SnackBar.this);
                            mActionClicked = true;
                        }
                    }
                    if (mShouldDismissOnActionClicked) {
                        dismiss();
                    }
                }
            });
            snackbarAction.setMaxLines(mType.getMaxLines());
        } else {
            snackbarAction.setVisibility(GONE);
        }

        View inner = layout.findViewById(R.id.view_snack_bar_layout);
        inner.setClickable(true);

        if (mCanSwipeToDismiss && res.getBoolean(R.bool.sb__is_swipeable)) {
            inner.setOnTouchListener(new SwipeDismissTouchListener(this, null,
                    new SwipeDismissTouchListener.DismissCallbacks() {
                        @Override
                        public boolean canDismiss(Object token) {
                            return true;
                        }

                        @Override
                        public void onDismiss(View view, Object token) {
                            if (view != null) {
                                if (mActionSwipeListener != null) {
                                    mActionSwipeListener.onSwipeToDismiss();
                                }
                                dismiss(false);
                            }
                        }

                        @Override
                        public void pauseTimer(boolean shouldPause) {
                            if (isIndefiniteDuration()) {
                                return;
                            }
                            if (shouldPause) {
                                removeCallbacks(mDismissRunnable);

                                mSnackbarFinish = System.currentTimeMillis();
                            } else {
                                mTimeRemaining -= (mSnackbarFinish - mSnackbarStart);

                                startTimer(mTimeRemaining);
                            }
                        }
                    }));
        }

        return params;
    }


    private void updateWindowInsets(Activity targetActivity, Rect outInsets) {
        outInsets.left = outInsets.top = outInsets.right = outInsets.bottom = 0;

        if (targetActivity == null) {
            return;
        }

        ViewGroup decorView = (ViewGroup) targetActivity.getWindow().getDecorView();
        Display display = targetActivity.getWindowManager().getDefaultDisplay();

        boolean isTranslucent = isNavigationBarTranslucent(targetActivity);
        boolean isHidden = isNavigationBarHidden(decorView);

        Rect dispFrame = mDisplayFrame;
        Point realDispSize = mRealDisplaySize;
        Point dispSize = mDisplaySize;

        decorView.getWindowVisibleDisplayFrame(dispFrame);

        DisplayCompat.getRealSize(display, realDispSize);
        DisplayCompat.getSize(display, dispSize);

        if (dispSize.x < realDispSize.x) {
            // navigation bar is placed on right side of the screen
            if (isTranslucent || isHidden) {
                int navBarWidth = realDispSize.x - dispSize.x;
                int overlapWidth = realDispSize.x - dispFrame.right;
                outInsets.right = Math.max(Math.min(navBarWidth, overlapWidth), 0);
            }
        } else if (dispSize.y < realDispSize.y) {
            // navigation bar is placed on bottom side of the screen

            if (isTranslucent || isHidden) {
                int navBarHeight = realDispSize.y - dispSize.y;
                int overlapHeight = realDispSize.y - dispFrame.bottom;
                outInsets.bottom = Math.max(Math.min(navBarHeight, overlapHeight), 0);
            }
        }
    }

    private static int dpToPx(int dp, float scale) {
        return (int) (dp * scale + 0.5f);
    }

    public void showByReplace(Activity targetActivity) {
        mIsShowingByReplace = true;
        show(targetActivity);
    }

    public void showByReplace(ViewGroup parent) {
        mIsShowingByReplace = true;
        show(parent, shouldUsePhoneLayout(parent.getContext()));
    }

    public void showByReplace(ViewGroup parent, boolean usePhoneLayout) {
        mIsShowingByReplace = true;
        show(parent, usePhoneLayout);
    }

    /**
     * Displays the {@link Snackbar} at the bottom of the
     * {@link android.app.Activity} provided.
     *
     * @param targetActivity
     */
    public void show(Activity targetActivity) {
        ViewGroup root = (ViewGroup) targetActivity.findViewById(android.R.id.content);
        boolean usePhoneLayout = shouldUsePhoneLayout(targetActivity);
        ViewGroup.MarginLayoutParams params = init(targetActivity, targetActivity, root, usePhoneLayout);
        updateLayoutParamsMargins(targetActivity, params);
        showInternal(targetActivity, params, root);
    }

    /**
     * Displays the {@link Snackbar} at the bottom of the
     * {@link android.view.ViewGroup} provided.
     *
     * @param parent
     */
    public void show(ViewGroup parent) {
        show(parent, shouldUsePhoneLayout(parent.getContext()));
    }

    /**
     * Displays the {@link Snackbar} at the bottom of the
     * {@link android.view.ViewGroup} provided.
     *
     * @param parent
     * @param usePhoneLayout
     */
    public void show(ViewGroup parent, boolean usePhoneLayout) {
        ViewGroup.MarginLayoutParams params = init(parent.getContext(), null, parent, usePhoneLayout);
        updateLayoutParamsMargins(null, params);
        showInternal(null, params, parent);
    }

    public SnackBar maxWidthPercentage(float maxWidthPercentage) {
        mMaxWidthPercentage = maxWidthPercentage;
        return this;
    }

    private void showInternal(Activity targetActivity, ViewGroup.MarginLayoutParams params, ViewGroup parent) {
        parent.removeView(this);

        // We need to make sure the Snackbar elevation is at least as high as
        // any other child views, or it will be displayed underneath them
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            for (int i = 0; i < parent.getChildCount(); i++) {
                View otherChild = parent.getChildAt(i);
                float elvation = otherChild.getElevation();
                if (elvation > getElevation()) {
                    setElevation(elvation);
                }
            }
        }
        parent.addView(this, params);

        bringToFront();

        // As requested in the documentation for bringToFront()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            parent.requestLayout();
            parent.invalidate();
        }

        mIsShowing = true;
        mTargetActivity = targetActivity;

        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                if (mEventListener != null) {
                    if (mIsShowingByReplace) {
                        mEventListener.onShowByReplace(SnackBar.this);
                    } else {
                        mEventListener.onShow(SnackBar.this);
                    }
                    if (!mShowAnimated) {
                        mEventListener.onShown(SnackBar.this);
                        mIsShowingByReplace = false; // reset flag
                    }
                }
                return true;
            }
        });

        if (!mShowAnimated) {
            if (shouldStartTimer()) {
                startTimer();
            }
            return;
        }

        Animation slideIn = AnimationUtils.loadAnimation(getContext(), getInAnimationResource(mPhonePosition));
        slideIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mEventListener != null) {
                    mEventListener.onShown(SnackBar.this);
                    mIsShowingByReplace = false; // reset flag
                }

                focusForAccessibility(snackbarText);

                post(new Runnable() {
                    @Override
                    public void run() {
                        mSnackbarStart = System.currentTimeMillis();

                        if (mTimeRemaining == -1) {
                            mTimeRemaining = getDuration();
                        }
                        if (shouldStartTimer()) {
                            startTimer();
                        }
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        startAnimation(slideIn);
    }

    private void focusForAccessibility(View view) {
        final AccessibilityEvent event = AccessibilityEvent.obtain(AccessibilityEvent.TYPE_VIEW_FOCUSED);

        AccessibilityEventCompat.asRecord(event).setSource(view);
        try {
            view.sendAccessibilityEventUnchecked(event);
        } catch (IllegalStateException e) {
            // accessibility is off.
        }
    }

    private boolean shouldStartTimer() {
        return !isIndefiniteDuration();
    }

    private boolean isIndefiniteDuration() {
        return getDuration() == SnackbarDuration.LENGTH_INDEFINITE.getDuration();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private boolean isNavigationBarHidden(ViewGroup root) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return false;
        }

        int viewFlags = root.getWindowSystemUiVisibility();
        return (viewFlags & View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION) ==
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
    }

    private boolean isNavigationBarTranslucent(Activity targetActivity) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return false;
        }

        int flags = targetActivity.getWindow().getAttributes().flags;
        return (flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION) != 0;
    }

    private void startTimer() {
        postDelayed(mDismissRunnable, getDuration());
    }

    private void startTimer(long duration) {
        postDelayed(mDismissRunnable, duration);
    }

    public void dismissByReplace() {
        mIsReplacePending = true;
        dismiss();
    }

    public void dismiss() {
        dismiss(mDismissAnimated);
    }

    private void dismiss(boolean animate) {
        if (mIsDismissing) {
            return;
        }

        mIsDismissing = true;

        if (mEventListener != null && mIsShowing) {
            if (mIsReplacePending) {
                mEventListener.onDismissByReplace(SnackBar.this);
            } else {
                mEventListener.onDismiss(SnackBar.this);
            }
        }

        if (!animate) {
            finish();
            return;
        }

        final Animation slideOut = AnimationUtils.loadAnimation(getContext(), getOutAnimationResource(mPhonePosition));
        slideOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        startAnimation(slideOut);
    }

    private void finish() {
        clearAnimation();
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeView(this);
        }
        if (mEventListener != null && mIsShowing) {
            mEventListener.onDismissed(this);
        }
        mIsShowing = false;
        mIsDismissing = false;
        mIsReplacePending = false;
        mTargetActivity = null;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        mIsShowing = false;

        if (mDismissRunnable != null) {
            removeCallbacks(mDismissRunnable);
        }
        if (mRefreshLayoutParamsMarginsRunnable != null) {
            removeCallbacks(mRefreshLayoutParamsMarginsRunnable);
        }
    }

    void dispatchOnWindowSystemUiVisibilityChangedCompat(int visible) {
        onWindowSystemUiVisibilityChangedCompat(visible);
    }

    protected void onWindowSystemUiVisibilityChangedCompat(int visible) {
        if (mRefreshLayoutParamsMarginsRunnable != null) {
            post(mRefreshLayoutParamsMarginsRunnable);
        }
    }

    protected void refreshLayoutParamsMargins() {
        if (mIsDismissing) {
            return;
        }

        ViewGroup parent = (ViewGroup) getParent();
        if (parent == null) {
            return;
        }

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getLayoutParams();

        updateLayoutParamsMargins(mTargetActivity, params);

        setLayoutParams(params);
    }

    protected void updateLayoutParamsMargins(Activity targetActivity, ViewGroup.MarginLayoutParams params) {
        if (mUsePhoneLayout) {
            // Phone
            params.topMargin = mMarginTop;
            params.rightMargin = mMarginRight;
            params.leftMargin = mMarginLeft;
            params.bottomMargin = mMarginBottom;
        } else {
            // Tablet/desktop
            params.topMargin = mMarginTop;
            params.rightMargin = mMarginRight;
            params.leftMargin = mMarginLeft + mOffset;
            params.bottomMargin = mMarginBottom + mOffset;
        }

        // Add bottom/right margin when navigation bar is hidden or translucent
        updateWindowInsets(targetActivity, mWindowInsets);

        params.rightMargin += mWindowInsets.right;
        params.bottomMargin += mWindowInsets.bottom;
    }


    public int getActionColor() {
        return mActionColor;
    }

    public CharSequence getActionLabel() {
        return mActionLabel;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public int getColor() {
        return mColor;
    }

    public int getLineColor() {
        return mLineColor;
    }

    public CharSequence getText() {
        return mText;
    }

    public long getDuration() {
        return mCustomDuration == -1 ? mDuration.getDuration() : mCustomDuration;
    }

    public SnackBarType getType() {
        return mType;
    }

    /**
     * @return whether the action button has been clicked. In other words, this method will let
     * you know if {@link com.nispok.snackbar.listeners.ActionClickListener#onActionClicked(Snackbar)}
     * was called. This is useful, for instance, if you want to know during
     * {@link com.nispok.snackbar.listeners.EventListener#onDismiss(Snackbar)} if the
     * {@link com.nispok.snackbar.Snackbar} is being dismissed because of its action click
     */
    public boolean isActionClicked() {
        return mActionClicked;
    }

    /**
     * @return the pixel offset of this {@link com.nispok.snackbar.Snackbar} from the left and
     * bottom of the {@link android.app.Activity}.
     */
    public int getOffset() {
        return mOffset;
    }

    /**
     * @return true only if both dismiss and show animations are enabled
     */
    public boolean isAnimated() {
        return mShowAnimated && mDismissAnimated;
    }

    public boolean isDismissAnimated() {
        return mDismissAnimated;
    }

    public boolean isShowAnimated() {
        return mShowAnimated;
    }

    public boolean shouldDismissOnActionClicked() {
        return mShouldDismissOnActionClicked;
    }

    /**
     * @return true if this {@link com.nispok.snackbar.Snackbar} is currently showing
     */
    public boolean isShowing() {
        return mIsShowing;
    }

    /**
     * @return true if this {@link com.nispok.snackbar.Snackbar} is dismissing.
     */
    public boolean isDimissing() {
        return mIsDismissing;
    }

    /**
     * @return false if this {@link com.nispok.snackbar.Snackbar} has been dismissed
     */
    public boolean isDismissed() {
        return !mIsShowing;
    }

    /**
     * @param snackbarPosition
     * @return the animation resource used by this {@link com.nispok.snackbar.Snackbar} instance
     * to enter the view
     */
    @AnimRes
    public static int getInAnimationResource(SnackbarPosition snackbarPosition) {
        return snackbarPosition == SnackbarPosition.TOP ? R.anim.top_in : R.anim.bottom_in;
    }

    /**
     * @param snackbarPosition
     * @return the animation resource used by this {@link com.nispok.snackbar.Snackbar} instance
     * to exit the view
     */
    @AnimRes
    public static int getOutAnimationResource(SnackbarPosition snackbarPosition) {
        return snackbarPosition == SnackbarPosition.TOP ? R.anim.top_out : R.anim.bottom_out;
    }

    /**
     * Set a Background Drawable using the appropriate Android version api call
     *
     * @param view
     * @param drawable
     */
    public static void setBackgroundDrawable(View view, Drawable drawable) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }
}
