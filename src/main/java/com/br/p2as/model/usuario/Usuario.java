package com.br.p2as.model.usuario;

import java.time.LocalDateTime;

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
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="TB_004_USUARIO")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TX_LOGIN", nullable = false, length = 8)
	private String login;
	
	@JsonIgnore
	@Column(name = "TX_SENHA", nullable = false)
	private String senha;
	
	@JsonIgnore
	@Column(name = "TX_TENANT_ID")
	private String tenantId;
	
	@JsonIgnore
	@Column(name = "TX_TENANT_SECRET")
	private String tenantSecret;
	
	@Column(name = "TX_CLIENT_ID")
	private String clientId;
	
	@Column(name = "TX_CLIENT_SECRET")
	private String clientSecret;
	
	@Column(name = "DH_CRIACAO")
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	@Column(name = "DH_INATIVACAO")
	private LocalDateTime dataInativacao;
	
	@Column(name = "TX_STATUS", length = 1, nullable = false)
	@Enumerated(EnumType.STRING)
	private SimNaoEnum status = SimNaoEnum.S;
	
	@OneToOne
	private Pessoa pessoa;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantSecret() {
		return tenantSecret;
	}

	public void setTenantSecret(String tenantSecret) {
		this.tenantSecret = tenantSecret;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataInativacao() {
		return dataInativacao;
	}

	public void setDataInativacao(LocalDateTime dataInativacao) {
		this.dataInativacao = dataInativacao;
	}

	public SimNaoEnum getStatus() {
		return status;
	}

	public void setStatus(SimNaoEnum status) {
		this.status = status;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
