package com.liudonghan.view.photo.proxy;

import android.content.Context;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/11/23
 */
public class IcsScroller extends GingerScroller {

    public IcsScroller(Context context) {
        super(context);
    }

    @Override
    public boolean computeScrollOffset() {
        return mScroller.computeScrollOffset();
    }

}
