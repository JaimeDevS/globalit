package com.globalit.contacorrente.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_transferencia")
public class Transferencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Conta contaOrigem;
	private Conta contaDestino;
	private Double valor;
	private Float taxa;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataTransferencia;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataAgendamento;

	public Transferencia(Integer id, Conta contaOrigem, Conta contaDestino, Double valor, Float taxa,
			Instant dataTransferencia, Instant dataAgendamento) {
		super();
		this.id = id;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
		this.taxa = taxa;
		this.dataTransferencia = dataTransferencia;
		this.dataAgendamento = dataAgendamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Float getTaxa() {
		return taxa;
	}

	public void setTaxa(Float taxa) {
		this.taxa = taxa;
	}

	public Instant getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(Instant dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public Instant getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Instant dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
	

}
