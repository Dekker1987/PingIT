package com.pingit.dekker.pingit;

import android.app.Application;

import com.pingit.dekker.pingit.enums.DefaultPingProperties;
import com.pingit.dekker.pingit.models.PropertyModel;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Sergii on 20.08.2018.
 */

@ReportsCrashes(mailTo = "dekker1349@gmail.com", mode = ReportingInteractionMode.TOAST, resToastText = R.string.crash_message_header)
public class App extends Application {
    public void onCreate(){
        super.onCreate();

        initDB();
        initBugTracker();
    }

    private void initDB(){
        Realm.init(this);
        RealmConfiguration realmConfigurationDefault = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfigurationDefault);

        initDefaultProperties();
    }

    private void initDefaultProperties(){
        Realm realmInstance = Realm.getDefaultInstance();

        if(((realmInstance.where(PropertyModel.class).findFirst() == null))) {
            realmInstance.beginTransaction();

            PropertyModel propertyModel = realmInstance.createObject(PropertyModel.class);
            propertyModel.setTimeout(DefaultPingProperties.TIMEOUT.getDefaultValue());
            propertyModel.setTtl(DefaultPingProperties.TTL.getDefaultValue());
            propertyModel.setPacketSize(DefaultPingProperties.PACKET_SIZE.getDefaultValue());
            propertyModel.setTries(DefaultPingProperties.TRIES.getDefaultValue());
            propertyModel.setDelay(DefaultPingProperties.DELAY.getDefaultValue());
            propertyModel.setVibrate(false);
            propertyModel.setVibrateOnSuccess(false);
            propertyModel.setVibrateOnFailure(false);

            realmInstance.commitTransaction();
        }
    }

    private void initBugTracker(){
        ACRA.init(this);
    }
}
