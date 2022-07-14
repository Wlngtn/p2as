package com.br.p2as.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ProfissionalExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProfissionalExistsException(String cpfCnpj) {
		super("C.P.F/C.N.P.J "+ cpfCnpj + " já possui profissional cadastrado no sistema");
	}
	
}
