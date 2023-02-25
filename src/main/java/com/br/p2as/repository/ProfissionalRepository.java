package com.br.p2as.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.br.p2as.model.pessoa.Profissional;

@Component
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
	
	@Nullable
	@Query("from Profissional where id = ?1")
	public Profissional getById(Long id);

	@Nullable
	@Query("from Profissional where pessoa.id = ?1")
	public Profissional getByIdPessoa(Long idPessoa);

	@Nullable
	@Query("from Profissional where pessoa.cpfCnpj like ?1")
	public Optional<Profissional> findByCPF(String cpf);

}
