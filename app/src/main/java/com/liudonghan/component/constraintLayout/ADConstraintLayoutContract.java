package com.liudonghan.component.constraintLayout;

import com.liudonghan.mvp.ADBasePresenter;
import com.liudonghan.mvp.ADBaseView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public interface ADConstraintLayoutContract {

    interface View extends ADBaseView<Presenter> {

    }

    interface Presenter extends ADBasePresenter {

    }
}