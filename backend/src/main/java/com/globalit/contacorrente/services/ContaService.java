package com.globalit.contacorrente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalit.contacorrente.repositories.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository repository;
	
	
}
