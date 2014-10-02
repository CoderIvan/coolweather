package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {

	/**
	 * Province表 建表语句
	 */
	public static final String CREATE_PROVINCE;
	/**
	 * City表 建表语句
	 */
	public static final String CREATE_CITY;
	/**
	 * Count表 建表语句
	 */
	public static final String CREATE_COUNTY;

	static {
		StringBuilder builder = new StringBuilder();

		builder.append("create table Province(");
		builder.append("id integer primary key autoincrement,");
		builder.append("province_name text,");
		builder.append("province_code text");
		builder.append(")");
		CREATE_PROVINCE = builder.toString();
		builder.setLength(0);

		builder.append("create table City(");
		builder.append("id integer primary key autoincrement,");
		builder.append("city_name text,");
		builder.append("city_code text,");
		builder.append("province_id integer");
		builder.append(")");
		CREATE_CITY = builder.toString();
		builder.setLength(0);

		builder.append("create table County(");
		builder.append("id integer primary key autoincrement,");
		builder.append("county_name text,");
		builder.append("county_code text,");
		builder.append("city_id integer");
		builder.append(")");
		CREATE_COUNTY = builder.toString();
		builder.setLength(0);

	}

	public CoolWeatherOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_COUNTY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}
