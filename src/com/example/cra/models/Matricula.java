package com.example.cra.models;

public class Matricula {

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
