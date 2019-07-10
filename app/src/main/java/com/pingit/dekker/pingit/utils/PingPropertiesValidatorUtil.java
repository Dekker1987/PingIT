package com.pingit.dekker.pingit.utils;

import com.pingit.dekker.pingit.enums.DefaultPingProperties;
import com.pingit.dekker.pingit.helpers.DBhelper;
import com.pingit.dekker.pingit.models.PropertyModel;

/**
 * Created by Sergii on 21.08.2018.
 */

public class PingPropertiesValidatorUtil {

    private static PingPropertiesValidatorUtil pingPropertiesValidatorUtil;
    private PropertyModel propertyModel;

    public static PingPropertiesValidatorUtil getInstance(){
        if(pingPropertiesValidatorUtil == null){
            pingPropertiesValidatorUtil = new PingPropertiesValidatorUtil();
        }
        return pingPropertiesValidatorUtil;
    }

    private PingPropertiesValidatorUtil(){
        initPropertiesModel();
    }

    private void initPropertiesModel(){
        propertyModel = DBhelper.getInstance().getPropertiesModel();
    }

    public int getValidatedPacketSize(String packetSize){ // type of argument is string because value from edittext
        if(!packetSize.equals("")){
            if(Integer.parseInt(packetSize)==0){
                return DefaultPingProperties.PACKET_SIZE.getDefaultValue();
            } else {
                return Integer.parseInt(packetSize);
            }
        } else {
            return propertyModel.getPacketSize();
        }
    }

    public int getValidatedTimeout(String timeout){ // type of argument is string because value from edittext
        if(!timeout.equals("")){
            if(Integer.parseInt(timeout)==0){
                return DefaultPingProperties.TIMEOUT.getDefaultValue();
            } else {
                return Integer.parseInt(timeout);
            }
        } else {
            return propertyModel.getTimeout();
        }
    }

    public int getValidatedTTL(String TTL){ // type of argument is string because value from edittext
        if(!TTL.equals("")){
            if(Integer.parseInt(TTL)==0){
                return DefaultPingProperties.TTL.getDefaultValue();
            } else {
                return Integer.parseInt(TTL);
            }
        } else {
            return propertyModel.getTtl();
        }
    }

    public int getValidatedTries(String tries){
        if(!tries.equals("")){
            if(Integer.parseInt(tries)==0){
                return DefaultPingProperties.TRIES.getDefaultValue();
            } else {
                return Integer.parseInt(tries);
            }
        } else {
            return propertyModel.getTries();
        }
    }

    public int getValidatedDelay(String delay){
        if(!delay.equals("")){
            if(Integer.parseInt(delay)==0){
                return DefaultPingProperties.DELAY.getDefaultValue();
            } else {
                return Integer.parseInt(delay);
            }
        } else {
            return propertyModel.getDelay();
        }
    }
}
