package  view.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;

/**
 * Created by Jordan on 7/19/2017.
 */

public class GlideManager {

    public  static int CENTER_CROP  = 0 ;
    public static int FIT_CENTER  = 1 ;
    public static int NO_SCALE = 1 ;

    Context mContext;

    // default values for these properties , once they are set all scaled down images will be based on these properties ;
    private int imagePxHeight = 0;

    private int imagePxWidth  = 0 ;

    private int scaleType =  FIT_CENTER ;

    private int scaleDownBy =  1 ;

    private boolean cache = true ;

    private boolean animate = false ;

    private DiskCacheStrategy cacheStrategy = DiskCacheStrategy.RESULT ;

    private ViewPropertyAnimation.Animator customAnimation = null   ;

    // if this is set to true pre draw listener will set the image px height and width so that layout listener is not called again
    private boolean measureOnlyOnce = false;

    Drawable placeholder = null;

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

    public void setGlideImage(Object image , ImageView view) {
        if(image != null) {
            if (    shouldMeasureViewInsteadOfGlide()   && !isViewAlreadyMeasured(view)  ) {
                setImageAfterViewIsMeasured( image, view  );
            } else {
                setImageProperties(   image, view );
            }
        }else {
            System.out.println("image given to glide manager was null");
        }
    }
    // if scale down is set we should measure view ourselves instead of letting glides built in method for measuring do it
    private boolean shouldMeasureViewInsteadOfGlide() { return scaleDownBy != NO_SCALE || scaleDownBy != 0;  }

    private boolean isViewAlreadyMeasured(View view) { return view.getHeight() > 0 && view.getWidth() > 0 ||( imagePxWidth > 0 && imagePxHeight > 0 && measureOnlyOnce ); }

    public  void setImageAfterViewIsMeasured(Object image , ImageView view  ){
        view.getViewTreeObserver().addOnPreDrawListener( getPreDrawListener(    view , image    ) );
    }

    private ViewTreeObserver.OnPreDrawListener getPreDrawListener(ImageView view , Object image) {
        return new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                if (view.getHeight() != 0 && view.getWidth() != 0) {

                    measureAndSetImageView(view, image);

                    return true;
                }
                else return false;
            }
        };
    }

    private void measureAndSetImageView(ImageView view, Object image ){
        if (measureOnlyOnce) {
            // will still run multiple times in grid views but will stop once showing images are done loading because gridviews use same layout listenr even if try to remove it
            imagePxHeight = view.getHeight();
            imagePxWidth = view.getWidth();
        }
        setImageProperties(image  ,  view   );
    }

    private void setImageProperties(Object image , ImageView view){
        BitmapTypeRequest imgFormation = Glide.with(mContext).load(  image   ).asBitmap() ;
        setImageDimensions(view , imgFormation);

        if(scaleType == CENTER_CROP) imgFormation.centerCrop();

        else if (scaleType == FIT_CENTER)   imgFormation.fitCenter();

        if(placeholder != null) imgFormation.placeholder(placeholder);

        if(!animate ) imgFormation.dontAnimate() ;

        if(customAnimation != null && animate)  imgFormation.animate(customAnimation);

        imgFormation.skipMemoryCache(cache);

        if(!cache) imgFormation.diskCacheStrategy(DiskCacheStrategy.NONE);

        else imgFormation.diskCacheStrategy(cacheStrategy);

        imgFormation.into( view    );
    }

    private void setImageDimensions(View view , BitmapTypeRequest imgFormation){
        int width  = getWidth(view.getWidth());
        int height = getHeight(view.getHeight());
        // only override if dimens do not equal zero otherwise do nothing
        if  (width != 0 && height != 0    )  imgFormation.override(width, height);
    }

    private int getHeight(int height ){
        int heightToScale = imagePxHeight != 0 ? imagePxHeight : height;
        return scaleDownBy != 0 && scaleDownBy != NO_SCALE ? heightToScale / scaleDownBy : heightToScale;
    }

    private int getWidth(int width ){
        int widthToScale = imagePxWidth != 0 ? imagePxWidth : width;
        return scaleDownBy != 0 && scaleDownBy != NO_SCALE ? widthToScale / scaleDownBy : widthToScale;
    }

    public void setImagePxHeight(int imagePxHeight) {
        this.imagePxHeight = imagePxHeight;
    }

    public void setImagePxWidth(int imagePxWidth) { this.imagePxWidth = imagePxWidth; }

    public void setPlaceholder(Drawable placeholder) {
        this.placeholder = placeholder;
    }

    public GlideManager setMeasureOnlyOnce(boolean measureOnlyOnce) { this.measureOnlyOnce = measureOnlyOnce;  return this; }

    public GlideManager setGlideAnimation(ViewPropertyAnimation.Animator glideAnimation) {
        this.customAnimation = glideAnimation;
        return this;
    }

    public GlideManager setScaleDownBy(int scaleDownBy) {
        this.scaleDownBy = scaleDownBy;
        return this ;
    }

    public GlideManager setCache(boolean cache) {
        this.cache = cache;
        return this ;
    }

    public GlideManager setScaleType(int scaleType) {
        this.scaleType = scaleType;
        return this;
    }

    public GlideManager setCacheStrategy(DiskCacheStrategy cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
        return this;
    }
}
