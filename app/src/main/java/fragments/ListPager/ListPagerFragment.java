package fragments.ListPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jordan.basicslibrary.R;
import Animations.ViewPagerAnimations.BaseTransformer;
import com.example.jordan.basicslibrary.Utilities.EventListeners.MOnPageChange;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan on 5/11/2017.
 */

public class ListPagerFragment extends Fragment {

    private ArrayList itemsList;
    private ViewPager mViewPager ;
    private ListPagerAdapter adapter;

    // default values
    private int screenLimitDefault = 1 ;
    private int currentPosition = 0;
    private boolean isInitialized = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pager_view, container, false);
        // sets up all our class variables
        instantiateView(savedInstanceState , v );
        setupCallbacks();
        return v;
    }

    public void instantiateView(Bundle savedInstanceState, View v){
        if(savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("savedPosition");
            isInitialized = false ;
        }
        mViewPager = (ViewPager) v.findViewById(R.id.pager);

    }

    public void setupCallbacks(){
        mViewPager.addOnPageChangeListener( new MOnPageChange( (int position) ->   onPageChange() ));
    }

    public void setupViewPager(int offScreenLimit){
        mViewPager.setOffscreenPageLimit(offScreenLimit);
        mViewPager.setAdapter(adapter);

        // this is used so that if viewpager needs to be opened at specific position it will be . otherwise will just open to position 0 ;
        mViewPager.getViewTreeObserver().addOnGlobalLayoutListener( this ::  setupLayoutListener );
    }

    public void setupLayoutListener(){
        // doing this to setcurrent item beyond 0 wont work if not done
        if (!isInitialized) {
            mViewPager.setCurrentItem(currentPosition);
            isInitialized = true;
        }
    }

    // must be call first .
    public void setAdapter(ListPagerAdapter adapter){
        this.adapter = adapter ;
    }

    public void onPageChange(){}

    public void setPageTransformer(ViewPager.PageTransformer pageTransformer){
        mViewPager.setPageTransformer( true, pageTransformer   );
    }

    public void setItemsList(ArrayList itemsList){
        this.itemsList = itemsList;
    }

    public void moveNext() {
        //goes to next fragment in viewpager
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
    }

    public void movePrevious() {
        //goes to previous fragment in viewpager
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
    }

    public int getCurrentPosition(){
         return currentPosition ;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        if(mViewPager !=    null){
            mViewPager.setCurrentItem(  currentPosition   );
        }
    }

    public ViewPager getViewPager(){
        return mViewPager;
    }

    public ListPagerAdapter getViewPagerAdapter(){
        return adapter;
    }

    public void setOffScreenLimit(int limit){
        mViewPager.setOffscreenPageLimit(limit);
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("savedPosition", mViewPager.getCurrentItem());
    }

}
