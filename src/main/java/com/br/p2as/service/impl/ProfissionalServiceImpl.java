package com.br.p2as.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.p2as.exception.PessoaExistsException;
import com.br.p2as.exception.ProfissionalExistsException;
import com.br.p2as.model.pessoa.Cliente;
import com.br.p2as.model.pessoa.Pessoa;
import com.br.p2as.model.pessoa.Profissional;
import com.br.p2as.repository.ProfissionalRepository;
import com.br.p2as.service.IProfissionalService;
import com.br.p2as.utils.enums.TipoPessoaEnum;

@Component
public class ProfissionalServiceImpl implements IProfissionalService{

	@Autowired
	private ProfissionalRepository repository;

	@Autowired
	private PessoaServiceImpl pessoaService;
	
	@Override
	public List<Profissional> buscarTodos() {
		return repository.findAll();
	}

	@Override
	public Profissional buscarPorId(Long id) {
		Profissional profissional = repository.getById(id);
		return profissional;
	}

	@Override
	public void excluirProfissional(Profissional profissional) {
		repository.deleteById(profissional.getId());		
	}

	@Override
	public Profissional criarProfissional(Profissional profissional) {
		
		if(verificaProfissionalPessoa(profissional.getPessoa())) {
			throw new ProfissionalExistsException(profissional.getPessoa().getCpfCnpj());
		}
		
		Pessoa pessoa = pessoaService.criarPessoa(profissional.getPessoa(), TipoPessoaEnum.P);
		
		profissional.setPessoa(pessoa);
		profissional = repository.save(profissional);
		return profissional;
	}
	
	private boolean verificaProfissionalPessoa(Pessoa pessoa) {
		if(pessoa.getId() != null) {
			Profissional profissionalBusca = repository.getByIdPessoa(pessoa.getId());
			if(profissionalBusca != null) {
				return Boolean.TRUE;
			}
		}
			
		return Boolean.FALSE;
	}

}
