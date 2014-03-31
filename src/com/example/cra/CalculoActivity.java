package com.example.cra;

import java.util.List;

import com.example.cra.daos.MateriasDataSource;
import com.example.cra.daos.MatriculaMateriasDataSource;
import com.example.cra.models.Materia;
import com.example.cra.models.Matricula;
import com.example.cra.models.MatriculaMateria;

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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class CalculoActivity extends ActionBarActivity {

	private MateriasDataSource materiasDatasource;
	private MatriculaMateriasDataSource matriculaMateriasDatasource;
	private Matricula matriculaObj;
	private TextView matricula_txt;
	private TextView carga_curso_txt;
	private TextView carga_cumprida_txt;
	private TextView cra_txt;
	private Button add_materia;
	private Button recalcular_bt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculo);

		materiasDatasource = new MateriasDataSource(this);
		materiasDatasource.open();
		matriculaMateriasDatasource = new MatriculaMateriasDataSource(this);
		matriculaMateriasDatasource.open();
		
		matriculaObj = (Matricula)getIntent().getSerializableExtra("MATRICULA");
		 
		matricula_txt = (TextView) findViewById(R.id.calculo_matricula);
		carga_curso_txt = (TextView) findViewById(R.id.calculo_carga_curso);
		carga_cumprida_txt = (TextView) findViewById(R.id.calculo_carga_cumprida);
		cra_txt = (TextView) findViewById(R.id.calculo_cra);
		add_materia = (Button) findViewById(R.id.calculo_add_materia);
		recalcular_bt = (Button) findViewById(R.id.calculo_recalcularbt);
		
		Log.w("matricula", matriculaObj.toString());
		matricula_txt.setText(matriculaObj.getMatricula());
		
		add_materia.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(CalculoActivity.this,
						MateriaActivity.class);
				
				Bundle params = new Bundle(); 

				params.putSerializable("MATRICULA", matriculaObj); 
		        intent.putExtras(params); 
		        startActivityForResult(intent, 1);
				
			}
		});

		recalcular_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				recalcular();
				
			}
		});
		  
		recalcular();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculo, menu);
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

	public void recalcular(){
		List<MatriculaMateria> matMats = matriculaMateriasDatasource.getAllMatriculaMateriasPorMatricula(matriculaObj.getId());

		//não sei se ta calculando certo, acho q ta errado isso =S
		//mas ai é so questão de arrumar a funcao de calculo de cra
		Double carga_cumprida = 0.0;
		Double cra = 0.0;
		if(matMats.size() != 0){
			
			for (MatriculaMateria matriculaMateria : matMats) {
				Materia materia = materiasDatasource.getMateria(matriculaMateria.getMateria_id());
				carga_cumprida+= materia.getCreditos();
				cra += matriculaMateria.getNota() * carga_cumprida;
				Log.v("RECALCULO: MATERIA", materia.getNome());
				Log.v("RECALCULO: MATERIA-Nota", String.valueOf(matriculaMateria.getNota()));
			}
		}
		cra = cra/carga_cumprida;
		carga_cumprida_txt.setText(carga_cumprida.toString());
		cra_txt.setText(cra.toString());
	}
	  @Override
	  protected void onResume() {
		  materiasDatasource.open();
		  matriculaMateriasDatasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
		  materiasDatasource.close();
		  matriculaMateriasDatasource.close();
	    super.onPause();
	  }


}
