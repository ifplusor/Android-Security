package com.JY.service;

import java.util.TimerTask;

import com.JY.utils.DataTraffic;

import android.content.Context;
import android.util.Log;

public class ServiceTraffic extends TimerTask {
	private Context mContext;
	private long lastTraffic = 0;
	public static String TAG = "TRAFFIC";
	public ServiceTraffic(Context mContext){
		this.mContext = mContext;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long currentTraffic = DataTraffic.getUidTotalRecvKB(10050);
		if (currentTraffic ==  lastTraffic) {
			Log.v(TAG, "没有下行流量");
		} else {
			Log.v(TAG, "有下行流量"+(currentTraffic-lastTraffic));
		}
		lastTraffic = currentTraffic;
	}

}
