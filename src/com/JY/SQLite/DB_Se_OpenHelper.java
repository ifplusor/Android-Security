package com.JY.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Se_OpenHelper extends SQLiteOpenHelper
{
	private static final int VERSION = 9;
	private static final String DBNAME = "lbe_security.db";
	
	public DB_Se_OpenHelper(Context context)
	{
		super(context, DBNAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		//SQLite schema
		//CREATE TABLE [eventlog] ([_id] INTEGER PRIMARY KEY AUTOINCREMENT , [title] VARCHAR NOT NULL , [content] VARCHAR NOT NULL , [pkg] VARCHAR NOT NULL , [timestamp] BIGINT NOT NULL , [action] INTEGER NOT NULL , [type] INTEGER NOT NULL, [raw] BLOB);
		
		db.execSQL("CREATE TABLE [eventlog] ([_id] INTEGER PRIMARY KEY AUTOINCREMENT , [title] VARCHAR NOT NULL , [content] VARCHAR NOT NULL , [pkg] VARCHAR NOT NULL , [timestamp] BIGINT NOT NULL , [action] INTEGER NOT NULL , [type] INTEGER NOT NULL, [raw] BLOB);");
		//db.execSQL("create table permissionrecord (title varchar,content varchar,pkg integer,timestamp bigint,id integer primary key,action integer,type integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{

	}

}
