package com.globalit.contacorrente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globalit.contacorrente.dto.ContaDTO;
import com.globalit.contacorrente.entities.Conta;
import com.globalit.contacorrente.repositories.ContaRepository;
import com.globalit.contacorrente.services.exceptions.ResourceNotFoundException;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;

	@Transactional(readOnly = true)
	public ContaDTO findById(Integer id) {
		Conta conta = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ContaDTO(conta);
	}
	
	@Transactional(readOnly = true)
	public ContaDTO findByNumeroConta(Integer id) {
		Conta conta = repository.findByNumeroConta(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ContaDTO(conta);
	}
}
