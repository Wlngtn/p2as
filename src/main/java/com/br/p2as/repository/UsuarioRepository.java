package com.br.p2as.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.p2as.model.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

		public Usuario getById(Long id);
		
		@Query("from Usuario where pessoa.id = ?1")
		public Usuario getByPessoaId(Long idPessoa);

		@Query("from Usuario where pessoa.id = ?1 and id = ?2")
		public Usuario getByIdPessoaId(Long idPessoa, Long idUsuario);
		
		@Query("delete from Usuario where pessoa.id = ?1 and id = ?2")
		public void deleteByIdPessoaId(Long idPessoa, Long id);
}
