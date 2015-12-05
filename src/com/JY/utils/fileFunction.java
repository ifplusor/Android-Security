package com.JY.utils;

import java.io.File;
import java.io.FileOutputStream;

import android.util.Log;

public class fileFunction {
	public static final String TAG = "ServiceTimer";
	public static void copyfile(File fromFile, File toFile, Boolean rewrite) {
		System.out.println("执行0");
		if (!fromFile.exists()) {
			return;
		}
		System.out.println("执行1");
		if (!fromFile.isFile()) {
			return;
		}
		System.out.println("执行2");
		if (!fromFile.canRead()) {
			return;
		}
		System.out.println("执行3");
		if (!toFile.getParentFile().exists()) {
			toFile.getParentFile().mkdirs();
		}
		System.out.println("执行4");
		if (toFile.exists() && rewrite) {
			toFile.delete();
		}
		System.out.println("执行5");
		try {
			System.out.println("执行");

			java.io.FileInputStream fosfrom = new java.io.FileInputStream(
					fromFile);

			java.io.FileOutputStream fosto = new FileOutputStream(toFile);

			byte bt[] = new byte[1024];

			int c;

			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c); // 将内容写到新文件当中

			}

			fosfrom.close();

			fosto.close();

		} catch (Exception ex) {

			Log.e("readfile", ex.getMessage());

		}
	}

	public static void copyDB() {
		System.out.println("COPY!!!!!!!!!!!!");
		File fromFile = new File("/data/data/com.lbe.security.miui/databases/hips_log.db");
		//File fromFile = new File("/data/data/com.lbe.security.miui/databases/lbe_security.db");
		setPermission(fromFile);
		File toFile = new File("/data/data/com.JY.UI/databases/lbe_security.db");
		fileFunction.copyfile(fromFile, toFile, true);
		Log.v(TAG, "copying....");
	}

	public static void setPermission(File f) {
		String command = "chmod 777 " + f.getAbsolutePath();
		SystemManager.RootCommand(command);
		System.out.println("chmod is done");
	}
}
