package com.br.p2as.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import com.br.p2as.model.comunicacao.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>{

	@Nullable
	@Query("from Mensagem where pessoa.id = ?1 and servico.id = ?2 and comunicacao.id = ?3")
	List<Mensagem> findByIdPessoaIdSericoIdComunicacao(Long idPessoa, Long idServico, Long idComunicacao);

	@Nullable
	@Query("from Mensagem where pessoa.id = ?1 and servico.id = ?2 and comunicacao.id = ?3 and id = ?4")
	Mensagem getByIdPessoaIdSericoIdComunicacaoId(Long idPessoa, Long idServico, Long idComunicacao, Long idMensagem);

}
