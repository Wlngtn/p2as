package com.br.p2as.webApi.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.p2as.exception.ErrorServiceException;
import com.br.p2as.exception.PessoaExistsException;
import com.br.p2as.exception.PessoaNotFoundException;
import com.br.p2as.model.pessoa.Cliente;
import com.br.p2as.service.IClienteService;

@RestController
@RequestMapping(value="/v1/api")
public class ClienteResource {
	
	@Autowired
	private IClienteService service;
	
	@GetMapping("/clientes")
	public List<Cliente> getClientes() {
		List<Cliente> clientes = service.buscarTodos();
		return clientes;
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
		try {
			cliente = service.criarCliente(cliente);
			
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(cliente.getId())
					.toUri();
			
			return ResponseEntity.created(location).build();
			
		}catch (PessoaExistsException pe) {
			throw new PessoaExistsException(cliente.getPessoa().getCpfCnpj(), cliente.getPessoa().getNome());
		} catch (Exception e) {
			throw new ErrorServiceException();
		}
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
