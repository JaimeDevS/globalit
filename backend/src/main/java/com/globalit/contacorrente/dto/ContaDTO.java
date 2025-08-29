package com.globalit.contacorrente.dto;

import com.globalit.contacorrente.entities.Conta;

public class ContaDTO {

	private Integer id;
	private Integer numeroConta;
	private String usuario;
	private Double saldo;
	
	public ContaDTO(Integer id, Integer numeroConta, String usuario, Double saldo) {
		this.id = id;
		this.numeroConta = numeroConta;
		this.usuario = usuario;
		this.saldo = saldo;
	}
	
	public ContaDTO(Conta entity) {
		this.id = entity.getId();
		this.numeroConta = entity.getNumeroConta();
		this.usuario = entity.getUsuario().getNome();
		this.saldo = entity.getSaldo();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	
	
}
