package com.br.p2as.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.p2as.model.profissional.LocalAtual;

@Service
public interface ILocalAtualService {

	List<LocalAtual> buscarPorProfissionalId(Long idProfissional);

	LocalAtual criarLoclizacaoAtual(Long idProfissional, LocalAtual localAtual);

	LocalAtual buscarAtivoPorProfissionalId(Long idProfissional);

	LocalAtual inativarLocalizacao(Long idProfissional);

}
