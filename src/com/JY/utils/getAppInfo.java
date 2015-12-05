package com.JY.utils;

import java.util.List;

import com.JY.packageInfo.AppInfo;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

public class getAppInfo {
	public PackageManager pm;

	public Drawable getIconByPkg(int pkg, List<AppInfo> listAppInfo) {
		AppInfo appInfo = new AppInfo();
		for (int i = 0; i < listAppInfo.size(); i++) {
			appInfo = listAppInfo.get(i);
			if (appInfo.getUid() == pkg) {
				break;
			}
		}
		return appInfo.getAppIcon();
	}
	
	public static String getAppLabelByPackageName(String packageName,List<AppInfo> listAppInfo){
		AppInfo appInfo = new AppInfo();
		for (int i = 0; i < listAppInfo.size(); i++) {
			appInfo = listAppInfo.get(i);
			//System.out.println(appInfo.getPkgName());
			if (appInfo.getPkgName().equals(packageName)) {
				break;
			}
		}
		return appInfo.getAppLabel();
	}
	
	public static boolean pkgNameInList(String packageName,List<AppInfo> listAppInfo){
		AppInfo appInfo = new AppInfo();
		for (int i = 0; i < listAppInfo.size(); i++) {
			appInfo = listAppInfo.get(i);
			//System.out.println(appInfo.getPkgName());
			if (appInfo.getPkgName().equals(packageName)) {
				return true;
			}
		}
		return false;
	}

}
