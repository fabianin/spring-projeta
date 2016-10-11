package br.ufes.ceunes.projeta.model;

public enum TipoCargo {
	TRAINEE("Trainee"), ASSESSOR("Assessor"), DIRETOR("Diretor");
	
	private String descricao;


	TipoCargo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
