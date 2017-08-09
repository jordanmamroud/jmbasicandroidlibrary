package glide;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Jordan on 7/24/2017.
 */

public class GlideUtils {

    public static void setDefaultGlideImage(Context context , Object image, boolean cache, ImageView view, boolean animate){
        BitmapTypeRequest imgFormation = Glide.with(context).load(  image   ).asBitmap();

        if(!animate) imgFormation.dontAnimate();
        imgFormation.skipMemoryCache(cache).diskCacheStrategy(DiskCacheStrategy.RESULT).into( view    );
    }

    public static void setLayoutBackgroundImage(Context context, ViewGroup layout, Object background, boolean isFromRes){
        GlideLayoutTarget imageTarget = new GlideLayoutTarget(context, layout , isFromRes);
        Glide.with(context).load( background ).fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(imageTarget);
    }

    public static  void setScaledDownImage(Context mContext , Object image , ImageView view, int downScale , boolean cache){
        BitmapTypeRequest imgFormation = Glide.with(mContext).load(  image   ).asBitmap() ;

        if( view.getHeight() > 0 && view.getWidth() > 0 ){
            imgFormation.override( view.getHeight() / downScale , view.getWidth() / downScale);
            setImage(imgFormation,  view, cache );
        }else {
            // returning true for proeceeding with drawing pass only if view has been measured
            ViewTreeObserver.OnPreDrawListener listener = new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    //must be removed here or will freeze on failed measures
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    if( view.getWidth() != 0 && view.getHeight() != 0) {

                        imgFormation.override(view.getWidth() / downScale , view.getHeight() / downScale);
                        setImage(imgFormation, view, cache);
                    }
                    return true;
                }
            };

            view.getViewTreeObserver().addOnPreDrawListener( listener );
        }
    }

    public static void setImage(BitmapTypeRequest imgFormation, ImageView imageView , boolean cache){
        imgFormation.centerCrop();
        imgFormation.fitCenter();
        imgFormation.dontAnimate();
        imgFormation.skipMemoryCache(cache);
        if(!cache){
            imgFormation.diskCacheStrategy(DiskCacheStrategy.NONE);
        } else {
            imgFormation.diskCacheStrategy(DiskCacheStrategy.RESULT);
        }
        imgFormation.into( imageView    );
    }

}
