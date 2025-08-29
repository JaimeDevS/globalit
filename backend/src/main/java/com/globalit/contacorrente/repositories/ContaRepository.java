package com.globalit.contacorrente.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globalit.contacorrente.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {

	Optional<Conta> findByNumeroConta(Integer id);

}
