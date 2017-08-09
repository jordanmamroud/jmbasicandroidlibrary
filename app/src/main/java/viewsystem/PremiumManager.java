package viewsystem;

import android.content.Context;

/**
 * Created by Jordan on 8/9/2017.
 */

public class PremiumManager extends PurchaseManager {

    private static final String SKU_PREMIUM = "premium";
    public static String IS_PREMIUM_KEY = "isPremium" ;
    private boolean isPremium = false ;
    private Context context;

    public PremiumManager(Context context, Purchaseable purchaseable) {
        super(context, purchaseable);
        this.context = context ;
    }

    public void savePurchasedStatusOffline(Context context ) {  super.savePurchasedStatusOffline(     context,    IS_PREMIUM_KEY  );        }

    public void connnect(IPurchaseSystem upgradeConnection ) {
        super.connnect(upgradeConnection, SKU_PREMIUM   , IS_PREMIUM_KEY   );
    }

    public boolean isPremium() { return isPremium; }

    public void setPremium(boolean premium) { isPremium = premium; }

}
