package  view.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jordan.jmbasicandroidlibrary.R;

import java.util.LinkedHashMap;
import java.util.Map;
import utilities.StringHelper;
import view.Animations.JAnimator;
import view.Animations.viewanimationslib.fading_entrances.FadeInUpAnimator;
import view.Animations.viewanimationslib.fading_exits.FadeOutDownAnimator;

/**
 * Created by Jordan on 7/22/2017.
 */

public class InfoView extends RelativeLayout{

    private Context context;
    private ImageView   itemImgHolder ;
    private TextView infoTitle;
    private ViewGroup infoContainerLo ;
    private String title ;
    private LinkedHashMap info ;

    public InfoView(Context context ) {
        super(context);
         init(context);
    }

    public InfoView(Context context, AttributeSet attrs ) {
        super(context, attrs);
        init(context);
    }

    public InfoView(Context context, AttributeSet attrs, int defStyleAttr, Context context1) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.component_info_view , this , true);
        infoContainerLo = (ViewGroup)  findViewById(R.id.infoContainerLo);
        infoTitle = (TextView)  findViewById(R.id.infoTitle);
        itemImgHolder = (ImageView)  findViewById(R.id.itemImgHolder);
    }

    public InfoView setInformation(String title, LinkedHashMap info ){
        this.info = info ;
        this.title = title;
        setupLayout();
        return this ;
    }

    //todo remove allowing image to only be set once .
    public ImageView getMainImageView(){
        return itemImgHolder ;
    }

    private void setupLayout(){
        if( title != null   ) infoTitle.setText(StringHelper.capitalize(   title  ) ) ;
        createCardBack(info , infoContainerLo);
    }

    public void createCardBack(LinkedHashMap<String, String> info, ViewGroup container){
        createSwitchers(info,   container );
    }

    public void createSwitchers(LinkedHashMap<String, String> info, ViewGroup parentLo){
        for (Map.Entry entry : info.entrySet()) {
            ViewGroup layout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.cv_switcher, null, false   );
            TextView label = (TextView) layout.getChildAt(0);
            Button btn = (Button) layout.getChildAt(1);
            TextView factTv = (TextView) layout.getChildAt(2);
            String labelTxt = StringHelper.capitalize(entry.getKey().toString() );
            String factTxt = StringHelper.capitalize(entry.getValue().toString());

            label.setText( labelTxt );
            factTv.setText(factTxt);
            btn.setOnClickListener( v-> revealInfo(btn , factTv));
            parentLo.addView(layout);
        }
    }

    public void revealInfo(Button button , TextView textView){
        JAnimator.createAnimation(new FadeOutDownAnimator()).setDuration(300).setViewsToAnimate(button).animateOut();
        JAnimator.createAnimation( new FadeInUpAnimator()).setDuration(300).setViewsToAnimate(textView).animateIn();
    }

    public void setTitle (  String title ){ this.title = title; }

}
