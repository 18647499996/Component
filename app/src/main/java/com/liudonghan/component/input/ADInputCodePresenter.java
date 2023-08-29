package com.liudonghan.component.input;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADInputCodePresenter extends ADBaseSubscription<ADInputCodeContract.View> implements ADInputCodeContract.Presenter {


    ADInputCodePresenter(ADInputCodeContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}