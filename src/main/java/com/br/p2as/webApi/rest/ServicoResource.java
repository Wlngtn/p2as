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

import com.br.p2as.exception.PessoaNotFoundException;
import com.br.p2as.model.profissional.Servico;
import com.br.p2as.service.IServicoService;

@RestController
@RequestMapping(value="/v1/api/profissionais/{idProfissional}")
public class ServicoResource {
	
	@Autowired
	private IServicoService service;
	
	@GetMapping("/servicos")
	public List<Servico> getServicos(@PathVariable(value="idProfissional") long idProfissional) {
		List<Servico> servicos = service.buscarTodos(Long.valueOf(idProfissional));
		return servicos;
	}
	
	@GetMapping("/servicos/{id}")
	public Servico getServico(@PathVariable(value="idProfissional") long idProfissional, @PathVariable(value="id") long id) {
		Servico servico = service.buscarPorIdProfissionalId(Long.valueOf(idProfissional), Long.valueOf(id));
		if(servico == null) {
			throw new PessoaNotFoundException("id - " + id);
		}
		return servico;
	}
	
	@PostMapping("/servicos")
	public ResponseEntity<Object> addProfissionais(@RequestBody Servico servico, @PathVariable(value="idProfissional") long idProfissional) {
		try {
			servico = service.criarServico(servico, Long.valueOf(idProfissional));
			
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(servico.getId())
					.toUri();
			
			return ResponseEntity.created(location).build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping("/servicos")
	public void deleteProfissionais(@PathVariable(value="idProfissional") long idProfissional, @RequestBody Servico servico) {
		
		Servico servicoBusca = service.buscarPorIdProfissionalId(Long.valueOf(idProfissional), servico.getId());
		
		if(servicoBusca == null) {
			throw new PessoaNotFoundException("id - " + servico.getId());
		}
		
		service.excluirServico(servicoBusca);
	}
}
