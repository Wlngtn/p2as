package com.br.p2as.webApi.rest;

import java.net.URI;

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

import com.br.p2as.exception.ComunicacaoNotFoundException;
import com.br.p2as.exception.ErrorServiceException;
import com.br.p2as.model.comunicacao.Comunicacao;
import com.br.p2as.model.pessoa.Cliente;
import com.br.p2as.service.IComunicacaoService;

@RestController
@RequestMapping(value="/v1/api/profissionais/{idProfissional}/servicos/{idServico}/")
public class ComunicacaoResource {
	
	@Autowired
	private IComunicacaoService service;
	
	@GetMapping("/comunicacoes/{idComunicacao}")
	public Comunicacao getServico(@PathVariable(value="idProfissional") long idProfissional, @PathVariable(value="idServico") long idServico, @PathVariable(value="idComunicacao") long idComunicacao) {
		Comunicacao comunicacao = service.buscarPorIdProfissionalIdServicoIdComunicacao(Long.valueOf(idProfissional), Long.valueOf(idServico), Long.valueOf(idComunicacao));
		if(comunicacao == null) {
			throw new ComunicacaoNotFoundException("id - " + idComunicacao);
		}
		return comunicacao;
	}
	
	@PostMapping("/comunicacoes")
	public ResponseEntity<Object> addComunicacoes(@RequestBody Cliente cliente, @PathVariable(value="idProfissional") long idProfissional,  @PathVariable(value="idServico") long idServico) {
		Comunicacao comunicacao = service.criarComunicacao(cliente, Long.valueOf(idProfissional), Long.valueOf(idServico));
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(comunicacao.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
			
	}	
}
