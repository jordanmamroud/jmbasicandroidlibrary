package fragments.ListPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jordan on 7/22/2017.
 */
// base class for fragments that go inside a view pager of  a list
public abstract class ViewPagerItemFragment extends Fragment implements Observer{

    private int position ;
    private Object item ;
    private String key ;

    public Fragment initNewInstance(Object object, int pos) {
        // must set position of new instance of fragment can not just set class position or will stay at 0.
        ViewPagerItemFragment fragment =  getInstance();
        fragment.setPosition(pos);
        fragment.setItem(  object    );
        fragment.setKey(String.valueOf(pos));
        return fragment;
    }


    public String getTag(int index, int viewpagerId){
        return  "android:switcher:" +viewpagerId + ":" + index;
    }

    public  abstract ViewPagerItemFragment getInstance();

    public void setPosition(int position) {     this.position = position;    }

    public int getItemPosition(){   return position;    }

    public Object getItem() {   return item;    }

    public void setItem(Object item) {  this.item = item;   }

    public void restore(Bundle savedInstanceState){
        if(savedInstanceState != null) {
            this.key = savedInstanceState.getString("key");
            this.position = savedInstanceState.getInt("position");
        }
    }

    @Override
    public void update(Observable o, Object arg) {}

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
        outState.putString("key", key);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}


