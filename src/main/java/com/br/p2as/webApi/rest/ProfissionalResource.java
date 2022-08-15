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

import com.br.p2as.exception.PessoaExistsException;
import com.br.p2as.exception.PessoaNotFoundException;
import com.br.p2as.model.pessoa.Profissional;
import com.br.p2as.service.IProfissionalService;

@RestController
@RequestMapping(value="/v1/api")
public class ProfissionalResource {
	
	@Autowired
	private IProfissionalService service;
	
	@GetMapping("/profissionais")
	public List<Profissional> getProfissionais() {
		List<Profissional> profissionais = service.buscarTodos();
		return profissionais;
	}
	
	@GetMapping("/profissionais/{id}")
	public Profissional getProfissional(@PathVariable(value="id") long id) {
		Profissional profissional = service.buscarPorId(Long.valueOf(id));
		if(profissional == null) {
			throw new PessoaNotFoundException("id - " + id);
		}
		return profissional;
	}
	
	@PostMapping("/profissionais")
	public ResponseEntity<Object> addProfissionais(@RequestBody Profissional profissional) {
		try {
			profissional = service.criarProfissional(profissional);
			
			System.out.println("Profissional " + profissional.getId() + " - " + profissional.getPessoa().getNome() + " - " + "criado");
			
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(profissional.getId())
					.toUri();
			
			return ResponseEntity.created(location).build();
			
		}catch (PessoaExistsException pe) {
			throw new PessoaExistsException(profissional.getPessoa().getCpfCnpj(), profissional.getPessoa().getNome());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping("/profissionais")
	public void deleteProfissionais(@RequestBody Profissional profissional) {
		
		Profissional profissionalBusca = service.buscarPorId(profissional.getId());
		
		if(profissionalBusca == null) {
			throw new PessoaNotFoundException("id - " + profissional.getId());
		}
		
		service.excluirProfissional(profissionalBusca);
	}
}
