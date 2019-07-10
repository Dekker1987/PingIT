package com.pingit.dekker.pingit.models;

import io.realm.RealmObject;

/**
 * Created by Sergii on 19.08.2018.
 */

public class PropertyModel extends RealmObject {

    private int timeout;
    private int ttl;
    private int packetSize;
    private int tries;
    private int delay;
    private boolean isVibrate;
    private boolean isVibrateOnSuccess;
    private boolean isVibrateOnFailure;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public int getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isVibrate() {
        return isVibrate;
    }

    public void setVibrate(boolean vibrate) {
        isVibrate = vibrate;
    }

    public boolean isVibrateOnSuccess() {
        return isVibrateOnSuccess;
    }

    public void setVibrateOnSuccess(boolean vibrateOnSuccess) {
        isVibrateOnSuccess = vibrateOnSuccess;
    }

    public boolean isVibrateOnFailure() {
        return isVibrateOnFailure;
    }

    public void setVibrateOnFailure(boolean vibrateOnFailure) {
        isVibrateOnFailure = vibrateOnFailure;
    }

    @Override
    public String toString() {
        return "PropertyModel{" +
                "timeout=" + timeout +
                ", ttl=" + ttl +
                ", packetSize=" + packetSize +
                ", tries=" + tries +
                ", delay=" + delay +
                ", isVibrate=" + isVibrate +
                ", isVibrateOnSuccess=" + isVibrateOnSuccess +
                ", isVibrateOnFailure=" + isVibrateOnFailure +
                '}';
    }
}
