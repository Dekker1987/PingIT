package com.pingit.dekker.pingit.commands;

/**
 * Created by Sergii on 16.08.2018.
 */

import org.icmp4j.IcmpPingRequest;
import org.icmp4j.IcmpPingUtil;
import org.icmp4j.constants.OsFamilyCode;
import org.icmp4j.util.PlatformUtil;

public class PingCommand {

    private IcmpPingRequest request;

    public PingCommand(){
        initRequest();
        initOStype();
    }

    private void initRequest(){
        request = IcmpPingUtil.createIcmpPingRequest();
    }

    private void initOStype(){
        PlatformUtil.setOsFamilyCode(OsFamilyCode.ANDROID);
    }

    private PingCommand setHost(String host){
        request.setHost(host);
        return this;
    }

    public PingCommand setPacketSize(int packetSize){
        request.setPacketSize(packetSize);
        return this;
    }

    public PingCommand setTimeout(int timeout){
        request.setTimeout(timeout);
        return this;
    }

    public PingCommand setTtl(int ttl){
        request.setTtl(ttl);
        return this;
    }

    public String getPingResult(String host){
        request.setHost(host);
        IcmpPingUtil.executePingRequest(request).setThrowable(new Exception());
        return  IcmpPingUtil.formatResponse(IcmpPingUtil.executePingRequest(request));
    }

    public String getPingProperties(){
        return "Host::" + request.getHost()
                + " Packet Size::" + request.getPacketSize()
                + " Timeout" + request.getTimeout()
                + " TTL::" + request.getTtl();
    }
}

