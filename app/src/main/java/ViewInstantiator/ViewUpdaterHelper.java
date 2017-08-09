package ViewInstantiator;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.jordan.jmbasicandroidlibrary.R;

import functionalinterfaces.Upgradeable;
import utilities.ViewHelper;

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
    private Upgradeable upgradeable ;

    public ViewUpdaterHelper(Context mContext,  Upgradeable upgradeable ,ViewInstantiatorHelper viewInstantiatorHelper) {
        this.mContext = mContext;
        this.viewInstantiatorHelper = viewInstantiatorHelper;
        this.upgradeable = upgradeable ;
    }

    public void requestUsersPremiumStatusAsync( ){  viewInstantiatorHelper.startSetup(  getSetupFinishedListener()  );   }
    
    private ViewInstantiatorHelper.OnIabSetupFinishedListener getSetupFinishedListener(){
        return (PIabResult result)  ->  {
            if (    result.isSuccess() ) checkIfUserHasMadePurchase();
            else handleSetupError();
        };
    }
    
    private void handleSetupError(){ showMessage(mContext.getString(   R.string.unableToConnectMsg    ));  }
    
    private void checkIfUserHasMadePurchase(){
        try {
            viewInstantiatorHelper.queryInventoryAsync(     getInventoryQueryFinishedListener()   )  ;
        }catch (Exception e){
            showMessage(mContext.getString( R.string.queryErrorMsg   ));
        }
    }
    
    private ViewInstantiatorHelper.QueryInventoryFinishedListener getInventoryQueryFinishedListener() {
        return (PIabResult result, PInventory inventory) -> {
            if (result.isFailure()) handleInventoryQueryError();

            else postUpgradeStatus(  inventory.hasPurchase( SKU_PREMIUM  )    );
        };
    }
    
    private void handleInventoryQueryError(){   ViewHelper.showMessage(mContext ,mContext.getString(R.string.queryErrorMsg), Toast.LENGTH_SHORT);   }

    public void purchaseAttempt(){
        // no problem if it runs even if prevous attempt is not running . 
        clearAsyncPurchaseAttempt();
        // sets listener for after purchase attempt is done  run in launch purchase flow;

        try {
            viewInstantiatorHelper.launchPurchaseFlow((Activity) mContext   , SKU_PREMIUM   ,
                    10001,  getPuchaseFinishedListener()    ,   "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ"    );
        }catch (Exception e){
            handlePurchaseAttemptError();
        }
    }

    private ViewInstantiatorHelper.OnIabPurchaseFinishedListener getPuchaseFinishedListener(){
        return (PIabResult result, PPurchases info)->{
            if (      isSucessful(info)      ) postPurchaseSuccessfulEvent();
            
            else if(    isFailureProbalySinceNotLoggedIn(   result )    ) showMessage(  mContext.getString(R.string.askToLoginMsg)   );
            
            clearAsyncPurchaseAttempt();
        };
    }

    private void handlePurchaseAttemptError(){  showMessage( mContext.getString(R.string.purchaseFailedErrorMsg))       ;   }

    private void postPurchaseSuccessfulEvent(){     upgradeable.purchaseSuccessful();   }

    private void postUpgradeStatus(boolean status   ){      upgradeable.onIsPurchasedResultReceived(status);    }

    private void showMessage(String msg){ Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();}

    private boolean isSucessful(PPurchases info){ return info.getSku().equals(SKU_PREMIUM) ;    }

    private boolean isFailureProbalySinceNotLoggedIn(PIabResult result) { return  result.getResponse() == BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE ; }

    private void clearAsyncPurchaseAttempt(){  viewInstantiatorHelper.flagEndAsync();  }

 }
