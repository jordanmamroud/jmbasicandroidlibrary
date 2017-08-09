package uievents.scrollingbehaviors;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by Jordan on 6/2/2017.
 */

public class ScrollingImageBehavior extends CoordinatorLayout.Behavior {


    // this method gets call every time something to layout change ie - toolbar expaning / collapsing etc...
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof Toolbar ;
    }

    // gets called any time layout depends on returns true

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

//        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
//        int fabBottomMargin = lp.bottomMargin;
//        int distanceToScroll = child.getHeight() + fabBottomMargin;
//        float ratio = (float)dependency.getY()/(float)toolbarHeight;
//
//        child.setTranslationY(-distanceToScroll * ratio);
//

        return true ;
    }
}
