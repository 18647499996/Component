package com.liudonghan.component.calendar;

import com.liudonghan.mvp.ADBaseExceptionManager;
import com.liudonghan.mvp.ADBaseRequestResult;
import com.liudonghan.mvp.ADBaseSubscription;
import com.liudonghan.view.calendar.ADCalendarEntity;
import com.liudonghan.view.calendar.ADCalendarHelp;

import java.util.Calendar;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class CalendarPresenter extends ADBaseSubscription<CalendarContract.View> implements CalendarContract.Presenter {


    CalendarPresenter(CalendarContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void getCalendarList() {
        Observable.unsafeCreate((Observable.OnSubscribe<List<ADCalendarEntity>>) subscriber -> {
            subscriber.onStart();
            subscriber.onNext(ADCalendarHelp.getInstance().getCalendarList(Calendar.getInstance().getTime(), false));
        })
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ADBaseRequestResult<List<ADCalendarEntity>>() {
                    @Override
                    protected void onCompletedListener() {

                    }

                    @Override
                    protected void onErrorListener(ADBaseExceptionManager.ApiException e) {

                    }

                    @Override
                    protected void onNextListener(List<ADCalendarEntity> adCalendarEntities) {
                        view.showCalendarList(adCalendarEntities);
                    }
                });
    }
}