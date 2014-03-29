package com.example.cra.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "cra.db";
	private static final int DATABASE_VERSION = 3;
	
	//usado por todas as tabelas!
	public static final String COLUMN_ID = "_id";

	public static final String TABLE_MATRICULA = "matricula";
	public static final String COLUMN_MATRICULA = "matricula";
	
	public static final String TABLE_MATERIA = "materia";
	public static final String COLUMN_NOME = "nome";
	public static final String COLUMN_CREDITOS = "creditos";
	public static final String COLUMN_PERIODO = "periodo";
	

	//representa o M2M de materia x matricula (contendo a nota dessa relação)
	public static final String TABLE_MATRICULA_MATERIA = "matricula_materia";
	public static final String COLUMN_MATRICULA_ID = "_matricula_id";
	public static final String COLUMN_MATERIA_ID = "_materia_id";
	public static final String COLUMN_NOTA = "nota";

	private static final String CREATE_TABLE_MATRICULA = "create table "
			+ TABLE_MATRICULA + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_MATRICULA
			+ " text not null);";

	private static final String CREATE_TABLE_MATERIA = "create table "
			+ TABLE_MATERIA + "(" 
			+ COLUMN_ID	
				+ " integer primary key autoincrement, " 
			+ COLUMN_NOME
				+ " text not null,"
			+ COLUMN_CREDITOS
				+ " integer not null," 
			+ COLUMN_PERIODO
				+ " integer not null" 
			+ ");";

	private static final String CREATE_TABLE_MATRICULA_MATERIA = "create table "
			+ TABLE_MATRICULA_MATERIA + "(" 
			+ COLUMN_ID	
				+ " integer primary key autoincrement, " 
			+ COLUMN_MATRICULA_ID
				+ " integer not null,"
			+ COLUMN_MATERIA_ID
				+ " integer not null," 
			+ COLUMN_NOTA
				+ " real not null" 
			+ ");";
	

	public SQLiteDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		Log.i(this.getClass().getName() + "onCreate", "CREAT DATABASE");
	    database.execSQL(CREATE_TABLE_MATRICULA);
	    database.execSQL(CREATE_TABLE_MATERIA);
	    database.execSQL(CREATE_TABLE_MATRICULA_MATERIA);
	    
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(this.getClass().getName() + "onUpgrade", "UPGRADE DATABASE");
	    Log.w(SQLiteDatabaseHelper.class.getName(),
	            "Upgrading database from version " + oldVersion + " to "
	                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATRICULA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATRICULA_MATERIA);
	        onCreate(db);
	      }

}
