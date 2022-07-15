package com.br.p2as.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServicoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -925891465530765470L;

	public ServicoNotFoundException(String message) {
		super(message);
	}
	
}
