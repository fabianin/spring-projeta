package br.ufes.ceunes.projeta.controllers;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Ponto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	Integer matricula;
	LocalDate entrada;
	LocalDate saida;
	
	public Ponto(Integer matricula, LocalDate entrada) {
		super();
		this.matricula = matricula;
		this.entrada = entrada;
	}
	
	
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public LocalDate getEntrada() {
		return entrada;
	}
	public void setEntrada(LocalDate entrada) {
		this.entrada = entrada;
	}
	public LocalDate getSaida() {
		return saida;
	}
	public void setSaida(LocalDate saida) {
		this.saida = saida;
	}

	
	

}
