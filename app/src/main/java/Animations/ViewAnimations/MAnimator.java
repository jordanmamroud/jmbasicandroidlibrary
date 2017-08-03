package Animations.ViewAnimations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import com.transitionseverywhere.Rotate;
import com.transitionseverywhere.TransitionManager;


import Animations.AnimationManager;
import Animations.avm.BaseViewAnimator;
import Animations.avm.fading_entrances.FadeInUpAnimator;
import Animations.avm.fading_exits.FadeOutDownAnimator;


/**
 * Created by Jordan on 5/30/2017.
 */

public class MAnimator {

    public static AnimationManager createAnimation(BaseViewAnimator animationType ){
        return  new AnimationManager(animationType);
    }

    public static void startAnimation(Context context , View viewToAnimate, int animationId ){
         Animation rotation = AnimationUtils.loadAnimation(context, animationId );
         viewToAnimate.startAnimation(rotation);
    }
    // any views that are visible animated out any invisible or gone animated in .
    public static void toggleViewsWithAnimation(BaseViewAnimator inAnim, BaseViewAnimator outAnim , int duration , View... views ){
        for ( View v : views){
            if(v.getVisibility() == View.VISIBLE) {
                setOutAnimation(outAnim, duration, true,  v);
            }else {
                setInAnimation(inAnim , duration , v );
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

    public static void rotate(View viewToAnimate, boolean isRotated){
        TransitionManager.beginDelayedTransition((ViewGroup) viewToAnimate.getParent() , new Rotate());
        viewToAnimate.setRotation(isRotated ? 135 : 0);
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

    // methods for android view animation library ;

    public static void fadeOutDown(int duration, boolean keepViews,View... views  ){ setOutAnimation(new FadeOutDownAnimator(), duration , keepViews,   views);}

    public static void fadeInUp(int duration ,  View... views) { setInAnimation( new FadeInUpAnimator() , duration , views);}


    public static void setInAnimation( BaseViewAnimator animator, int duration , View... views){
        if(duration != 0 ) animator.setDuration(duration);
        animator.addAnimatorListener( inAnimationEvents(views) );
        for(View v : views){
            animator.prepare(v);
            v.setVisibility(View.VISIBLE);
            animator.animate();
        }
    }

    public static void setOutAnimation( BaseViewAnimator animator, int duration , boolean keepViews, View... views ){
        if(duration != 0 ) animator.setDuration(duration);
        animator.addAnimatorListener( outAnimationEvents(true, views));
        for(View v : views){
            v.setClickable(false);
            animator.prepare(v);
            animator.animate();
        }
    }

    // keep views means setting to invisibile so that view can be animated back in, but it still takes space
    public static AnimatorListenerAdapter outAnimationEvents(boolean keepViews, View... views){
        return new AnimatorListenerAdapter() {
            boolean cancelled ;
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                for (View v : views) {
                    if(!keepViews) {
                        v.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                System.out.println("Cancelled");
                cancelled = true;
            }
        };
    }

    public static AnimatorListenerAdapter inAnimationEvents( View... views){
        return new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                System.out.println("clickable huh");
                for (View v : views) {
                    System.out.println("clickable again");
                    v.setClickable(true);
                    v.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                for (View v : views) {
                    v.setClickable(true);
                    v.setVisibility(View.VISIBLE);
                }
            }
        };
    }


// end methods for android view animation library
}
