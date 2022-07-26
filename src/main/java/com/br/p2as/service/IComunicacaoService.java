package com.br.p2as.service;

import org.springframework.stereotype.Component;

import com.br.p2as.model.comunicacao.Comunicacao;
import com.br.p2as.model.pessoa.Cliente;

@Component
public interface IComunicacaoService {

	Comunicacao buscarPorIdProfissionalIdServicoIdComunicacao(Long idProfissional, Long idServico, Long idComunicacao);

	Comunicacao criarComunicacao(Cliente cliente, Long idProfissional, Long idServico);

	Comunicacao finalizarComunicacao(Comunicacao comunicacao);

}
