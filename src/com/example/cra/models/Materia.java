package com.example.cra.models;

import java.io.Serializable;


public class Materia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8444280877045575079L;
	private long id;
	private String nome;
	private Integer creditos;
	private Integer periodo;
	
	public Materia(String nome, Integer creditos, Integer periodo) {
		super();
		this.nome = nome;
		this.creditos = creditos;
		this.periodo = periodo;
	}


	public Materia() {
		super();
	}


	public Integer getPeriodo() {
		return periodo;
	}


	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}


	public Integer getCreditos() {
		return creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome + " - " + creditos + "CR - " + periodo+ "º";
	}

}
