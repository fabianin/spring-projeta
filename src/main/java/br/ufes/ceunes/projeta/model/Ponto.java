package br.ufes.ceunes.projeta.model;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Ponto {
	
	@Id
	Membro membro;
	Instant entrada;
	Instant saida;
	
	public Ponto(Membro membro, Instant agora) {
		super();
		this.membro = membro;
		this.entrada = agora;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public Instant getEntrada() {
		return entrada;
	}

	public void setEntrada(Instant entrada) {
		this.entrada = entrada;
	}

	public Instant getSaida() {
		return saida;
	}

	public void setSaida(Instant saida) {
		this.saida = saida;
	}
	
	
}
