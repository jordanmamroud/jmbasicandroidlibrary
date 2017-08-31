package view.fragments.ListPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;


import view.fragments.BaseFragment;
import view.uievents.touchevents.JOnPageChange;

/**
 * Created by Jordan on 5/11/2017.
 */

public abstract class ViewPagerFragment extends BaseFragment  {

    private int currentPosition = 0 ;
    private ViewPager mViewPager ;
    private ViewPagerAdapter adapter ;
    // would use this in large list of items to avoid having viewpager scroll through to reach items and create unused view.fragments

    private boolean hasRunOnRestart = false ;
    private boolean isRunningOnRestart = false ;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
            currentPosition = savedInstanceState.getInt("position");

        if( getArguments() != null )
            currentPosition = getArguments().getInt("selectedPosition");
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if( mViewPager == null  )
            throw new IllegalStateException("viewpager was never attached to superclass viewpager fragment");
    }

    public void attachViewPager(ViewPager viewPager, ViewPagerAdapter adapter){
        this.mViewPager = viewPager ;
        this.adapter = adapter ;
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener( new JOnPageChange(  this ::  onPageChanged   )  );
    }

    public void onPageChanged(int position){
        isRunningOnRestart = false ;
        if( isRestarted()  && currentPosition > 0 &&  ! hasRunOnRestart )
            isRunningOnRestart = true;
        hasRunOnRestart = true ;
        this.currentPosition = position;
    }

    public boolean isRunningOnRestart(int position){
        return isRunningOnRestart && position > 0 ;
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

    public abstract ViewPagerItemFragment initChildFrag();

    // this needs work not happy with this solution but will continue working on it does  job for now.
    public void setCurrentItem( int position ) {
        this.currentPosition = position;
        setItemOnReusedInstance(position);
    }

    private void setItemOnReusedInstance(int position){
        mViewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                if(mViewPager.getWidth() != 0) {
                    mViewPager.setCurrentItem(position, true);
                    mViewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    public int getCurrentPosition(){
        return currentPosition ;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public boolean isChildFragManagerAttached(){
        return  getChildFragmentManager() !=null &&  getChildFragmentManager().getFragments()!= null ;
    }

    public void notifyDataSetChanged(){
        if( adapter != null  )
            adapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position" , mViewPager.getCurrentItem()    );
    }
}
