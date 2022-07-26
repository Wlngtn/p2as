package com.br.p2as.model.endereco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.br.p2as.model.pessoa.Pessoa;
import com.br.p2as.utils.enums.SimNaoEnum;

@Entity
@Table(name="TB_003_ENDERECO")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@OneToOne
	private Pessoa pessoa;

	@Column(name = "TX_CEP", nullable = false, length = 8)
	private String cep;
	
	@Column(name = "TX_LOGRAOURO", nullable = false)
	private String logradouro;
	
	@Column(name = "TX_COMPLEMENTO")
	private String complemento;
	
	@Column(name = "NU_NUMERO")
	private Integer numero;
	
	@Column(name = "TX_CIDADE", nullable = false)
	private String cidade;
	
	@Column(name = "TX_UF", nullable = false, length = 2)
	private String uf;
	
	@Column(name = "TX_BRASIL", nullable = false)
	private String pais;

	@Column(name = "TX_STATUS", length = 1, nullable = false)
	@Enumerated(EnumType.STRING)
	private SimNaoEnum status = SimNaoEnum.S;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public SimNaoEnum getStatus() {
		return status;
	}

	public void setStatus(SimNaoEnum status) {
		this.status = status;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
}
