package Animations.layoutflip;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
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
    private IOnFlipToFront  iOnFlipToFront ;
    private IOnFlipToBack iOnFlipToBack ;

    public LayoutFlipController(Context mContext, View frontLayout , View backLayout) {
        this.frontLayout = frontLayout ;
        this.backLayout = backLayout ;
        this.mContext = mContext ;
        // if this is not done + hardware accelerated in manifest then flip animation will flicker back views.
        frontLayout.setLayerType(View.LAYER_TYPE_HARDWARE,  null);
        backLayout.setLayerType(View.LAYER_TYPE_HARDWARE,   null);
        loadAnimations();
        setupFlipLayouts();
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

    public void setupFlipLayouts(){
        int distance = 8000;
        float scale = mContext.getResources().getDisplayMetrics().density * distance;
        frontLayout.setCameraDistance(scale);
        backLayout.setCameraDistance(scale);
    }

    public void flipIfNotFlipping(){     if( !   mIsRunning ) flipLayouts();        }

    private void flipLayouts(){
        mIsRunning = false ;
        // passing in oppositite of what it is because thats what it will be .
        if (!mIsBackVisible) {
            if (iOnFlipToBack != null) iOnFlipToBack.startFlip();
            flipToBack();
        } else {
            if (iOnFlipToFront != null) iOnFlipToFront.startFlip();
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

    public boolean isIsBackVisible() {
        return mIsBackVisible;
    }

    public void setIsBackVisible(boolean visible){ this.mIsBackVisible = visible ;}

    public LayoutFlipController setOnFlipToFrontListener(IOnFlipToFront listener){
        this.iOnFlipToFront = listener;
        return this ;
    }

    public LayoutFlipController setOnFlipToBackListener(IOnFlipToBack listener){
        this.iOnFlipToBack = listener;
        return this;
    }

    public void setAnimationListener(AnimatorListenerAdapter animationListener){ mSetLeftIn.addListener( animationListener   ); }

    public interface IOnFlipToFront{ void startFlip ();  }

    public interface IOnFlipToBack { void startFlip();  }

}
