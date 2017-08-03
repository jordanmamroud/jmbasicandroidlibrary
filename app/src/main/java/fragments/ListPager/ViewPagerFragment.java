package fragments.ListPager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.ViewTreeObserver;

import com.example.jordan.basicslibrary.Utilities.EventListeners.MOnPageChange;

import java.util.ArrayList;
import java.util.Observable;

import fragments.BaseFragment;

/**
 * Created by Jordan on 5/11/2017.
 */

public abstract class ViewPagerFragment extends BaseFragment  {

    // default values
    private int currentPosition = 0;
    private ViewPager mViewPager ;
    private ViewPagerAdapter adapter;


    // would use this in large list of items to avoid having viewpager scroll through to reach items and create unused fragments
    private boolean createNewInstanceEachTime = true;

    public void setupPager(ViewPager viewPager, int offScreenLimit, ViewPager.PageTransformer pageTransformer){
        this.mViewPager = viewPager;
        this.adapter = new ViewPagerAdapter(getChildFragmentManager() , getListOfItems() , initItemFragInstance() );
        mViewPager.setAdapter(adapter);

        mViewPager.setOffscreenPageLimit(offScreenLimit);
        mViewPager.addOnPageChangeListener( new MOnPageChange(  this ::  onPageChanged   )  );

        if(pageTransformer != null) mViewPager.setPageTransformer( false    ,  pageTransformer   );
        setCurrentItem( currentPosition  );
    }

    // any subclass must have these two methods
    public abstract  ViewPagerItemFragment initItemFragInstance() ;

    public abstract ArrayList getListOfItems();

    public void onPageChanged(int position){
        this.currentPosition = position;
    }

    public void moveNext() { mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1); }

    public void movePrevious() { mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1); }

    // this needs work not happy with this solution but will continue working on it does  job for now
    public void setCurrentItem( int position ) {
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
                System.out.println("global layout listener ran");
                mViewPager.setCurrentItem(  position,   true    );
                mViewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }


    @Override
    public void update(Observable o, Object arg) {
        if(adapter != null)    adapter.notifyDataSetChanged();
    }

    public void restore(Bundle savedInstanceState){ if(savedInstanceState != null) currentPosition = savedInstanceState.getInt("savedPosition");}

    public int getCurrentPosition(){
         return currentPosition ;
    }

    public void setCurrentPosition(int currentPosition) { this.currentPosition = currentPosition; }

    public ViewPager getViewPager(){ return mViewPager; }

    public ViewPagerAdapter getAdapter(){ return adapter ; }

    public int getCount(){return adapter.getCount();}

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
         outState.putInt("savedPosition", mViewPager.getCurrentItem());
    }

}
