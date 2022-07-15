package com.br.p2as.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErrorServiceException() {
		super("Estamos com instabilidade no sistema. Aguarde um momento!");
	}
	
}
