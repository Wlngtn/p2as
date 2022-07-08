package com.br.p2as.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.p2as.model.endereco.Endereco;
import com.br.p2as.model.pessoa.Pessoa;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

		public Endereco getById(Long id);
		public Endereco getByPessoa(Pessoa pessoa);
	
}
