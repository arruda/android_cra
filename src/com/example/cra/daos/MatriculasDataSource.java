package com.example.cra.daos;

import java.util.ArrayList;
import java.util.List;

import com.example.cra.helpers.SQLiteDatabaseHelper;
import com.example.cra.models.Matricula;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class MatriculasDataSource {

	// Database fields
	private SQLiteDatabase database;
	private SQLiteDatabaseHelper dbHelper;
	private String[] allColumns = { SQLiteDatabaseHelper.COLUMN_ID,
			SQLiteDatabaseHelper.COLUMN_MATRICULA };

	public MatriculasDataSource(Context context) {
		dbHelper = new SQLiteDatabaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Matricula createMatricula(String matricula) {
		ContentValues values = new ContentValues();
		values.put(SQLiteDatabaseHelper.COLUMN_MATRICULA, matricula);
		long insertId = database.insert(SQLiteDatabaseHelper.TABLE_MATRICULA, null,
				values);
		Cursor cursor = database.query(SQLiteDatabaseHelper.TABLE_MATRICULA,
				allColumns, SQLiteDatabaseHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Matricula newMatricula = cursorToMatricula(cursor);
		cursor.close();
		return newMatricula;
	}

	public void deleteMatricula(Matricula matricula) {
		long id = matricula.getId();
		System.out.println("Matricula deleted with id: " + id);
		database.delete(SQLiteDatabaseHelper.TABLE_MATRICULA, SQLiteDatabaseHelper.COLUMN_ID
				+ " = " + id, null);
	}
	
	public Matricula getMatricula(String matricula) {
		
		Matricula matriculaObj = null;
		Cursor cursor = database.query(SQLiteDatabaseHelper.TABLE_MATRICULA,
				allColumns, SQLiteDatabaseHelper.COLUMN_MATRICULA + " = '" + matricula +"'", null,
				null, null, null);
		if(cursor.getCount() != 0){
			cursor.moveToFirst();
			matriculaObj = cursorToMatricula(cursor);
		}
		cursor.close();
		return matriculaObj;
	}
	
	public Matricula getOrCreateMatricula(String matricula){
		
		Matricula matriculaObj = this.getMatricula(matricula);
		if(matriculaObj == null){
			matriculaObj = this.createMatricula(matricula);
		}
		return matriculaObj;
	}

	public List<Matricula> getAllMatriculas() {
		List<Matricula> matriculas = new ArrayList<Matricula>();

		Cursor cursor = database.query(SQLiteDatabaseHelper.TABLE_MATRICULA,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Matricula matricula = cursorToMatricula(cursor);
			matriculas.add(matricula);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return matriculas;
	}

	private Matricula cursorToMatricula(Cursor cursor) {
		Matricula matricula = new Matricula();
		matricula.setId(cursor.getLong(0));
		matricula.setMatricula(cursor.getString(1));
		return matricula;
	}
}
