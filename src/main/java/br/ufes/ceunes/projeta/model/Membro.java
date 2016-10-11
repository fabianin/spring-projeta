package br.ufes.ceunes.projeta.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import br.ufes.ceunes.projeta.controllers.Ponto;

@Entity
public class Membro {
	private String nome;
	private String sobrenome;
	private String curso;
	@Id
	private Integer matricula;
	private LocalDate dataAdmissao;
	private LocalDate dataNascimento;
	private LocalDate dataDemissao;
	private String senha;
	private String dicaSenha;
	private String naturalidade;
	private TipoCargo cargo;
	private Ponto ponto = null;
	
	
	public Ponto getPonto() {
		return ponto;
	}
	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
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
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
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
