package com.br.p2as.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.p2as.exception.UsuarioPessoaExistsException;
import com.br.p2as.model.usuario.Usuario;
import com.br.p2as.repository.PessoaRepository;
import com.br.p2as.repository.UsuarioRepository;
import com.br.p2as.service.IPessoaService;
import com.br.p2as.service.IUsuarioService;

@Component
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private IPessoaService pessoaService;
	
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
		validarUsuario(Long.valueOf(idPessoa), usuario);
		
		usuario.setPessoa(pessoaService.buscarPorId(idPessoa));
		usuario = repository.save(usuario);
		return usuario;
	}

	private void validarUsuario(Long idPessoa, Usuario usuario) {
		validarUsuarioParaPessoa(idPessoa, usuario);		
	}

	private void validarUsuarioParaPessoa(Long idPessoa, Usuario usuario) {
		Usuario usuarioBusca = buscarPorPessoaId(idPessoa);
		
		if(usuarioBusca != null) {
			throw new UsuarioPessoaExistsException("Usuario já cadastrado para o C.P.F./C.N.P.J. " + usuarioBusca.getPessoa().getCpfCnpj());
		}
		
		validarUsuarioPorLogin(usuario);
	}
	
	private void validarUsuarioPorLogin(Usuario usuario) {
		Usuario usuarioBusca = buscarPorLogin(usuario.getLogin());
		
		if(usuarioBusca != null) {
			throw new UsuarioPessoaExistsException("Login " + usuarioBusca.getLogin() + " já cadastrado");
		}
	}
	
	@Override
	public void excluirUsuario(Long idPessoa, Long id) {
		repository.deleteByIdPessoaId(idPessoa, id);
	}

	@Override
	public Usuario buscarPorLogin(String login) {
		Usuario usuario = repository.getByLogin(login);
		return usuario;
	}
	

}
