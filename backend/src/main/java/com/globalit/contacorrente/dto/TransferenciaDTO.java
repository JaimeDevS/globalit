package com.globalit.contacorrente.dto;

import java.time.Instant;

import com.globalit.contacorrente.entities.Conta;
import com.globalit.contacorrente.entities.Transferencia;
import com.globalit.contacorrente.entities.enums.StatusTransferencia;

public class TransferenciaDTO {

	private Integer id;
	private Conta contaOrigem;
	private Conta contaDestino;
	private Double valor;
	private Float taxa;
	private Instant dataTransferencia;
	private Instant dataAgendamento;
	private StatusTransferencia status;
	
	public TransferenciaDTO(Integer id, Conta contaOrigem, Conta contaDestino, Double valor, Float taxa,
			Instant dataTransferencia, Instant dataAgendamento, StatusTransferencia status) {
		this.id = id;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.valor = valor;
		this.taxa = taxa;
		this.dataTransferencia = dataTransferencia;
		this.dataAgendamento = dataAgendamento;
		this.status = status;
	}
	
	public TransferenciaDTO(Transferencia entity) {
		this.id = entity.getId();
		this.contaOrigem = entity.getContaOrigem();
		this.contaDestino = entity.getContaDestino();
		this.valor = entity.getValor();
		this.taxa = entity.getTaxa();
		this.dataTransferencia = entity.getDataTransferencia();
		this.dataAgendamento = entity.getDataAgendamento();
		this.status = entity.getStatus();
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

	public StatusTransferencia getStatus() {
		return status;
	}

	public void setStatus(StatusTransferencia status) {
		this.status = status;
	}
	
	
}
