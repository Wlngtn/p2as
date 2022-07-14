package com.br.p2as.webApi.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.p2as.exception.MensagemNotCreatedException;
import com.br.p2as.exception.MensagemNotFoundException;
import com.br.p2as.model.comunicacao.Mensagem;
import com.br.p2as.service.IMensagemService;

@RestController
@RequestMapping(value="/v1/api/pessoas/{idPessoa}/comunicacoes/{idComunicacao}/servicos/{idServico}")
public class MensagemResource {
	
	@Autowired
	private IMensagemService service;
	
	@GetMapping("/mensagens")
	public List<Mensagem> getMensagens(@PathVariable(value="idPessoa") long idPessoa, @PathVariable(value="idComunicacao") long idComunicacao, @PathVariable(value="idServico") long idServico) {
		List<Mensagem> mensagens = service.buscarTodos(Long.valueOf(idPessoa), Long.valueOf(idComunicacao), Long.valueOf(idServico));
		return mensagens;
	}
	
	@GetMapping("/mensagens/{id}")
	public Mensagem getMensagem(@PathVariable(value="idPessoa") long idPessoa, @PathVariable(value="idComunicacao") long idComunicacao, @PathVariable(value="idServico") long idServico, @PathVariable(value="id") long id) {
		Mensagem mensagem = service.buscarPorId(Long.valueOf(idPessoa), Long.valueOf(idComunicacao), Long.valueOf(idServico), Long.valueOf(id));
		if(mensagem == null) {
			throw new MensagemNotFoundException();
		}
		return mensagem;
	}
	
	@PostMapping("/mensagens")
	public ResponseEntity<Object> addMensagens(@RequestBody Mensagem mensagem, @PathVariable(value="idPessoa") long idPessoa, @PathVariable(value="idComunicacao") long idComunicacao, @PathVariable(value="idServico") long idServico) {
		Mensagem mensagemCriada = service.criarMensagem(mensagem, Long.valueOf(idPessoa), Long.valueOf(idComunicacao), Long.valueOf(idServico));
		
		if(mensagemCriada == null) {
			throw new MensagemNotCreatedException(mensagem.getTexto());
		}
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(mensagemCriada.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
			
	}

}
