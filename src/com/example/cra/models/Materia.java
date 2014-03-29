package com.example.cra.models;

import java.util.ArrayList;
import java.util.List;

public class Materia {

	private long id;
	private String nome;
	private Integer credito;
	
	public Materia(String nome, Integer credito) {
		super();
		this.nome = nome;
		this.credito = credito;
	}


	public Materia() {
		super();
	}


	public Integer getCredito() {
		return credito;
	}

	public void setCredito(Integer credito) {
		this.credito = credito;
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
		return nome;
	}

}
