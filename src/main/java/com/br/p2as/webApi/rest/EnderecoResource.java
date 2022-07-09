package com.br.p2as.webApi.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.p2as.model.endereco.Endereco;
import com.br.p2as.service.IEnderecoService;

@RestController
@RequestMapping(value="/api")
public class EnderecoResource {
	
	@Autowired
	private IEnderecoService service;
	
	@GetMapping("/pessoa/{idPessoa}/enderecos")
	public List<Endereco> getPessoas(@PathVariable(value="idPessoa") long idPessoa) {
		List<Endereco> endereco = service.buscarTodos(Long.valueOf(idPessoa));
		return endereco;
	}
	
	@GetMapping("/pessoa/{idPessoa}/enderecos/{id}")
	public Endereco getEndereco(@PathVariable(value="idPessoa") long idPessoa, @PathVariable(value="id") long id) {
		Endereco endereco = service.buscarPorIdPessoaId(Long.valueOf(idPessoa), Long.valueOf(id));
		return endereco;
	}
	
	@PostMapping("/pessoa/{idPessoa}/enderecos")
	public Endereco addEnderecos(@PathVariable(value="idPessoa") long idPessoa, @RequestBody Endereco endereco) {
		try {
			return service.criarEndereco(Long.valueOf(idPessoa), endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping("/pessoa/{idPessoa}/enderecos")
	public void deleteEndereco(@PathVariable(value="idPessoa") long idPessoa, @RequestBody Endereco endereco) {
			service.excluirEndereco(Long.valueOf(idPessoa), endereco);
	}
}
