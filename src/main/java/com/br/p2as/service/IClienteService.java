package com.br.p2as.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.p2as.model.pessoa.Cliente;

@Service
public interface IClienteService {

	Cliente criarCliente(Cliente cliente);

	List<Cliente> buscarTodos();

	Cliente buscarPorId(Long id);

	void excluirCliente(Cliente cliente);

}
