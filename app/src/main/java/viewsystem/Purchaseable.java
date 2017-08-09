package viewsystem;

/**
 * Created by Jordan on 8/7/2017.
 */

public interface Purchaseable {
    void purchaseSuccessful();
    void onIsPurchasedResultReceived(boolean isPurchased);
}
