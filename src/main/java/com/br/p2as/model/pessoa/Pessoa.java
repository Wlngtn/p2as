package com.br.p2as.model.pessoa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.br.p2as.model.endereco.Endereco;
import com.br.p2as.model.usuario.Usuario;
import com.br.p2as.utils.enums.SimNaoEnum;
import com.br.p2as.utils.enums.TipoPessoaEnum;
import com.br.p2as.utils.enums.TipoPessoaFisicaJuridicaEnum;

@Entity
@Table(name="TB_000_PESSOA")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TX_CPF_CNPJ", nullable = false, length = 11, unique = true, updatable = false)
	private String cpfCnpj;
	
	@Column(name = "TX_NOME", nullable = false, length = 100, unique = true, updatable = false)
	private String nome;

	@Column(name = "TX_STATUS", length = 1, nullable = false)
	@Enumerated(EnumType.STRING)
	private SimNaoEnum status = SimNaoEnum.S;

	@OneToMany(mappedBy = "pessoa")
	private List<Endereco> enderecos;
	
	@Column(name = "TP_PESSOA", length = 1, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoPessoaEnum tipoPessoa;
	
	@Column(name = "TP_PESSOA_FISICA_JURIDICA", length = 1, nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoPessoaFisicaJuridicaEnum tipoPessoaFisicaJuridica;
	
	@Column(name = "TX_EMAIL", nullable = false)
	private String email;
	
	@Transient
	private Usuario usuario;
	
	public Pessoa(String cpf, String nome) {
		this.cpfCnpj = cpf;
		this.nome = nome;
	}
	
	public Pessoa() {}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}


	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
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

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public TipoPessoaEnum getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoPessoaFisicaJuridicaEnum getTipoPessoaFisicaJuridica() {
		return tipoPessoaFisicaJuridica;
	}

	public void setTipoPessoaFisicaJuridica(TipoPessoaFisicaJuridicaEnum tipoPessoaFisicaJuridica) {
		this.tipoPessoaFisicaJuridica = tipoPessoaFisicaJuridica;
	}

	public TipoPessoaFisicaJuridicaEnum retornaTipoFisicaJuridica() {
		return this.getCpfCnpj().length() == 11 ? TipoPessoaFisicaJuridicaEnum.F : TipoPessoaFisicaJuridicaEnum.J;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void insereTipoPessoa(TipoPessoaEnum tipoNovo) {
		if(this.tipoPessoa == null) {
			tipoPessoa = tipoNovo;
		}else if(!this.tipoPessoa.equals(tipoNovo)){
			this.tipoPessoa = TipoPessoaEnum.T;
		}
	}

}
