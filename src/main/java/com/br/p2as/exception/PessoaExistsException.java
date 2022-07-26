package com.br.p2as.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PessoaExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PessoaExistsException(String cpfCnpj, String nome) {
		super("C.P.F/C.N.P.J "+ cpfCnpj + " já cadastrado no sistema");
	}
	
}
