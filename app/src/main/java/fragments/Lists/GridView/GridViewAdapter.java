package fragments.Lists.GridView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import java.util.ArrayList;

import fragments.IAdapterDelegates;


/**
 * Created by Jordan on 6/20/2017.
 */

public class GridViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList list ;
    private Context mContext;
    private IAdapterDelegates viewDelegate;

    public GridViewAdapter(Context context, ArrayList items,  IAdapterDelegates viewDelegate) {
        this.mContext = context ;
        this.viewDelegate = viewDelegate;
        this.list = items ;
    };

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewDelegate.createViewHolder(parent, viewType  );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        viewDelegate.onBindViewHolder( list, holder , position    );
    };

    // updates delegate prior to running notify data set changed
    public void updateDelegate(Object o){
        viewDelegate.update(o);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
