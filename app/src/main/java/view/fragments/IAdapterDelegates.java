package view.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Jordan on 5/19/2017.
 */
public interface IAdapterDelegates {

    void onBindViewHolder(ArrayList itemsList, RecyclerView.ViewHolder holder, int position);
    RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType);
    void update(Object o);
}
