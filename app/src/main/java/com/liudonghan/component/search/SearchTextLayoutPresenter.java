package com.liudonghan.component.search;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class SearchTextLayoutPresenter extends ADBaseSubscription<SearchTextLayoutContract.View> implements SearchTextLayoutContract.Presenter {


    SearchTextLayoutPresenter(SearchTextLayoutContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}