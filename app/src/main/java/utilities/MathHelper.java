package utilities;

import android.content.Context;
import android.util.DisplayMetrics;

import java.util.Random;

/**
 * Created by Jordan on 8/9/2017.
 */

public class MathHelper {

    public static int getRandomNum(int max){
        Random random = new Random();
        return random.nextInt(max);
    }

    public static int getPixelsFromDps(int dps, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dps * scale + 0.5f);
    }

    public static int getDpsFromPixels(Context context ,int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static boolean isNumEven(int n){     return n % 2 == 0 ;     }

}
