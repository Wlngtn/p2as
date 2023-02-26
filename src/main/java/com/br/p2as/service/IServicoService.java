package com.br.p2as.service;

import java.util.List;

import com.br.p2as.model.pessoa.Profissional;
import com.br.p2as.model.profissional.Servico;
import com.br.p2as.model.profissional.to.ServicoTO;

public interface IServicoService {

	void excluirServico(Servico servicoBusca);

	List<Servico> buscarTodos(Long idProfissional);

	Servico buscarPorIdProfissionalId(Long idProfissional, Long id);

	Servico criarServico(Servico servico, Long idProfissional) throws Exception;
	
	List<ServicoTO> getAll();

	Servico getById(long id);

	List<ServicoTO> buscaPorNomeDescricao(String texto);

	void iniciarTodosAtendimentos(Profissional profissional);

	void inativar(Servico servicoBusca);

	void ativar(Servico servicoBusca);

}
