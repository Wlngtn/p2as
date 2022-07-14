package com.br.p2as.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.p2as.model.comunicacao.Mensagem;

@Service
public interface IMensagemService {

	List<Mensagem> buscarTodos(Long valueOf, Long valueOf2, Long valueOf3);

	Mensagem buscarPorId(Long valueOf, Long valueOf2, Long valueOf3, Long valueOf4);

	Mensagem criarMensagem(Mensagem mensagem, Long valueOf, Long valueOf2, Long valueOf3);

}
