package com.example.cra;

import com.example.cra.daos.MateriasDataSource;
import com.example.cra.daos.MatriculaMateriasDataSource;
import com.example.cra.models.Materia;
import com.example.cra.models.Matricula;
import com.example.cra.models.MatriculaMateria;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class NotaActivity extends ActionBarActivity {
	
	MatriculaMateriasDataSource matriculaMateriasDatasource;
	private Matricula matriculaObj;
	private Materia materiaObj;
	private MatriculaMateria matMat;
	private EditText nota_txt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nota);

		matriculaObj = (Matricula)getIntent().getSerializableExtra("MATRICULA");
		materiaObj = (Materia)getIntent().getSerializableExtra("MATERIA");
		nota_txt = (EditText) findViewById(R.id.nota_nota);

		matriculaMateriasDatasource = new MatriculaMateriasDataSource(this);
		matriculaMateriasDatasource.open();
		matMat = matriculaMateriasDatasource.getMatriculaMateriaOrCreate(
				matriculaObj.getId(), 
				materiaObj.getId()
				);
		Log.v("MATMAT", matMat.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nota, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
