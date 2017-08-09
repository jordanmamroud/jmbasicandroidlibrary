package utilities;

/**
 * Created by Jordan on 8/9/2017.
 */

public class StringHelper {

    public static String[] splitString(String stringToSplit){
        return stringToSplit.split("(?!^)");
    }

    public static boolean compareString(String one , String two){
        return one.toLowerCase().trim().equals(two.toLowerCase());
    }

    public static String capitalize(final String line) {
        String allLowerCase = line.toLowerCase();
        return Character.toUpperCase(allLowerCase.charAt(0)) + allLowerCase.substring(1);
    }


}
