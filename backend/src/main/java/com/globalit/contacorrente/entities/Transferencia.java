package com.globalit.contacorrente.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.globalit.contacorrente.entities.enums.StatusTransferencia;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_transferencia")
public class Transferencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "conta_origem_id")
	private Conta contaOrigem;
	
	@ManyToOne
	@JoinColumn(name = "conta_destino_id")
	private Conta contaDestino;
	
	private BigDecimal valor;
	private BigDecimal taxa;
	
	//@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDate dataTransferencia;
	
	//@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDate dataAgendamento;
	
	@Enumerated(EnumType.STRING)
	private StatusTransferencia status;
	
	public Transferencia() {
	}

	public Transferencia(Integer id, Conta contaOrigem, Conta contaDestino, BigDecimal valor, BigDecimal taxa,
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

//	public Transferencia(TransferenciaDTO dto) {
//		this.id = dto.getId();
////		this.contaOrigem = dto.getContaOrigem();
////		this.contaDestino = entity.getContaDestino();
//		this.valor = dto.getValor();
//		this.taxa = dto.getTaxa();
//		this.dataTransferencia = dto.getDataTransferencia();
//		this.dataAgendamento = dto.getDataAgendamento();
//		this.status = dto.getStatus();
//	}
	
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
