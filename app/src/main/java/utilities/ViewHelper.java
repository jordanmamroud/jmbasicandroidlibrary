package utilities;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by Jordan on 7/7/2017.
 */

public class ViewHelper {


    public static void showMessage(Context context, String message, int length){
        Toast.makeText(context , message , length).show();
    }
    public static void showToastAtCustomPosition(Context context, String msg, int toastLength , int gravity){
        Toast toast = Toast.makeText(context, msg, toastLength);
        toast.setGravity(gravity, 0 , 0 );
        toast.show();
    }

    public static void setViewsClickability(boolean clickable, View... views){
        for(View v : views){
            v.setClickable(clickable);
        }
    }

    public static boolean isViewVisible(View view){
        if(view.getVisibility() == View.VISIBLE){
            return  true ;
        }else {
            return false;
        }
    }

    public static void removeViews(  View... views){
        for (View v : views)    {
            v.setVisibility(View.GONE);
            v.setClickable(false);
        }
    }

    public static void hideViews(View... views){
        for (View v : views) v.setVisibility(View.INVISIBLE);
    }

    public static void showViews( View... views){
        for (View v : views){

            v.setClickable(true);
            v.setVisibility(View.VISIBLE);
        }
    }

    public static int calculateSpace(int marginRightPlusLeft , int numOfViews, int totalAvailableWidth ){
        return (totalAvailableWidth/ numOfViews)   - marginRightPlusLeft ;
    }

    // call getToken on view to get windowtoken
    public static void hideKeyboard(Context context , IBinder windowToken){
        InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow( windowToken , 0);

    }

}
//
//    // must put zero if no rule needs to be removed
//    public static void addRuleToViews(int rule , @Nullable int ruleToRemoveOrZero, View... views){
//        // adds a rule to multiple children of relative layout
//        for( View v : views) {
//            RelativeLayout.LayoutParams updatedParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
//            updatedParams.addRule(rule);
//            if(ruleToRemoveOrZero!= 0) {
//                // this removes the rule , there is a actual remove rule method is for api 17 and above
//                updatedParams.addRule(ruleToRemoveOrZero, 0);
//            }
//            v.setLayoutParams(updatedParams);
//        }
//    }
//
//    public static void addMultipleRulesToView(View v, int ruleToRemoveOrZero,int... rules){
//        RelativeLayout.LayoutParams updatedParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
//        if(ruleToRemoveOrZero!= 0) {
//            // this removes the rule , actual remove rule method is for api 17 and above
//            updatedParams.addRule(ruleToRemoveOrZero, 0);
//        }
//        for (int rule : rules){
//            updatedParams.addRule(rule);
//        }
//        v.setLayoutParams(updatedParams);
//    }
//
//
//    public static void removeRulesFromView(View v , int... rules){
//        RelativeLayout.LayoutParams updatedParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
//        for (int rule : rules){
//            updatedParams.addRule(rule , 0);
//        }
//        v.setLayoutParams(updatedParams);
//    }
//
//    public static void updateParams( boolean remove,    RelativeLayout.LayoutParams params, int... rules){
//        for (int rule : rules){
//            if(remove) {
//                params.addRule(rule, 0);
//            }else {
//                params.addRule(rule);
//            }
//        }
//    }
//
//    public static void addAlignmentRule(View v , int alignmentRule , int viewIdToAlignTo){
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v.getLayoutParams();
//        params.addRule(alignmentRule, viewIdToAlignTo);
//        v.setLayoutParams(params);
//    }
//
//    public static void removeLayoutRules(int ruleToRemove, View v){
//        RelativeLayout.LayoutParams updatedParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
//        // this removes the rule , actual remove rule method is for api 17 and above
//        updatedParams.addRule(ruleToRemove, 0);
//    }
//
//    public static void setViewsMargin ( int amount, View... views){
//        for (View v : views){
//            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams() ;
//
//            params.setMargins(amount,amount,amount,amount);
//
//            v.setLayoutParams(params);
//        }
//    }
//
