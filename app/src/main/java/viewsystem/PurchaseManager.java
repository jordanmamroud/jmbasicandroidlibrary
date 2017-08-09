package viewsystem;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.jordan.jmbasicandroidlibrary.R;

import utilities.ResourcesHelper;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Jordan on 8/7/2017.
 */

public class PurchaseManager {

    private Purchaseable purchaseable;
    private IPurchaseSystem purchaseSystem;
    private Context context ;

    public PurchaseManager(Context context, Purchaseable purchaseable) {
        this.purchaseable = purchaseable;
        this.context = context;
    }

    public void requestStatus(String dbKey , String localKey){
        if(isOnline()) {
            requestStatusFromServerAsync(   dbKey    );
        }else {
            if(localKey!=null)  checkPurchaseStatusLocally(  localKey   );
        }
    }

    private void requestStatusFromServerAsync(String purchaseKey){ purchaseSystem.requestPurchasedStatusAsync( purchaseKey   ); }

    private void checkPurchaseStatusLocally(String purchaseKey ){
        SharedPreferences prefs = context.getSharedPreferences(purchaseKey   , MODE_PRIVATE);
        boolean isPurchased =   prefs.getBoolean(   purchaseKey , false  );
        purchaseable.onIsPurchasedResultReceived(    isPurchased    );
    }

    public void savePurchasedStatusOffline(Context context, String purchaseKey){
        SharedPreferences.Editor editor = context.getSharedPreferences( purchaseKey    , MODE_PRIVATE    ).edit();
        editor.putBoolean(purchaseKey , true );
        editor.apply();
    }

    public void upgradeAttempt(){
        if(isOnline()) {
            purchaseSystem.purchaseAttempt();
        }else {
            Toast.makeText(context , context.getString(R.string.noInternetWhenUpgradingMsg) , Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean isOnline()	{    return ResourcesHelper.isOnline(context);  }

    public void connnect(IPurchaseSystem upgradeConnection, String dbKey, String localKey ){
        this.purchaseSystem = upgradeConnection ;
        requestStatus(dbKey , localKey);
    }


    public void destroy(){      purchaseSystem.destroy();     }


}
