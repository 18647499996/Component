package com.liudonghan.component.recyclerview;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADRecyeclerViewPresenter extends ADBaseSubscription<ADRecyeclerViewContract.View> implements ADRecyeclerViewContract.Presenter {


    ADRecyeclerViewPresenter(ADRecyeclerViewContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}