package com.globalit.contacorrente.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.globalit.contacorrente.entities.Transferencia;
import com.globalit.contacorrente.entities.enums.StatusTransferencia;

public class TransferenciaDTO {

	private Integer id;
	private Integer contaOrigem;
	private Integer contaDestino;
	private BigDecimal valor;
	private BigDecimal taxa;
	private LocalDate dataTransferencia;
	private LocalDate dataAgendamento;
	private StatusTransferencia status;
	
	public TransferenciaDTO() {
	}
	
	public TransferenciaDTO(Integer id, Integer contaOrigem, Integer contaDestino, BigDecimal valor, BigDecimal taxa,
			LocalDate dataTransferencia, LocalDate dataAgendamento, StatusTransferencia status) {
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
		this.contaOrigem = entity.getContaOrigem().getId();
		this.contaDestino = entity.getContaDestino().getId();
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

	public Integer getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Integer contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Integer getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Integer contaDestino) {
		this.contaDestino = contaDestino;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public LocalDate getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(LocalDate dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public StatusTransferencia getStatus() {
		return status;
	}

	public void setStatus(StatusTransferencia status) {
		this.status = status;
	}
	
	
}
