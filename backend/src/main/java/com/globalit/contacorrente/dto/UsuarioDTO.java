package com.globalit.contacorrente.dto;

import com.globalit.contacorrente.entities.Usuario;

public class UsuarioDTO {

	private Integer id;
	private String nome;
	
	public UsuarioDTO(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public UsuarioDTO(Usuario entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
