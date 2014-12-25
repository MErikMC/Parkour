package com.merikmc.util;

/**
 * Created by codermason on 12/25/14.
 */
public class NumberUtil {

    public static int parseInteger(String input) {
        if(isNumber(input)) {
            return Integer.parseInt(input);
        } else {
            return 0;
        }
    }

    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch(NumberFormatException nfe) {
            return false;
        }
    }

}
