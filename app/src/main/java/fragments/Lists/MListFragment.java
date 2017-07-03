package fragments.Lists;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jordan.mlibrary.EventListeners.MOnItemSelected;
import com.example.jordan.mlibrary.IAdapterDelegates;
import com.example.jordan.mlibrary.Lists.MListAdapter;
import com.example.jordan.mlibrary.R;

import java.util.ArrayList;

/**
 * Created by Jordan on 5/8/2017.
 */

public class MListFragment extends Fragment {

    private RecyclerView list;
    private MOnItemSelected.OnItemClickListener onItemClickListener;
    private IAdapterDelegates delegate ;
    private ArrayList dataList ;
    private int layoutResource = 0;
    private int recyclerViewId ;
    private RecyclerView.OnScrollListener onScrollListener ;

    public void setAdapterDelegate(IAdapterDelegates delegate){
        this.delegate = delegate ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(layoutResource == 0){
            layoutResource= R.layout.fragment_list;
        }
        View view = inflater.inflate(layoutResource, container, false);
        instantiateView(view);
        setupCallbacks();

        return view;
    }

    public void instantiateView(View view){

            list = (RecyclerView) view.findViewById(recyclerViewId);
        if (list!= null){
            MListAdapter adapter = new MListAdapter(getContext(), delegate, dataList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            list.setLayoutManager(linearLayoutManager);
            list.setAdapter(adapter);
        }
    }

    public void setList(ArrayList list){ this.dataList = list ; }

    public void setContentView(int layoutResource, int recyclerViewId){
        this.layoutResource = layoutResource ;
        this.recyclerViewId = recyclerViewId ;
    }

    public void setupCallbacks(){
        list.addOnItemTouchListener(new MOnItemSelected(getContext(), new MOnItemSelected.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, position);
                }
            }
        }));
    }

    public void  setOnItemClick(MOnItemSelected.OnItemClickListener onItemClick){
        this.onItemClickListener = onItemClick;
    }
}
