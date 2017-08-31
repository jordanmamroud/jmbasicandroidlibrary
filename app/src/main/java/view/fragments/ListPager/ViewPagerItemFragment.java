package view.fragments.ListPager;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jordan on 7/22/2017.
 */
// base class for view.fragments that go inside a view pager of  a list
public abstract class ViewPagerItemFragment extends Fragment implements Observer{

    private int position ;
    private Object item ;

    public Fragment initNewInstance(Object object, int pos) {
        // must set position of new instance of fragment can not just set class position or will stay at 0.
        ViewPagerItemFragment fragment =  getInstance();
        fragment.setPosition(pos);
        fragment.setItem(  object    );
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null )
            restore( savedInstanceState);

    }


    public  abstract ViewPagerItemFragment getInstance();

    public void setPosition(int position) {     this.position = position;    }

    public int getItemPosition(){
        return position;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    // todo not necessary now but to make univeral check for serializable and parcelable and set accordingly
    public void restore(Bundle savedInstanceState){
        this.position = savedInstanceState.getInt("position");
        this.item = restoreItem( savedInstanceState   ) ;
    }

    public  Object restoreItem(Bundle savedInstanceState){
        if(  savedInstanceState.getParcelable("item") != null){
            System.out.println("parcelable not null");
            return savedInstanceState.getParcelable("item");
        }else {
            return savedInstanceState.getSerializable("item");
        }
    }
    @Override
    public void update(Observable o, Object arg) {}

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
        if(item instanceof Parcelable) {
            System.out.println("saving parceleable child item");
            outState.putParcelable("item", (Parcelable) item);
        }
        if(item instanceof Serializable)
            outState.putSerializable("item",(Serializable) item);
    }

}


