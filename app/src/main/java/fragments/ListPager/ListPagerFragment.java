package fragments.ListPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Jordan on 5/11/2017.
 */

public class ListPagerFragment extends Fragment {

    private List itemsList;
    private int currentPosition;
    private Fragment fragment;
    private int layoutResource ;
    private int pagerViewId ;
    private ListPagerAdapter adapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(layoutResource, container, false);
        ViewPager mViewPager = (ViewPager) v.findViewById(pagerViewId);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(currentPosition);

        return v;
    }

    public void setAdapter(ListPagerAdapter adapter){
        this.adapter = adapter ;
    }

    public void setItemsList(List itemsList) {
        this.itemsList = itemsList;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setItemFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setContentView(int layoutResource, int pagerViewId){
        this.layoutResource = layoutResource ;
        this.pagerViewId = pagerViewId ;
    }

}
