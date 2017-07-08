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
    private IListFragment iActivity ;

    public void setAdapterDelegate(IAdapterDelegates delegate){
        this.delegate = delegate ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        iActivity = (IListFragment) getActivity() ;
        if(layoutResource == 0){
            layoutResource= R.layout.fragment_list;

        }
        View view = inflater.inflate(layoutResource, container, false);
        instantiateView(view);
        setupCallbacks();

        return view;
    }

    public void instantiateView(View view){
        list = (RecyclerView) view.findViewById(R.id.mListview);
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
        // gets event from activity
        list.addOnItemTouchListener(new MOnItemSelected(getContext(),
                (View view, int position)-> iActivity.onListItemSelected(position, dataList.get(position))));
    }

    public void  setOnItemClick(MOnItemSelected.OnItemClickListener onItemClick){
        this.onItemClickListener = onItemClick;
    }

    public interface IListFragment {
        public void onListItemSelected(int position, Object object);
    }

}
