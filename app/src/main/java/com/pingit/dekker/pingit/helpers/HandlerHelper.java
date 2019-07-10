package com.pingit.dekker.pingit.helpers;

import android.os.Handler;
import android.os.Message;
import com.pingit.dekker.pingit.MainActivity;
import com.pingit.dekker.pingit.models.PointModel;

/**
 * Created by Sergii on 28.06.2019.
 */

public class HandlerHelper extends Handler {

    private MainActivity mainActivity;
    private PointModel pointModel;

    public HandlerHelper(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void handleMessage(Message msg) {
        pointModel = (PointModel)msg.obj;

        this.post(new Runnable(){
            @Override
            public void run() {
                mainActivity.showPingRes(pointModel);
            }
        });
    }
}
