package CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.jordan.jmbasicandroidlibrary.R;

import utilities.ViewHelper;

/**
 * Created by Jordan on 8/11/2017.
 */

public class LockedLayout extends RelativeLayout{

    private Context context;
    private Button upgradeBtn ;
    private ImageView lockedImg ;

    public LockedLayout(Context context) {
        super(context);
        init(context);
    }

    public LockedLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LockedLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public void init ( Context context){
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cv_locked_upgrade    , this , true);
        upgradeBtn = (Button) findViewById(R.id.upgradeBtn);
        lockedImg = (ImageView) findViewById(R.id.lockImg);
    }

    public void showLock(){ setView(View.VISIBLE); }

    public void removeLock(){
        setView(View.GONE);
    }

    private void setView(int visibility){ViewHelper.setVisibility( visibility, this, upgradeBtn, lockedImg);}

    public void setOnUpgradeBtnClick(View.OnClickListener onUpgradeBtnClick){   upgradeBtn.setOnClickListener(onUpgradeBtnClick);   }

    public void setOnLockImgClick(View.OnClickListener onLockImgClick){ lockedImg.setOnClickListener(       onLockImgClick   );     }

}
