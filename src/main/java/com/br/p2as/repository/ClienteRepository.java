package com.br.p2as.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import com.br.p2as.model.pessoa.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Nullable
	@Query("from Cliente where id = ?1")
	public Cliente getById(Long id);

	@Nullable
	@Query("from Cliente where pessoa.id = ?1")
	public Cliente getByIdPessoa(Long idPessoa);

}
