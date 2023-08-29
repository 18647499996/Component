package com.liudonghan.component.constraintLayout;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADConstraintLayoutPresenter extends ADBaseSubscription<ADConstraintLayoutContract.View> implements ADConstraintLayoutContract.Presenter {


    ADConstraintLayoutPresenter(ADConstraintLayoutContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}