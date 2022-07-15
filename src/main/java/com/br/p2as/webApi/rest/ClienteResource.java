package com.br.p2as.webApi.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.p2as.exception.PessoaNotFoundException;
import com.br.p2as.model.pessoa.Cliente;
import com.br.p2as.service.IClienteService;

@RestController
@RequestMapping(value="/v1/api")
public class ClienteResource {
	
	@Autowired
	private IClienteService service;
	
	@GetMapping("/clientes")
	public ResponseEntity<Object> getClientes() {
		List<Cliente> clientes = service.buscarTodos();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientes);
	}
	
	@GetMapping("/clientes/{id}")
	public Cliente getCliente(@PathVariable(value="id") long id) {
		Cliente cliente = service.buscarPorId(Long.valueOf(id));
		if(cliente == null) {
			throw new PessoaNotFoundException("id - " + id);
		}
		return cliente;
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<Object> addClientes(@RequestBody Cliente cliente) {
		cliente = service.criarCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}
	
	@DeleteMapping("/clientes")
	public void deleteClientes(@RequestBody Cliente cliente) {
		
		Cliente clienteBusca = service.buscarPorId(cliente.getId());
		
		if(clienteBusca == null) {
			throw new PessoaNotFoundException("id - " + cliente.getId());
		}
		
		service.excluirCliente(clienteBusca);
	}
}
