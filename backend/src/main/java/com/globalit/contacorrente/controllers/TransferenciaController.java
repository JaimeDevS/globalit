package com.globalit.contacorrente.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalit.contacorrente.dto.TransferenciaDTO;
import com.globalit.contacorrente.entities.Transferencia;
import com.globalit.contacorrente.services.TransferenciaService;

@RestController
@RequestMapping(value = "/transferencias")
public class TransferenciaController {

	@Autowired
    private TransferenciaService service;
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<TransferenciaDTO> findById(@PathVariable Integer id) {
		TransferenciaDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }
	
	/**
	 * PASSAR PARA DTO *
	 */
	
	@PostMapping
    public ResponseEntity<Transferencia> agendar(@RequestBody TransferenciaDTO transferencia) {
        return ResponseEntity.ok(service.agendar(transferencia));
    }

    @GetMapping
    public ResponseEntity<List<Transferencia>> listar() {
        return ResponseEntity.ok(service.listar());
    }
	
}
