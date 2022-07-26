package com.br.p2as.model.comunicacao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.br.p2as.model.pessoa.Cliente;
import com.br.p2as.model.pessoa.Profissional;
import com.br.p2as.model.profissional.Servico;
import com.br.p2as.utils.enums.SimNaoEnum;

@Entity
@Table(name="TB_007_COMUNICACAO")
public class Comunicacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name = "ID_PROFISSIONAL")
	private Profissional profissional;
	
	@OneToOne
	@JoinColumn(name = "ID_SERVICO")
	private Servico servico;
	
	@OneToMany(mappedBy = "comunicacao")
	private List<Mensagem> mensagens;
	
	@Column(name = "ST_ATIVO", length = 1, nullable = false)
	@Enumerated(EnumType.STRING)
	private SimNaoEnum ativo = SimNaoEnum.S;
	
	@Column(name = "DH_CRIACAO")
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	@Column(name = "DH_INATIVACAO")
	private LocalDateTime dataInativacao;

	public Comunicacao() {
		super();
	}

	public Comunicacao(Cliente cliente, Profissional profissional, Servico servico) {
		this.cliente = cliente;
		this.profissional = profissional;
		this.servico = servico;
		this.dataCriacao = LocalDateTime.now();
		this.ativo = SimNaoEnum.S;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public SimNaoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(SimNaoEnum ativo) {
		this.ativo = ativo;
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

	public void finalizar() {
		setAtivo(SimNaoEnum.N);
		setDataInativacao(LocalDateTime.now());
	}
	
}
