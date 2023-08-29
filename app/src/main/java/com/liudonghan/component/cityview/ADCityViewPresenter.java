package com.liudonghan.component.cityview;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADCityViewPresenter extends ADBaseSubscription<ADCityViewContract.View> implements ADCityViewContract.Presenter {


    ADCityViewPresenter(ADCityViewContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}