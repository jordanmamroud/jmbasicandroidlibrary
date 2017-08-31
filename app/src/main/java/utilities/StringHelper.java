package utilities;

/**
 * Created by Jordan on 8/9/2017.
 */

public class StringHelper {

    public static String[] splitString(String stringToSplit){
        return stringToSplit.split("(?!^)");
    }

    public static boolean compare(String one , String two){
        return one.toLowerCase().trim().equals(two.toLowerCase());
    }

    public static String capitalize(final String line) {
        return  line.length() > 0 ? Character.toUpperCase(line.toLowerCase().charAt(0)) + line.toLowerCase().substring(1) : line ;
    }


}
