package com.br.p2as.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.p2as.model.endereco.Endereco;
import com.br.p2as.model.pessoa.Pessoa;

@Service
public interface IEnderecoService {

	Endereco criarEndereco(Endereco endereco) throws Exception;

	List<Endereco> buscarTodos();

	Endereco buscarPorId(Long id);

	void excluirEndereco(Endereco endereco);

	List<Endereco> buscarEnderecosPessoa(Pessoa pessoa);

}
