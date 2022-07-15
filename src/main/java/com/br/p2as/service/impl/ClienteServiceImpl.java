package com.br.p2as.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.p2as.exception.PessoaExistsException;
import com.br.p2as.model.pessoa.Cliente;
import com.br.p2as.model.pessoa.Pessoa;
import com.br.p2as.repository.ClienteRepository;
import com.br.p2as.service.IClienteService;
import com.br.p2as.utils.enums.TipoPessoaEnum;

@Component
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaServiceImpl pessoaService;

	@Override
	public List<Cliente> buscarTodos() {
		return repository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Cliente cliente = repository.getById(id);
		return cliente;
	}

	@Override
	public void excluirCliente(Cliente cliente) {
		repository.deleteById(cliente.getId());		
	}

	@Override
	public Cliente criarCliente(Cliente cliente) {
		Pessoa pessoa = cliente.getPessoa();
		pessoa = pessoaService.criarPessoa(pessoa, TipoPessoaEnum.C);
		
		if(verificaClientePessoa(pessoa)) {
			throw new PessoaExistsException(pessoa.getCpfCnpj(), pessoa.getNome());
		}
		
		cliente.setPessoa(pessoa);
		cliente = repository.save(cliente);
		return cliente;
	}

	private boolean verificaClientePessoa(Pessoa pessoa) {
		if(pessoa.getId() != null) {
			Cliente clienteBusca = repository.getByIdPessoa(pessoa.getId());
			if(clienteBusca != null) {
				return Boolean.TRUE;
			}
		}
			
		return Boolean.FALSE;
	}

}
