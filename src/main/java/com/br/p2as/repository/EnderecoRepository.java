package com.br.p2as.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import com.br.p2as.model.endereco.Endereco;
import com.br.p2as.model.pessoa.Pessoa;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

		public Endereco getById(Long id);
		public Endereco getByPessoa(Pessoa pessoa);
		
		@Nullable
		@Query("from Endereco where pessoa = ?1")
		public List<Endereco> findAllByPessoa(Pessoa pessoa);

		@Query("from Endereco where pessoa.id = ?1")
		public List<Endereco> findAllByPessoaId(Long idPessoa);
		
		@Query("from Endereco where pessoa.id = ?1 and id = ?2")
		public Endereco getByIdPessoaId(Long idPessoa, Long id);
		
		@Query("delete from Endereco where pessoa.id = ?1 and id = ?2")
		public void deleteByIdPessoaId(Long idPessoa, Long id);
	
}
