package view.Animations.layoutflip;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.jordan.jmbasicandroidlibrary.R;


/**
 * Created by Jordan on 7/22/2017.
 */

public class LayoutFlipController {

    private Context mContext ;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private View frontLayout ;
    private View backLayout ;

    private boolean mIsBackVisible ;
    private boolean mIsRunning ;


    public LayoutFlipController(Context mContext ) {
        this.mContext = mContext ;
        loadAnimations();
    }

    public LayoutFlipController setupLayoutsToFlip(View frontLayout , View backLayout){
        this.frontLayout = frontLayout ;
        this.backLayout = backLayout ;
        // if this is not done + hardware accelerated in manifest then flip animation will flicker back views.
        frontLayout.setLayerType(View.LAYER_TYPE_HARDWARE,  null);
        backLayout.setLayerType(View.LAYER_TYPE_HARDWARE,   null);

        int distance = 8000;
        float scale = mContext.getResources().getDisplayMetrics().density * distance;
        frontLayout.setCameraDistance(scale);
        backLayout.setCameraDistance(scale);
        return this ;
    }

    public void loadAnimations(){
        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.in_animation);
        mSetRightOut.addListener(getAnimationListener());
    }

    public AnimatorListenerAdapter getAnimationListener(){
        return new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) { mIsRunning = false ;}
            @Override
            public void onAnimationEnd(Animator animation) {
                mIsRunning = false;
            }

            @Override
            public void onAnimationStart(Animator animation) {

            }
        };
    }

    public void flipIfNotFlipping(@Nullable Runnable onFlipStartlistener){
        if( !   mIsRunning )
            flipLayouts(onFlipStartlistener);
    }

    private void flipLayouts(Runnable onFlipStartlistener){
        mIsRunning = false ;
        if(onFlipStartlistener != null )
            onFlipStartlistener.run();
        // passing in oppositite of what it is because thats what it will be .
        if (!mIsBackVisible) {
            flipToBack();
        } else {
            flipToFront();
        }
    }

    private void flipToBack(){
        mIsBackVisible = true;
        backLayout.bringToFront();
        backLayout.setVisibility(View.VISIBLE);
        mSetRightOut.setTarget(frontLayout);
        mSetLeftIn.setTarget(backLayout);
        mSetRightOut.start();
        mSetLeftIn.start();
    }

    private void flipToFront(){
        frontLayout.setVisibility(View.VISIBLE);
        mIsBackVisible = false;
        frontLayout.bringToFront();
        mSetRightOut.setTarget(backLayout);
        mSetLeftIn.setTarget(frontLayout);
        mSetRightOut.start();
        mSetLeftIn.start();
    }

    public void cancelAnimations(){
        if(mSetRightOut != null  && mSetLeftIn != null ){
            mSetRightOut.cancel();
            mSetLeftIn.cancel();
        }
    }

    public void saveState(Bundle outState){
        outState.putBoolean("isBackShowing", isFlipped()   );
    }

    public void restoreFlippedState(Bundle savedInstanceState){
        if(savedInstanceState.getBoolean("isBackShowing")   ) restoreBackLayout();
    }

    private void restoreBackLayout(){
        frontLayout.setVisibility(View.INVISIBLE);
        backLayout.setVisibility(View.VISIBLE);
        backLayout.bringToFront();
        setIsBackVisible(true);
    }

    public boolean isFlipped() {    return mIsBackVisible;  }

    public void setIsBackVisible(boolean visible){ this.mIsBackVisible = visible ;}

    public void setAnimationListener(AnimatorListenerAdapter animationListener){ mSetLeftIn.addListener( animationListener   ); }


}
