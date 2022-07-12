package com.br.p2as.exception.response;

import java.util.Date;

public class ExceptionResponse {

	private Date timestamp;
	private String mensagem;
	private String detalhe;
		
	public ExceptionResponse(Date timestamp, String mensagem, String detalhe) {
		super();
		this.timestamp = timestamp;
		this.mensagem = mensagem;
		this.detalhe = detalhe;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getDetalhe() {
		return detalhe;
	}
	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	
	
}
