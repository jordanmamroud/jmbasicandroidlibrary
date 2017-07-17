package fragments.Lists.GridView;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jordan.basicslibrary.R;
import com.example.jordan.basicslibrary.Utilities.Interfaces.IDrawableImage;
import com.example.jordan.basicslibrary.Utilities.Utils.MHelper;
import com.example.jordan.basicslibrary.Utilities.Utils.ViewHelper;

import java.util.ArrayList;

import fragments.IAdapterDelegates;

/**
 * Created by Jordan on 7/7/2017.
 */

public class GridViewDelegate implements IAdapterDelegates {

    private Context mContext;
    private int availableImagesCount = 0;
    private boolean lockEnabled = false;


    public GridViewDelegate(Context mContext) {
        this.mContext = mContext;
        lockEnabled = false ;
    }

    // this constructor is used for locking feature, if not used lock feature must be set
    public GridViewDelegate(Context mContext,  int availableImagesCount) {
        this.mContext = mContext;
        this.availableImagesCount = availableImagesCount;
        lockEnabled = true ;
    }

    @Override
    public void onBindViewHolder(ArrayList itemsList, RecyclerView.ViewHolder holder, int position) {
        GridViewHolder mHolder = (GridViewHolder) holder ;

        // sets up smooth image caching and loading checking that item has a image from resource.
        if (itemsList.get( position) instanceof IDrawableImage) {

            String imgLocation = ((IDrawableImage) itemsList.get(position)).getDrawableName();

            int imageId = mContext.getResources().getIdentifier(imgLocation, "drawable", mContext.getPackageName());

            Glide.with(mContext).load(imageId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into((ImageView) mHolder.thumbnail);

            // hiding all lock views prior to showing available ones if lock view is enabled .
            if (lockEnabled) {
                mHolder.removeLock();
            }
            // shows image as locked if lock feature is enabled and image is unavailble
            if (position >= availableImagesCount && lockEnabled && availableImagesCount > -1) {
                mHolder.enableLock();
            }

        }

    }

    public void setLockEnabled(boolean enableLock){
        this.lockEnabled = enableLock;
    }

    public void setAvailableImagesCount(int count ){
        this.availableImagesCount = count ;
    }

    @Override
    public void update(Object o) {
        System.out.println("being updated");
        // show all images if they are locked , or if new num of available images is passed int as object set it as that ;
        if(o != null  && o instanceof Integer ){
            availableImagesCount = (int) o  ;
        }else {
            lockEnabled = false ;
            availableImagesCount = -1;
        }
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_locked_image, parent, false);
        return new GridViewHolder(v  , lockEnabled  );
    }

    public class GridViewHolder extends RecyclerView.ViewHolder{
        FrameLayout layout ;
        ImageView thumbnail ;

        public GridViewHolder(View itemView , boolean lockEnabled ) {
            super(itemView);
            layout = (FrameLayout) itemView.findViewById(R.id.imageItemLO);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);

            if(lockEnabled) {
                layout.addView( setLockImage() );
                layout.addView( setBlurView()    );
            }
        }

        public void removeLock(){
            if(layout.findViewWithTag("lockIMG") != null) {
                layout.findViewWithTag("lockIMG").setVisibility(View.GONE);
                layout.findViewWithTag("blurView").setVisibility(View.GONE);
            }
        }

        public void enableLock(){
            if(layout != null) {
                layout.findViewWithTag("lockIMG").setVisibility(View.VISIBLE);
                layout.findViewWithTag("blurView").setVisibility(View.VISIBLE);
            }
        }

        public ImageView setLockImage(){
            ImageView imageView = new ImageView(mContext);
            int height = MHelper.getPixelsFromDps(100 , mContext) ;
            int width = MHelper.getPixelsFromDps(100 , mContext) ;
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams( width , height  );
            params.gravity = Gravity.CENTER ;
            imageView.setTag("lockIMG");
            imageView.setVisibility(View.GONE);
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.drawable.lock);
            return imageView ;
        }

        public View setBlurView(){
            View blurView = new View(mContext);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT );
            blurView.setTag("blurView");
            blurView.setVisibility(View.GONE);
            blurView.setLayoutParams(layoutParams);
            blurView.setAlpha( .9f );
            blurView.setBackgroundColor(Color.parseColor("#999EA0"));

            return blurView ;
        }

        public View getLockView(){
            return layout.findViewWithTag("lockIMG") ;
        }

        public View getBlurView(){
            return layout.findViewWithTag("blurView") ;
        }

    }

}
