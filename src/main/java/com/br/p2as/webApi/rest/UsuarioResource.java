package com.br.p2as.webApi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.p2as.model.usuario.Usuario;
import com.br.p2as.service.IUsuarioService;

@RestController
@RequestMapping(value="/api")
public class UsuarioResource {
	
	@Autowired
	private IUsuarioService service;
	
	@GetMapping("/pessoa/{idPessoa}/usuarios")
	public Usuario getUsuario(@PathVariable(value="idPessoa") long idPessoa) {
		Usuario usuario = service.buscarPorPessoaId(idPessoa);
		return usuario;
	}
	
	@PostMapping("/pessoa/{idPessoa}/usuarios")
	public Usuario addUsuarios(@PathVariable(value="idPessoa") long idPessoa, @RequestBody Usuario usuario) {
		try {
			return service.criarUsuario(Long.valueOf(idPessoa), usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping("/pessoa/{idPessoa}/usuarios/{id}")
	public void deleteUsuario(@PathVariable(value="idPessoa") long idPessoa, @PathVariable(value="id") long id) {
			service.excluirUsuario(Long.valueOf(idPessoa), Long.valueOf(id));
	}
}
