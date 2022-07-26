package com.br.p2as.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import com.br.p2as.model.profissional.LocalAtual;

public interface LocalAtualRepository  extends JpaRepository<LocalAtual, Long>{

	@Nullable
	@Query("from LocalAtual where profissional.id = ?1")
	List<LocalAtual> getAllByProfissional(Long idProfissional);

	@Nullable
	@Query("from LocalAtual where profissional.id = ?1 and ativo = 'S'")
	LocalAtual buscarAtivo(Long idProfissional);

}
