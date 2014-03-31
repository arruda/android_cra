package com.example.cra;

import com.example.cra.daos.MateriasDataSource;
import com.example.cra.daos.MatriculaMateriasDataSource;
import com.example.cra.models.Materia;
import com.example.cra.models.Matricula;
import com.example.cra.models.MatriculaMateria;

import android.view.View.OnClickListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class NotaActivity extends ActionBarActivity {
	
	MatriculaMateriasDataSource matriculaMateriasDatasource;
	private Matricula matriculaObj;
	private Materia materiaObj;
	private MatriculaMateria matMat;
	private EditText nota_txt;
	private TextView materia_txt;

	private Button salvar_nota;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nota);

		matriculaObj = (Matricula)getIntent().getSerializableExtra("MATRICULA");
		materiaObj = (Materia)getIntent().getSerializableExtra("MATERIA");
		nota_txt = (EditText) findViewById(R.id.nota_nota);
		materia_txt = (TextView)findViewById(R.id.nota_materia);
		materia_txt.setText(materiaObj.getNome());
		salvar_nota = (Button) findViewById(R.id.nota_salvar_nota);
		
		matriculaMateriasDatasource = new MatriculaMateriasDataSource(this);
		matriculaMateriasDatasource.open();
		matMat = matriculaMateriasDatasource.getMatriculaMateriaOrCreate(
				matriculaObj.getId(), 
				materiaObj.getId()
				);
		
		nota_txt.setText(String.valueOf(matMat.getNota()));
		
		salvar_nota.setOnClickListener(new OnClickListener() {
			
//			matMat.setNota(nota);
			@Override
			public void onClick(View v) {
				Double nota = Double.valueOf(nota_txt.getText().toString());
				matMat.setNota(nota);
				Log.v("NOVANOTA", nota.toString());
				matriculaMateriasDatasource.updateMatriculaMateria(
							matriculaObj.getId(),
							materiaObj.getId(), 
							nota);
				
			}
		});
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
	
	  @Override
	  protected void onResume() {
//		  matriculasDatasource.open();
		  matriculaMateriasDatasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
//		  matriculasDatasource.close();
		  matriculaMateriasDatasource.close();
	    super.onPause();
	  }


}
