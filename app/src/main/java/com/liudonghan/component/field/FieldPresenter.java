package com.liudonghan.component.field;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class FieldPresenter extends ADBaseSubscription<FieldContract.View> implements FieldContract.Presenter {


    FieldPresenter(FieldContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}