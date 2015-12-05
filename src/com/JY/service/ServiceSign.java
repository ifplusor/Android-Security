package com.JY.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;

import com.JY.SQLite.SignatureRecordDao;
import com.JY.packageInfo.AppInfo;
import com.JY.utils.fileFunction;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class ServiceSign extends TimerTask {
	private ActivityManager activityManager;
	private Context context;
	public static String lastPackageName = null;
	private SignatureRecordDao sigRecordDao = null;
	public PackageManager pm;
	public String homePackageName = "com.miui.home";
	public static final String TAG = "ServiceTimer";
	private List<AppInfo> listAppInfo;

	public ServiceSign(Context mcontext) {
		this.context = mcontext;
		this.activityManager = (ActivityManager) mcontext
				.getSystemService("activity");
		this.sigRecordDao = new SignatureRecordDao(context);
		listAppInfo = queryFilterAppInfo();
		// System.out.println("ServiceTimer is created!");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		String currentPackageName = ((ActivityManager.RunningTaskInfo) this.activityManager
				.getRunningTasks(1).get(0)).topActivity.getPackageName();

		if ((!currentPackageName.equals(lastPackageName))
				&& (!currentPackageName.equals("com.FTD.UI"))
				&& (com.JY.utils.getAppInfo.pkgNameInList(currentPackageName,
						listAppInfo))) {
			{
				Log.v(TAG, currentPackageName);
				Log.v(TAG,
						checkSigHash(com.JY.utils.getAppInfo
								.getAppLabelByPackageName(currentPackageName,
										listAppInfo), currentPackageName)
								+ "");
				fileFunction.copyDB();
				lastPackageName = currentPackageName;
				Intent intent = new Intent();
				intent.setClassName("com.FTD.UI", "com.FTD.activity.Action");
				intent.setFlags(268435456);
				Bundle bundle = new Bundle();
				switch (checkSigHash(
						com.JY.utils.getAppInfo.getAppLabelByPackageName(
								currentPackageName, listAppInfo),
						currentPackageName)) {
				case 0:
					Log.v(TAG, "result--->0");
					bundle.putInt("result", 0);
					intent.putExtras(bundle);
					context.startActivity(intent);
					break;
				case 1:
					Log.v(TAG, "result--->1");
					bundle.putInt("result", 1);
					intent.putExtras(bundle);
					context.startActivity(intent);
					break;
				case -1:
					Log.v(TAG, "result--->-1");
					bundle.putInt("result", -1);
					intent.putExtras(bundle);
					context.startActivity(intent);
					break;

				default:
					break;
				}

			}
		}
	}

	private int checkSigHash(String appLabel, String pkgName) {
		if (sigRecordDao.find(appLabel) == null) {
			return -1;// 没有查询到
		}
		long sigNum = sigRecordDao.find(appLabel).getSignature();
		if (sigNum == SystemGetSignature(pkgName)) {
			return 1;// 签名验证通过
		} else {
			return 0;// 签名不正确
		}
	}

	public long SystemGetSignature(String packageName) {
		PackageManager pm = context.getPackageManager();
		PackageInfo packageInfo = null;
		long hash = 0;
		try {
			packageInfo = pm.getPackageInfo(packageName,
					PackageManager.GET_SIGNATURES);
			android.content.pm.Signature[] signs = packageInfo.signatures;
			hash = signs[0].hashCode();
			// android.content.pm.Signature sign = signs[0];
			// parseSignature(sign.toByteArray());
		} catch (Exception e) {
			hash = 0;
		}
		return hash;
	}

	public List<AppInfo> queryFilterAppInfo() {
		pm = context.getPackageManager();
		// 查询所有已经安装的应用程序
		List<ApplicationInfo> listAppcations = pm
				.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		Collections.sort(listAppcations,
				new ApplicationInfo.DisplayNameComparator(pm));// 排序
		List<AppInfo> appInfos = new ArrayList<AppInfo>();

		appInfos.clear();
		for (ApplicationInfo app : listAppcations) {
			// 非系统程序
			if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
				appInfos.add(getAppInfo(app));
			}
			// 本来是系统程序，被用户手动更新后，该系统程序也成为第三方应用程序了
			else if ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
				appInfos.add(getAppInfo(app));
			}

		}

		return appInfos;
	}

	private AppInfo getAppInfo(ApplicationInfo app) {
		AppInfo appInfo = new AppInfo();
		appInfo.setAppLabel((String) app.loadLabel(pm));
		appInfo.setAppIcon(app.loadIcon(pm));
		appInfo.setPkgName(app.packageName);
		appInfo.setUid(app.uid);
		return appInfo;
	}

}
