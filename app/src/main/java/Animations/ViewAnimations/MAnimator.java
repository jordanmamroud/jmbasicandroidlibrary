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

import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.androidanimations.library.fading_entrances.FadeInAnimator;
import com.daimajia.androidanimations.library.fading_entrances.FadeInUpAnimator;
import com.daimajia.androidanimations.library.fading_exits.FadeOutAnimator;
import com.daimajia.androidanimations.library.fading_exits.FadeOutDownAnimator;
import com.daimajia.androidanimations.library.sliders.SlideInLeftAnimator;
import com.daimajia.androidanimations.library.sliders.SlideInRightAnimator;
import com.daimajia.androidanimations.library.sliders.SlideInUpAnimator;
import com.daimajia.androidanimations.library.sliders.SlideOutDownAnimator;
import com.daimajia.androidanimations.library.zooming_entrances.ZoomInAnimator;
import com.daimajia.androidanimations.library.zooming_exits.ZoomOutAnimator;
import com.example.jordan.basicslibrary.Utilities.Utils.ViewHelper;

import com.transitionseverywhere.Rotate;
import com.transitionseverywhere.TransitionManager;

import java.lang.reflect.Method;


/**
 * Created by Jordan on 5/30/2017.
 */

public class MAnimator {

    public static void startAnimation(Context context , View viewToAnimate, int animationId ){
         Animation rotation = AnimationUtils.loadAnimation(context, animationId );
         viewToAnimate.startAnimation(rotation);
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

    public static void collapse(  final int initialHeight, final View v) {
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {

                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setDuration(200);
        v.startAnimation(a);
    }


    public static void expand(final int initialHeight, final View v ){
        v.measure(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);

        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewPager.LayoutParams.WRAP_CONTENT
                        : (int)(initialHeight * interpolatedTime);

                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration(400);
        v.startAnimation(a);
    }
// methods for android view animation library ;
    public static void slideInUp(View... views ){
        SlideInUpAnimator slideInUpAnimator = new SlideInUpAnimator();
        for (View v : views){
            // only views that are not already visible get animated or else looks messed up

            slideInUpAnimator.prepare(v);
            v.setVisibility(View.VISIBLE);
            slideInUpAnimator.animate();

        }
    }

    public static void slideOutDown(View... views){
        for (View v : views){
            // only views that are already visible get animated or else looks messed up
            SlideOutDownAnimator slideOutDownAnimator = new SlideOutDownAnimator();
            slideOutDownAnimator.prepare(v);
            v.setVisibility(View.INVISIBLE);
            slideOutDownAnimator.animate();
        }
    }

    public static void zoomIn(int duration, View... views){
        ZoomInAnimator zoomInAnimator = new ZoomInAnimator();
        if(duration!=0)zoomInAnimator.setDuration(duration);

        animateIn( (View v)->{
            zoomInAnimator.prepare(v);
            v.setVisibility(View.VISIBLE);
            zoomInAnimator.animate();
        },   views);

    }

    public static void fadeOutDown(int duration, boolean keepViewsInLayout ,View... views){
        FadeOutDownAnimator fadeOutDownAnimator = new FadeOutDownAnimator();
        fadeOutDownAnimator.addAnimatorListener( onOutAnimationEnd(keepViewsInLayout , views    ));
        if(duration != 0) fadeOutDownAnimator.setDuration(duration);

        for(View v : views){
            v.clearAnimation();
            fadeOutDownAnimator.prepare(v);
        }
        fadeOutDownAnimator.animate();
    }

    public static void fadeInUp (@Nullable int duration, View... views){
        FadeInUpAnimator fadeInUpAnimator = new FadeInUpAnimator();
        fadeInUpAnimator.addAnimatorListener( onInAnimationEnd(views));
        if(duration != 0) fadeInUpAnimator.setDuration(duration);
        animateIn( (View v )->{
            v.clearAnimation();
            fadeInUpAnimator.prepare(v);
            fadeInUpAnimator.animate();
            v.setVisibility(View.VISIBLE);
        },  views    );
    }

    public static void slideInLeft(ViewGroup view , int duration){
        SlideInLeftAnimator slideInLeftAnimator = new SlideInLeftAnimator();
        if(duration !=0) slideInLeftAnimator.setDuration( duration   );
        slideInLeftAnimator.prepare(view);
        slideInLeftAnimator.animate();
    }

    public static void slideInRight(ViewGroup view , int duration){
        SlideInRightAnimator slideInRightAnimator = new SlideInRightAnimator();
        if(duration != 0 ) slideInRightAnimator.prepare(view);
        slideInRightAnimator.animate();
        view.setVisibility(View.VISIBLE);
    }

    public static void rotate(View viewToAnimate, boolean isRotated){
        TransitionManager.beginDelayedTransition((ViewGroup) viewToAnimate.getParent() , new Rotate());
        viewToAnimate.setRotation(isRotated ? 135 : 0);
    }

    public static void toggleFade(  View... viewsToFade){
        for (View v : viewsToFade) {
            if(v.getVisibility() == View.VISIBLE){
                fadeOutDown(0, true, v );
            }else {
                fadeInUp(0, v);
            }
        }
    }

    public static void toggleFade(@Nullable int duration, View... viewsToFade){
        FadeOutDownAnimator fadeOutDownAnimator = new FadeOutDownAnimator();
        for (View v : viewsToFade) {
            fadeOutDownAnimator.prepare(v);
            fadeOutDownAnimator.animate();
        }
    }

    public static void fadeOut(int duration, View... views){
        FadeOutAnimator fadeOutAnimator = new FadeOutAnimator();
        for (View v : views) {
            v.clearAnimation();
            fadeOutAnimator.prepare(v);
            if (duration != 0) fadeOutAnimator.setDuration(duration);
            fadeOutAnimator.animate();
        }
    }

    public static void fadeIn(int duration, View... views){
        FadeInAnimator fadeInAnimator = new FadeInAnimator();
        animateIn(  (View v)->{
            v.clearAnimation();
            fadeInAnimator.prepare(v);
            if (duration != 0) fadeInAnimator.setDuration(duration);
            fadeInAnimator.animate();
            v.setVisibility(View.VISIBLE);
        }, views);

    }

    // keep views means setting to invisibile so that view can be animated back in, but it still takes space
    public static AnimatorListenerAdapter onOutAnimationEnd(boolean keepViews, View... views){
            return new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if(!keepViews) {
                        for (View v : views) {
                            v.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    super.onAnimationCancel(animation);

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


    public static void animateIn(InAnimation inAnimation    , View... views){
        for (View v : views){
                inAnimation.animateIn(v);
        }
    }

    interface InAnimation{
        void animateIn(View v );
    }

// end methods for android view animation library
}
