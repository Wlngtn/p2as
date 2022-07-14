package com.br.p2as.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import com.br.p2as.model.comunicacao.Comunicacao;

public interface ComunicacaoRepository extends JpaRepository<Comunicacao, Long>{

	@Nullable
	@Query("from Comunicacao where profissional.id = ?1 and servico.id = ?2 and id = ?3")	
	Comunicacao findByIdProfissionalIdServicoIdComunicacao(Long idProfissional, Long idServico, Long idComunicacao);

	@Nullable
	@Query("from Comunicacao where cliente.id = ?1 and profissional.id = ?2 and servico.id = ?3")	
	Comunicacao getByProfissionalClienteServico(Long idCliente, Long idProfissional, Long idServico);

}
