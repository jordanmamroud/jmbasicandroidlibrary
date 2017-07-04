package fragments.ListPager;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Jordan on 5/11/2017.
 */

public class ListPagerAdapter extends FragmentStatePagerAdapter{

    private List data;
    private IPagerItemFragment fragment;
    private Fragment currentFrag;

    public ListPagerAdapter(FragmentManager fm, List data,  IPagerItemFragment fragment){
        super(fm);
        this.data = data;
        this.fragment = fragment;
    }

    @Override
    public Fragment getItem(int position) {
        currentFrag = null;
        currentFrag =fragment.newInstance(data.get(position) , position);
        return   currentFrag;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Parcelable saveState() {
        return null ;
    }

    public Fragment getCurrentFrag(){
        return currentFrag;
    }
}
