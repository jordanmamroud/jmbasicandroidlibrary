package utilities;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Jordan on 7/21/2017.
 */

public class JsonHelper {

    public static JSONArray loadJSONFromAsset(Context context , String filelocation , String arrayName)  {
        return readJsonFile(context, filelocation, arrayName) ;
    }

    public static String loadJSONFromAsset(Context context , String filelocation )  {
        return readJsonFile(    context, filelocation   ) ;
    }


    private static String readJsonFile(Context context , String filelocation  ){
        String json = null;
        try {
            InputStream is = context.getAssets().open(filelocation);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            return json ;
        } catch (IOException ex) {
            JHelper.printError("Error reading json in JsonHelper");
            ex.printStackTrace();
            return null;
        }
    }

    private static JSONArray readJsonFile(Context context , String filelocation , String arrayName)  {
        String json = null;
        JSONArray jsonObject = null ;

        try {
            InputStream is = context.getAssets().open(filelocation);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            JHelper.printError("Error reading json in JsonHelper");
            ex.printStackTrace();
            return null;
        }
        try {
            jsonObject =  new JSONObject(json).getJSONArray(arrayName);
        }catch (JSONException e){
            JHelper.printError("Error reading json in JsonHelper");
        }

        return jsonObject ;
    }



}
