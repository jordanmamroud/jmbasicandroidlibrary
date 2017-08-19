package viewsystem;

import android.content.Context;

import viewsystem.ViewInstantiator.ViewInstantiatorHelper;
import viewsystem.ViewInstantiator.ViewUpdaterHelper;

/**
 * Created by Jordan on 8/9/2017.
 */
//todo this and parent need work
public class PremiumManager extends PurchaseManager {

    private static final String SKU_PREMIUM = "premium";
    private boolean isPremium = false ;
    private Context context;

    public PremiumManager(Context context, Purchaseable purchaseable, ViewInstantiatorHelper viewInstantiatorHelper) {
        super(context, purchaseable);
        this.context = context ;
        setPurchaseSystem( new ViewUpdaterHelper(   context    , purchaseable , viewInstantiatorHelper )    );
    }

    public void savePurchasedStatusOffline(Context context ) {  super.savePurchasedStatusOffline(     context,    SKU_PREMIUM  );        }

    public void connnect() {    super.connnect(  SKU_PREMIUM    );  }

    public boolean isPremium() { return isPremium; }

    public void setPremium(boolean premium) { isPremium = premium; }

}
