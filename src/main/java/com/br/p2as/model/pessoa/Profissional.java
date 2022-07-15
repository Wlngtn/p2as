package com.br.p2as.model.pessoa;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.br.p2as.model.profissional.LocalAtual;
import com.br.p2as.model.profissional.Servico;
import com.br.p2as.model.usuario.Usuario;

@Entity
@Table(name="TB_002_PROFISSIONAL")
public class Profissional{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "profissional")
	private List<Servico> servicos;
	
	@OneToMany(mappedBy = "profissional")
	private List<LocalAtual> localAtual;
	
	@OneToOne
	@JoinColumn(name="ID_PESSOA")
	private Pessoa pessoa;

	public Profissional() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<LocalAtual> getLocalAtual() {
		return localAtual;
	}

	public void setLocalAtual(List<LocalAtual> localAtual) {
		this.localAtual = localAtual;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
