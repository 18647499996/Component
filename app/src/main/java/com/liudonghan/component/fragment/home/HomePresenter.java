package com.liudonghan.component.fragment.home;


import com.liudonghan.mvp.ADBaseSubscription;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class HomePresenter extends ADBaseSubscription<HomeContract.View> implements HomeContract.Presenter {


    HomePresenter(HomeContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}