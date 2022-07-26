package com.br.p2as.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class MensagemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MensagemNotFoundException() {
		super("Mensagem Não Encontrada");
	}

}
