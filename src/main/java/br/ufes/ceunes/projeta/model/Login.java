package br.ufes.ceunes.projeta.model;

public class Login {
	private Integer matricula;
	private String senha;
	private boolean relatorio;
	
	public boolean isRelatorio() {
		return relatorio;
	}
	public void setRelatorio(boolean relatorio) {
		this.relatorio = relatorio;
	}
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
