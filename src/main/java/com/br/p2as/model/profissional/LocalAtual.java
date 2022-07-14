package com.br.p2as.model.profissional;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.br.p2as.model.pessoa.Profissional;
import com.br.p2as.utils.enums.SimNaoEnum;

@Entity
@Table(name="TB_005_LOCALIZACAO_ATUAL")
public class LocalAtual {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TX_LONGITUDE", nullable = false, length = 100, updatable = false)
	private String longitude;
	
	@Column(name = "TX_LATITUDE", nullable = false, length = 100, updatable = false)
	private String latitude;
	
	@Column(name = "DH_CRIACAO")
	private LocalDateTime dataInicio = LocalDateTime.now();
	
	@Column(name = "DH_INATIVACAO")
	private LocalDateTime dataFim;
	
	@Column(name = "ST_ATIVO", length = 1, nullable = false)
	@Enumerated(EnumType.STRING)
	private SimNaoEnum ativo = SimNaoEnum.S;
	
	@ManyToOne
	@JoinColumn(name="ID_PROFISSIONAL")
	private Profissional profissional;

	public LocalAtual(String longitude, String latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public LocalAtual() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public SimNaoEnum getAtivo() {
		return ativo;
	}

	public void setAtivo(SimNaoEnum ativo) {
		this.ativo = ativo;
	}
	
}
