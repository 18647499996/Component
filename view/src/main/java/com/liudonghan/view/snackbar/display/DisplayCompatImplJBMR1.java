package com.liudonghan.view.snackbar.display;

import android.graphics.Point;
import android.view.Display;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/6/23
 */
public class DisplayCompatImplJBMR1 extends DisplayCompat.Impl {
    @Override
    void getSize(Display display, Point outSize) {
        display.getSize(outSize);
    }

    @Override
    void getRealSize(Display display, Point outSize) {
        display.getRealSize(outSize);
    }
}
