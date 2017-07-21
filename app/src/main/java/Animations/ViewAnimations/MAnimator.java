package Animations.ViewAnimations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;



import com.transitionseverywhere.Rotate;
import com.transitionseverywhere.TransitionManager;


import Animations.avm.BaseViewAnimator;
import Animations.avm.fading_entrances.FadeInAnimator;
import Animations.avm.fading_entrances.FadeInDownAnimator;
import Animations.avm.fading_entrances.FadeInUpAnimator;
import Animations.avm.fading_exits.FadeOutAnimator;
import Animations.avm.fading_exits.FadeOutDownAnimator;
import Animations.avm.fading_exits.FadeOutUpAnimator;
import Animations.avm.sliders.SlideInDownAnimator;
import Animations.avm.sliders.SlideInLeftAnimator;
import Animations.avm.sliders.SlideInRightAnimator;
import Animations.avm.sliders.SlideInUpAnimator;
import Animations.avm.sliders.SlideOutDownAnimator;
import Animations.avm.sliders.SlideOutLeftAnimator;
import Animations.avm.sliders.SlideOutRightAnimator;
import Animations.avm.sliders.SlideOutUpAnimator;
import Animations.avm.specials.RollInAnimator;
import Animations.avm.specials.RollOutAnimator;
import Animations.avm.zooming_entrances.ZoomInAnimator;
import Animations.avm.zooming_exits.ZoomOutUpAnimator;


/**
 * Created by Jordan on 5/30/2017.
 */

public class MAnimator {

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

    public static void rollOut(int duration , boolean keepViews,View... views){ setOutAnimation( new RollOutAnimator() , duration ,keepViews, views); }

    public static void rollIn(int duration , View... views){setInAnimation(new RollInAnimator(), duration , views);}

    public static void slideOutLeft(int duration ,boolean keepViews, View... views){ setOutAnimation( new SlideOutLeftAnimator(), duration ,keepViews, views);}

    public static void slideInLeft(int duration, View... views  ){ setInAnimation( new SlideInLeftAnimator() , duration , views);}

    public static void slideOutRight(int duration, boolean keepViews,View... views  ){ setOutAnimation( new SlideOutRightAnimator() , duration ,keepViews, views);}

    public static void slideInRight(int duration, View... views  ){ setInAnimation( new SlideInRightAnimator() , duration , views);}

    public static void slideOutUp(int duration,boolean keepViews, View... views  ){ setOutAnimation( new SlideOutUpAnimator() , duration ,keepViews, views);}

    public static void slideInUp(int duration, View... views  ){ setInAnimation( new SlideInUpAnimator() , duration , views);}

    public static void slideOutDown(int duration,boolean keepViews, View... views  ){ setOutAnimation( new SlideOutDownAnimator() , duration , keepViews,views);}

    public static void slideInDown(int duration, View... views  ){ setInAnimation( new SlideInDownAnimator() , duration , views);}

    public static void zoomIn(int duration, View... views  ){ setInAnimation( new ZoomInAnimator() , duration , views);}

    public static void zoomOut(int duration, boolean keepViews,View... views  ){ setOutAnimation( new ZoomOutUpAnimator() , duration ,keepViews, views);}

    public static void fadeInDown(int duration, View... views){ setInAnimation(new FadeInDownAnimator(), duration, views);}

    public static void fadeOutDown(int duration, boolean keepViews,View... views  ){ setOutAnimation(new FadeOutDownAnimator(), duration , keepViews,   views);}

    public static void fadeInUp(int duration ,  View... views) { setInAnimation( new FadeInUpAnimator() , duration , views);}

    public static void fadeOutUp(int duration ,boolean keepViews, View... views) { setOutAnimation( new FadeOutUpAnimator() , duration ,keepViews, views);}

    public static void fadeIn(int duration ,View... views){ setInAnimation( new FadeInAnimator() , duration , views ); }

    public static void fadeOut(int duration, boolean keepViews, View... views){ setOutAnimation(  new FadeOutAnimator() , duration ,keepViews, views ); }

    private static void setInAnimation( BaseViewAnimator animator, int duration , View... views){
        if(duration != 0 ) animator.setDuration(duration);
        for(View v : views){
            v.setClickable(true);
            animator.prepare(v);
            v.setVisibility(View.VISIBLE);
            animator.animate();
        }
    }

    private static void setOutAnimation( BaseViewAnimator animator, int duration , boolean keepViews, View... views ){
        if(duration != 0 ) animator.setDuration(duration);
        animator.addAnimatorListener( onOutAnimationEnd(true, views));
        for(View v : views){
            v.setClickable(false);
            animator.prepare(v);
            animator.animate();
        }
    }

    // keep views means setting to invisibile so that view can be animated back in, but it still takes space
    public static AnimatorListenerAdapter onOutAnimationEnd(boolean keepViews, View... views){
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

    public static AnimatorListenerAdapter onInAnimationEnd( View... views){
        return new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                for (View v : views) {
                    v.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);

            }
        };
    }


// end methods for android view animation library
}
