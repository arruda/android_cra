package com.example.cra.daos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.cra.helpers.SQLiteDatabaseHelper;
import com.example.cra.models.Materia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;

public class MateriasDataSource {

	// Database fields
	private SQLiteDatabase database;
	private SQLiteDatabaseHelper dbHelper;
	private String[] allColumns = { 
				SQLiteDatabaseHelper.COLUMN_ID,
				SQLiteDatabaseHelper.COLUMN_NOME,  
				SQLiteDatabaseHelper.COLUMN_CREDITOS,  
				SQLiteDatabaseHelper.COLUMN_PERIODO,  
			};

	public MateriasDataSource(Context context) {
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

	public Materia createMateria(String nome, Integer creditos, Integer periodo) {

		ContentValues values = new ContentValues();
		values.put(SQLiteDatabaseHelper.COLUMN_NOME, nome);
		values.put(SQLiteDatabaseHelper.COLUMN_CREDITOS, creditos);
		values.put(SQLiteDatabaseHelper.COLUMN_PERIODO, periodo);
		
		long insertId = database.insert(SQLiteDatabaseHelper.TABLE_MATERIA, null,
				values);
		
		Log.i(this.getClass().getName() + "create", "CREATE");
		Cursor cursor = database.query(SQLiteDatabaseHelper.TABLE_MATERIA,
				allColumns, SQLiteDatabaseHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Materia newMateria = cursorToMateria(cursor);
		cursor.close();
		return newMateria;
	}

	public Materia persistMateria(Materia materia) {
		Materia newMateria = createMateria(
								materia.getNome(),
								materia.getCreditos(),
								materia.getPeriodo()
							);
		return newMateria;
	}

	public void deleteMateria(Materia materia) {
		long id = materia.getId();
		Log.i(this.getClass().getName() + "delete", "DELETE");
		database.delete(SQLiteDatabaseHelper.TABLE_MATERIA, SQLiteDatabaseHelper.COLUMN_ID
				+ " = " + id, null);
	}
	
	public Materia getMateria(String nome) {
		
		Materia obj = null;
		Cursor cursor = database.query(SQLiteDatabaseHelper.TABLE_MATERIA,
				allColumns, SQLiteDatabaseHelper.COLUMN_NOME + " = '" + nome +"'", null,
				null, null, null);
		Log.i(this.getClass().getName() + "get", "GET");
		if(cursor.getCount() != 0){
			cursor.moveToFirst();
			obj = cursorToMateria(cursor);
		}
		cursor.close();
		return obj;
	}

	public List<Materia> getAllMaterias() {
		List<Materia> materias = new ArrayList<Materia>();
		Cursor cursor = database.query(SQLiteDatabaseHelper.TABLE_MATERIA,
				allColumns, null, null, null, null, null);

		Log.i(this.getClass().getName() + "getAll", "GET_ALL");
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Materia materia = cursorToMateria(cursor);
			materias.add(materia);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		Log.i(this.getClass().getName() + "getAll: ", materias.toString());
		return materias;
	}
	
	public List<Materia> getAllMateriasOrderByPeriodo() {
		List<Materia> materias = new ArrayList<Materia>();
		Cursor cursor = database.query(SQLiteDatabaseHelper.TABLE_MATERIA,
				allColumns, null, null, null, null, SQLiteDatabaseHelper.COLUMN_PERIODO);

		Log.i(this.getClass().getName() + "getAll", "GET_ALL_BY_PERIODO");
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Materia materia = cursorToMateria(cursor);
			materias.add(materia);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		Log.i(this.getClass().getName() + "getAll: ", materias.toString());
		return materias;
	}

	private Materia cursorToMateria(Cursor cursor) {
		Materia materia = new Materia();
		materia.setId(cursor.getLong(0));
		materia.setNome(cursor.getString(1));
		materia.setCreditos(cursor.getInt(2));
		materia.setPeriodo(cursor.getInt(3));
		return materia;
	}

	
	public List<Materia> addDefaultMaterias(String materiasJson){
		List<Materia> materias = getAllMaterias();

		Log.i(this.getClass().getName() + "addDefault: ", "DEFAULT");
		List<Materia> materiasDefault = getDefaultMaterias(materiasJson);

		if(materias.size() >= materiasDefault.size()){
			return materias;
		}
		
		List<Materia> materiasPersisted = new ArrayList<Materia>();
		for (Materia materia : materiasDefault) {
			materiasPersisted.add(persistMateria(materia));
		}
		return materiasPersisted;
	}
	
	/**
	 * Seria legal aqui usar a lib GSON e abrir um json que tem a lista de cada materia
	 * dai é só mandar ler o arquivo e na hora de parsear o gson dizer que é da classe: Materia[].class
	 * que ele vai popular cada objeto sozinho.
	 * 
	 */
	private List<Materia> getDefaultMaterias(String json){
		Gson gson = new Gson();
		Materia[] materiasVet = gson.fromJson(json, Materia[].class);   

		List<Materia> materias = new ArrayList<Materia>(Arrays.asList(materiasVet));
		
//		Materia fsi = new Materia("FSI", 4, 1);
//		materias.add(fsi);
		
		return materias;
	}
}
