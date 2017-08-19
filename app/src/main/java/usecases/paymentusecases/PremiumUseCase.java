package usecases.paymentusecases;

import android.content.Context;

import viewsystem.PremiumManager;
import viewsystem.Purchaseable;
import viewsystem.ViewInstantiator.ViewInstantiatorHelper;

/**
 * Created by Jordan on 8/16/2017.
 */

public abstract class PremiumUseCase implements IPaymentsUseCase {

    private PremiumManager premiumManager;
    private Context context ;
    private Purchaseable purchaseable ;

    public PremiumUseCase(Context context ) {
        this.context = context;
    }

    public abstract String getApiKey() ;

    @Override
    public void subscribeToPaymentEvents(Purchaseable purchaseable) {
        this.purchaseable = purchaseable  ;
    }

    public PremiumUseCase setup(){
        if(premiumManager == null) premiumManager = new PremiumManager(context  , handlePurchaseEvents()   , new ViewInstantiatorHelper(  context   , getApiKey() )   );
        return  this;
    }

    public void fetchPurchaseStatus(){
        if(premiumManager == null)setup() ;
        premiumManager.connnect();
    }

    public Purchaseable handlePurchaseEvents(){
        return new Purchaseable() {
            @Override
            public void purchaseSuccessful() {
                purchaseable.purchaseSuccessful();
                setToPremium();
            }

            @Override
            public void onPurchasedResultReceived(boolean isPurchased) {
                purchaseable.onPurchasedResultReceived(isPurchased);
                if( isPurchased ) setToPremium();
            }
        };
    }

    public void upgradeAttempt(){
        if(premiumManager == null)setup() ;
        premiumManager.upgradeAttempt();
    }

    private void setToPremium(){
//        vm.updateObserverFragments(true);
        premiumManager.savePurchasedStatusOffline(  context );
    }

    public void unsubscribe(){  premiumManager.destroy();   }

}
