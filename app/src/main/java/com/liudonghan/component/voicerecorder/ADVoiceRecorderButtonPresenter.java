package com.liudonghan.component.voicerecorder;

import com.liudonghan.mvp.ADBaseSubscription;


/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public class ADVoiceRecorderButtonPresenter extends ADBaseSubscription<ADVoiceRecorderButtonContract.View> implements ADVoiceRecorderButtonContract.Presenter {


    ADVoiceRecorderButtonPresenter(ADVoiceRecorderButtonContract.View view) {
        super(view);
    }

    @Override
    public void onSubscribe() {

    }

}