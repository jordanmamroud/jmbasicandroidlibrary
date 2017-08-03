package fragments.ListPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Jordan on 5/11/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

    private List data;
    private ViewPagerItemFragment fragment;
    private FragmentManager fm ;

    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
        this.fm = fm;
    }

    public ViewPagerAdapter(FragmentManager fm, ArrayList data, ViewPagerItemFragment fragment){
        super(fm);
        this.fm = fm;
        this.data = data;
        this.fragment = fragment;
    }

    @Override
    public Fragment getItem(int position) {

            return fragment.initNewInstance(data.get(position), position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    // get item position is run when notify data set changed is called so overriding it here will force call to fragments update method if implimenting .
    @Override
    public int getItemPosition(Object object) {
        // if the fragment impliments observer call the update method
        if (object instanceof Observer) {
            ((Observer) object).update( null, object);
        }

        return super.getItemPosition(object);
    }

    // roverriding save state will fuck up whole pager do not do this ...
//    @Override
//    public Parcelable saveState() {
//        return super.saveState() ;
//    }

}
