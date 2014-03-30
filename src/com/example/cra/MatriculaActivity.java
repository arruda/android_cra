package com.example.cra;

import java.util.Iterator;
import java.util.List;

import com.example.cra.daos.MateriasDataSource;
import com.example.cra.daos.MatriculasDataSource;
import com.example.cra.models.Matricula;
import com.example.cra.util.JsonConfigLoader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

public class MatriculaActivity extends ActionBarActivity implements
		OnClickListener {

	private MatriculasDataSource matriculasDatasource;
	private MateriasDataSource materiasDatasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_matricula);

		matriculasDatasource = new MatriculasDataSource(this);
		matriculasDatasource.open();
		
		materiasDatasource = new MateriasDataSource(this);
		materiasDatasource.open();
		
		//add as materias padrões, nesse caso se ja houver todas
		//as materias, então ele não faz nada nesse ponto
		String materiasJson = JsonConfigLoader.loadJSONFromAsset(this);
		materiasDatasource.addDefaultMaterias(materiasJson);
//
//		matriculasDatasource.createMatricula("123");
//		matriculasDatasource.createMatricula("321");
//		matriculasDatasource.createMatricula("000");
//		List<Matricula> values = matriculasDatasource.getAllMatriculas();
//		for (Matricula matricula : values) {
//			matriculasDatasource.deleteMatricula(matricula);
//		}
//		Log.w("LISTA DAS MATRICULAS", values.toString());
		
		final Button button = (Button) findViewById(R.id.entrar_matricula);
		button.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.matricula, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_matricula,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		EditText campoMatricula = (EditText) findViewById(R.id.matricula);
		String matricula = campoMatricula.getText().toString();

		Matricula matriculaObj = matriculasDatasource.getOrCreateMatricula(matricula);
		List<Matricula> values = matriculasDatasource.getAllMatriculas();
		materiasDatasource.getAllMaterias();
		Log.w("MATRICULA SELECIONADA", matriculaObj.toString());
//		Intent intent = new Intent(MatriculaActivity.this,
//				CalculoActivity.class);
//		startActivity(intent);

	}

	  @Override
	  protected void onResume() {
		  matriculasDatasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
		  matriculasDatasource.close();
	    super.onPause();
	  }

}
