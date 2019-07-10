package com.pingit.dekker.pingit.utils;

/**
 * Created by Sergii on 09.09.2018.
 */

public class MonthNameUtil {

    private MonthNameUtil(){
        throw new AssertionError();
    }

    public static String getMonthName(int month) {

        switch (month) {
            case 1:
                return "jan";
            case 2:
                return "feb";
            case 3:
                return "mar";
            case 4:
                return "apr";
            case 5:
                return "may";
            case 6:
                return "jun";
            case 7:
                return "jul";
            case 8:
                return "aug";
            case 9:
                return "sep";
            case 10:
                return "oct";
            case 11:
                return "nov";
            case 12:
                return "dec";
            default:
                return String.valueOf(month);
        }
    }
}
