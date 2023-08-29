package com.liudonghan.component.indicatortab;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADIndicatorTabPresenter extends ADBaseSubscription<ADIndicatorTabContract.View> implements ADIndicatorTabContract.Presenter {


    ADIndicatorTabPresenter(ADIndicatorTabContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}