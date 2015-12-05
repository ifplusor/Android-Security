package com.JY.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SignatureRecordDao {
	private SQLiteDatabase db;
	private DB_Sign_OpenHelper helper;
	
	public SignatureRecordDao(Context context){
		helper = new DB_Sign_OpenHelper(context);
	}
	public void add(SignatureRecord sigRecord){
		System.out.println("add begin!");
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("appLabel", sigRecord.getAppLabel());
		values.put("pkgname", sigRecord.getPkgName());
		values.put("signature", sigRecord.getSignature());
		db.insert("signatureRecord", "appLabel", values);
		System.out.println("add is done!");
	}
	
	public SignatureRecord find(String appLabel){
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("signatureRecord",new String[]{"appLabel","pkgname","signature"}, "appLabel=?", new String[]{String.valueOf(appLabel)}, null, null, null);
		if (cursor.moveToNext()) {
			return new SignatureRecord(cursor.getString(0), cursor.getString(1), cursor.getLong(2));
		}
		return null;
	}
	
	public SignatureRecord findByPackageName(String packageName){
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("signatureRecord",new String[]{"appLabel","pkgname","signature"}, "pkgname=?", new String[]{String.valueOf(packageName)}, null, null, null);
		if (cursor.moveToNext()) {
			return new SignatureRecord(cursor.getString(0), cursor.getString(1), cursor.getLong(2));
		}
		return null;
	}


}
