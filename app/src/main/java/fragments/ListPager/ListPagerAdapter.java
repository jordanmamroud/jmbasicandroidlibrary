package fragments.ListPager;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Jordan on 5/11/2017.
 */

public class ListPagerAdapter extends FragmentStatePagerAdapter{


    private List data;
    private IPagerItemFragment fragment;
    private int screenLimit ;

    public ListPagerAdapter(FragmentManager fm){
        super(fm);
        this.data = data;
        this.fragment = fragment;
    }

    public ListPagerAdapter(FragmentManager fm, ArrayList data, IPagerItemFragment fragment){
        super(fm);
        this.data = data;
        this.fragment = fragment;
    }

    public void setList(){
        this.data = data ;
    };

    public void setPagerFragmentType(IPagerItemFragment iPagerItemFragment){
        this.fragment = iPagerItemFragment ;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment currentFrag =  fragment.newInstance(data.get(position) , position);
        return   currentFrag;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    // returning null on save state could help with fragments performance also prevents trying to find a null fragment( i think check again later   )
    @Override
    public Parcelable saveState() {
        return null ;
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

}
