package br.ufes.ceunes.projeta.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PontoAberto {
	@Id
	private Long matricula;
	private Instant entrada;	
	
	
	
	public PontoAberto() {
		super();
	}
	public Long getMatricula() {
		return matricula;
	}
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	public Instant getEntrada() {
		return entrada;
	}
	public void setEntrada(Instant entrada) {
		this.entrada = entrada;
	}
	public PontoAberto(Long matricula, Instant entrada) {
		super();
		this.matricula = matricula;
		this.entrada = entrada;
	}
}
