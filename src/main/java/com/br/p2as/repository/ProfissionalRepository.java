package com.br.p2as.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import com.br.p2as.model.pessoa.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
	
	@Nullable
	@Query("from Profissional where id = ?1")
	public Profissional getById(Long id);

	@Nullable
	@Query("from Profissional where pessoa.id = ?1")
	public Profissional getByIdPessoa(Long idPessoa);

}
