package com.liudonghan.view.city;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:2/3/23
 */
public interface OnTabSelectedListener {

    void onTabSelected(ADCitySelector addressSelector, ADCitySelector.Tab tab);

    void onTabReselected(ADCitySelector addressSelector, ADCitySelector.Tab tab);
}
