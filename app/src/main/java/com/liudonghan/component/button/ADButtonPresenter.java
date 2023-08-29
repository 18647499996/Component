package com.liudonghan.component.button;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADButtonPresenter extends ADBaseSubscription<ADButtonContract.View> implements ADButtonContract.Presenter {


    ADButtonPresenter(ADButtonContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}