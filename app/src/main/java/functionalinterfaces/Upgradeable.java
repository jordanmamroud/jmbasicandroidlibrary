package functionalinterfaces;

/**
 * Created by Jordan on 8/7/2017.
 */

public interface Upgradeable {
    void purchaseSuccessful();
    void onIsPurchasedResultReceived(boolean isPurchased);
}
