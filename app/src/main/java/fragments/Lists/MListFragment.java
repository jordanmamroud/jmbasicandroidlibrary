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

    private int layoutResource = 0;
    private int recyclerViewId ;

    private MListAdapter adapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // this is for customizing layout resource
        if(layoutResource == 0){
            layoutResource= R.layout.fragment_list;
        }
        View view = inflater.inflate(layoutResource, container, false);
        instantiateView(view);
        setupCallbacks();
        return view;
    }
    // must be called first
    public void setAdapter( MListAdapter adapter){
         this.adapter = adapter ;
    }

    public void setupCallbacks(){
        list.addOnItemTouchListener(
                new MOnItemSelected( getContext() , this :: onItemSelected  ));
    }

    public void setupLayout(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(  getContext()  );
        list.setLayoutManager(linearLayoutManager);
        list.setAdapter(adapter);
    }

    public void instantiateView(View view){
        list = (RecyclerView) view.findViewById(R.id.mListview);
    }

    public void onItemSelected(View v , int position){

    }

    public void setContentView(int layoutResource, int recyclerViewId){
        this.layoutResource = layoutResource ;
        this.recyclerViewId = recyclerViewId ;
    }

}
