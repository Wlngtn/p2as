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

import com.br.p2as.model.Pessoa;
import com.br.p2as.service.IPessoaService;

@RestController
@RequestMapping(value="/api")
public class PessoaResource {
	
	@Autowired
	private IPessoaService service;
	
	@GetMapping("/pessoas")
	public List<Pessoa> getPessoas() {
		return service.buscarTodos();
	}
	
	@GetMapping("/pessoas/{id}")
	public Pessoa getPessoa(@PathVariable(value="id") long id) {
		return service.buscarPorId(Long.valueOf(id));
	}
	
	@PostMapping("/pessoas")
	public Pessoa addPessoas(@RequestBody Pessoa pessoa) {
		try {
			return service.criarPessoa(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping("/pessoas")
	public void deletePessoas(@RequestBody Pessoa pessoa) {
			service.excluirPessoa(pessoa);
	}
}
