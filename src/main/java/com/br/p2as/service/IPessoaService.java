package com.br.p2as.service;

import java.util.List;

import com.br.p2as.model.pessoa.Pessoa;

public interface IPessoaService {

	Pessoa criarPessoa(Pessoa pessoa) throws Exception;

	List<Pessoa> buscarTodos();

	Pessoa buscarPorId(Long id);

	void excluirPessoa(Pessoa pessoa);

}
