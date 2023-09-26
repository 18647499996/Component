package com.liudonghan.component.fragment.home;


import com.liudonghan.mvp.ADBasePresenter;
import com.liudonghan.mvp.ADBaseView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public interface HomeContract {

    interface View extends ADBaseView<Presenter> {

    }

    interface Presenter extends ADBasePresenter {

    }
}