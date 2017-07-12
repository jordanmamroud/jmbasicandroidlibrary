package com.example.jordan.basicslibrary.Utilities.Utils;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.Random;

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
        return (int) (dps * scale + 0.5f);
    }

    public static int getDpsFromPixels(Context context ,int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static FragmentTransaction setupFragTransaction(FragmentManager manager,int animIn, int animOut,  String backstack){
        FragmentTransaction transaction =manager.beginTransaction();
        transaction.setCustomAnimations( animIn, animOut, animIn, animOut   );
        if(backstack != null) {
            transaction.addToBackStack(backstack);
        }

        return transaction ;
    }

    public static int getRandomNum(int max){
        Random random = new Random();
        return random.nextInt(max);
    }

    public static int getImageId(Context context, String imageName){
        return context.getResources().getIdentifier(imageName.toLowerCase().trim() , "drawable", context.getPackageName() );
    }

    public static boolean compareString(String one , String two){
        return one.toLowerCase().trim().equals(two.toLowerCase());
    }

    public static boolean isDefaultLandscape(final Context context) {

        int orientation = context.getResources().getConfiguration().orientation;

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            return true ;
        }else {
            return false;
        }
    }

    public static String[] splitString(String stringToSplit){
        return stringToSplit.split("(?!^)");
    }

    public static boolean isNumEven(int n){
        if ( ( n % 2 ) == 0 ) {
            return true ;
        } else {
            return  false;
        }
    }

    public static int getDeviceWidth(Context context){
        final Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point deviceDisplay = new Point();
        display.getSize(deviceDisplay);
        int deviceWidth = deviceDisplay.x;
        return deviceWidth ;
    }




}
