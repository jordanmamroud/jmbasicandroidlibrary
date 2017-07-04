package fragments.ListPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.example.jordan.basicslibrary.R;
import com.example.jordan.basicslibrary.Utilities.EventListeners.MOnPageChange;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan on 5/11/2017.
 */

public class ListPagerFragment extends Fragment {

    private ArrayList itemsList;
    private int currentPosition = 0;
    private int offScreenLimit = 1;

    private ViewPager mViewPager ;
    private ListPagerAdapter adapter;
    private IListPager iActivity ;
    private boolean isInitialized = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pager_view, container, false);
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

        setupCallbacks();


        iActivity.setCurrentPositionTxt(currentPosition);
        // this is used so that if viewpager needs to be opened at specific position it will be . otherwise will just open to position 0 ;
        mViewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                // if we do not check initialized then this will run everytime page change listener runs
                if (!isInitialized) {
                    mViewPager.setCurrentItem(currentPosition);
                    isInitialized = true;
                }
            }
        });

        return v;
    }


    public void update(){
        if(adapter !=   null    ){
            adapter.notifyDataSetChanged();
        }
    }

    public void setupCallbacks(){
        mViewPager.addOnPageChangeListener(new MOnPageChange((int p)->{
                currentPosition = p;
                iActivity.onPageChange(p);
        }));
    }

    public int getCurrentPosition(){
        if(mViewPager != null) {
            return mViewPager.getCurrentItem();
        }else return 0 ;
    }

    public void moveNext() {
        //it doesn't matter if you're already in the last item
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
    }

    public void movePrevious() {
        //it doesn't matter if you're already in the first item
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
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
