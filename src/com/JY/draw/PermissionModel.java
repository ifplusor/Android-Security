package com.JY.draw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.JY.utils.timestamp2time;

public class PermissionModel {
	private String appName=null;
	private long SendMsgTimestamp;
	private long ReadCtcTimestamp;
	private long ReadPhoneTimestamp;
	private long TrackTimestamp;
	private long IMEITimestamp;
	private long RootTimestamp;
	private long MonitorCallTimestamp;
	public long getReadMsgTimestamp() {
		return ReadMsgTimestamp;
	}
	public void setReadMsgTimestamp(long readMsgTimestamp) {
		ReadMsgTimestamp = readMsgTimestamp;
	}
	private long ReadMsgTimestamp;
	private boolean isSendMsg=false;
	private boolean isReadCtc=false;
	private boolean isReadPhone=false;
	private boolean isTrack=false;
	private boolean isIMEI=false;
	private boolean isRoot=false;
	private boolean isMonitorCall=false;
	private boolean isReadMsg=false;
	public boolean isReadMsg() {
		return isReadMsg;
	}
	public void setReadMsg(boolean isReadMsg) {
		this.isReadMsg = isReadMsg;
	}
	@Override
	public String toString() {
		return "PermissionModle [appName=" + appName + ", isSendMsg="
				+ isSendMsg + ", isReadCtc=" + isReadCtc + ", isReadPhone="
				+ isReadPhone + ", isTrack=" + isTrack + ", isIMEI=" + isIMEI
				+ ", isRoot=" + isRoot + ", isMonitorCall=" + isMonitorCall
				+ "]";
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public long getSendMsgTimestamp() {
		return SendMsgTimestamp;
	}
	public void setSendMsgTimestamp(long sendMsgTimestamp) {
		SendMsgTimestamp = sendMsgTimestamp;
	}
	public long getReadCtcTimestamp() {
		return ReadCtcTimestamp;
	}
	public void setReadCtcTimestamp(long readCtcTimestamp) {
		ReadCtcTimestamp = readCtcTimestamp;
	}
	public long getReadPhoneTimestamp() {
		return ReadPhoneTimestamp;
	}
	public void setReadPhoneTimestamp(long readPhoneTimestamp) {
		ReadPhoneTimestamp = readPhoneTimestamp;
	}
	public long getTrackTimestamp() {
		return TrackTimestamp;
	}
	public void setTrackTimestamp(long trackTimestamp) {
		TrackTimestamp = trackTimestamp;
	}
	public long getIMEITimestamp() {
		return IMEITimestamp;
	}
	public void setIMEITimestamp(long iMEITimestamp) {
		IMEITimestamp = iMEITimestamp;
	}
	public long getRootTimestamp() {
		return RootTimestamp;
	}
	public void setRootTimestamp(long rootTimestamp) {
		RootTimestamp = rootTimestamp;
	}
	public long getMonitorCallTimestamp() {
		return MonitorCallTimestamp;
	}
	public void setMonitorCallTimestamp(long monitorCallTimestamp) {
		MonitorCallTimestamp = monitorCallTimestamp;
	}
	public boolean isSendMsg() {
		return isSendMsg;
	}
	public void setSendMsg(boolean isSendMsg) {
		this.isSendMsg = isSendMsg;
	}
	public boolean isReadCtc() {
		return isReadCtc;
	}
	public void setReadCtc(boolean isReadCtc) {
		this.isReadCtc = isReadCtc;
	}
	public boolean isReadPhone() {
		return isReadPhone;
	}
	public void setReadPhone(boolean isReadPhone) {
		this.isReadPhone = isReadPhone;
	}
	public boolean isTrack() {
		return isTrack;
	}
	public void setTrack(boolean isTrack) {
		this.isTrack = isTrack;
	}
	public boolean isIMEI() {
		return isIMEI;
	}
	public void setIMEI(boolean isIMEI) {
		this.isIMEI = isIMEI;
	}
	public boolean isRoot() {
		return isRoot;
	}
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	public boolean isMonitorCall() {
		return isMonitorCall;
	}
	public void setMonitorCall(boolean isMonitorCall) {
		this.isMonitorCall = isMonitorCall;
	}
	public int getNumOfPermissions(){
		int i=0;
		if(isIMEI)i++;
		if(isMonitorCall)i++;
		if(isReadCtc)i++;
		if(isReadPhone)i++;
		if(isRoot)i++;
		if(isSendMsg)i++;
		if(isTrack)i++;
		if(isReadMsg)i++;
		return i;
	}
	public List<HashMap<String, String>> getPermissionNameByCounter(int counter,PermissionModel permissionModle){
		String name=null;
		long time=0;
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		int i=0;
		if(permissionModle.isSendMsg&&(i!=counter)){i++; name="发送短信的权限";time=permissionModle.getSendMsgTimestamp();}
		if(permissionModle.isReadCtc&&(i!=counter)){i++;name="读取联系人数据的权限";time=permissionModle.getReadCtcTimestamp();}
		if(permissionModle.isReadPhone&&(i!=counter)){i++;name="读取通话记录的权限";time=permissionModle.getReadPhoneTimestamp();}
		if(permissionModle.isTrack&&(i!=counter)){i++;name="获取您当前位置";time=permissionModle.getTrackTimestamp();}
		if(permissionModle.isIMEI&&(i!=counter)){i++;name="IMEI号码的权限";time=permissionModle.getIMEITimestamp();}
		if(permissionModle.isRoot&&(i!=counter)){i++;name="ROOT权限";time=permissionModle.getRootTimestamp();}
		if(permissionModle.isMonitorCall&&(i!=counter)){i++;name="监听来电状态的权限";time=permissionModle.getMonitorCallTimestamp();}
		if(permissionModle.isReadMsg&&(i!=counter)){i++;name="读取短信内容的权限";time=permissionModle.getReadMsgTimestamp();}
		HashMap<String, String> mapName = new HashMap<String, String>();
		mapName.put("PermissionName", name);
		HashMap<String, String> mapTime = new HashMap<String, String>();
		mapTime.put("time",	timestamp2time.getTime(time));
		list.add(mapName);
		list.add(mapTime);
	
		return list;
	}
}
