package br.ufes.ceunes.projeta.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Membro {
	private String nome;
	private String sobrenome;
	private String curso;
	
	@Id
	private Long matricula;
	private LocalDate dataAdmissao;
	private LocalDate dataNascimento;
	private LocalDate dataDemissao;
	private String senha;
	private String dicaSenha;
	private String naturalidade;
	private TipoCargo cargo;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Ponto> pontos = new ArrayList<>();
	
	
	public List<Ponto> getPontos() {
		return pontos;
	}
	

	public TipoCargo getCargo() {
		return cargo;
	}
	public void setCargo(TipoCargo cargo) {
		this.cargo = cargo;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Long getMatricula() {
		return matricula;
	}
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}


	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public LocalDate getDataDemissao() {
		return dataDemissao;
	}
	public void setDataDemissao(LocalDate dataDemissao) {
		this.dataDemissao = dataDemissao;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getDicaSenha() {
		return dicaSenha;
	}
	public void setDicaSenha(String dicaSenha) {
		this.dicaSenha = dicaSenha;
	}
	
	

}
