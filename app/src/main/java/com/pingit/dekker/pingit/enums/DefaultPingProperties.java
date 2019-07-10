package com.pingit.dekker.pingit.enums;

/**
 * Created by Sergii on 25.08.2018.
 */

public enum DefaultPingProperties {

     PACKET_SIZE(32) // value in Bytes
    ,TIMEOUT(5000) // value in Milliseconds (1 second = 1000 milliseconds)
    ,TTL(255) // value in Milliseconds (1 second = 1000 milliseconds)
    ,TRIES(4) // used 4 because it is standart value in Ping Command
    ,DELAY(1000); // value in Milliseconds (1 second = 1000 milliseconds)

    private int defaultValue;

    DefaultPingProperties(int defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getDefaultValue(){
        return defaultValue;
    }
}
