package activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

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
        EventBus.getDefault().register(this);
        isRestarted = savedInstanceState != null     ;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(! EventBus.getDefault().isRegistered(this)   ) EventBus.getDefault().register(this);

    }

    public abstract void launchStartFragment();

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void updateObserverFragments(Object object){
        for(Fragment fragment: getSupportFragmentManager().getFragments()) {
            if (isObserver(fragment)) {
                ((Observer) fragment).update(null, object);
            }
        }
    }

    private boolean isObserver(Fragment fragment ){ return fragment instanceof Observer ; }

    public boolean isRestarted(){ return isRestarted ;}
}
