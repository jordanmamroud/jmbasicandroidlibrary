package fragments.Lists;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import uievents.touchevents.JOnItemSelected;

/**
 * Created by Jordan on 5/8/2017.
 */

public class ListFragment extends Fragment {

    private RecyclerView list;
    private JOnItemSelected.OnItemClickListener onItemClickListener;
    private ListAdapter adapter ;


    // must be called first
    public void setAdapter( ListAdapter adapter){
         this.adapter = adapter ;
    }

    // pass in recyclerview
    public void setupRecyclerview(RecyclerView mRecyclerView){
        this.list = mRecyclerView ;
        list.addOnItemTouchListener(new JOnItemSelected( getContext() , this :: onItemSelected  ));
        list.setLayoutManager(getLayoutManager() );
        list.setAdapter(adapter);
    }

    public LinearLayoutManager getLayoutManager(){
        return new LinearLayoutManager(getContext());
    }

    public void onItemSelected(View v , int position){

    }


}
