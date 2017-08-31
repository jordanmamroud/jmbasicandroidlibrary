package  view.uievents.scrollingbehaviors;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Jordan on 5/27/2017.
 */

public class ScrollingFabBehavior extends CoordinatorLayout.Behavior{

    //need to fix how we get toolbar height
    private int toolbarHeight = 50;
    private TextView label ;

    public ScrollingFabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollingFabBehavior(Context context) {
        super(context, null);
    }

    public ScrollingFabBehavior(Context context, int toolbarHeight) {
        super(context, null);
        init();

    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
            return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        if (dependency instanceof AppBarLayout) {

            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            int fabBottomMargin = lp.bottomMargin;
            int distanceToScroll = child.getHeight() + fabBottomMargin;
            float ratio = (float)dependency.getY()/(float)toolbarHeight;

            child.setTranslationY(-distanceToScroll * ratio);

        }
        return true;
    }

    public void init(){
        this.toolbarHeight = toolbarHeight;
    }


}

