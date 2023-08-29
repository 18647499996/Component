package com.liudonghan.component.cell;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADCellTextLayoutPresenter extends ADBaseSubscription<ADCellTextLayoutContract.View> implements ADCellTextLayoutContract.Presenter {


    ADCellTextLayoutPresenter(ADCellTextLayoutContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}