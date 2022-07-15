package com.br.p2as.model.profissional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.br.p2as.model.pessoa.Pessoa;
import com.br.p2as.model.pessoa.Profissional;
import com.br.p2as.utils.enums.SimNaoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="TB_006_SERVICO")
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TX_CODIGO", nullable = false, length = 6, unique = true, updatable = false)
	private String codigo;
	@Column(name = "TX_NOME", nullable = false, length = 100)
	private String nome;
	
	@Column(name = "TX_DESCRICAO")
	private String descricao;
	
	@Column(name = "ST_ATIVO", length = 1, nullable = false)
	@Enumerated(EnumType.STRING)
	private SimNaoEnum ativo = SimNaoEnum.S;
	
	@Column(name = "ST_EM_ATENDIMENTO", length = 1, nullable = false)
	@Enumerated(EnumType.STRING)
	private SimNaoEnum emAtendimento = SimNaoEnum.N;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "ID_PROFISSIONAL")
	private Profissional profissional;
	
	public Servico(String nome, String descricao, Profissional profissional) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.profissional = profissional;
	}

	public Servico() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public SimNaoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(SimNaoEnum ativo) {
		this.ativo = ativo;
	}

	public SimNaoEnum getEmAtendimento() {
		return emAtendimento;
	}

	public void setEmAtendimento(SimNaoEnum emAtendimento) {
		this.emAtendimento = emAtendimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	
}
