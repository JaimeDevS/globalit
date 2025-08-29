package com.globalit.contacorrente.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalit.contacorrente.repositories.TransferenciaRepository;

@Service
public class TransferenciaService {
	
	@Autowired
	private TransferenciaRepository repository;

}
