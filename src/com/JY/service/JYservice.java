package com.JY.service;

import java.util.Timer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class JYservice extends Service {
	private Timer timer;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Service onBind");
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		System.out.println("Service onCreat");
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("Service onStartCommand");
		startServiceTimer();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("Service onDestroy");
		super.onDestroy();
	}
	
	private void startServiceTimer(){
		if(this.timer == null)
			this.timer = new Timer();
		ServiceSign serviceSign = new ServiceSign(this);
		this.timer .schedule(serviceSign, 0L, 500L);
		
		ServiceTraffic serviceTraffic = new ServiceTraffic(this);
		this.timer.schedule(serviceTraffic, 0L, 2000L);
	}
}
