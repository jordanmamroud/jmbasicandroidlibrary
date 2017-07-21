package com.example.jordan.basicslibrary.Utilities.Utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Jordan on 7/21/2017.
 */

public class JsonHelper {
    public static JSONArray loadJSONFromAsset(Context context , String filelocation , String arrayName) {

        String json = null;

        try {
            InputStream is = context.getAssets().open(filelocation);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        try {
            JSONObject obj = new JSONObject(json);

            return obj.getJSONArray(arrayName);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
