package com.br.p2as.service;

import java.util.List;

import com.br.p2as.model.profissional.Servico;

public interface IServicoService {

	void excluirServico(Servico servicoBusca);

	List<Servico> buscarTodos(Long idProfissional);

	Servico buscarPorIdProfissionalId(Long idProfissional, Long id);

	Servico criarServico(Servico servico, Long idProfissional) throws Exception;
	
	List<Servico> getAll();

	Servico getById(long id);

}
