package com.liudonghan.component.card;

import androidx.cardview.widget.CardView;

import com.liudonghan.mvp.ADBaseActivity;
import com.liudonghan.mvp.ADBasePresenter;
import com.liudonghan.mvp.ADBaseSubscription;

public class CardPresenter extends ADBaseSubscription<CardContract.View> implements CardContract.Presenter {

    protected CardPresenter(CardContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }
}
