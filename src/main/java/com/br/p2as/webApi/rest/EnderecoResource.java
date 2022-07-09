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
@RequestMapping(value="/api/pessoa")
public class EnderecoResource {
	
	@Autowired
	private IEnderecoService service;
	
	@GetMapping("/enderecos")
	public List<Endereco> getPessoas() {
		List<Endereco> endereco = service.buscarTodos();
		return endereco;
	}
	
	@GetMapping("/endereco/{id}")
	public Endereco getEndereco(@PathVariable(value="id") long id) {
		Endereco endereco = service.buscarPorId(Long.valueOf(id));
		return endereco;
	}
	
	@PostMapping("/endereco")
	public Endereco addEnderecos(@RequestBody Endereco endereco) {
		try {
			return service.criarEndereco(endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping("/endereco")
	public void deleteEndereco(@RequestBody Endereco endereco) {
			service.excluirEndereco(endereco);
	}
}
