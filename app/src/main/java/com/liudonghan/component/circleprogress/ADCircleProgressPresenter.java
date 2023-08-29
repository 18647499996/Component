package com.liudonghan.component.circleprogress;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADCircleProgressPresenter extends ADBaseSubscription<ADCircleProgressContract.View> implements ADCircleProgressContract.Presenter {


    ADCircleProgressPresenter(ADCircleProgressContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}