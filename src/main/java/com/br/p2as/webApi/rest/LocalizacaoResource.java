package com.br.p2as.webApi.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.p2as.exception.EnderecoNotFoundException;
import com.br.p2as.exception.LocalAtualNotFoundException;
import com.br.p2as.model.endereco.Endereco;
import com.br.p2as.model.profissional.LocalAtual;
import com.br.p2as.service.IEnderecoService;
import com.br.p2as.service.ILocalAtualService;

@RestController
@RequestMapping(value="/v1/api/profissionais/{idProfissional}")
public class LocalizacaoResource {
	
	@Autowired
	private ILocalAtualService service;
	
	@Autowired
	private IEnderecoService enderecoService;
	
	@GetMapping("/localizacao")
	public ResponseEntity<Object>  getLocalizacoes(@PathVariable(value="idProfissional") long idProfissional) {
		List<LocalAtual> localAtual = service.buscarPorProfissionalId(idProfissional);

		if(localAtual == null || localAtual.isEmpty()) {
			throw new LocalAtualNotFoundException("Profissional não possui localização atual");
		}

		return ResponseEntity.accepted().body(localAtual);
	}
	
	@PostMapping("/localizacao")
	public ResponseEntity<Object> addLocalizacaoAtual(@PathVariable(value="idProfissional") long idProfissional, @RequestBody LocalAtual localAtual) {
		localAtual = service.criarLoclizacaoAtual(Long.valueOf(idProfissional), localAtual);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(localAtual.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();

	}

	@GetMapping("/localizacao/ativo")
	public ResponseEntity<Object> getAtivo(@PathVariable(value="idProfissional") long idProfissional) {
		LocalAtual localAtual = service.buscarAtivoPorProfissionalId(idProfissional);
		
		if(localAtual == null) {
			throw new LocalAtualNotFoundException("Profissional não possui localização atual ativa");
		}
		
		return ResponseEntity.accepted().body(localAtual);

	}
	
	@PutMapping("/localizacao/{id}/inativar")
	public ResponseEntity<Object> inativar(@PathVariable(value="idProfissional") long idProfissional, @PathVariable(value="idProfissional") long idLocalAtual) {
		LocalAtual localAtual = service.inativarLocalizacao(idProfissional, idLocalAtual);
		
		if(localAtual == null) {
			throw new LocalAtualNotFoundException("Profissional não possui localização atual ativa");
		}
		
		return ResponseEntity.accepted().body(localAtual);

	}
	
	@GetMapping("localizacao/endereco")
	public ResponseEntity<Object> getEndereco(@PathVariable(value="idProfissional") long idProfissional, @PathVariable(value="idProfissional") long idLocalAtual) {
		Endereco endereco = enderecoService.buscarEnderecoProfissional(idProfissional);
		return ResponseEntity.accepted().body(endereco);

	}
}
