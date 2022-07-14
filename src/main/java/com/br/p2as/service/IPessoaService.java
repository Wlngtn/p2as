package com.br.p2as.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.p2as.model.pessoa.Pessoa;
import com.br.p2as.utils.enums.TipoPessoaEnum;

@Service
public interface IPessoaService {

	List<Pessoa> buscarTodos();
	Pessoa buscarPorId(Long id);
	Pessoa criarPessoa(Pessoa pessoa, TipoPessoaEnum tipoPessoaNovo);

}
