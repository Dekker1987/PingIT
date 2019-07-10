package com.pingit.dekker.pingit.models;

import com.pingit.dekker.pingit.utils.MonthNameUtil;

/**
 * Created by Sergii on 09.09.2018.
 */

public class PointModel {
    private int seconds;
    private int minutes;
    private int hours;

    private int year;
    private int month;
    private int day;

    private long unixTime;

    private String pingUrl;
    private String pingConnTypeName;
    private String pingRawPingResult;

    private int pingTimeout;
    private int pingBytes;
    private int pingTTL;


    public int getSeconds(){
        return this.seconds;
    }

    public int getMinutes(){
        return this.minutes;
    }

    public int getHours(){
        return this.hours;
    }

    public int getYear(){
        return year;
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    public long getPointUnixTime(){
        return unixTime;
    }

    public PointModel setPingRawPingResult(String pingRawPingResult){
        this.pingRawPingResult = pingRawPingResult;
        return this;
    }

    public PointModel setYear(int year){
        this.year = year;
        return this;
    }

    public PointModel setMonth(int month){
        this.month = month;
        return this;
    }

    public PointModel setDay(int day){
        this.day = day;
        return this;
    }

    public String getPingRawPingResult(){
        return pingRawPingResult;
    }

    public String getPingUrl(){
        return pingUrl;
    }

    public String getConnTypeName(){
        return pingConnTypeName;
    }

    public int getTimeout(){
        return pingTimeout;
    }

    public int getBytes(){
        return pingBytes;
    }

    public int getTtl(){
        return pingTTL;
    }

    public PointModel setPingUrl(String url){
        this.pingUrl = url;
        return this;
    }

    public PointModel setHours(int hours){
        this.hours = hours;
        return this;
    }

    public PointModel setMinutes(int minutes){
        this.minutes = minutes;
        return this;
    }

    public PointModel setSeconds(int seconds){
        this.seconds = seconds;
        return this;
    }

    public PointModel setPointUnixTime(long pointUnixTime){
        this.unixTime = pointUnixTime;
        return this;
    }

    public PointModel setPingTimeout(int timeout){
        this.pingTimeout = timeout;
        return this;
    }

    public PointModel setPingBytes(int bytes){
        this.pingBytes = bytes;
        return this;
    }

    public PointModel setPingTtl(int ttl){
        this.pingTTL = ttl;
        return this;
    }

    public PointModel setConnTypeName(String connType){
        this.pingConnTypeName = connType;
        return this;
    }

    public String toString(){
        return  "Host: " + pingUrl + "\n"
                + "Connection type: " + pingConnTypeName + "\n"
                + "Date: " + year + " " + MonthNameUtil.getMonthName(month) + " " + day + "\n"
                + "Time: " + hours + ":" + minutes + ":" + seconds + "\n"
                + "Bytes: " + pingBytes + " Time: " + pingTimeout + " TTL: " + pingTTL;
    }
}



