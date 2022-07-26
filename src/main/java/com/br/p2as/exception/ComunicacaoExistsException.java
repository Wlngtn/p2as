package com.br.p2as.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ComunicacaoExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ComunicacaoExistsException() {
		super("Comunicação já aberta para essa situação.");
	}
	
}
