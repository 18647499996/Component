package com.liudonghan.view.tabhost;

import androidx.fragment.app.Fragment;

import com.liudonghan.view.R;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:9/25/23
 */
public class ADNavigationEntity {

    private String title;
    private int unResId;
    private int selectorResId;
    private int selectorTextColor = R.color.color_342e2e;
    private int unTextColor = R.color.color_7c7c7c;
    private Fragment fragment;
    private boolean isSelector = false;
    private boolean isShowCount;
    private int count;


    public ADNavigationEntity() {

    }

    public ADNavigationEntity(String title, int selectorTextColor, int unTextColor, Fragment fragment, boolean isSelector) {
        this.title = title;
        this.selectorTextColor = selectorTextColor;
        this.unTextColor = unTextColor;
        this.fragment = fragment;
        this.isSelector = isSelector;
    }

    public ADNavigationEntity(int selectorResId, int unResId, Fragment fragment, boolean isSelector) {
        this.selectorResId = selectorResId;
        this.unResId = unResId;
        this.fragment = fragment;
        this.isSelector = isSelector;
    }

    public ADNavigationEntity(String title, int selectorResId, int unResId, int selectorTextColor, int unTextColor, Fragment fragment, boolean isSelector) {
        this.title = title;
        this.selectorResId = selectorResId;
        this.unResId = unResId;
        this.selectorTextColor = selectorTextColor;
        this.unTextColor = unTextColor;
        this.fragment = fragment;
        this.isSelector = isSelector;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUnResId() {
        return unResId;
    }

    public void setUnResId(int unResId) {
        this.unResId = unResId;
    }

    public int getSelectorResId() {
        return selectorResId;
    }

    public void setSelectorResId(int selectorResId) {
        this.selectorResId = selectorResId;
    }

    public int getSelectorTextColor() {
        return selectorTextColor;
    }

    public void setSelectorTextColor(int selectorTextColor) {
        this.selectorTextColor = selectorTextColor;
    }

    public int getUnTextColor() {
        return unTextColor;
    }

    public void setUnTextColor(int unTextColor) {
        this.unTextColor = unTextColor;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public boolean isSelector() {
        return isSelector;
    }

    public void setSelector(boolean selector) {
        isSelector = selector;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public boolean isShowCount() {
        return isShowCount;
    }

    public void setShowCount(boolean showCount) {
        isShowCount = showCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
