package com.br.p2as.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.p2as.model.pessoa.Pessoa;
import com.br.p2as.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

		public Usuario getById(Long id);
	
		public Usuario getByPessoa(Pessoa pessoa);
}
