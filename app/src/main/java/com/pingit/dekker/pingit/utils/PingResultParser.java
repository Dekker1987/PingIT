package com.pingit.dekker.pingit.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sergii on 09.09.2018.
 */

public class PingResultParser {

    private String resultParser(String regExpPattern,String stringToParse){
        Matcher matcher = Pattern.compile(regExpPattern).matcher(stringToParse);
        if (matcher.find()){
            return matcher.group(1);
        }
        return "";
    }

    public String getBytesVal(String pingResult){
        return resultParser("bytes=(.*?)([\\s]){1}",pingResult);
    }

    public String getTTLVal(String pingResult){
        return resultParser("TTL=(.*?)($)",pingResult);
    }

    public String getTimeVal(String pingResult){
        return resultParser("time=(.*?)([a-z]){2}",pingResult);
    }

    public String getUrlVal(String pingResult){
        return resultParser("Reply from (.*?):",pingResult);
    }

    public boolean isPingResultError(String pingResult){
        return pingResult.startsWith("Error");
    }
}
