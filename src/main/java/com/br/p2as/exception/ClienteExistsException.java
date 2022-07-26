package com.br.p2as.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ClienteExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteExistsException(String cpfCnpj) {
		super("C.P.F/C.N.P.J "+ cpfCnpj + " já possui cliente cadastrado no sistema");
	}
	
}
