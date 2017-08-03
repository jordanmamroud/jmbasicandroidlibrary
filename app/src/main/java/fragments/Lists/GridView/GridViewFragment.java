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
import java.util.Observable;
import java.util.Observer;

import fragments.BaseFragment;
import fragments.IAdapterDelegates;

public abstract class GridViewFragment extends BaseFragment{

    private RecyclerView mRecyclerView;
    private GridViewAdapter gridViewAdapter ;

    public void setupGridView(RecyclerView recyclerView , int itemsPerRow){
        this.mRecyclerView = recyclerView ;
        mRecyclerView.addOnItemTouchListener(new MOnItemSelected(   getContext()    , this :: onItemClicked     ));
        setupRecyclerView(  itemsPerRow   );
    }

    private void setupRecyclerView(int itemsOnLine){
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), itemsOnLine);
        gridViewAdapter = initAdapter() ;
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(gridViewAdapter);
     }

    public abstract GridViewAdapter initAdapter();

    public abstract void onItemClicked(View v , int pos);

    @Override
    public void update(Observable o, Object arg) {
        if(gridViewAdapter !=null) {
            gridViewAdapter.updateDelegate(o);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public RecyclerView getRecyclerView(){
        return mRecyclerView;
    }

    public GridViewAdapter getGridViewAdapter(){return gridViewAdapter; }

    public int getCount(){ return gridViewAdapter.getItemCount();   }

}
