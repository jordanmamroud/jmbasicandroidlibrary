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

/**
 * Created by Jordan on 5/11/2017.
 */

public class ListPagerFragment extends Fragment {

    private ViewPager mViewPager ;
    private ListPagerAdapter adapter;
    private ViewPager.PageTransformer pageTransformer ;
    // default values
    private int offScreenLimit = 1 ;
    private int currentPosition = 0;

    // read me steps , first override oncreateview and must setAdapter, then do any customizations
    // ie. page transformer , offscreen limit and setup layout should never be called in oncreate view

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupLayout();
    }

    public void instantiateView(Bundle savedInstanceState, ViewPager viewPager){
        if(savedInstanceState != null) currentPosition = savedInstanceState.getInt("savedPosition");
        this.mViewPager = viewPager;
    }

    public void setupCallbacks(){
        mViewPager.addOnPageChangeListener( new MOnPageChange( this :: onPageChange     ));
    }

    public void setupLayout(){
        if(pageTransformer != null) mViewPager.setPageTransformer(false, pageTransformer);
        mViewPager.setOffscreenPageLimit(offScreenLimit);
        // fragments created to start dont know why it is more then offscreen limit that gets created
        mViewPager.setAdapter(adapter);

        // this is used so that if viewpager needs to be opened at specific position it will be . otherwise will just open to position 0 ;
        setPagerItem();
    }

    // this needs work not happy with this solution but will continue working on it does  job for now
    public void setPagerItem(){
        // do not like this approach
        mViewPager.postDelayed(
                ()-> mViewPager.setCurrentItem(getCurrentPosition(), true) , 10 );
    }

    // must be called first .
    public void setAdapter(ListPagerAdapter adapter){
        this.adapter = adapter ;
    }

    public void onPageChange(int position){
        this.currentPosition = position;
    }

    public void setPageTransformer(ViewPager.PageTransformer pageTransformer){
        this.pageTransformer = pageTransformer;
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
    }

    public ViewPager getViewPager(){
        return mViewPager;
    }

    public ListPagerAdapter getViewPagerAdapter(){
        return adapter;
    }

    public int getCount(){return adapter.getCount();}

    public void setOffScreenLimit(int limit){
        this.offScreenLimit = limit;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("savedPosition", mViewPager.getCurrentItem());
    }

}
