package com.JY.SQLite;

import java.util.ArrayList;
import java.util.List;

import com.JY.UI.StartActivity;
import com.JY.packageInfo.AppInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PermissionRecordDAO {
    private DB_Se_OpenHelper helper;
    private SQLiteDatabase db;

    public PermissionRecordDAO(Context context) {
        helper = new DB_Se_OpenHelper(context);
    }

    public void add(PermissionRecord permissionrecord) {
        db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", permissionrecord.getTitle());
        values.put("content", permissionrecord.getContent());
        values.put("pkg", permissionrecord.getPkg());
        values.put("timestamp", permissionrecord.getTimestamp());
        values.put("_id", permissionrecord.getId());
        values.put("action", permissionrecord.getType());
        values.put("type", permissionrecord.getType());
        db.insert("permissionrecord", "_id", values);
    }

    public PermissionRecord find(int id) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("eventlog", new String[]{"title", "content", "pkg", "timestamp", "_id", "action", "type"}, "_id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToNext()) {
            return new PermissionRecord(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getLong(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6));
        }
        return null;
    }

    
    public int getNumOf_Pkg(String pkg) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("eventlog", new String[]{"title", "content", "pkg", "timestamp", "_id", "action", "type"}, "pkg=?", new String[]{String.valueOf(pkg)}, null, null, null);
        return cursor.getCount();
    }

    public int getNumOf_action(int action) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("eventlog", new String[]{"title", "content", "pkg", "timestamp", "_id", "action", "type"}, "action=?", new String[]{String.valueOf(action)}, null, null, null);
        return cursor.getCount();
    }

    public int getTyep_NumOf_pkg(int type, String pkg) {
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("eventlog", new String[]{"title", "content", "pkg", "timestamp", "_id", "action", "type"}, "pkg=? and type=?", new String[]{String.valueOf(pkg),String.valueOf(type)}, null, null, null);
        return cursor.getCount();
    }
    public int getTotalNumOf_type(int type){
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("eventlog", new String[]{"title", "content", "pkg", "timestamp", "_id", "action", "type"}, "type=?", new String[]{String.valueOf(type)}, null, null, null);
        return cursor.getCount();
    }

    //所有记录的个数
    public int getNumOfRecord() {
        return getNumOf_action(0) + getNumOf_action(1) + getNumOf_action(2);
    }

    public List<PermissionRecord> getPermissionRecordOf_pkg(String pkg) {
        List<PermissionRecord> permissionRecords = new ArrayList<PermissionRecord>();
        
        try {
        	db = helper.getWritableDatabase();
            Cursor cursor = db.query("eventlog", new String[]{"title", "content", "pkg", "timestamp", "_id", "action", "type"}, "pkg=?", new String[]{pkg}, null, null, null);
            while (cursor.moveToNext()) {
                permissionRecords.add(new PermissionRecord(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getLong(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6)));
            }
        } catch (Exception ex) {
        	Log.d("error", ex.toString());
        }
        
        return permissionRecords;
    }

    public String[] getAllPkgName() {
        String[] pkgList = new String[StartActivity.static_listAppInfo.size()];
        int i = 0;
        for (AppInfo appInfo : StartActivity.static_listAppInfo) {
        	pkgList[i] = appInfo.getPkgName();
            i = i + 1;        	
        }
        return pkgList;
    }
}
