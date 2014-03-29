package com.example.cra.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "cra.db";
	private static final int DATABASE_VERSION = 1;
	
	//usado por todas as tabelas!
	public static final String COLUMN_ID = "_id";

	public static final String TABLE_MATRICULA = "matricula";
	public static final String COLUMN_MATRICULA = "matricula";
	
	public static final String TABLE_MATERIA = "materia";
	public static final String COLUMN_CREDITOS = "creditos";

	private static final String CREATE_TABLE_MATRICULA = "create table "
			+ TABLE_MATRICULA + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_MATRICULA
			+ " text not null);";

	private static final String CREATE_TABLE_MATERIA = "create table "
			+ TABLE_MATERIA + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_CREDITOS
			+ " integer not null);";
	

	public SQLiteDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
	    database.execSQL(CREATE_TABLE_MATRICULA);
	    database.execSQL(CREATE_TABLE_MATERIA);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(SQLiteDatabaseHelper.class.getName(),
	            "Upgrading database from version " + oldVersion + " to "
	                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATRICULA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATRICULA);
	        onCreate(db);
	      }

}
