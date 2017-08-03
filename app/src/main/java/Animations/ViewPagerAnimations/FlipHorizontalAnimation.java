package Animations.ViewPagerAnimations;

/**
 * Created by Jordan on 7/10/2017.
 */
import android.view.View;


public class FlipHorizontalAnimation extends BaseTransformer {

    @Override
    protected void onTransform(View view, float position) {

        final float rotation = 180f * position;

        view.setVisibility(rotation > 90f || rotation < -90f ? View.INVISIBLE : View.VISIBLE);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setRotationY(rotation);
    }

}