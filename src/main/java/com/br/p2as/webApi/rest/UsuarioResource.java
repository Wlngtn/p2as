package com.br.p2as.webApi.rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.p2as.exception.UsuarioNotFoundException;
import com.br.p2as.exception.UsuarioPessoaExistsException;
import com.br.p2as.model.usuario.Usuario;
import com.br.p2as.service.IPessoaService;
import com.br.p2as.service.IUsuarioService;

@RestController
@RequestMapping(value="/v1/api")
public class UsuarioResource {
	
	@Autowired
	private IUsuarioService service;
	
	@GetMapping("/pessoas/{idPessoa}/usuarios")
	public Usuario getUsuario(@PathVariable(value="idPessoa") long idPessoa) {
		Usuario usuario = service.buscarPorPessoaId(idPessoa);

		if(usuario == null) {
			throw new UsuarioNotFoundException("Pessoa com id - " + idPessoa + " não possui usuário cadastrado");
		}

		return usuario;
	}
	
	@PostMapping("/pessoas/{idPessoa}/usuarios")
	public ResponseEntity<Object> addUsuarios(@PathVariable(value="idPessoa") long idPessoa, @RequestBody Usuario usuario) {
		validarUsuario(Long.valueOf(idPessoa), usuario);
		
		try {
			
			usuario = service.criarUsuario(Long.valueOf(idPessoa), usuario);
			
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(usuario.getId())
					.toUri();
			
			return ResponseEntity.created(location).build();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void validarUsuario(Long idPessoa, Usuario usuario) {
		validarUsuarioParaPessoa(idPessoa, usuario);		
	}

	private void validarUsuarioParaPessoa(Long idPessoa, Usuario usuario) {
		Usuario usuarioBusca = service.buscarPorPessoaId(idPessoa);
		
		if(usuarioBusca != null) {
			throw new UsuarioPessoaExistsException("Usuario já cadastrado para o C.P.F./C.N.P.J. " + usuarioBusca.getPessoa().getCpfCnpj());
		}
		
		validarUsuarioPorLogin(usuario);
	}
	
	private void validarUsuarioPorLogin(Usuario usuario) {
		Usuario usuarioBusca = service.buscarPorLogin(usuario.getLogin());
		
		if(usuarioBusca != null) {
			throw new UsuarioPessoaExistsException("Login " + usuarioBusca.getLogin() + " já cadastrado");
		}
	}

	@DeleteMapping("/pessoas/{idPessoa}/usuarios/{id}")
	public void deleteUsuario(@PathVariable(value="idPessoa") long idPessoa, @PathVariable(value="id") long id) {
		
		Usuario usuarioBusca = service.buscarPorIdPessoaId(idPessoa, Long.valueOf(id));
		
		if(usuarioBusca == null) {
			throw new UsuarioNotFoundException("Pessoa com id - " + idPessoa + " não possui usuário cadastrado");
		}
		
		service.excluirUsuario(Long.valueOf(idPessoa), Long.valueOf(id));

	}
}
