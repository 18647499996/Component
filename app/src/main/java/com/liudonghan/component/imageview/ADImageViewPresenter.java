package com.liudonghan.component.imageview;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADImageViewPresenter extends ADBaseSubscription<ADImageViewContract.View> implements ADImageViewContract.Presenter {


    ADImageViewPresenter(ADImageViewContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}