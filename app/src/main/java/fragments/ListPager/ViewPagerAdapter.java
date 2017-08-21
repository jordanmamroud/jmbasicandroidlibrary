package fragments.ListPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jordan on 5/11/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter{

    private List list;
    private ViewPagerItemFragment fragment;
    private FragmentManager fm ;

    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
        this.fm = fm;
    }

    public ViewPagerAdapter(FragmentManager fm, ArrayList list, ViewPagerItemFragment fragment){
        super(fm);
        this.fm = fm;
        this.list = list;
        this.fragment = fragment;
    }

    public List getList() { return list;    }

    public void setList(List list) {    this.list = list;   }

    @Override
    public Fragment getItem(int position) {
            return fragment.initNewInstance(list.get(position), position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    // get item position is run when notify list set changed is called so overriding it here will force call to fragments update method if implimenting .
    @Override
    public int getItemPosition(Object object) {
        //todo make this boolean to enable the ability to update child fragments.
        // if the child fragment impliments observer call the update method , only enable this if child fragments need updating.
//        if (object instanceof Observer) {
//            ((Observer) object).update( null, object);
//        }

        return super.getItemPosition(object);
    }


    public void addItem(Object object){
        System.out.println("item added " + getCount());
//        list.add(object);
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    // roverriding save state will fuck up whole pager do not do this ...
//    @Override
//    public Parcelable saveState() {
//        return super.saveState() ;
//    }

}
