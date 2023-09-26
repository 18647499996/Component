package com.liudonghan.component.fragment.mine;


import com.liudonghan.mvp.ADBaseSubscription;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class MinePresenter extends ADBaseSubscription<MineContract.View> implements MineContract.Presenter {


    MinePresenter(MineContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}