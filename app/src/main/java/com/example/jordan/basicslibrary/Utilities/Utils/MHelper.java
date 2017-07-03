package com.example.jordan.basicslibrary.Utilities.Utils;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * Created by Jordan on 6/5/2017.
 */

public class MHelper {

    public static String capitalize(final String line) {
        String allLowerCase = line.toLowerCase();
        return Character.toUpperCase(allLowerCase.charAt(0)) + allLowerCase.substring(1);
    }

    public static int getPixelsFromDps(int dps, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        int pixels = (int) (dps * scale + 0.5f);
        return pixels;
    }

    public static boolean isDefaultLandscape(final Context context) {

        int orientation = context.getResources().getConfiguration().orientation;

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            return true ;
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
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)v.getLayoutParams();
            params.setMargins(amount,amount,amount,amount);
            v.setLayoutParams(params);
        }
    }



}
