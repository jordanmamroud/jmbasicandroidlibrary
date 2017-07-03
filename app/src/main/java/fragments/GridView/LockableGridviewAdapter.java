package fragments.GridView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jordan.basicslibrary.R;


import java.util.ArrayList;



/**
 * Created by Jordan on 6/20/2017.
 */

public class LockableGridviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList list ;
    private Context mContext;
    private ArrayList<String> imageTitles ;
    private ArrayList<Integer> images;
    private int availableImagesCount;

    public LockableGridviewAdapter(Context context, ArrayList<Integer> images, int availableImagesCount) {
        this.mContext = context ;
        this.images = images;
        this.availableImagesCount = availableImagesCount;
    };

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_locked_image, parent , false);
        return new GridViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GridViewHolder mHolder = (GridViewHolder) holder ;
        mHolder.lock.setVisibility(View.GONE);
        mHolder.blur.setVisibility(View.GONE);
        mHolder.thumbnail.setScaleType(ImageView.ScaleType.FIT_XY);

//        System.out.println("why");
        Glide.with(mContext).load(images.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into((ImageView) mHolder.thumbnail);

//        mHolder.thumbnail.setImageResource(images.get(position));
//        mHolder.thumbnail.setImageResource(images.get(position));
        if(position >= availableImagesCount) {
                mHolder.lock.setVisibility(View.VISIBLE);
                mHolder.blur.setVisibility(View.VISIBLE);
        }
    };

    public void unlockAll(){
        availableImagesCount = images.size() + 1;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder{
        ImageView thumbnail ;
        ImageView lock;
        View blur;
        public GridViewHolder(View itemView) {
            super(itemView);

            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            lock = (ImageView) itemView.findViewById(R.id.lock);
            blur = itemView.findViewById(R.id.blur);
        }
    }
}
