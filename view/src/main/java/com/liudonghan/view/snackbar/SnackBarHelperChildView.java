package com.liudonghan.view.snackbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewParent;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/6/23
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class SnackBarHelperChildView  extends View {
    public SnackBarHelperChildView(Context context) {
        super(context);
        setSaveEnabled(false);
        setWillNotDraw(true);
        setVisibility(GONE);
    }

    @Override
    public void onWindowSystemUiVisibilityChanged(int visible) {
        super.onWindowSystemUiVisibilityChanged(visible);

        final ViewParent parent = getParent();
        if (parent instanceof SnackBar) {
            ((SnackBar) parent).dispatchOnWindowSystemUiVisibilityChangedCompat(visible);
        }
    }
}
