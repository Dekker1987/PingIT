package com.pingit.dekker.pingit.helpers;

import com.pingit.dekker.pingit.models.PropertyModel;
import io.realm.Realm;

/**
 * Created by Sergii on 19.08.2018.
 */

public class DBhelper {
    private Realm realmInstance;
    private static DBhelper dBhelper;

    private DBhelper(){
        initRealmInstance();
    }

    private void initRealmInstance(){
        realmInstance = Realm.getDefaultInstance();
    }

    public static DBhelper getInstance(){
        if(dBhelper == null){
            dBhelper = new DBhelper();
        }
        return dBhelper;
    }

    public void saveProperties(PropertyModel propertyModel){
       updatePropertiesInDB(propertyModel);
    }

    private void updatePropertiesInDB(final PropertyModel inPropertyModel) {
        realmInstance.beginTransaction();

        PropertyModel propertyModel = realmInstance.where(PropertyModel.class).findFirst();
        propertyModel.setTimeout(inPropertyModel.getTimeout());
        propertyModel.setTtl(inPropertyModel.getTtl());
        propertyModel.setPacketSize(inPropertyModel.getPacketSize());
        propertyModel.setTries(inPropertyModel.getTries());
        propertyModel.setDelay(inPropertyModel.getDelay());
        propertyModel.setVibrate(inPropertyModel.isVibrate());
        propertyModel.setVibrateOnSuccess(inPropertyModel.isVibrateOnSuccess());
        propertyModel.setVibrateOnFailure(inPropertyModel.isVibrateOnFailure());

        realmInstance.commitTransaction();
    }

    public PropertyModel getPropertiesModel(){
        return realmInstance.where(PropertyModel.class).findFirst();
    }

    public void closeDB(){
        realmInstance.close();
    }
}
