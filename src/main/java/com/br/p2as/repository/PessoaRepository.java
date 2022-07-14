package com.br.p2as.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import com.br.p2as.model.pessoa.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
		
		@Nullable
		@Query("from Pessoa where id = ?1")
		public Pessoa getById(Long id);

		@Nullable
		@Query("from Pessoa where cpfCnpj = ?1")
		public Pessoa getByCpfCnpj(String cpfCnpj);
	
}
