package fragments.Lists;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Jordan on 5/4/2017.
 */

public class MListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int layoutResource;
    private ArrayList list ;
    private Context context;
    private IAdapterDelegates delegate ;

    public MListAdapter(Context context , IAdapterDelegates delegate){
        this.context = context ;
        this.delegate =delegate ;
    }

    public MListAdapter(Context context, IAdapterDelegates delegate, ArrayList list ){
        this.list = list;
        this.context = context;
        this.delegate = delegate ;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return(RecyclerView.ViewHolder) delegate.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegate.onBindViewHolder(  list, holder,  position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
