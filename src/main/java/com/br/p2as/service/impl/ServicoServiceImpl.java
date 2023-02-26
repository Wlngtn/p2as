package com.br.p2as.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.br.p2as.exception.ServicoNotFoundException;
import com.br.p2as.model.pessoa.Profissional;
import com.br.p2as.model.profissional.Servico;
import com.br.p2as.model.profissional.to.ServicoTO;
import com.br.p2as.repository.ServicoRepository;
import com.br.p2as.service.IServicoService;
import com.br.p2as.utils.enums.SimNaoEnum;

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
		repository.delete(servico);		
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
	public List<ServicoTO> getAll() {
		List<ServicoTO> servicosTO = new ArrayList<ServicoTO>();
		for(Servico servico : repository.buscaOrdenadoAtivoNota()) {
			servicosTO.add(new ServicoTO(servico));
		}
		
		return servicosTO;
	}

	@Override
	public Servico getById(long id) {
		 Optional<Servico> servicoOpt = repository.findById(id);
		 return servicoOpt.isPresent() ? servicoOpt.get() : null;
	}

	@Override
	public List<ServicoTO> buscaPorNomeDescricao(String texto) {
		List<Servico> servicos = repository.getServicosByNomeEDescricaoEProfissional(texto);
		List<ServicoTO> servicosTO = new ArrayList<ServicoTO>();
		
		if(servicos == null) {
			return servicosTO;
		}
		
		for(Servico servico : servicos) {
			servicosTO.add(new ServicoTO(servico));
		}
		
		return servicosTO;
	}

	@Override
	public void iniciarTodosAtendimentos(Profissional profissional) {
		
		List<Servico> servicos = (List<Servico>) repository.findByIdProfissional(profissional.getId()).stream().filter(s -> s.getAtivo().getIsAtivo()).collect(Collectors.toList());
		
		for(Servico servico : servicos) {
			servico.setEmAtendimento(SimNaoEnum.S);
			repository.save(servico);
		}
				
	}
	
	@Override
	public void finalizarTodosAtendimentos(Profissional profissional) {
		
		List<Servico> servicos = (List<Servico>) repository.findByIdProfissional(profissional.getId()).stream().filter(s -> s.getAtivo().getIsAtivo()).collect(Collectors.toList());
		
		for(Servico servico : servicos) {
			servico.setEmAtendimento(SimNaoEnum.N);
			repository.save(servico);
		}
				
	}
	
	@Override
	public void ativar(Servico servico) {
		servico.setAtivo(SimNaoEnum.S);
		repository.save(servico);		
	}
	
	@Override
	public void inativar(Servico servico) {
		servico.setAtivo(SimNaoEnum.N);
		repository.save(servico);		
	}

}
