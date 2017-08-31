package domain.viewsystem.ViewInstantiator;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.example.jordan.jmbasicandroidlibrary.R;

import domain.viewsystem.Purchaseable;
import utilities.ViewHelper;
import domain.viewsystem.IPurchaseSystem;

import static domain.viewsystem.ViewInstantiator.ViewInstantiatorHelper.BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE;

/**
 * Created by Jordan on 7/17/2017.
 */
// role is to
public class ViewUpdaterHelper implements IPurchaseSystem {

    private Context mContext ;
    private ViewInstantiatorHelper viewInstantiatorHelper;
    private Purchaseable purchaseable;
    private String purchaseToQuery ;

    public ViewUpdaterHelper(Context mContext, Purchaseable purchaseable, ViewInstantiatorHelper viewInstantiatorHelper) {
        this.mContext = mContext;
        this.viewInstantiatorHelper = viewInstantiatorHelper;
        this.purchaseable = purchaseable ;
    }

    @Override
    public void requestPurchasedStatusAsync(    String purchaseKey  ) {
        this.purchaseToQuery = purchaseKey;
        viewInstantiatorHelper.startSetup(  this    ::  handleSetupResult   );
    }

    private void handleSetupResult(PIabResult result){
        if (    result.isSuccess() ) checkIfUserHasMadePurchase();
        else handleSetupError();
    }

    private void checkIfUserHasMadePurchase(){
        try {
            viewInstantiatorHelper.queryInventoryAsync(     this :: handlePurchaseQueryCompleted      )  ;
        }catch (Exception e){
            showMessage(mContext.getString( R.string.queryErrorMsg   ));
        }
    }

    private void handleSetupError(){ showMessage(mContext.getString(   R.string.unableToConnectMsg    ));  }

    private void handlePurchaseQueryCompleted(PIabResult result, PInventory inventory){
        if (result.isFailure()) handleInventoryQueryError();
        else postUpgradeStatus(  inventory.hasPurchase( purchaseToQuery  )    );
    }

    private void handleInventoryQueryError(){   ViewHelper.showMessage(mContext ,mContext.getString(R.string.queryErrorMsg), Toast.LENGTH_SHORT);   }

    public void purchaseAttempt(){
        // no problem if it runs even if prevous attempt is not running . 
        clearAsyncPurchaseAttempt();
        // sets listener for after purchase attempt is done  run in launch purchase flow;

        try {
            viewInstantiatorHelper.launchPurchaseFlow((Activity) mContext   , purchaseToQuery   ,
                    10001,  onPurchaseFlowCompleted()    ,   "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ"    );
        }catch (Exception e){
            handlePurchaseAttemptError();
        }
    }

    private ViewInstantiatorHelper.OnIabPurchaseFinishedListener onPurchaseFlowCompleted(){
        return (PIabResult result, PPurchases info)->{
            if (      isSucessful(info)      ) postPurchaseSuccessfulEvent();
            
            else if(    isFailureProbalySinceNotLoggedIn(   result )    ) showMessage(  mContext.getString(R.string.askToLoginMsg)   );
            
            clearAsyncPurchaseAttempt();
        };
    }



    public void destroy(){  viewInstantiatorHelper.disposeEvenIfAsyncRunning(); }

    private void handlePurchaseAttemptError(){  showMessage( mContext.getString(R.string.purchaseFailedErrorMsg))       ;   }

    private void postPurchaseSuccessfulEvent(){     purchaseable.purchaseSuccessful();   }

    private void postUpgradeStatus(boolean status   ){
        purchaseable.onPurchasedResultReceived(status);

    }

    private void showMessage(String msg){ Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();}

    private boolean isSucessful(PPurchases info){ return info.getSku().equals(purchaseToQuery) ;    }

    private boolean isFailureProbalySinceNotLoggedIn(PIabResult result) { return  result.getResponse() == BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE ; }

    private void clearAsyncPurchaseAttempt(){  viewInstantiatorHelper.flagEndAsync();  }

 }
