package com.br.p2as.webApi.rest;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

import com.br.p2as.exception.PessoaNotFoundException;
import com.br.p2as.model.pessoa.Pessoa;
import com.br.p2as.service.IPessoaService;

@RestController
@RequestMapping(value="/v1/api")
public class PessoaResource {
	
	@Autowired
	private IPessoaService service;
	
	@GetMapping("/pessoas")
	public List<Pessoa> getPessoas() {
		List<Pessoa> pessoas = service.buscarTodos();
		return pessoas;
	}
	
	@GetMapping("/pessoas/{id}")
	public Pessoa getPessoa(@PathVariable(value="id") long id) {
		Pessoa pessoa = service.buscarPorId(Long.valueOf(id));
		if(pessoa == null) {
			throw new PessoaNotFoundException("id - " + id);
		}
		return pessoa;
	}
	
}
