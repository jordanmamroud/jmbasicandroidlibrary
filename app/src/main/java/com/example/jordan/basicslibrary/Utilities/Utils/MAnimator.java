package com.example.jordan.basicslibrary.Utilities.Utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;

import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Rotate;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;


/**
 * Created by Jordan on 5/30/2017.
 */

public class MAnimator {

    public static void startAnimation(Context context , View viewToAnimate,int animationId ){
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


    public static void slideFromLeft(ViewGroup view , int duration){
        Slide slide = new Slide(Gravity.RIGHT);
        slide.setDuration(duration);

        TransitionManager.beginDelayedTransition(   view  , slide  );

    }

    public static void slideFromRight(ViewGroup view , int duration){
        Slide slide = new Slide(Gravity.LEFT);
        slide.setDuration(150);
        TransitionManager.beginDelayedTransition(   view  , slide  );
    }

    public static void rotate(View viewToAnimate, boolean isRotated){
        TransitionManager.beginDelayedTransition((ViewGroup) viewToAnimate.getParent() , new Rotate());
        viewToAnimate.setRotation(isRotated ? 135 : 0);
    }

    public static void toggleFade( View... viewsToFade){
        for (View v : viewsToFade) {
            beginTransition(v , 0 );
        }
    }

    public static void toggleFade(@Nullable int duration, View... viewsToFade){

        for (View v : viewsToFade) {
            beginTransition(v , duration );
        }

    }


    // only used in this class but is static because static methods can only use static methods
    private static void beginTransition(View v , int duration ){
        // creates a bubble toggleFade effect

        boolean visible = MHelper.isViewVisible(v);

        TransitionSet set = new TransitionSet()
                .addTransition(new Scale(0.7f))
                .addTransition(new Fade())
                .setInterpolator(visible ?  new FastOutLinearInInterpolator() : new LinearOutSlowInInterpolator());

        if(duration != 0){
            set.setDuration(duration);
        }
        TransitionManager.beginDelayedTransition((ViewGroup) v.getParent()  , set);
        v.setVisibility(visible ? View.INVISIBLE : View.VISIBLE );
    }
}
