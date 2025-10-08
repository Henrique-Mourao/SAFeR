package com.devsDoAgi.SAFeR.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import com.devsDoAgi.SAFeR.exception.AccountNotFound;
import com.devsDoAgi.SAFeR.exception.ClientNotFound;
import com.devsDoAgi.SAFeR.exception.DeviceNotFound;
import org.springframework.stereotype.Service;

import com.devsDoAgi.SAFeR.dto.ContaRequestDTO;
import com.devsDoAgi.SAFeR.dto.ContaResponseDTO;
import com.devsDoAgi.SAFeR.enums.ContaStatus;
import com.devsDoAgi.SAFeR.mapper.ContaMapper;
import com.devsDoAgi.SAFeR.model.Cliente;
import com.devsDoAgi.SAFeR.model.Conta;
import com.devsDoAgi.SAFeR.model.Dispositivo;
import com.devsDoAgi.SAFeR.repository.ClienteRepository;
import com.devsDoAgi.SAFeR.repository.ContaRepository;
import com.devsDoAgi.SAFeR.repository.DispositivoRepository;

import jakarta.transaction.Transactional;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;
    private final DispositivoRepository dispositivoRepository;
    private final ContaMapper contaMapper;

    public ContaService(ContaRepository contaRepository, ClienteRepository clienteRepository,
            DispositivoRepository dispositivoRepository, ContaMapper contaMapper) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.dispositivoRepository = dispositivoRepository;
        this.contaMapper = contaMapper;
    }

    /*
     * Gerador de número de conta
     * Formato: 6 dígitos numéricos
     */

    private final Random random = new Random();
    private String gerarNumeroConta() {
        String numero;
        do {
            numero = String.format("%06d", random.nextInt(1_000_000)); // 000000 até 999999
        } while (contaRepository.existsById(numero)); // garante unicidade
        return numero;
    }

    @Transactional
    public ContaResponseDTO criarConta(ContaRequestDTO dto) {
        Conta conta = contaMapper.toEntity(dto);

        // Busca o cliente no banco pelo CPF informado
        Cliente cliente = clienteRepository.findById(dto.getCpfCliente())
                .orElseThrow(() -> new ClientNotFound("Cliente não encontrado"));
        conta.setCliente(cliente);

        // Busca o dispositivo no banco pelo ID informado
        if (dto.getIdDispositivo() != null) {
            Dispositivo dispositivo = dispositivoRepository.findById(dto.getIdDispositivo())
                .orElseThrow(() -> new DeviceNotFound("Dispositivo não encontrado"));
            conta.setDispositivo(dispositivo);
        }

        // Garante que cada transação conhece sua conta
        if (conta.getHistoricoTransacoes() != null) {
            conta.getHistoricoTransacoes().forEach(t -> t.setConta(conta));
        }
        
        conta.setNumConta(gerarNumeroConta());
        conta.setNumAgencia("0001");
        conta.setIspb("77");
        conta.setDataAbertura(LocalDateTime.now());
        conta.setLimiteNoturno(new BigDecimal("10000.00"));
        conta.setStatus(ContaStatus.ATIVA);
        Conta salvo = contaRepository.save(conta);
        return contaMapper.toResponseDTO(salvo);
    }

    @Transactional
    public ContaResponseDTO buscarConta(String numConta) {
        Conta conta = contaRepository.findById(numConta)
                .orElseThrow(() -> new AccountNotFound("Conta não encontrada"));
        return contaMapper.toResponseDTO(conta);
    }

    @Transactional
    public List<ContaResponseDTO> listarContas() {
        List<Conta> contas = contaRepository.findAll();
        return contaMapper.toResponseDTOList(contas);
    }

    @Transactional
    public List<ContaResponseDTO> listarContasAtivas() {
        List<Conta> contas = contaRepository.findAll()
                .stream()
                .filter(c -> c.getStatus().equals(ContaStatus.ATIVA))
                .toList();
        return contaMapper.toResponseDTOList(contas);
    }

    @Transactional
    public List<ContaResponseDTO> listarContasBloqueadas() {
        List<Conta> contas = contaRepository.findAll()
                .stream()
                .filter(c -> c.getStatus().equals(ContaStatus.BLOQUEADA))
                .toList();
        return contaMapper.toResponseDTOList(contas);
    }

    @Transactional
    public List<ContaResponseDTO> listarContasEncerradas() {
        List<Conta> contas = contaRepository.findAll()
                .stream()
                .filter(c -> c.getStatus().equals(ContaStatus.ENCERRADA))
                .toList();
        return contaMapper.toResponseDTOList(contas);
    }
    
    @Transactional
    public ContaResponseDTO atualizarConta(String numConta, ContaRequestDTO dto) {
        Conta contaExistente = contaRepository.findById(numConta)
                .orElseThrow(() -> new AccountNotFound("Conta não encontrada"));
        contaExistente.setNumAgencia(dto.getNumAgencia());

        if (dto.getIdDispositivo() != null) {
            contaExistente.setDispositivo(dispositivoRepository.findById(dto.getIdDispositivo())
                    .orElseThrow(() -> new DeviceNotFound("Dispositivo não encontrado")));
        }

        contaExistente.setLimiteNoturno(new BigDecimal(dto.getLimiteNoturno()));
        contaExistente.setStatus(ContaStatus.valueOf(dto.getStatus()));
        Conta atualizado = contaRepository.save(contaExistente);
        return contaMapper.toResponseDTO(atualizado);
    }

    @Transactional
    public ContaResponseDTO bloquearConta(String numConta) {
        Conta contaExistente = contaRepository.findById(numConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        contaExistente.setStatus(ContaStatus.BLOQUEADA);
        Conta bloqueada = contaRepository.save(contaExistente);
        return contaMapper.toResponseDTO(bloqueada);
    }

    @Transactional
    public ContaResponseDTO encerrarConta(String numConta) {
        Conta contaExistente = contaRepository.findById(numConta)
                .orElseThrow(() -> new AccountNotFound("Conta não encontrada"));
        contaExistente.setStatus(ContaStatus.ENCERRADA);
        Conta encerrada = contaRepository.save(contaExistente);
        return contaMapper.toResponseDTO(encerrada);
    }

    @Transactional
    public ContaResponseDTO ativarConta(String numConta) {
        Conta contaExistente = contaRepository.findById(numConta)
                .orElseThrow(() -> new AccountNotFound("Conta não encontrada"));
        contaExistente.setStatus(ContaStatus.ATIVA);
        Conta ativada = contaRepository.save(contaExistente);
        return contaMapper.toResponseDTO(ativada);
    }
}
