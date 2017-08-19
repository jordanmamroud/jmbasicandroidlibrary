package viewsystem;

/**
 * Created by Jordan on 8/7/2017.
 */

public interface Purchaseable {
    void purchaseSuccessful();
    void onPurchasedResultReceived(boolean isPurchased);
}
