package com.br.p2as.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.p2as.model.endereco.Endereco;
import com.br.p2as.model.pessoa.Pessoa;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

		public Endereco getById(Long id);
		public Endereco getByPessoa(Pessoa pessoa);
		
		@Query("from Endereco where pessoa = ?1")
		public List<Endereco> findAllByPessoa(Pessoa pessoa);
	
}
