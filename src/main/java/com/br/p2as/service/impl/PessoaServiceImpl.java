package com.br.p2as.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.p2as.model.pessoa.Pessoa;
import com.br.p2as.repository.PessoaRepository;
import com.br.p2as.service.IPessoaService;
import com.br.p2as.utils.enums.TipoPessoaEnum;

@Component
public class PessoaServiceImpl implements IPessoaService{

	@Autowired
	private PessoaRepository repository;

	@Override
	public Pessoa criarPessoa(Pessoa pessoa, TipoPessoaEnum tipoPessoaNovo) {
		
		Pessoa pessoaBusca = repository.getByCpfCnpj(pessoa.getCpfCnpj());
		
		if(pessoaBusca != null) {
			pessoaBusca.insereTipoPessoa(tipoPessoaNovo);
			pessoaBusca = repository.saveAndFlush(pessoaBusca);
			return pessoaBusca;
		}
		
		pessoa.setTipoPessoaFisicaJuridica(pessoa.retornaTipoFisicaJurida());
		pessoa.insereTipoPessoa(tipoPessoaNovo);
		
		pessoa = repository.save(pessoa);
		return pessoa;
	}
	
	@Override
	public List<Pessoa> buscarTodos() {
		return repository.findAll();
	}

	@Override
	public Pessoa buscarPorId(Long id) {
		Pessoa pessoa = repository.getById(id);
		return pessoa;
	}

}
