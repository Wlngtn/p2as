package com.br.p2as.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.p2as.exception.ErrorServiceException;
import com.br.p2as.exception.ProfissionalExistsException;
import com.br.p2as.exception.ProfissionalNotFoundException;
import com.br.p2as.exception.ServicoNotFoundException;
import com.br.p2as.model.pessoa.Pessoa;
import com.br.p2as.model.pessoa.Profissional;
import com.br.p2as.model.pessoa.to.ProfissionalTO;
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
	public Profissional buscarPorId(Long idProfissional) {
		Optional<Profissional> optProfissional = repository.findById(idProfissional);
		if(!optProfissional.isPresent()) {
			new ServicoNotFoundException("Serviço não encontrado");
		}
		return optProfissional.get();
	}

	@Override
	public void excluirProfissional(Profissional profissional) {
		Optional<Profissional> optProfissional = repository.findById(profissional.getId());
		if(!optProfissional.isPresent()) {
			new ServicoNotFoundException("Serviço não encontrado");
		}
		try {
			repository.deleteById(optProfissional.get().getId());		
		}catch(Exception e){
			throw new ErrorServiceException();
		}
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

	@Override
	public ProfissionalTO buscarPorCpf(String cpf) {
		try {
			Optional<Profissional> optProfissional = repository.findByCPF(cpf);
			if(optProfissional.isPresent()) {
				return new ProfissionalTO(optProfissional.get());
			}
		
		}catch(Exception e) {
			new ProfissionalTO(); 
		}
		
		return new ProfissionalTO();
	}

}
