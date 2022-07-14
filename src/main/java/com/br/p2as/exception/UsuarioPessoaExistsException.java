package com.br.p2as.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UsuarioPessoaExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioPessoaExistsException(String message) {
		super(message);
	}
	
}
