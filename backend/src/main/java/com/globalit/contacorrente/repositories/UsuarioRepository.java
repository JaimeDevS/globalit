package com.globalit.contacorrente.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globalit.contacorrente.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
