package usecases.paymentusecases;

import viewsystem.Purchaseable;

/**
 * Created by Jordan on 8/18/2017.
 */

public interface IPaymentsUseCase {
    void unsubscribe() ;
    void subscribeToPaymentEvents(Purchaseable purchaseable);
    void upgradeAttempt() ;
    void fetchPurchaseStatus();
}
