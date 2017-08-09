package utilities;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Jordan on 7/28/2017.
 */

public class ResourcesHelper {

    public static Boolean isOnline(Context context)	{
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni!= null && ni.isConnected() ;
    }


    public static int getImageId(Context context, String imageName){
        if(imageName == null) return  0 ;

        return context.getResources().getIdentifier(imageName.toLowerCase().trim() , "drawable", context.getPackageName() );
    }

    public static boolean isDefaultLandscape(final Context context) {
        int orientation = context.getResources().getConfiguration().orientation;

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            return true ;
        }else {
            return false;
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
