package com.globalit.contacorrente.repositories;

import java.util.List;import org.springframework.data.jpa.repository.JpaRepository;

import com.globalit.contacorrente.entities.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {

	// Optional<Transferencia> findByContaOrigem(Long numeroConta);
	List<Transferencia> findByContaOrigem_Id(Long idContaOrigem);
}
