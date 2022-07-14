package com.br.p2as.model.comunicacao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.br.p2as.model.pessoa.Pessoa;
import com.br.p2as.model.profissional.Servico;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="TB_008_MENSAGEM")
public class Mensagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "ID_COMUNICACAO")
	private Comunicacao comunicacao;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "ID_SERVICO")
	private Servico servico;
	
	@OneToOne
	@JoinColumn(name = "DH_PESSOA")
	private Pessoa pessoa;
	
	@Column(name = "TX_TEXTO")
	private String texto;
	
	@Column(name = "DH_INATIVACAO")
	private LocalDateTime envio;

	public Mensagem(Comunicacao comunicacao, Pessoa pessoa, Servico servico, String texto) {
		super();
		this.comunicacao = comunicacao;
		this.pessoa = pessoa;
		this.servico = servico;
		this.texto = texto;
		this.envio = LocalDateTime.now();
	}

	public Mensagem() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Comunicacao getComunicacao() {
		return comunicacao;
	}

	public void setComunicacao(Comunicacao comunicacao) {
		this.comunicacao = comunicacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getEnvio() {
		return envio;
	}

	public void setEnvio(LocalDateTime envio) {
		this.envio = envio;
	}
	
}
