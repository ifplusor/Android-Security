package com.JY.packageInfo;

import android.graphics.drawable.Drawable;

public class AppInfo {
	private String appLabel;// 应用程序标签
	private Drawable appIcon;// 应用程序图标
	private String pkgName;// 应用程序包名
	private int uid; // 进程所在的用户id ，即该进程是有谁启动的 root/普通用户等
	private long currentDownTraffic = 0;
	private long currentUpTraffic = 0;
	private long lastDownTraffic = 0;
	private long lastUpTraffic = 0;
	private long ingDownTraffic = 0;
	private long ingUpTraffic = 0;
	
	public AppInfo() {
	}

	public AppInfo(String appLabel, Drawable appIcon, String pkgNane, int uid) {
		super();
		this.appLabel = appLabel;
		this.appIcon = appIcon;
		this.pkgName = pkgNane;
		this.uid = uid;
	}


	public String getAppLabel() {
		return appLabel;
	}

	public void setAppLabel(String appname) {
		this.appLabel = appname;
	}

	public Drawable getAppIcon() {
		return appIcon;
	}

	public void setAppIcon(Drawable appIcon) {
		this.appIcon = appIcon;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public long getCurrentDownTraffic() {
		return currentDownTraffic;
	}

	public void setCurrentDownTraffic(long currentDownTraffic) {
		this.currentDownTraffic = currentDownTraffic;
	}

	public long getCurrentUpTraffic() {
		return currentUpTraffic;
	}

	public void setCurrentUpTraffic(long currentUpTraffic) {
		this.currentUpTraffic = currentUpTraffic;
	}

	public long getLastDownTraffic() {
		return lastDownTraffic;
	}

	public void setLastDownTraffic(long lastDownTraffic) {
		this.lastDownTraffic = lastDownTraffic;
	}

	public long getLastUpTraffic() {
		return lastUpTraffic;
	}

	public void setLastUpTraffic(long lastUpTraffic) {
		this.lastUpTraffic = lastUpTraffic;
	}

	public long getIngDownTraffic() {
		return ingDownTraffic;
	}

	public void setIngDownTraffic(long ingDownTraffic) {
		this.ingDownTraffic = ingDownTraffic;
	}

	public long getIngUpTraffic() {
		return ingUpTraffic;
	}

	public void setIngUpTraffic(long ingUpTraffic) {
		this.ingUpTraffic = ingUpTraffic;
	}
	
}
