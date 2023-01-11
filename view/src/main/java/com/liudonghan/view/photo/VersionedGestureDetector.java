package com.liudonghan.view.photo;

import android.content.Context;
import android.os.Build;

import com.liudonghan.view.photo.listener.GestureDetector;
import com.liudonghan.view.photo.listener.OnGestureListener;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/11/23
 */
public class VersionedGestureDetector {

    public static GestureDetector newInstance(Context context,
                                              OnGestureListener listener) {
        final int sdkVersion = Build.VERSION.SDK_INT;
        GestureDetector detector;

        if (sdkVersion < Build.VERSION_CODES.ECLAIR) {
            detector = new CupcakeGestureDetector(context);
        } else if (sdkVersion < Build.VERSION_CODES.FROYO) {
            detector = new EclairGestureDetector(context);
        } else {
            detector = new FroyoGestureDetector(context);
        }

        detector.setOnGestureListener(listener);

        return detector;
    }

}