package activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.Observer;

/**
 * Created by Jordan on 8/7/2017.
 */

public abstract class JFragmentActivity extends AppCompatActivity {

    boolean isRestarted ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isRestarted = savedInstanceState != null     ;
    }

    public abstract void launchStartFragment();

    public void updateObserverFragments(Object object){
        for(Fragment fragment: getSupportFragmentManager().getFragments()) {
            if (isObserver(fragment)) {
                ((Observer) fragment).update(null, object);
            }
        }
    }

    public void showMessage(String message){    Toast.makeText(JFragmentActivity.this, message, Toast.LENGTH_SHORT).show(); }

    private boolean isObserver(Fragment fragment ){ return fragment instanceof Observer ; }

    public boolean isRestarted(){ return isRestarted ;}
}
