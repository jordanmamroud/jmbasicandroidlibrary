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
        return view;
    }

    public void createAdapter(ArrayList listItems , IAdapterDelegates delegate){
         this.adapter = new MListAdapter(  getContext(),  listItems , delegate );
    }

    public void setupLayout(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(  getContext()  );
        list.setLayoutManager(linearLayoutManager);
        list.setAdapter(adapter);
    }

    public void instantiateView(View view){
        list = (RecyclerView) view.findViewById(R.id.mListview);
    }

    public void setOnItemClickListener( MOnItemSelected onItemClickListener){
        list.addOnItemTouchListener(    onItemClickListener );
    }

    public void setContentView(int layoutResource, int recyclerViewId){
        this.layoutResource = layoutResource ;
        this.recyclerViewId = recyclerViewId ;
    }

}
