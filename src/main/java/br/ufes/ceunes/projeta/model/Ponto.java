package br.ufes.ceunes.projeta.model;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Ponto {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	Long matricula;
	Instant entrada;
	Instant saida;
	Long duracaoHours;
	Long duracaoMinutes;
	Long duracao=Long.parseLong("2");
	
	
	public Ponto() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ponto other = (Ponto) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	
	public Ponto(Long matricula, Instant entrada, Instant saida) {
		super();
		this.matricula = matricula;
		this.entrada = entrada;
		this.saida = saida;
		duracaoHours = entrada.until(saida, ChronoUnit.HOURS);
		entrada = entrada.plus(duracaoHours, ChronoUnit.HOURS);
		duracaoMinutes = entrada.until(saida, ChronoUnit.MINUTES);
		entrada = entrada.minus(duracaoHours, ChronoUnit.HOURS);
	}

	public Long getDuracaoHours() {
		return duracaoHours;
	}

	public void setDuracaoHours(Long duracaoHours) {
		this.duracaoHours = duracaoHours;
	}

	public Long getDuracaoMinutes() {
		return duracaoMinutes;
	}

	public void setDuracaoMinutes(Long duracaoMinutes) {
		this.duracaoMinutes = duracaoMinutes;
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

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	
	
}
