package com.pingit.dekker.pingit.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionInfo {

	private ConnectivityManager connManager;
	private Context context;

	public ConnectionInfo(Context context){
		this.context = context;
		initConnectivityManager();
	}

	private void initConnectivityManager(){
		connManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	}
	
	private NetworkInfo getConnType(){
		return connManager.getActiveNetworkInfo();
	}
	
	public String getConnectionTypeName(){

	String connType = "UNKNOWN";
	
	try{
		int mobileTypeId = getConnType().getType();
		switch(mobileTypeId){
			case 0:
				connType = getConnType().getSubtypeName();
				break;
			case 1:
				connType = getConnType().getTypeName();
				break;
			default:
				connType = "UNKNOWN";
		break;
	 }
	}catch(NullPointerException exc){
		connType = "UNKNOWN";
	}
	 return connType;
}
	
	public boolean isConnected(){
		connManager =  
	            (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	            if (connManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
	            		connManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
	            				connManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
	            						connManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
	                return true;
	            } else if (
	            		connManager.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
	            				connManager.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
	                return false;
	            }
	          return false;
		}
}

