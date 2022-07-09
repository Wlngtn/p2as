package com.br.p2as.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.p2as.model.pessoa.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
		
		@Query("from Pessoa where id = ?1")
		public Pessoa getById(Long id);
	
}
