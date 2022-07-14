package com.br.p2as.utils.enums;

public enum TipoPessoaFisicaJuridicaEnum {
	F("Física"), J("Jurídica");
	
	private String descricao;
	
	TipoPessoaFisicaJuridicaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isPessoaFisica() {
		return this.equals(TipoPessoaFisicaJuridicaEnum.F);
	}
	
	public boolean isPessoaJuridica() {
		return this.equals(TipoPessoaFisicaJuridicaEnum.J);
	}

}
