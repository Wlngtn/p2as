package com.br.p2as.utils.enums;

public enum SimNaoEnum {
	S("Sim", true), N("Não", false);
	
	private String descricao;
	private Boolean valor;
	
	SimNaoEnum(String descricao, Boolean valor) {
		this.descricao = descricao;
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getValor() {
		return valor;
	}

	public void setValor(Boolean valor) {
		this.valor = valor;
	}

	public boolean getIsAtivo() {
		return this.getValor();
	}

}
