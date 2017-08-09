package Animations;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import Animations.viewanimationslib.BaseViewAnimator;


/**
 * Created by Jordan on 5/30/2017.
 */

public class JAnimator {

    public static AnimationBuilder createAnimation(BaseViewAnimator animationType ){
        return  new AnimationBuilder(animationType);
    }

    public static void startAnimation(Context context , View viewToAnimate, int animationId ){
         Animation rotation = AnimationUtils.loadAnimation(context, animationId );
         viewToAnimate.startAnimation(rotation);
    }
    // any views that are visible animated out any invisible or gone animated in .
    public static void toggleViewsWithAnimation(BaseViewAnimator inAnim, BaseViewAnimator outAnim , int duration , View... views ){
        AnimationBuilder inAnimation = new AnimationBuilder(inAnim);
        AnimationBuilder outAnimation = new AnimationBuilder(outAnim);

        for ( View v : views){
            if(v.getVisibility() == View.VISIBLE) {
                inAnimation.setViewsToAnimate(v).setDuration(duration).animateOut();
                v.setVisibility(View.INVISIBLE);

            }else {
                outAnimation.setViewsToAnimate(v).setDuration(duration).animateIn();
            }
        }
    }

    public static void hideViews(@Nullable Animation animation, View... views){
        for (View v : views){
            if(animation != null) {
                v.startAnimation(animation);
                v.setVisibility(View.INVISIBLE);
            }else {
                v.setVisibility(View.GONE);
            }
        }
    }

    public static void showViews(@Nullable Animation animation,View... views){
        for (View v : views){
            if(animation != null) {
                v.startAnimation(animation);
            }
            v.setClickable(true);
            v.setVisibility(View.VISIBLE);
        }
    }

// end methods for android view animation library
}
