package com.example.cra.models;


public class MatriculaMateria {

	private long id;
	private long matricula_id;
	private long meteria_id;
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



	public long getMeteria_id() {
		return meteria_id;
	}



	public void setMeteria_id(long meteria_id) {
		this.meteria_id = meteria_id;
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
		return ""+id;
	}

}
