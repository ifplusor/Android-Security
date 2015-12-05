package com.JY.utils;

import android.net.TrafficStats;

public class DataTraffic {
	
	public static long getTotalRecvKB(){  
		//获取总的接受流量（KB），包含Mobile和WiFi等  
        return TrafficStats.getTotalRxBytes()==TrafficStats.UNSUPPORTED?0:(TrafficStats.getTotalRxBytes()/1024);  
    }  
	
    public static long getTotalSendKB(){  
    	//获取总的发送流量（KB），包含Mobile和WiFi等  
        return TrafficStats.getTotalTxBytes()==TrafficStats.UNSUPPORTED?0:(TrafficStats.getTotalTxBytes()/1024);  
    }  
    
    public static long getUidTotalRecvKB(int uid){
    	//获取指定UID的应用程序接收的流量（KB）
    	return TrafficStats.getUidRxBytes(uid)==TrafficStats.UNSUPPORTED?0:(TrafficStats.getUidRxBytes(uid)/1024);
    }
    
    public static long getUidTotalSendKB(int uid){
    	//获取指定UID的应用程序发送的流量（KB）
    	return TrafficStats.getUidTxBytes(uid)==TrafficStats.UNSUPPORTED?0:(TrafficStats.getUidTxBytes(uid)/1024);
    }
}
