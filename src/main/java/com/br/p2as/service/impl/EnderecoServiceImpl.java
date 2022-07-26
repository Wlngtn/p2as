package com.br.p2as.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.p2as.exception.EnderecoNotFoundException;
import com.br.p2as.model.endereco.Endereco;
import com.br.p2as.model.pessoa.Pessoa;
import com.br.p2as.model.pessoa.Profissional;
import com.br.p2as.repository.EnderecoRepository;
import com.br.p2as.service.IEnderecoService;
import com.br.p2as.service.IProfissionalService;

@Component
public class EnderecoServiceImpl implements IEnderecoService{
	@Autowired
	private EnderecoRepository repository;
	
	@Autowired
	private IProfissionalService profissionalService;

	@Override
	public List<Endereco> buscarEnderecosPessoa(Pessoa pessoa) {
		List<Endereco> enderecos = repository.findAllByPessoa(pessoa);
		enderecos.parallelStream().forEach(p -> p.setPessoa(null));
		return enderecos;
	}

	@Override
	public Endereco criarEndereco(Endereco endereco) throws Exception {
		endereco = repository.save(endereco);
		return endereco;
	}

	@Override
	public List<Endereco> buscarTodos() {
		return repository.findAll();
	}

	@Override
	public Endereco buscarPorId(Long id) {
		Endereco endereco = repository.getById(id);
		return endereco;
	}

	@Override
	public void excluirEndereco(Endereco endereco) {
		repository.deleteById(endereco.getId());
	}

	@Override
	public List<Endereco> buscarTodos(Long idPessoa) {
		List<Endereco> enderecos = repository.findAllByPessoaId(idPessoa);
		enderecos.parallelStream().forEach(p -> p.setPessoa(null));
		return enderecos;
	}

	@Override
	public Endereco buscarPorIdPessoaId(Long idPessoa, Long id) {
		Endereco endereco = repository.getByIdPessoaId(idPessoa, id);
		return endereco;
	}

	@Override
	public Endereco criarEndereco(Long idPessoa, Endereco endereco) {
		endereco.setPessoa(new Pessoa());
		endereco.getPessoa().setId(idPessoa);
		endereco = repository.save(endereco);
		return endereco;
	}

	@Override
	public void excluirEndereco(Long idPessoa, Endereco endereco) {
		repository.deleteByIdPessoaId(idPessoa, endereco.getId());
	}

	@Override
	public Endereco buscarEnderecoProfissional(Long idProfissional) {
		Profissional profissional = profissionalService.buscarPorId(idProfissional);
		
		if(profissional.getPessoa().getEnderecos().isEmpty()) {
			throw new EnderecoNotFoundException("Profissional não possui endereço cadastrado");
		}
		
		return profissional.getPessoa().getEnderecos().get(0);
	}

}
