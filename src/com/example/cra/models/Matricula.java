package com.example.cra.models;
import java.io.Serializable; 

public class Matricula implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4352081547017316237L;
	
	private long id;
	private String matricula;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return matricula;
	}
}
