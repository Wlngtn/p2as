package com.br.p2as.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.p2as.model.pessoa.Pessoa;
import com.br.p2as.model.usuario.Usuario;
import com.br.p2as.repository.UsuarioRepository;
import com.br.p2as.service.IUsuarioService;

@Component
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario buscarPorPessoaId(Long idPessoa) {
		Usuario usuario = repository.getByPessoaId(idPessoa);
		return usuario;
	}
	
	@Override
	public Usuario buscarPorIdPessoaId(Long idPessoa, Long idUsuario) {
		Usuario usuario = repository.getByIdPessoaId(idPessoa, idUsuario);
		return usuario;
	}

	@Override
	public Usuario criarUsuario(Long idPessoa, Usuario usuario) {
		usuario.setPessoa(new Pessoa());
		usuario.getPessoa().setId(idPessoa);
		usuario = repository.save(usuario);
		return usuario;
	}

	@Override
	public void excluirUsuario(Long idPessoa, Long id) {
		repository.deleteByIdPessoaId(idPessoa, id);
	}
	

}
