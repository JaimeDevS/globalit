package com.globalit.contacorrente.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalit.contacorrente.dto.ContaDTO;
import com.globalit.contacorrente.services.ContaService;

@RestController
@RequestMapping(value = "/contas")
public class ContaController {

	@Autowired
    private ContaService service;
	
	@GetMapping
	public ResponseEntity<List<ContaDTO>> listar() {
		return ResponseEntity.ok(service.listar());
	}
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<ContaDTO> findById(@PathVariable Integer id) {
		ContaDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }
	
	@GetMapping(value = "/numero/{numeroConta}")
    public ResponseEntity<ContaDTO> findByNumeroConta(@PathVariable Long numeroConta) {
		ContaDTO dto = service.findByNumeroConta(numeroConta);
        return ResponseEntity.ok(dto);
    }
}
