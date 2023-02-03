package com.liudonghan.view.city;


import java.util.List;

/**
 * Author: Blincheng.
 * Date: 2017/5/9.
 * Description:
 */

public interface OnItemClickListener {
    /**
     * @param city 返回地址列表对应点击的对象
     * @param tabPosition 对应tab的位置
     * @param position 列表索引
     * */
    void itemClick(ADCitySelector addressSelector, List<CityModel> city, int tabPosition, int position);
}
