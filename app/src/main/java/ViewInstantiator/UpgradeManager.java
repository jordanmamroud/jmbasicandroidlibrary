package ViewInstantiator;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.jordan.jmbasicandroidlibrary.R;

import functionalinterfaces.Upgradeable;
import utilities.ResourcesHelper;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Jordan on 8/7/2017.
 */

public class UpgradeManager {

    public static String IS_PREMIUM_KEY = "isPremium" ;
    private Upgradeable upgradeable ;
    private ViewInstantiatorHelper viewInstantiatorHelper;
    private ViewUpdaterHelper viewUpdaterHelper;
    private Context context ;
    private boolean isPremium = false ;

    public UpgradeManager (Context context, Upgradeable upgradeable) {
        this.upgradeable = upgradeable;
        this.context = context;
    }

    public void requestStatusAsync(){
        if(isOnline()) {
            viewUpdaterHelper.requestUsersPremiumStatusAsync();
        }else {
            SharedPreferences prefs = context.getSharedPreferences(IS_PREMIUM_KEY   , MODE_PRIVATE);
            isPremium = prefs.getBoolean(   IS_PREMIUM_KEY , false  );
        }
    }

    public void upgradeAttempt(){
        if(isOnline()) {
            viewUpdaterHelper.purchaseAttempt();
        }else {
            Toast.makeText(context , context.getString(R.string.noInternetWhenUpgradingMsg) , Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean isOnline()	{    return ResourcesHelper.isOnline(context);  }

    public UpgradeManager setViewInstantiator(ViewInstantiatorHelper viewInstantiatorHelper){
        this.viewInstantiatorHelper = viewInstantiatorHelper ;
        return this;
    }

    public UpgradeManager create(){
        viewUpdaterHelper = new ViewUpdaterHelper(context , upgradeable ,   viewInstantiatorHelper );
        return this ;
    }

    public void savePremiumStatusOffline(Context context){
        setPremium( true );
        SharedPreferences.Editor editor = context.getSharedPreferences( IS_PREMIUM_KEY    , MODE_PRIVATE    ).edit();
        editor.putBoolean(IS_PREMIUM_KEY , true );
        editor.apply();
    }

    public void destroy(){  viewInstantiatorHelper.disposeEvenIfAsyncRunning();   }


    public boolean isPremium() { return isPremium; }

    public void setPremium(boolean premium) { isPremium = premium; }


}
