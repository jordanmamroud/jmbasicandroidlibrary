package fragments.Lists.GridView;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.example.jordan.jmbasicandroidlibrary.R;

import java.util.ArrayList;

import GlideHelper.GlideManager;
import fragments.IAdapterDelegates;
import functionalinterfaces.IImage;
import functionalinterfaces.ILockable;

/**
 * Created by Jordan on 7/7/2017.
 */

public class GridViewDelegate implements IAdapterDelegates {

    private Context mContext;
    private GlideManager glideManager;
    private boolean allUnlocked = false ;

    public GridViewDelegate(Context mContext) {     this.mContext = mContext;   }

    // this constructor is used for locking feature, if not used lock feature must be set
    public GridViewDelegate(Context mContext, GlideManager glideManager) {
        this.mContext = mContext;
        this.glideManager = glideManager ;
    }

    public GridViewDelegate(Context mContext, GlideManager glideManager, boolean allUnlocked) {
        this.mContext = mContext;
        this.glideManager = glideManager ;
        this.allUnlocked = allUnlocked ;
    }
    @Override
    public void onBindViewHolder(ArrayList itemsList, RecyclerView.ViewHolder holder, int position) {
        GridViewHolder mHolder = (GridViewHolder) holder ;

        // removing from start to prevent visibility issues
        mHolder.toggleLock( View.GONE );

        Object listItem = itemsList.get(position);

        // sets up smooth image caching and loading checking that item has a image .
        bindImage(   listItem , mHolder );

        if(   isLocked(listItem)   )     mHolder.toggleLock(View.VISIBLE);
    }

    private boolean isLocked    (Object item){  return  item instanceof ILockable && ((ILockable) item).isLocked() && ! allUnlocked;  }

    private void bindImage(Object listItem , GridViewHolder mHolder){
        Object image = null ;
        if (    hasImage(listItem)  ) {
            image = ((IImage) listItem).getImage(mContext);

            if(glideManager != null)  glideManager.setGlideImage(image ,  mHolder.thumbnail );

            else mHolder.thumbnail.setImageResource( (int)  image   );
        }else {
            System.out.println("item does not have a image");
        }
    }

    private boolean hasImage(Object listItem){ return     listItem instanceof IImage && ((IImage) listItem).getImage(mContext)   !=    null;      }

    @Override
    public void update(Object obj) {
        // show all images if they are locked
        System.out.println("update gridview delegate");
        if ( obj instanceof  Boolean )      allUnlocked =   true ;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_locked_image, parent, false);
        return new GridViewHolder(v);
    }

    public void setAllUnlocked(boolean allUnlocked) {   this.allUnlocked = allUnlocked; }

    public class GridViewHolder extends RecyclerView.ViewHolder{
        CardView layout ;
        ImageView thumbnail , lockImg;
        View blurredView ;
        int h ;
        int w ;

        public GridViewHolder(View itemView   ) {
            super(itemView);
            layout = (CardView) itemView.findViewById(R.id.imageItemLO);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            blurredView = itemView.findViewById(R.id.blurView);
            lockImg = (ImageView) itemView.findViewById(R.id.lockedImg) ;

            // doing this to save height and width to avoid having to always recalculate for views of same size
            thumbnail.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    thumbnail.getViewTreeObserver().removeOnPreDrawListener(this);
                     // doing this to prevent always having to measure thumbnail when setting view , is faster .
                    glideManager.setImagePxHeight(  h );
                    glideManager.setImagePxWidth(   w  );
                    return false;
                }
            });
         }

        private void toggleLock(int visibility){
            blurredView.setVisibility(visibility);
            lockImg.setVisibility(visibility);
        }
    }
}
