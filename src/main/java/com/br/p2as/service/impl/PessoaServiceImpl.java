package com.br.p2as.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.p2as.model.pessoa.Pessoa;
import com.br.p2as.repository.PessoaRepository;
import com.br.p2as.service.IPessoaService;

@Component
public class PessoaServiceImpl implements IPessoaService{

	@Autowired
	private PessoaRepository repository;
	
	@Override
	public Pessoa criarPessoa(Pessoa pessoa) throws Exception {
		pessoa = repository.save(pessoa);
		return pessoa;
	}

	@Override
	public List<Pessoa> buscarTodos() {
		return repository.findAll();
	}

	@Override
	public Pessoa buscarPorId(Long id) {
		return repository.getById(id);
	}

	@Override
	public void excluirPessoa(Pessoa pessoa) {
		repository.deleteById(pessoa.getId());		
	}
	
}
