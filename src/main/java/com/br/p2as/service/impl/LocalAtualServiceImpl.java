package com.br.p2as.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.p2as.exception.LocalAtualNotFoundException;
import com.br.p2as.exception.ProfissionalNotFoundException;
import com.br.p2as.model.pessoa.Profissional;
import com.br.p2as.model.profissional.LocalAtual;
import com.br.p2as.repository.LocalAtualRepository;
import com.br.p2as.repository.ProfissionalRepository;
import com.br.p2as.service.ILocalAtualService;
import com.br.p2as.service.IServicoService;

@Component
public class LocalAtualServiceImpl  implements ILocalAtualService{

	@Autowired
	private LocalAtualRepository repository;

	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Autowired
	private IServicoService servicoService;

	@Override
	public List<LocalAtual> buscarPorProfissionalId(Long idProfissional) {
		return repository.getAllByProfissional(idProfissional);
	}

	@Override
	public LocalAtual criarLoclizacaoAtual(Long idProfissional, LocalAtual localAtual) {
		
		Optional<Profissional> optProfissional = profissionalRepository.findById(idProfissional);
		
		if(!optProfissional.isPresent()) {
			throw new ProfissionalNotFoundException("Profissional com id "+idProfissional+" não cadastrado");
		}
		
		Profissional profissional = optProfissional.get();
		
		servicoService.iniciarTodosAtendimentos(profissional);
		
		inativarLocalizacaoAnterior(profissional.getId());
		
		localAtual.setProfissional(profissional);
		
		return repository.save(localAtual);
	}

	public LocalAtual inativarLocalizacaoAnterior(Long idProfissional) {
		
		Optional<Profissional> optProfissional = profissionalRepository.findById(idProfissional);
		
		if(!optProfissional.isPresent()) {
			throw new ProfissionalNotFoundException("Profissional com id "+ idProfissional +" não cadastrado");
		}
		
		Profissional profissional = optProfissional.get();
		
		LocalAtual localAtivo = repository.buscarAtivo(profissional.getId());
		
		if(localAtivo != null) {
			localAtivo.inativar();
			return repository.save(localAtivo);
		
		}
		return null;		
	}
	
	@Override
	public LocalAtual inativarLocalizacao(Long idProfissional, Long idLocalAtual) {
		
		Optional<Profissional> optProfissional = profissionalRepository.findById(idProfissional);
		
		if(!optProfissional.isPresent()) {
			throw new ProfissionalNotFoundException("Profissional com id "+ idProfissional +" não cadastrado");
		}
		
		Profissional profissional = optProfissional.get();
		
		Optional<LocalAtual> optLocalAtivo = repository.findById(idLocalAtual);
		
		if(!optLocalAtivo.isPresent()) {
			throw new LocalAtualNotFoundException("Locaização não cadastrada!");
		}
		
		LocalAtual localAtivo = optLocalAtivo.get();
		localAtivo.setProfissional(profissional);
		localAtivo.inativar();
		
		return repository.save(localAtivo);
		
	}

	@Override
	public LocalAtual buscarAtivoPorProfissionalId(Long idProfissional) {
		Optional<Profissional> optProfissional = profissionalRepository.findById(idProfissional);
		
		if(!optProfissional.isPresent()) {
			throw new ProfissionalNotFoundException("Profissional com id "+idProfissional+" não cadastrado");
		}
		
		return repository.buscarAtivo(optProfissional.get().getId());
	}
	

}
