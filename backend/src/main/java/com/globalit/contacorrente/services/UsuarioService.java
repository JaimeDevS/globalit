package com.globalit.contacorrente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globalit.contacorrente.dto.UsuarioDTO;
import com.globalit.contacorrente.entities.Usuario;
import com.globalit.contacorrente.repositories.UsuarioRepository;
import com.globalit.contacorrente.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Transactional(readOnly = true)
    public UsuarioDTO findById(Integer id) {
        Usuario usuario = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        return new UsuarioDTO(usuario);
    }

}
