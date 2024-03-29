package com.br.p2as.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import com.br.p2as.model.profissional.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
	
	@Nullable
	@Query("from Servico where id = ?1")
	public Servico getById(Long id);

	@Nullable
	@Query("from Servico where profissional.id = ?1")
	public List<Servico> findByIdProfissional(Long idProfissional);

	@Nullable
	@Query("from Servico where profissional.id = ?1 and id = ?2")
	public Servico getByIdProfissionalId(Long idProfissional, Long id);

}
