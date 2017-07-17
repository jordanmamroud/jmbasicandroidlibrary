package GlideHelper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Jordan on 7/13/2017.
 */

public class GlideLayoutTarget extends ViewGroupTarget  {

    private Context context;
    // defaulting to true , if not from resource using bitmap.
    private boolean isFromResource = true;


    public GlideLayoutTarget(Context context, LinearLayout linearLayout) {

        super(linearLayout);

        this.context = context;
    }

    public GlideLayoutTarget(Context context, ViewGroup linearLayout, boolean isfromResource) {
        super(linearLayout);

        this.context = context;
        this.isFromResource = isfromResource ;
    }


    @Override
    protected void setResource(Object resource) {
        // if from resource cast to drawable.
        if(isFromResource){
            view.setBackground((Drawable) resource );
        }
        // casts object to bitmap if not a resource from drawable
        else{
            view.setBackground(new BitmapDrawable(context.getResources(),(Bitmap) resource));
        }

    }
}
