package  view.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jordan.jmbasicandroidlibrary.R;


/**
 * Created by Jordan on 8/11/2017.
 */

public class PictureView extends RelativeLayout{

    private View  frontCardBg   ;
    private ImageView   itemImg ;
    private Context context ;

    public PictureView(Context context) {
        super(context);
        init(context);
    }

    public PictureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PictureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init (Context context){
        this.context = context ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.component_picture_view , this , true);
        frontCardBg = findViewById(R.id.frontCardBg);
        itemImg = (ImageView) findViewById(R.id.itemImg);
    }

    public ImageView getMainImageView(){
        return itemImg ;
     }

    public void setBackgroundImg(int backgroundImg) {
        frontCardBg.setBackgroundResource( backgroundImg );
    }
}
