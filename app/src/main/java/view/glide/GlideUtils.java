package  view.glide;

import android.content.Context;
import android.view.View;
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

    // todo figure out why this causes memory definetly something to do with viewtree observer.
    public static  void setScaledDownImage(Context mContext , Object image , ImageView view, int downScale , boolean cache){
        BitmapTypeRequest imgFormation = Glide.with(mContext).load(  image   ).asBitmap() ;

        if( view.getHeight() > 0 && view.getWidth() > 0 ){
            imgFormation.override( view.getHeight() / downScale , view.getWidth() / downScale);
            setImage(imgFormation,  view, cache );

        }else {
            // returning true for proeceeding with drawing pass only if view has been measured
            ViewTreeObserver.OnGlobalLayoutListener listener = new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if( view.getWidth() != 0 && view.getHeight() != 0) {
                        imgFormation.override(view.getWidth() / downScale, view.getHeight() / downScale);
                        setImage(imgFormation, view, cache);
                    }
                }
            };

            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if( view.getWidth() != 0 && view.getHeight() != 0) {
                        imgFormation.override(view.getWidth() / downScale, view.getHeight() / downScale);
                        setImage(imgFormation, view, cache);
                        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });
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
