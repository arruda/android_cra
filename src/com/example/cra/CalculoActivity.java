package com.example.cra;

import com.example.cra.models.Matricula;

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

	private Matricula matriculaObj;
	private TextView matricula_txt;
	private TextView carga_curso_txt;
	private TextView carga_cumprida_txt;
	private TextView cra_txt;
	private Button add_materia;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculo);

		matriculaObj = (Matricula)getIntent().getSerializableExtra("MATRICULA");
		 
		matricula_txt = (TextView) findViewById(R.id.calculo_matricula);
		carga_curso_txt = (TextView) findViewById(R.id.calculo_carga_curso);
		carga_cumprida_txt = (TextView) findViewById(R.id.calculo_carga_cumprida);
		cra_txt = (TextView) findViewById(R.id.calculo_carga_cumprida);
		add_materia = (Button) findViewById(R.id.calculo_add_materia);
		
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
				startActivity(intent);
				
			}
		});
		  

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


}
