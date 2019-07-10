package com.pingit.dekker.pingit.helpers;

import android.content.Context;
import android.util.Log;
import com.pingit.dekker.pingit.commands.PingCommand;
import com.pingit.dekker.pingit.enums.DefaultPingProperties;
import com.pingit.dekker.pingit.models.PointModel;
import com.pingit.dekker.pingit.models.PropertyModel;
import com.pingit.dekker.pingit.utils.ConnectionInfo;
import com.pingit.dekker.pingit.utils.DateTimeUtil;
import com.pingit.dekker.pingit.utils.PingResultParser;
import android.os.Message;

/**
 * Created by Sergii on 16.08.2018.
 */

public class PingHelper implements Runnable {

    private PingResultParser pingResultParser;
    private PingCommand pingCommand;
    private String pingResult;
    private String hostToPing;
    private Message resultMessage;
    private Thread thrd;
    private ConnectionInfo connectionInfo;
    private HandlerHelper handlerHelper;

    private boolean suspended;
    private boolean isRunned;

    private PropertyModel propertyModel;

    public PingHelper(Context context){
        isRunned = true;
        pingResultParser = new PingResultParser();
        connectionInfo = new ConnectionInfo(context);
        resultMessage = Message.obtain();

        initPingCommand();
        initHostToPing(hostToPing);
        initPingProperties();
    }

    private void initPingCommand(){
        pingCommand = new PingCommand();
    }

    private void  initPingProperties(){
        propertyModel = DBhelper.getInstance().getPropertiesModel();
    }

    public void initHostToPing(String hostToPing){
        this.hostToPing = hostToPing;
    }

    public void run() {
        while(isRunned) {
            makePause();
            makePing();
          //  showPingLog();
        }
    }

    private void makePause(){
        synchronized (this){
            while (suspended){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void makePing(){
         makeDelay(DefaultPingProperties.DELAY.getDefaultValue());
         pingResult = pingCommand.getPingResult(hostToPing);

         if(!pingResultParser.isPingResultError(pingResult)){
             Log.e("debug","success...");
             createPoint(pingResult);
         } else{
             Log.e("debug","error...");
         }
    }

    private void createPoint(String pingResult){

        int bytes = Integer.parseInt(pingResultParser.getBytesVal(pingResult));
        int timeout = Integer.parseInt(pingResultParser.getTimeVal(pingResult));
        int ttl = Integer.parseInt(pingResultParser.getTTLVal(pingResult));
        String host = pingResultParser.getUrlVal(pingResult);
        String connTypeName = connectionInfo.getConnectionTypeName();
        int hours = DateTimeUtil.getHours();
        int minutes = DateTimeUtil.getMinutes();
        int seconds = DateTimeUtil.getSeconds();
        long pointUnixTime = DateTimeUtil.getUnixTimeStamp();
        int year = DateTimeUtil.getYear();
        int month = DateTimeUtil.getMonth();
        int day = DateTimeUtil.getDay();

        resultMessage.obj = new PointModel()
                .setPingRawPingResult(pingResult)
                .setPingUrl(host)
                .setPingBytes(bytes)
                .setPingTimeout(timeout)
                .setPingTtl(ttl)
                .setConnTypeName(connTypeName)
                .setHours(hours)
                .setMinutes(minutes)
                .setSeconds(seconds)
                .setPointUnixTime(pointUnixTime)
                .setYear(year)
                .setMonth(month)
                .setDay(day);

        handlerHelper.dispatchMessage(resultMessage);
    }

    private void makeDelay(int delay){
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        if(thrd == null){
            thrd = new Thread(this);
            thrd.start();
        }
    }

    public void suspend(){
        suspended = true;
    }

    synchronized public void resume(){
        suspended = false;
        notify();
    }

    public void interruptPing(){
        isRunned = false;
    }

    public void setHandler(HandlerHelper handlerHelper){
        this.handlerHelper = handlerHelper;
    }

    private void showPingLog(){
        Log.e(getClass().getName(),"bytes:: " + pingResultParser.getBytesVal(pingResult)
         + " TTL:: " + pingResultParser.getTTLVal(pingResult)
         + " URL:: " + pingResultParser.getUrlVal(pingResult) + " Time:: " + pingResultParser.getTimeVal(pingResult));
    }

}
