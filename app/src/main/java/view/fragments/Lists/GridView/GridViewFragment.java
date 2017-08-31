package view.fragments.Lists.GridView;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import view.uievents.touchevents.JOnItemSelected;

import java.util.Observable;

import view.fragments.BaseFragment;

public abstract class GridViewFragment extends BaseFragment{

    private RecyclerView mRecyclerView;
    private GridViewAdapter adapter ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void attachGridview(RecyclerView recyclerView , GridViewAdapter adapter , int itemsPerRow){
        this.mRecyclerView = recyclerView ;
        this.adapter = adapter ;
        mRecyclerView.addOnItemTouchListener(new JOnItemSelected(   getContext()    , this :: onItemClicked     ));
        setupRecyclerView(  itemsPerRow  ,  adapter);
    }

    private void setupRecyclerView(int itemsOnLine, GridViewAdapter gridViewAdapter){
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), itemsOnLine);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(gridViewAdapter);
     }

    public abstract void onItemClicked(View v , int pos);

    public void notifyDataSetChanged(){
        if(adapter !=   null)
            adapter.notifyDataSetChanged();
    }

    @Override
    public void update(Observable o, Object arg) {
//        items.add(arg);
//         if(gridViewAdapter !=null) {
//             gridViewAdapter.notifyDataSetChanged();
//             gridViewAdapter.updateDelegate(arg);
//        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public RecyclerView getRecyclerView(){
        return mRecyclerView;
    }



}
