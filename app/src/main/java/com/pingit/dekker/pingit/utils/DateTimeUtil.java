package com.pingit.dekker.pingit.utils;

import java.util.Calendar;

/**
 * Created by Sergii on 09.09.2018.
 */

public class DateTimeUtil {

    private static Calendar calendar = Calendar.getInstance();

    private DateTimeUtil() {
        throw new AssertionError();
    }

    public static int getSeconds() {
        return calendar.get(Calendar.SECOND);
    }

    public static int getMinutes() {
        return calendar.get(Calendar.MINUTE);
    }

    public static int getHours() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getMonth() {
        return (calendar.get(Calendar.MONTH)) + 1;
    }

    public static int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public static long getUnixTimeStamp() {
        return System.currentTimeMillis() / 1000L;
    }
}
