package Animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

import Animations.avm.BaseViewAnimator;

/**
 * Created by Jordan on 7/29/2017.
 */
// key methods are animateIn() and animateOut()
public class AnimationBuilder {

    BaseViewAnimator animator;
    View[] viewsToAnimate ;
    boolean keepViewsInLayout = true    ;
    AnimatorListenerAdapter eventListener   ;
    int duration = 0 ;
    boolean isInAnimation ;

    public AnimationBuilder(BaseViewAnimator animator) { this.animator = animator; }

    public void animateIn(){         animate( true  );           }

    private void prepareSingleInAnimationView(View viewToPrep){
        animator.prepare(   viewToPrep ) ;
        viewToPrep.setClickable(true);
        viewToPrep.setVisibility(View.VISIBLE)   ;
    }

    private AnimatorListenerAdapter getDefaultInAnimationEvents(){
        return null ;
    }

    public void animateOut(){    animate( false  );  }

    private void prepareSingleOutAnimationView(View viewToPrep){
        viewToPrep.setClickable(    false   );
        animator.prepare(   viewToPrep );
    }

    private AnimatorListenerAdapter getDefaultOutAnimationEvents( ) {
        return new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {    if (!keepViewsInLayout) removeViewsFromLayout();    }

            @Override
            public void onAnimationCancel(Animator animation) {     if (!keepViewsInLayout) removeViewsFromLayout();        }
        };
    }

    private void removeViewsFromLayout(){
        for (View v : viewsToAnimate)     if(!keepViewsInLayout) v.setVisibility(View.GONE)   ;
    }

    // shared methods

    private void animate(boolean isInAnimation){
        this.isInAnimation = isInAnimation ;
        prepareAnimation();
        startAnimation();
    }

    public void startAnimation(){ animator.animate(); }


    private void prepareAnimation(){
        if( duration != 0     )     animator.setDuration(duration) ;
        addEventListener(  eventListener  );
        prepareViews();
    }

    private void prepareViews(){
        if( isInAnimation){
            for(    View viewToPrep : viewsToAnimate )  prepareSingleInAnimationView( viewToPrep );
        }else {
            for(    View viewToPrep : viewsToAnimate )  prepareSingleOutAnimationView( viewToPrep );
        }
    }

    private void addEventListener(AnimatorListenerAdapter listener){
        this.eventListener = isInAnimation ? getDefaultInAnimationEvents() : getDefaultOutAnimationEvents() ;
        if (    useDefaultAnimationEvents()  ) {
            this.eventListener = listener ;
            if(eventListener != null )   animator.addAnimatorListener(listener);
        }
    }

    private boolean useDefaultAnimationEvents() { return eventListener == null;  }

    // getters / setters

    public AnimationBuilder dontKeepViewsInLayout() {
        this.keepViewsInLayout = false;
        return this;
    }

    public AnimationBuilder setViewsToAnimate(View... viewsToAnimate) {
        this.viewsToAnimate = viewsToAnimate;
        return this;
    }

    public AnimationBuilder setEventListener(AnimatorListenerAdapter eventListener) {
        this.eventListener = eventListener;
        return this ;
    }

    public AnimationBuilder setDuration(int duration) {
        this.duration = duration;
        return  this ;
    }

    public void setAnimator(BaseViewAnimator animator) { this.animator = animator; }

}
