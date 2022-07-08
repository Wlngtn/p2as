package com.br.p2as.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.p2as.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

		public Pessoa getById(Long id);
	
}
