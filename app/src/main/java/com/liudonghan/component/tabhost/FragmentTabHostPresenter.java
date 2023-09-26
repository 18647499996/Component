package com.liudonghan.component.tabhost;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class FragmentTabHostPresenter extends ADBaseSubscription<FragmentTabHostContract.View> implements FragmentTabHostContract.Presenter {


    FragmentTabHostPresenter(FragmentTabHostContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}