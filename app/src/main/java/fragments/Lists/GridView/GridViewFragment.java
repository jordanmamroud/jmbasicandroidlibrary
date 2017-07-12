package fragments.Lists.GridView;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jordan.basicslibrary.R;
import com.example.jordan.basicslibrary.Utilities.EventListeners.MOnItemSelected;

import java.util.ArrayList;

import fragments.IAdapterDelegates;

public class GridViewFragment extends Fragment {

    private String OUTSTATE_KEY_NUMIMAGES = "numOfAvailableImages" ;
    private RecyclerView mRecyclerView;
    private ArrayList items = new ArrayList<>();
    private GridViewAdapter gridViewAdapter ;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(  R.layout.fragment_grid_view   , container , false   );
        instantiateView(v);
        return v    ;
    }

    public void instantiateView(View v){
        mRecyclerView = (RecyclerView) v.findViewById(R.id.imagesGrid);
    }

    public void setupLayout(int itemsOnLine){
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), itemsOnLine);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(gridViewAdapter);
    }
    // must be called before setuplayout or adapter will be null unles setAdapter was called.
    public void createGridViewAdapter( ArrayList itemsList, IAdapterDelegates delegates){
        this.items = itemsList ;
        gridViewAdapter = new GridViewAdapter(getContext(), itemsList, delegates);
    }

    public void setOnItemClick(MOnItemSelected onItemSelected){
        mRecyclerView.addOnItemTouchListener(onItemSelected);
    }

   public void setGridViewAdapter(GridViewAdapter adapter){
       this.gridViewAdapter = adapter;
   }

    public void update(Object o ){
        if(gridViewAdapter !=null) {
            gridViewAdapter.updateDelegate(o);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
