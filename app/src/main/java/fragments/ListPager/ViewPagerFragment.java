package fragments.ListPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;


import fragments.BaseFragment;
import uievents.touchevents.JOnPageChange;

/**
 * Created by Jordan on 5/11/2017.
 */

public abstract class ViewPagerFragment extends BaseFragment  {

    private int currentPosition = 0 ;
    private ViewPager mViewPager ;

    // would use this in large list of items to avoid having viewpager scroll through to reach items and create unused fragments
    private boolean createNewInstanceEachTime = true;

    public void setViewPager(ViewPager viewPager, ViewPagerAdapter adapter){
        this.mViewPager = viewPager;
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener( new JOnPageChange(  this ::  onPageChanged   )  );
    }

    public void onPageChanged(int position){    this.currentPosition = position;    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         if(savedInstanceState != null) currentPosition = savedInstanceState.getInt("position");
         return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void moveNext() {
        this.currentPosition = mViewPager.getCurrentItem() + 1 ;
        mViewPager.setCurrentItem(currentPosition);
    }

    public void movePrevious() {
        this.currentPosition =  mViewPager.getCurrentItem() - 1 ;
        mViewPager.setCurrentItem(currentPosition);
    }

    public Fragment getCurrentFragment() {
        return (Fragment) mViewPager.getAdapter().instantiateItem(mViewPager, mViewPager.getCurrentItem());
    }

    // this needs work not happy with this solution but will continue working on it does  job for now
    public void setCurrentItem( int position ) {
        this.currentPosition = position;
        if( createNewInstanceEachTime) {
            mViewPager.setCurrentItem(  position   );
        }else {
            setItemOnReusedInstance(position);
        }
    }

    private void setItemOnReusedInstance(int position){
        mViewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mViewPager.setCurrentItem(  position,   true    );
                mViewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    public void restorePosition(Bundle savedInstanceState){ currentPosition = savedInstanceState.getInt("position");}

    public int getCurrentPosition(){
         return currentPosition ;
    }

    public void setCurrentPosition(int currentPosition) { this.currentPosition = currentPosition; }

    public ViewPager getViewPager(){ return mViewPager; }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position" , mViewPager.getCurrentItem()    );
    }
}
