package com.br.p2as.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ComunicacaoNotCreatedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ComunicacaoNotCreatedException() {
		super("Comunicação não foi aberta por dados inconsistentes");
	}

}
