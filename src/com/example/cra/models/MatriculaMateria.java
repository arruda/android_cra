package com.example.cra.models;


public class MatriculaMateria {

	private long id;
	private long matricula_id;
	private long materia_id;
	private double nota;
//	
//	public MatriculaMateria(String nome, Integer creditos, Integer periodo) {
//		super();
//		this.nome = nome;
//		this.creditos = creditos;
//		this.periodo = periodo;
//	}


	public MatriculaMateria() {
		super();
	}



	public long getMatricula_id() {
		return matricula_id;
	}



	public void setMatricula_id(long matricula_id) {
		this.matricula_id = matricula_id;
	}



	public long getMateria_id() {
		return materia_id;
	}



	public void setMateria_id(long materia_id) {
		this.materia_id = materia_id;
	}



	public double getNota() {
		return nota;
	}



	public void setNota(double nota) {
		this.nota = nota;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	@Override
	public String toString() {
		return "" + matricula_id + " - " + materia_id+" - " + nota;
	}

}
