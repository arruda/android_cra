package com.example.cra.daos;

import java.util.ArrayList;
import java.util.List;

import com.example.cra.helpers.SQLiteDatabaseHelper;
import com.example.cra.models.MatriculaMateria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MatriculaMateriasDataSource {

	// Database fields
	private SQLiteDatabase database;
	private SQLiteDatabaseHelper dbHelper;
	private String[] allColumns = { 
				SQLiteDatabaseHelper.COLUMN_ID,
				SQLiteDatabaseHelper.COLUMN_MATRICULA_ID,  
				SQLiteDatabaseHelper.COLUMN_MATERIA_ID,  
				SQLiteDatabaseHelper.COLUMN_NOTA,  
			};

	public MatriculaMateriasDataSource(Context context) {
		dbHelper = new SQLiteDatabaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
		Log.i(this.getClass().getName() + "open", "DATABASE OPEN");
	}

	public void close() {
		dbHelper.close();
		Log.i(this.getClass().getName() + "close", "DATABASE CLOSE");
	}

	public MatriculaMateria createMatriculaMateria(Long matricula_id, Long materia_id, Double nota) {

		ContentValues values = new ContentValues();
		values.put(SQLiteDatabaseHelper.COLUMN_MATRICULA_ID, matricula_id);
		values.put(SQLiteDatabaseHelper.COLUMN_MATERIA_ID, materia_id);
		values.put(SQLiteDatabaseHelper.COLUMN_NOTA, nota);
		
		long insertId = database.insert(SQLiteDatabaseHelper.TABLE_MATRICULA_MATERIA, null,
				values);
		
		Log.i(this.getClass().getName() + "create", "CREATE");
		Cursor cursor = database.query(SQLiteDatabaseHelper.TABLE_MATRICULA_MATERIA,
				allColumns, SQLiteDatabaseHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		MatriculaMateria newMatmat = cursorToMatriculaMateria(cursor);
		cursor.close();
		return newMatmat;
	}

//	public MatriculaMateria persistMateria(Materia materia) {
//		Materia newMateria = createMateria(
//								materia.getNome(),
//								materia.getCreditos(),
//								materia.getPeriodo()
//							);
//		return newMateria;
//	}

	public void deleteMatriculaMateria(MatriculaMateria matmat) {
		long id = matmat.getId();
		Log.i(this.getClass().getName() + "delete", "DELETE");
		database.delete(SQLiteDatabaseHelper.TABLE_MATRICULA_MATERIA, SQLiteDatabaseHelper.COLUMN_ID
				+ " = " + id, null);
	}
	

	public MatriculaMateria getMatriculaMateria(Long matricula_id, Long materia_id) {
		
		MatriculaMateria obj = null;
		Cursor cursor = database.query(SQLiteDatabaseHelper.TABLE_MATRICULA_MATERIA,
				allColumns, 
						SQLiteDatabaseHelper.COLUMN_MATRICULA_ID + " = " + matricula_id 
						+" and "
						+ SQLiteDatabaseHelper.COLUMN_MATERIA_ID + " = " + materia_id,
				null, null, null, null);
		Log.i(this.getClass().getName() + "get", "GET");
		if(cursor.getCount() != 0){
			cursor.moveToFirst();
			obj = cursorToMatriculaMateria(cursor);
		}
		cursor.close();
		return obj;
	}

	public List<MatriculaMateria> getAllMatriculaMaterias() {
		List<MatriculaMateria> matmats = new ArrayList<MatriculaMateria>();
		Cursor cursor = database.query(SQLiteDatabaseHelper.TABLE_MATRICULA_MATERIA,
				allColumns, null, null, null, null, null);

		Log.i(this.getClass().getName() + "getAll", "GET_ALL");
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			MatriculaMateria matMat = cursorToMatriculaMateria(cursor);
			matmats.add(matMat);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return matmats;
	}

	private MatriculaMateria cursorToMatriculaMateria(Cursor cursor) {
		MatriculaMateria matMat = new MatriculaMateria();
		matMat.setId(cursor.getLong(0));
		
		matMat.setMatricula_id(cursor.getLong(1));
		matMat.setMeteria_id(cursor.getInt(2));
		matMat.setNota(cursor.getDouble(3));
		return matMat;
	}

	
}
