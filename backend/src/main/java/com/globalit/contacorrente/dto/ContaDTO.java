package com.globalit.contacorrente.dto;

import com.globalit.contacorrente.entities.Conta;
import com.globalit.contacorrente.entities.Usuario;

public class ContaDTO {

	private Integer id;
	private Integer numeroConta;
	private Usuario usuario;
	private Double saldo;
	
	public ContaDTO(Integer id, Integer numeroConta, Usuario usuario, Double saldo) {
		this.id = id;
		this.numeroConta = numeroConta;
		this.usuario = usuario;
		this.saldo = saldo;
	}
	
	public ContaDTO(Conta entity) {
		this.id = entity.getId();
		this.numeroConta = entity.getNumeroConta();
		this.usuario = entity.getUsuario();
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	
	
}
