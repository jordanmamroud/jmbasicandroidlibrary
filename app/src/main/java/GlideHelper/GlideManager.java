package GlideHelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.animation.GlideAnimationFactory;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

/**
 * Created by Jordan on 7/19/2017.
 */

public class GlideManager {

    public  static int CENTER_CROP  = 0 ;
    public static int FIT_CENTER  = 1 ;
    public static int NO_SCALE = 1 ;

    Context mContext;

    // default values for these properties ;
    private int imagePxHeight = 0;

    private int imagePxWidth  = 0 ;

    private int scaleType =  FIT_CENTER ;

    private int scaleDownBy =  1 ;

    private boolean cache = true ;

    private boolean animate = true ;

    private DiskCacheStrategy cacheStrategy = DiskCacheStrategy.RESULT ;

    private ViewPropertyAnimation.Animator customAnimation = null   ;

    public GlideManager(Context mContext) {
        this.mContext = mContext;
    }

    public GlideManager(Context mContext, int scaleDownBy) {
        this.mContext = mContext;
        this.scaleDownBy = scaleDownBy;
    }

    public GlideManager(Context context , int imagePxHeight, int imagePxWidth) {
        this.mContext = context ;
        this.imagePxHeight = imagePxHeight;
        this.imagePxWidth = imagePxWidth;
    }

    public static void setDefaultGlideImage(Context context , Object image, boolean cache, ImageView view, boolean animate){
        BitmapTypeRequest imgFormation = Glide.with(context).load(  image   ).asBitmap();

        if(!animate) imgFormation.dontAnimate();

        imgFormation.skipMemoryCache(cache).diskCacheStrategy(DiskCacheStrategy.RESULT).into( view    );
    }

    public void setScaledDownImage(Object image , ImageView view, int downScale){
        BitmapImageViewTarget bitmapImageViewTarget = new BitmapImageViewTarget(view);

        // this sets a scales down the image size (in pixels) , waits for imageview size to come back then scaling down accordingly.
        // glide checks if image height or width == 0 and if they do means view is not measured and it adds a layout listener and waits for views to be measures then runs
        // size ready callback , dont need to know this but think it is cool.

        SizeReadyCallback cb = (int viewWidth, int viewHeight)-> {
            int widthToScale = imagePxWidth !=  0 ? imagePxWidth : viewWidth ;
            int heightToScale = imagePxHeight != 0 ? imagePxHeight : viewHeight ;

            //if downscale is not 0 or 1 scales it down
            int scaledDownWidth = downScale != 0 && downScale!= NO_SCALE    ? widthToScale / downScale : widthToScale ;
            int scaledDownHeight =  downScale != 0 && downScale!= NO_SCALE ? heightToScale / downScale : widthToScale ;

            setImage(image , view , scaledDownWidth , scaledDownHeight);
        };

        bitmapImageViewTarget.getSize(cb);
    }

    public void setGlideImage(Object image , ImageView view) {
        if (scaleDownBy != NO_SCALE || scaleDownBy != 0) {
            setScaledDownImage(image, view, scaleDownBy);
        } else{
            setImage(image, view, imagePxWidth, imagePxHeight);
        }
    }


    public void setImage(Object image , ImageView view, int width , int height){
        BitmapTypeRequest imgFormation = Glide.with(mContext).load(  image   ).asBitmap();
        if( width != 0 && height != 0){
            imgFormation.override(width, height);
        };

        if(scaleType == CENTER_CROP){
            imgFormation.centerCrop();
        }else if (scaleType == FIT_CENTER){
            imgFormation.fitCenter();
        }

        if(!animate) imgFormation.dontAnimate() ;

        if(customAnimation != null && animate)  imgFormation.animate(customAnimation);
        imgFormation.skipMemoryCache(cache);
        if(!cache){
            imgFormation.diskCacheStrategy(DiskCacheStrategy.NONE);
        }
        else {
            imgFormation.diskCacheStrategy(cacheStrategy);
        }
        imgFormation.into( view    );
    }


    public ViewPropertyAnimation.Animator getGlideAnimation() {
        return customAnimation;
    }

    public void setGlideAnimation(ViewPropertyAnimation.Animator glideAnimation) {
        this.customAnimation = glideAnimation;
    }

    public int getScaleDownBy() {
        return scaleDownBy;
    }

    public void setScaleDownBy(int scaleDownBy) {
        this.scaleDownBy = scaleDownBy;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public int getImagePxHeight() {
        return imagePxHeight;
    }

    public void setImagePxHeight(int imagePxHeight) {
        this.imagePxHeight = imagePxHeight;
    }

    public int getImagePxWidth() {
        return imagePxWidth;
    }

    public void setImagePxWidth(int imagePxWidth) {
        this.imagePxWidth = imagePxWidth;
    }

    public int getScaleType() {
        return scaleType;
    }

    public void setScaleType(int scaleType) {
        this.scaleType = scaleType;
    }

    public DiskCacheStrategy getCacheStrategy() {
        return cacheStrategy;
    }

    public void setCacheStrategy(DiskCacheStrategy cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
    }
}
