package fragments.GridView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import fragments.Lists.IAdapterDelegates;


/**
 * Created by Jordan on 6/20/2017.
 */

public class GridViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList list ;
    private Context mContext;
    private ArrayList<String> imageTitles ;
    private ArrayList<Integer> images;
    private IAdapterDelegates delegates ;


    public GridViewAdapter(Context context, ArrayList<Integer> images, IAdapterDelegates delegates  ) {
        this.mContext = context ;
        this.images = images;
        this.delegates = delegates ;
    };

    public GridViewAdapter(Context context, ArrayList<Integer> images) {
        this.mContext = context ;
        this.images = images;

    };

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegates.createViewHolder(parent, viewType  );
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegates.onBindViewHolder( images, holder , position    );
    };

    // updates delegate prior to running notify data set changed
    public void updateDelegate(Object o){
        delegates.update(o);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


}
