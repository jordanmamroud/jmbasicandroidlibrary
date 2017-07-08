package com.example.jordan.basicslibrary.Utilities.Utils;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Jordan on 7/7/2017.
 */

public class ViewHelper {

    public static boolean isViewVisible(View view){
        if(view.getVisibility() == View.VISIBLE){
            return  true ;
        }else {
            return false;
        }
    }
    // must put zero if no rule needs to be removed
    public static void addRuleToViews(int rule , @Nullable int ruleToRemoveOrZero, View... views){
        // adds a rule to multiple children of relative layout
        for( View v : views) {
            RelativeLayout.LayoutParams updatedParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
            updatedParams.addRule(rule);
            if(ruleToRemoveOrZero!= 0) {
                // this removes the rule , there is a actual remove rule method is for api 17 and above
                updatedParams.addRule(ruleToRemoveOrZero, 0);
            }
            v.setLayoutParams(updatedParams);
        }
    }

    public static void addMultipleRulesToView(View v, int ruleToRemoveOrZero,int... rules){
        RelativeLayout.LayoutParams updatedParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
        if(ruleToRemoveOrZero!= 0) {
            // this removes the rule , actual remove rule method is for api 17 and above
            updatedParams.addRule(ruleToRemoveOrZero, 0);
        }
        for (int rule : rules){
            updatedParams.addRule(rule);
        }
        v.setLayoutParams(updatedParams);
    }


    public static void removeRulesFromView(View v , int... rules){
        RelativeLayout.LayoutParams updatedParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
        for (int rule : rules){
            updatedParams.addRule(rule , 0);
        }
        v.setLayoutParams(updatedParams);
    }

    public static void updateParams( boolean remove,    RelativeLayout.LayoutParams params, int... rules){
        for (int rule : rules){
            if(remove) {
                params.addRule(rule, 0);
            }else {
                params.addRule(rule);
            }
        }
    }

    public static void addAlignmentRule(View v , int alignmentRule , int viewIdToAlignTo){
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v.getLayoutParams();
        params.addRule(alignmentRule, viewIdToAlignTo);
        v.setLayoutParams(params);
    }

    public static void removeLayoutRules(int ruleToRemove, View v){
        RelativeLayout.LayoutParams updatedParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
        // this removes the rule , actual remove rule method is for api 17 and above
        updatedParams.addRule(ruleToRemove, 0);
    }

    public static void setViewsMargin ( int amount, View... views){
        for (View v : views){
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams() ;

            params.setMargins(amount,amount,amount,amount);

            v.setLayoutParams(params);
        }
    }


}
