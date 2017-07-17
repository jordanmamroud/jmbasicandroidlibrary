package ViewInstantiator;

import android.content.Context;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import Events.CommonEvents;
import ViewInstantiator.PIabResult;
import ViewInstantiator.PInventory;
import ViewInstantiator.PPurchases;
import ViewInstantiator.ViewInstantiatorHelper;

/**
 * Created by Jordan on 7/17/2017.
 */

public class ViewUpdaterHelper {
    private Context mContext ;
    private ViewInstantiatorHelper viewInstantiatorHelper;
    private static final String SKU_PREMIUM = "premium";
    boolean mIsPremium = false  ;

    public ViewUpdaterHelper(Context mContext, ViewInstantiatorHelper viewInstantiatorHelper) {
        this.mContext = mContext;
        this.viewInstantiatorHelper = viewInstantiatorHelper;
        checkPremiumStatus(false);
        System.out.println("payment helper");
    }

    public void checkPremiumStatus(boolean purchaseAttempt){
        viewInstantiatorHelper.startSetup(new ViewInstantiatorHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(PIabResult result) {
                if (!result.isSuccess()) {
                    // Oh no, there was a problem.
                    if(result.getResponse() == 3 && purchaseAttempt){
                        Toast.makeText(mContext, "Please sign into your google account to upgrade " , Toast.LENGTH_LONG).show();
                    }
                }else {
                    try {
                        viewInstantiatorHelper.queryInventoryAsync(new ViewInstantiatorHelper.QueryInventoryFinishedListener() {
                            @Override
                            public void onQueryInventoryFinished(PIabResult result, PInventory inv) {

                                if (result.isFailure()) {
                                    // handle error here
                                    System.out.println("there was a error check status");
                                }
                                else {
                                    // if upgraded all images become available
                                    // does the user have the premium upgrade?
                                    mIsPremium = inv.hasPurchase(SKU_PREMIUM);
                                    System.out.println(mIsPremium);
                                    EventBus.getDefault().post( new CommonEvents.UpgradeStatus(mIsPremium));

                                }
                            }
                        });
                    }catch (Exception e){
                        System.out.println("there was a error check status");
                    }
                }
                if(mIsPremium) {
                    try {
                        viewInstantiatorHelper.dispose();
                    } catch (Exception e) {
                        System.out.println("dispose error");
                    }
                }
            }
        });
    }

    public void purchaseAttempt(){
        viewInstantiatorHelper.flagEndAsync();
        // just listener for after purchase attempt is done  run in launch purchase flow;
        ViewInstantiatorHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new ViewInstantiatorHelper.OnIabPurchaseFinishedListener() {
            @Override
            public void onIabPurchaseFinished(PIabResult result, PPurchases info) {
                if (info.getSku().equals(SKU_PREMIUM)) {
                    // give user access to premium content and update the UI
                    EventBus.getDefault().post( new CommonEvents.UpgradeStatus(true));

                }else if(result.getResponse() == 3){
                    Toast.makeText(mContext, "Please sign into your google account to upgrade " , Toast.LENGTH_LONG).show();
                }
            }
        };
//        try {
//            viewInstantiatorHelper.launchPurchaseFlow((Activity) mContext   , SKU_PREMIUM, 10001,
//                    mPurchaseFinishedListener, "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ"   );
//        }catch (Exception e){
//            checkPremiumStatus(true);
//        }
    }

}
