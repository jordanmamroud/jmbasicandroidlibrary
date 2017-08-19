package viewsystem;

import android.content.Context;

/**
 * Created by Jordan on 8/9/2017.
 */

public interface IPurchaseSystem {

    void requestPurchasedStatusAsync(String purchaseKey)    ;
    void purchaseAttempt()  ;
    void destroy();

}
