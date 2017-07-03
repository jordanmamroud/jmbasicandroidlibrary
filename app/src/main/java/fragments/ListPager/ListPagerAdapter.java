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


    public ListPagerAdapter(FragmentManager fm, List data,  IPagerItemFragment fragment){
        super(fm);
        this.data = data;
        this.fragment = fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return  fragment.newInstance(data.get(position) , position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Parcelable saveState() {
        return null ;
    }
}
