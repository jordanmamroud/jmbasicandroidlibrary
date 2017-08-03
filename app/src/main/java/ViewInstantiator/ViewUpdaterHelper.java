package ViewInstantiator;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.jordan.basicslibrary.Utilities.Utils.ViewHelper;

import org.greenrobot.eventbus.EventBus;

import mevents.CommonEvents;

import static ViewInstantiator.ViewInstantiatorHelper.BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE;

/**
 * Created by Jordan on 7/17/2017.
 */

public class ViewUpdaterHelper {
    private Context mContext ;
    private ViewInstantiatorHelper viewInstantiatorHelper;
    private static final String SKU_PREMIUM = "premium";

    public ViewUpdaterHelper(Context mContext, ViewInstantiatorHelper viewInstantiatorHelper) {
        this.mContext = mContext;
        this.viewInstantiatorHelper = viewInstantiatorHelper;
    }

    public void requestUsersPremiumStatusAsync( ){  viewInstantiatorHelper.startSetup(  getSetupFinishedListener()  );   }
    
    private ViewInstantiatorHelper.OnIabSetupFinishedListener getSetupFinishedListener(){
        return (PIabResult result)  ->  {
            if (    !   result.isSuccess() ) handleSetupError(   result );

            else checkIfUserHasMadePurchase();
        };
    }
    
    private void handleSetupError(PIabResult result){   ViewHelper.showMessage(mContext , "Sorry we are not able to connect to your google account", Toast.LENGTH_SHORT );  }
    
    private void checkIfUserHasMadePurchase(){
        try {
            viewInstantiatorHelper.queryInventoryAsync(     getInventoryQueryFinishedListener()   )  ;
        }catch (Exception e){
            Log.d("ViewUpdaterHelper", "there was a error checking status");
        }
    }
    
    private ViewInstantiatorHelper.QueryInventoryFinishedListener getInventoryQueryFinishedListener() {
        return (PIabResult result, PInventory inventory) -> {
            if (result.isFailure()) handleInventoryQueryError();

            else postUpgradeStatus(  inventory.hasPurchase(SKU_PREMIUM)    );
        };
    }
    
    private void handleInventoryQueryError(){
        Log.d("viewUpdaterHelper", "there was a error checking status");
        ViewHelper.showMessage(mContext , " Sorry we are unable to connect please try again later", Toast.LENGTH_SHORT);
    }

    public void purchaseAttempt(){
        // no problem if it runs even if prevous attempt is not running . 
        clearAsyncPurchaseAttempt();
        // just listener for after purchase attempt is done  run in launch purchase flow;
         
        try {
            viewInstantiatorHelper.launchPurchaseFlow((Activity) mContext   , SKU_PREMIUM   ,    10001, getPuchaseFinishedListener()    ,   "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ"    );
        }catch (Exception e){
            handlePurchaseAttemptError();
        }
    }
    
    private ViewInstantiatorHelper.OnIabPurchaseFinishedListener getPuchaseFinishedListener(){
        return (PIabResult result, PPurchases info)->{
            if (      isSucessful(info)      ) postPurchaseSuccessfulEvent();
            
            else if(    isFailureProbalySinceNotLoggedIn(   result )    ) showAskToLoginMessage();
            
            clearAsyncPurchaseAttempt();
        };
    }

    private void postPurchaseSuccessfulEvent(){
        postUpgradeStatus(true);
        ViewHelper.showMessage(mContext, "Congratulations on your upgrade" , Toast.LENGTH_SHORT)    ;
    }

    private void postUpgradeStatus(boolean status   ){EventBus.getDefault().post(new CommonEvents.UpgradeStatus(status));    }

    private void handlePurchaseAttemptError(){
        Toast.makeText(mContext , " sorry we are unable to make purchases at this time", Toast.LENGTH_SHORT).show();
        Log.e("viewupdaterhelper", "sorry there with the attempt to upgrade ");
    }

    private void showAskToLoginMessage(){ Toast.makeText(mContext, "Please sign into your google account to upgrade " , Toast.LENGTH_LONG).show();  }

    private boolean isSucessful(PPurchases info){ return info.getSku().equals(SKU_PREMIUM) ;    }

    private boolean isFailureProbalySinceNotLoggedIn(PIabResult result) { return  result.getResponse() == BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE ; }

    private void clearAsyncPurchaseAttempt(){  viewInstantiatorHelper.flagEndAsync();  }

 }
