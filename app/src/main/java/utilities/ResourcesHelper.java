package utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Jordan on 7/28/2017.
 */

public class ResourcesHelper {

    public static Boolean isOnline(Context context)	{
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni!= null && ni.isConnected() ;
    }

}
