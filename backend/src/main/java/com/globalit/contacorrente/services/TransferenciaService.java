package com.globalit.contacorrente.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globalit.contacorrente.dto.TransferenciaDTO;
import com.globalit.contacorrente.entities.Conta;
import com.globalit.contacorrente.entities.Transferencia;
import com.globalit.contacorrente.repositories.ContaRepository;
import com.globalit.contacorrente.repositories.TransferenciaRepository;
import com.globalit.contacorrente.services.exceptions.ResourceNotFoundException;

@Service
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository repository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Transactional(readOnly = true)
	public List<Transferencia> listar() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public TransferenciaDTO findById(Integer id) {
		Transferencia transferencia = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new TransferenciaDTO(transferencia);
	}
	
	public Transferencia agendar(TransferenciaDTO transferenciaDTO) {
		transferenciaDTO.setDataAgendamento(LocalDate.now());
		
		long dias = calcularDiasEntre(LocalDate.now(), transferenciaDTO.getDataTransferencia());
		
		BigDecimal taxa = calcularTaxa(transferenciaDTO.getValor(), dias);
		transferenciaDTO.setTaxa(taxa);

		Transferencia entity = dtoParaEntity(transferenciaDTO);
		
		return repository.save(entity);
	}
	
	private Transferencia dtoParaEntity(TransferenciaDTO transferenciaDTO) {
		
		Conta origem = contaRepository.getOne(transferenciaDTO.getContaOrigem());
		Conta destino = contaRepository.getOne(transferenciaDTO.getContaDestino());
		
		Transferencia entity = new Transferencia();
		
		entity.setContaOrigem(origem);
		entity.setContaDestino(destino);
		entity.setDataTransferencia(transferenciaDTO.getDataTransferencia());
		entity.setDataAgendamento(LocalDate.now());
		entity.setStatus(transferenciaDTO.getStatus());
		entity.setTaxa(transferenciaDTO.getTaxa());
		entity.setValor(transferenciaDTO.getValor());
		
		return entity;
	}
	
	private long calcularDiasEntre(LocalDate dataInicio, LocalDate dataFim) {
        return ChronoUnit.DAYS.between(dataInicio, dataFim);
    }

	private BigDecimal calcularTaxa(BigDecimal valor, long dias) {
		if (dias == 0) {
			// 3,00 + 2,5%
			return BigDecimal.valueOf(3.00).add(valor.multiply(BigDecimal.valueOf(0.025)));
		} else if (dias >= 1 && dias <= 10) {
			// 12,00 fixo
			return BigDecimal.valueOf(12.00);
		} else if (dias >= 11 && dias <= 20) {
			// 8,2%
			return valor.multiply(BigDecimal.valueOf(0.082));
		} else if (dias >= 21 && dias <= 30) {
			// 6,9%
			return valor.multiply(BigDecimal.valueOf(0.069));
		} else if (dias >= 31 && dias <= 40) {
			// 4,7%
			return valor.multiply(BigDecimal.valueOf(0.047));
		} else if (dias >= 41 && dias <= 50) {
			// 1,7%
			return valor.multiply(BigDecimal.valueOf(0.017));
		} else {
			// Fora da regra → erro
			throw new IllegalArgumentException(
					"Não é possível agendar transferências com mais de 50 dias de antecedência.");
		}
	}

}
