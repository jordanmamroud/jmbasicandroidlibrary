package CustomViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jordan.jmbasicandroidlibrary.R;


/**
 * Created by Jordan on 5/30/2017.
 */

public class MProgressBar extends RelativeLayout {

    ProgressBar progressBar ;
    TextView progressTxtView ;
    TextView progressBarLabel ;
    String progressTxt ;
    int progress ;

    // do not work colors are controlled in colors.xml for simplicity
    int fillColor = 0;
    // will be transaparent if not set
    int emptyColor = 0;

    public MProgressBar(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MProgressBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a  = context.obtainStyledAttributes(attrs, R.styleable.MProgressBar);
        init(context);
    }

    public MProgressBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.cv_progressbar, this);

        progressBar = (ProgressBar) findViewById(R.id.progress_br);
        progressTxtView = (TextView)  findViewById(R.id.progress_txt);
        progressBarLabel = (TextView) findViewById(R.id.progressBarLabel);

    }

    public void setProgress(int progress, @Nullable String progressLabel){
        this.progress = progress ;
        progressBar.setProgress(progress);

        if(progressLabel !=null){
            progressBarLabel.setText(progressLabel);
        }else {
            progressBarLabel.setVisibility(GONE);
        }
    }

    public void setProgressTxt(String txt, boolean alignRight ){
        if(alignRight) {
            LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) progressTxtView.getLayoutParams();
            p.weight = progress;
        }
        progressTxtView.setText(    txt );
    }



    public TextView getProgressBarLabel() {
        return progressBarLabel;
    }

    public void setProgressBarLabel(TextView progressBarLabel) {
        this.progressBarLabel = progressBarLabel;
    }


}
