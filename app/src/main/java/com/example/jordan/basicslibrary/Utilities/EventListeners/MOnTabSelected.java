package com.example.jordan.basicslibrary.Utilities.EventListeners;

import android.support.design.widget.TabLayout;

/**
 * Created by Jordan on 5/1/2017.
 */

public class MOnTabSelected implements TabLayout.OnTabSelectedListener {

    private MyTabs tabSelected ;

    public MOnTabSelected(MyTabs tabSelected) {
        this.tabSelected = tabSelected ;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tabSelected.tabSelected(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    public interface MyTabs{
        public void tabSelected(int position);
    }
}
