package com.br.p2as.service;

import org.springframework.stereotype.Service;

import com.br.p2as.model.usuario.Usuario;

@Service
public interface IUsuarioService {

	Usuario buscarPorIdPessoaId(Long idPessoa, Long idUsuario);

	Usuario criarUsuario(Long valueOf, Usuario usuario);

	void excluirUsuario(Long valueOf, Long id);

	Usuario buscarPorPessoaId(Long idPessoa);

}
