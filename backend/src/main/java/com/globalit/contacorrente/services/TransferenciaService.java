package com.globalit.contacorrente.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globalit.contacorrente.dto.TransferenciaDTO;
import com.globalit.contacorrente.entities.Conta;
import com.globalit.contacorrente.entities.Transferencia;
import com.globalit.contacorrente.entities.enums.StatusTransferencia;
import com.globalit.contacorrente.repositories.ContaRepository;
import com.globalit.contacorrente.repositories.TransferenciaRepository;
import com.globalit.contacorrente.services.exceptions.InsufficientBalanceException;
import com.globalit.contacorrente.services.exceptions.RateNotApplicableException;
import com.globalit.contacorrente.services.exceptions.ResourceNotFoundException;

@Service
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository repository;

	@Autowired
	private ContaRepository contaRepository;

	@Transactional(readOnly = true)
	public List<TransferenciaDTO> listar() {
		List<Transferencia> list = repository.findAll();
		return list.stream().map(TransferenciaDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public TransferenciaDTO findById(Integer id) {
		Transferencia transferencia = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new TransferenciaDTO(transferencia);
	}
	
	@Transactional(readOnly = true)
	public List<TransferenciaDTO> findByContaOrigem(Long numeroConta) {		
		List<Transferencia> list = repository.findByContaOrigem_Id(numeroConta);
		return list.stream().map(TransferenciaDTO::new).collect(Collectors.toList());
	}

	@Transactional
	public TransferenciaDTO transferirSaldoOuAgendarTransferecia(TransferenciaDTO transferenciaDTO) {
		transferenciaDTO.setDataAgendamento(LocalDate.now());

		long dias = calcularDiasEntre(LocalDate.now(), transferenciaDTO.getDataTransferencia());

		BigDecimal taxa = calcularTaxa(transferenciaDTO, dias);
		transferenciaDTO.setTaxa(taxa);

		if (dias == 0) {
			atualizarSaldoEntreContas(transferenciaDTO, taxa, transferenciaDTO.getValor());
		}

		Transferencia entity = dtoParaEntity(transferenciaDTO);

		return new TransferenciaDTO(repository.save(entity));
	}

	private BigDecimal calcularTaxa(TransferenciaDTO dto, long dias) {
		if (dias == 0) {
			// 3,00 + 2,5%
			dto.setStatus(StatusTransferencia.CONCLUIDO);
			return BigDecimal.valueOf(3.00).add(dto.getValor().multiply(BigDecimal.valueOf(0.025)));
		} else if (dias >= 1 && dias <= 10) {
			// 12,00 fixo
			dto.setStatus(StatusTransferencia.AGENDADO);
			return BigDecimal.valueOf(12.00);
		} else if (dias >= 11 && dias <= 20) {
			// 8,2%
			dto.setStatus(StatusTransferencia.AGENDADO);
			return dto.getValor().multiply(BigDecimal.valueOf(0.082));
		} else if (dias >= 21 && dias <= 30) {
			// 6,9%
			dto.setStatus(StatusTransferencia.AGENDADO);
			return dto.getValor().multiply(BigDecimal.valueOf(0.069));
		} else if (dias >= 31 && dias <= 40) {
			// 4,7%
			dto.setStatus(StatusTransferencia.AGENDADO);
			return dto.getValor().multiply(BigDecimal.valueOf(0.047));
		} else if (dias >= 41 && dias <= 50) {
			// 1,7%
			dto.setStatus(StatusTransferencia.AGENDADO);
			return dto.getValor().multiply(BigDecimal.valueOf(0.017));
		} else {
			throw new RateNotApplicableException(
					"Não é possível agendar transferências com mais de 50 dias de antecedência.");
		}
	}

	@Transactional
	private void atualizarSaldoEntreContas(TransferenciaDTO dto, BigDecimal taxa, BigDecimal valorTransferencia) {
		Conta contaOrigem = contaRepository.getById(dto.getContaOrigem());
		Conta contaDestino = contaRepository.getById(dto.getContaDestino());

		BigDecimal valorTotalDebito = valorTransferencia.add(taxa);

		if (contaOrigem.getSaldo().compareTo(valorTotalDebito) < 0) {
			throw new InsufficientBalanceException("Saldo insuficiente para realizar a transferência");
		}

		BigDecimal novoSaldoOrigem = contaOrigem.getSaldo().subtract(valorTotalDebito);
		contaOrigem.setSaldo(novoSaldoOrigem);

		BigDecimal novoSaldoDestino = contaDestino.getSaldo().add(valorTransferencia);
		contaDestino.setSaldo(novoSaldoDestino);

		contaRepository.save(contaOrigem);
		contaRepository.save(contaDestino);
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
}
