package com.globalit.contacorrente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globalit.contacorrente.dto.TransferenciaDTO;
import com.globalit.contacorrente.entities.Transferencia;
import com.globalit.contacorrente.repositories.TransferenciaRepository;
import com.globalit.contacorrente.services.exceptions.ResourceNotFoundException;

@Service
public class TransferenciaService {
	
	@Autowired
	private TransferenciaRepository repository;
	
	@Transactional(readOnly = true)
	public TransferenciaDTO findById(Integer id) {
		Transferencia transferencia = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new TransferenciaDTO(transferencia);
	}
	

}
