package com.globalit.contacorrente.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_conta")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true)
	private Long numeroConta;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	private BigDecimal saldo;
	
	public Conta() {}
	
	public Conta(Integer id, Long numeroConta, Usuario usuario, BigDecimal saldo) {
		this.id = id;
		this.numeroConta = numeroConta;			
		this.usuario = usuario;
		this.saldo = saldo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Long numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	
}
