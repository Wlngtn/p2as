package com.br.p2as.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.p2as.model.pessoa.Profissional;
import com.br.p2as.model.pessoa.to.ProfissionalTO;

@Service
public interface IProfissionalService {

	Profissional criarProfissional(Profissional profissioal);

	List<Profissional> buscarTodos();

	Profissional buscarPorId(Long id);

	void excluirProfissional(Profissional profissional);

	ProfissionalTO buscarPorCpf(String cpf);

}
