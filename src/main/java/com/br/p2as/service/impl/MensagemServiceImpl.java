package com.br.p2as.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.p2as.model.comunicacao.Comunicacao;
import com.br.p2as.model.comunicacao.Mensagem;
import com.br.p2as.model.pessoa.Pessoa;
import com.br.p2as.model.profissional.Servico;
import com.br.p2as.repository.MensagemRepository;
import com.br.p2as.service.IMensagemService;

@Component
public class MensagemServiceImpl implements IMensagemService{

	@Autowired
	private MensagemRepository repository;

	@Autowired
	private ServicoServiceImpl servicoService;
	
	@Autowired
	private PessoaServiceImpl pessoaService;
	
	@Autowired
	private ComunicacaoServiceImpl comunicacaoService;
	
	
	@Override
	public List<Mensagem> buscarTodos(Long idPessoa, Long idServico, Long idComunicacao) {
		return repository.findByIdPessoaIdSericoIdComunicacao(idPessoa, idServico, idComunicacao);
	}

	@Override
	public Mensagem buscarPorId(Long idPessoa, Long idServico, Long idComunicacao, Long idMensagem) {
		Mensagem mensagem = repository.getByIdPessoaIdSericoIdComunicacaoId(idPessoa, idServico, idComunicacao, idMensagem);
		return mensagem;
	}

	@Override
	public Mensagem criarMensagem(Mensagem mensagem, Long idPessoa, Long idServico, Long idComunicacao) {
		Comunicacao comunicacao = comunicacaoService.buscarPorId(idComunicacao);
		Pessoa pessoa = pessoaService.buscarPorId(idPessoa);
		Servico servico = servicoService.buscarPorId(idServico);
		
		if(comunicacao == null || pessoa == null || servico == null) {
			return null;
		}
		
		mensagem = new Mensagem(comunicacao, pessoa, servico, mensagem.getTexto());
		
		mensagem = repository.save(mensagem);
		
		return mensagem;
	}
	
}
