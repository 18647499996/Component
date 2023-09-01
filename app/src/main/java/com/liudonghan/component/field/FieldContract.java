package com.liudonghan.component.field;

import com.liudonghan.mvp.ADBasePresenter;
import com.liudonghan.mvp.ADBaseView;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:
 */
public interface FieldContract {

    interface View extends ADBaseView<Presenter> {

    }

    interface Presenter extends ADBasePresenter {

    }
}