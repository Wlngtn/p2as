package com.br.p2as.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ComunicacaoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ComunicacaoNotFoundException(String message) {
		super(message);
	}

}
