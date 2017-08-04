package fragments.Lists.GridView;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import touchevents.JMOnItemSelected;

import java.util.Observable;

import fragments.BaseFragment;

public abstract class GridViewFragment extends BaseFragment{

    private RecyclerView mRecyclerView;
    private GridViewAdapter gridViewAdapter ;

    public void setupGridView(RecyclerView recyclerView , int itemsPerRow){
        this.mRecyclerView = recyclerView ;
        mRecyclerView.addOnItemTouchListener(new JMOnItemSelected(   getContext()    , this :: onItemClicked     ));
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
