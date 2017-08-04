package utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Jordan on 7/19/2017.
 */

public class StorageHelper {

    public static boolean getSharedPrefBoolean(Context context , String key, boolean defaultVal ){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPrefs.getBoolean(key , defaultVal);
    }

    public static void saveSharedPrefBoolean(Context context , String key, boolean bool ){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(  key , bool );
        editor.apply();
    }

}
