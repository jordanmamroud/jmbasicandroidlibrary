package domain.viewsystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.jordan.jmbasicandroidlibrary.R;

import utilities.ResourcesHelper;
import domain.viewsystem.ViewInstantiator.ViewInstantiatorHelper;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Jordan on 8/7/2017.
 */

public class PurchaseManager {

    private Purchaseable purchaseable;
    private IPurchaseSystem purchaseSystem;
    private ViewInstantiatorHelper viewInstantiatorHelper ;
    private Context context ;

    public PurchaseManager( Context context, Purchaseable purchaseable ) {
        this.purchaseable = purchaseable;
        this.context = context;
    }

    public void setPurchaseSystem(IPurchaseSystem iPurchaseSystem){ this.purchaseSystem = iPurchaseSystem ;  }

    public void upgradeAttempt(){
        if(isOnline()) {
            purchaseSystem.purchaseAttempt();
        }else {
            Toast.makeText(context , context.getString(R.string.noInternetWhenUpgradingMsg) , Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean isOnline()	{    return ResourcesHelper.isOnline(context);  }

    public void connnect( String purchaseKey){  requestStatus(purchaseKey );    }

    public void requestStatus(String purchaseKey){
        if(isOnline()) {
            requestStatusFromServerAsync(   purchaseKey    );
        }
        else if(isStoredLocally(purchaseKey)){  checkPurchaseStatusLocally(  purchaseKey   );      }
    }

    private boolean isStoredLocally(String localKey){   return localKey !=   null ; }

    public void checkPurchaseStatusLocally(String purchaseKey ){
        SharedPreferences prefs = context.getSharedPreferences(purchaseKey   , MODE_PRIVATE);
        boolean isPurchased =   prefs.getBoolean(   purchaseKey , false  );
        purchaseable.onPurchasedResultReceived(    isPurchased    );
    }

    private void requestStatusFromServerAsync(String purchaseKey){  purchaseSystem.requestPurchasedStatusAsync( purchaseKey   );     }

    public void savePurchasedStatusOffline(Context context, String purchaseKey){
        SharedPreferences.Editor editor = context.getSharedPreferences( purchaseKey    , MODE_PRIVATE    ).edit();
        editor.putBoolean(purchaseKey , true );
        editor.apply();
    }

    public void destroy(){      purchaseSystem.destroy();     }

}
