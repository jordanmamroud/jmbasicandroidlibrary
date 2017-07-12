package fragments.ListPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jordan.basicslibrary.R;
import com.example.jordan.basicslibrary.Utilities.EventListeners.MOnPageChange;

import java.util.ArrayList;

/**
 * Created by Jordan on 5/11/2017.
 */

public class ListPagerFragment extends Fragment {

    private ArrayList itemsList;
    private ViewPager mViewPager ;
    private ListPagerAdapter adapter;
    private ViewPager.PageTransformer pageTransformer ;
    // default values
    private int offScreenLimit = 1 ;
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupLayout();
    }

    public void instantiateView(Bundle savedInstanceState, View v){
        if(savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("savedPosition");
            isInitialized = false ;
        }
        mViewPager = (ViewPager) v.findViewById(R.id.pager);
    }

    public void setupCallbacks(){
        mViewPager.addOnPageChangeListener( new MOnPageChange( this :: onPageChange     ));
    }

    public void setupLayout(){
        if(pageTransformer != null) mViewPager.setPageTransformer(false, pageTransformer);
        mViewPager.setOffscreenPageLimit(offScreenLimit);
        mViewPager.setAdapter(adapter);

        // this is used so that if viewpager needs to be opened at specific position it will be . otherwise will just open to position 0 ;
        setPagerItem();

    }

    // this needs work not happy with this solution but will continue working on it does  job for now
    public void setPagerItem(){
        int p = getCurrentPosition();
        mViewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(p, true);
            }
        }, 100);
    }

    // must be call first .
    public void setAdapter(ListPagerAdapter adapter){
        this.adapter = adapter ;
    }

    public void onPageChange(int position){
        this.currentPosition = position;
    }

    public void setPageTransformer(ViewPager.PageTransformer pageTransformer){
        this.pageTransformer = pageTransformer;
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

//        if(mViewPager !=  null){
//            mViewPager.setCurrentItem(  currentPosition   );
//        }
    }

    public ViewPager getViewPager(){
        return mViewPager;
    }

    public ListPagerAdapter getViewPagerAdapter(){
        return adapter;
    }

    public void setOffScreenLimit(int limit){
        this.offScreenLimit = limit;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("savedPosition", mViewPager.getCurrentItem());
        // uninitializing layout listener or else it will stay as true and not run
        isInitialized = false;
    }

}
