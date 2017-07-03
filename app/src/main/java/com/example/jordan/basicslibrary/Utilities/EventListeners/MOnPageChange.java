package com.example.jordan.basicslibrary.Utilities.EventListeners;

import android.support.v4.view.ViewPager;

/**
 * Created by Jordan on 5/30/2017.
 */

public class MOnPageChange implements ViewPager.OnPageChangeListener{

    OnPageChange onPageChange;

    public MOnPageChange(OnPageChange onPageChange) {
        this.onPageChange = onPageChange ;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        onPageChange.onPageChange(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public     interface OnPageChange {
       public void  onPageChange(int position);
    }
}
