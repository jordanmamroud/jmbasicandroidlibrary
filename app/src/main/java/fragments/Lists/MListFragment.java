package fragments.Lists;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.jordan.basicslibrary.R;
import com.example.jordan.basicslibrary.Utilities.EventListeners.MOnItemSelected;

import java.util.ArrayList;
import java.util.List;

import fragments.IAdapterDelegates;

/**
 * Created by Jordan on 5/8/2017.
 */

public class MListFragment extends Fragment {

    private RecyclerView list;
    private MOnItemSelected.OnItemClickListener onItemClickListener;
    private MListAdapter adapter ;


    // must be called first
    public void setAdapter( MListAdapter adapter){
         this.adapter = adapter ;
    }

    // pass in recyclerview
    public void setupRecyclerview(RecyclerView mRecyclerView){
        this.list = mRecyclerView ;
        list.addOnItemTouchListener(new MOnItemSelected( getContext() , this :: onItemSelected  ));
        list.setLayoutManager(getLayoutManager() );
        list.setAdapter(adapter);
    }

    public LinearLayoutManager getLayoutManager(){
        return new LinearLayoutManager(getContext());
    }

    public void onItemSelected(View v , int position){

    }


}
