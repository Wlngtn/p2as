package com.br.p2as.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.br.p2as.exception.ServicoNotFoundException;
import com.br.p2as.model.pessoa.Profissional;
import com.br.p2as.model.profissional.Servico;
import com.br.p2as.repository.ServicoRepository;
import com.br.p2as.service.IServicoService;

@Component
public class ServicoServiceImpl implements IServicoService{

	@Autowired
	private ServicoRepository repository;

	@Autowired
	private ProfissionalServiceImpl profissionalService;
	
	@Override
	public List<Servico> buscarTodos(Long idProfissional) {
		return repository.findByIdProfissional(idProfissional);
	}

	@Override
	public Servico buscarPorIdProfissionalId(Long idProfissional, Long id) {
		Servico servico = repository.getByIdProfissionalId(idProfissional, id);
		return servico;
	}

	@Override
	public void excluirServico(Servico servico) {
		repository.deleteById(servico.getId());		
	}

	@Override
	public Servico criarServico(Servico servico, Long idProfissional) throws Exception {
		Profissional profissional = profissionalService.buscarPorId(idProfissional);
		servico.setProfissional(profissional);
		servico = repository.save(servico);
		return servico;
	}

	public Servico buscarPorId(Long idServico) {
		Optional<Servico> optServico = repository.findById(idServico);
		if(!optServico.isPresent()) {
			new ServicoNotFoundException("Serviço não encontrado");
		}
		return optServico.get();
	}

	@CrossOrigin
	@Override
	public List<Servico> getAll() {
		return repository.findAll();
	}

	@Override
	public Servico getById(long id) {
		 Optional<Servico> servicoOpt = repository.findById(id);
		 return servicoOpt.isPresent() ? servicoOpt.get() : null;
	}

}
