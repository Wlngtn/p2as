package com.br.p2as.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.br.p2as.model.enums.SimNaoEnum;


@Entity
@Table(name="TB_001_PESSOA")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TX_CPF", nullable = false, length = 11, unique = true, updatable = false)
	private String cpf;
	
	@Column(name = "TX_NOME", nullable = false, length = 100, unique = true, updatable = false)
	private String nome;

	@Column(name = "TX_STATUS", length = 1)
	@Enumerated(EnumType.STRING)
	private SimNaoEnum status = SimNaoEnum.S;
	
	
	public Pessoa(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
	}
	
	public Pessoa() {}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public SimNaoEnum getStatus() {
		return status;
	}

	public void setStatus(SimNaoEnum status) {
		this.status = status;
	}
	
}
