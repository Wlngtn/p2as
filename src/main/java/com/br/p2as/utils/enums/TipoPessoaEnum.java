package com.br.p2as.utils.enums;

public enum TipoPessoaEnum {
	C("Cliente"), P("Profissional"), T("Todos");
	
	private String descricao;
	
	TipoPessoaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isCliente() {
		return this.equals(TipoPessoaEnum.C);
	}
	
	public boolean isProfissional() {
		return this.equals(TipoPessoaEnum.P);
	}
	
	public boolean isAmbos() {
		return this.equals(TipoPessoaEnum.T);
	}
}
