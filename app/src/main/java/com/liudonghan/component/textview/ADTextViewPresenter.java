package com.liudonghan.component.textview;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADTextViewPresenter extends ADBaseSubscription<ADTextViewContract.View> implements ADTextViewContract.Presenter {


    ADTextViewPresenter(ADTextViewContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}