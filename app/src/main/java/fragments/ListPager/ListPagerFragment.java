package fragments.ListPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jordan.basicslibrary.R;
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
    private IListPager iActivity ;

    // default values
    private int currentPosition = 0;
    private int offScreenLimit = 1;
    private boolean isInitialized = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pager_view, container, false);

        // sets up all our class variables
        instantiateView(savedInstanceState , v );
        setupCallbacks();

        // sets text of header in activity
        iActivity.setCurrentPositionTxt(currentPosition);

        // this is used so that if viewpager needs to be opened at specific position it will be . otherwise will just open to position 0 ;
        mViewPager.getViewTreeObserver().addOnGlobalLayoutListener( this ::  setupLayout );

        return v;
    }

    public void instantiateView(Bundle savedInstanceState, View v){
        if(savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("savedPosition");
            offScreenLimit = savedInstanceState.getInt("offScreenLimit");
            isInitialized = false ;
        }

        iActivity = (IListPager) getActivity();
        itemsList = (ArrayList) iActivity.getList();

        adapter = new ListPagerAdapter(getChildFragmentManager(), itemsList,  iActivity.getPagerFragmentType());
        mViewPager = (ViewPager) v.findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(offScreenLimit);
        mViewPager.setAdapter(adapter);
    }

    public void setupLayout(){
        if (!isInitialized) {
            mViewPager.setCurrentItem(currentPosition);
            isInitialized = true;
        }
    }

    public void setupCallbacks(){
        mViewPager.addOnPageChangeListener(new MOnPageChange((int p)->{
                currentPosition = p;
                iActivity.onPageChange(p);
        }));
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
        if(mViewPager != null) {
            return mViewPager.getCurrentItem();
        }else return 0 ;
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

    public void setOffScreenLimit(int limit){
        this.offScreenLimit  = limit;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("savedPosition", mViewPager.getCurrentItem());
        outState.putInt("offScreenLimit", offScreenLimit);
    }

   public interface IListPager{
       List getList();
       void onPageChange(int p);
       IPagerItemFragment getPagerFragmentType();
       void setCurrentPositionTxt(int pos);
   };

}
