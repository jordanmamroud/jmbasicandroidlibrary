package CustomViews;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jordan.basicslibrary.R;
import com.example.jordan.basicslibrary.Utilities.Utils.MHelper;

/**
 * Created by Jordan on 7/21/2017.
 */

public class LockedView {

    Context mContext ;
    ViewGroup layout  ;
    View blurView ;
    ImageView lockedImg ;

    public LockedView(Context mContext ) {      this.mContext = mContext;    }

    public void addLockedView(ViewGroup parentLayout){
        View v = LayoutInflater.from(mContext).inflate(R.layout.cv_locked , parentLayout , true) ;
        blurView = v.findViewWithTag(R.id.blurView) ;
        lockedImg = (ImageView) v.findViewById(R.id.lockedImg) ;
    }

    public void enableLock(){
        if(layout != null) {
            layout.findViewWithTag("lockIMG").setVisibility(View.VISIBLE);
            layout.findViewWithTag("blurView").setVisibility(View.VISIBLE);
        }
    }

    public void removeLock(){
        if(layout.findViewWithTag("lockIMG") != null) {
            layout.findViewWithTag("lockIMG").setVisibility(View.GONE);
            layout.findViewWithTag("blurView").setVisibility(View.GONE);
        }
    }

    public ImageView getLockImage(){
        ImageView imageView = new ImageView(mContext);
        int height = MHelper.getPixelsFromDps(100 , mContext) ;
        int width = MHelper.getPixelsFromDps(100 , mContext) ;

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams( width , height  );
        imageView.setTag("lockIMG");
        imageView.setVisibility(View.GONE);
        imageView.setLayoutParams(params);
        imageView.setImageResource(R.drawable.lock);
        return imageView ;
    }

    public View setBlurView(){
        View blurView = new View(mContext);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.MATCH_PARENT );
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
