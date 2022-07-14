package com.br.p2as.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.p2as.exception.ComunicacaoExistsException;
import com.br.p2as.exception.ComunicacaoNotCreatedException;
import com.br.p2as.model.comunicacao.Comunicacao;
import com.br.p2as.model.pessoa.Cliente;
import com.br.p2as.model.pessoa.Profissional;
import com.br.p2as.model.profissional.Servico;
import com.br.p2as.repository.ComunicacaoRepository;
import com.br.p2as.service.IComunicacaoService;

@Component
public class ComunicacaoServiceImpl implements IComunicacaoService{

	@Autowired
	private ComunicacaoRepository repository;

	@Autowired
	private ServicoServiceImpl servicoService;
	
	@Autowired
	private ClienteServiceImpl clienteService;
	
	@Autowired
	private ProfissionalServiceImpl profissionalService;
	
	@Override
	public Comunicacao buscarPorIdProfissionalIdServicoIdComunicacao(Long idProfissional, Long idServico,
			Long idComunicacao) {
		return repository.findByIdProfissionalIdServicoIdComunicacao(idProfissional, idServico, idComunicacao);
	}

	@Override
	public Comunicacao criarComunicacao(Cliente cliente, Long idProfissional, Long idServico) {
		Profissional profissional = profissionalService.buscarPorId(idProfissional);
		cliente = clienteService.buscarPorId(cliente.getId());
		Servico servico = servicoService.buscarPorIdProfissionalId(profissional.getId(), idServico);

		validarComunicacao(cliente, profissional, servico);
		
		Comunicacao comunicacao = new Comunicacao(cliente, profissional, servico);
		comunicacao = repository.save(comunicacao);
		return comunicacao;
	}

	private void validarComunicacao(Cliente cliente, Profissional profissional, Servico servico) {
		
		validarRelacionamentos(cliente, profissional, servico);
		
	}

	private void validarRelacionamentos(Cliente cliente, Profissional profissional, Servico servico) {
		
		if(cliente == null || profissional == null || servico == null) {
			throw new ComunicacaoNotCreatedException();
		}
		
		validarComunicacaoAberta(cliente, profissional, servico);
	}

	private void validarComunicacaoAberta(Cliente cliente, Profissional profissional, Servico servico) {
		Comunicacao comunicacaoBusca = repository.getByProfissionalClienteServico(cliente.getId(), profissional.getId(), servico.getId());
		
		if(comunicacaoBusca != null) {
			throw new ComunicacaoExistsException();
		}
	}

	@Override
	public Comunicacao finalizarComunicacao(Comunicacao comunicacao) {
		comunicacao.finalizar();
		comunicacao = repository.save(comunicacao);
		return comunicacao;
	}

	public Comunicacao buscarPorId(Long idComunicacao) {
		 Optional<Comunicacao> optComunicacao = repository.findById(idComunicacao);
		 
		 return optComunicacao.isPresent() ? optComunicacao.get() : null;
	}
	

}
