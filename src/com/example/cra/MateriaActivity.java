package com.example.cra;

import java.util.List;

import com.example.cra.daos.MateriasDataSource;
import com.example.cra.models.Materia;
import com.example.cra.models.Matricula;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MateriaActivity extends ListActivity {

	private Matricula matriculaObj;
	private MateriasDataSource materiasDatasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materia);

		matriculaObj = (Matricula)getIntent().getSerializableExtra("MATRICULA");
		materiasDatasource = new MateriasDataSource(this);
		materiasDatasource.open();
	    List<Materia> materias = materiasDatasource.getAllMateriasOrderByPeriodo();

	    // use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<Materia> adapter = new ArrayAdapter<Materia>(this,
	        android.R.layout.simple_list_item_1, materias);
	    setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Materia materiaSelec = (Materia) getListView().getItemAtPosition(position);
		Log.v("CLICK", materiaSelec.toString());

		Intent intent = new Intent(MateriaActivity.this,
				NotaActivity.class);
		
		Bundle params = new Bundle(); 

		params.putSerializable("MATRICULA", matriculaObj); 
		params.putSerializable("MATERIA", materiaSelec); 
        intent.putExtras(params); 
		startActivity(intent);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.materia, menu);
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
		  materiasDatasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
//		  matriculasDatasource.close();
		  materiasDatasource.close();
	    super.onPause();
	  }



}
