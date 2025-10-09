package com.devsDoAgi.SAFeR.service;

import java.time.LocalDate;
import java.util.List;

import com.devsDoAgi.SAFeR.exception.AccounNotFound;
import com.devsDoAgi.SAFeR.exception.ClientNotFound;
import org.springframework.stereotype.Service;

import com.devsDoAgi.SAFeR.dto.ClienteRequestDTO;
import com.devsDoAgi.SAFeR.dto.ClienteResponseDTO;
import com.devsDoAgi.SAFeR.mapper.ClienteMapper;
import com.devsDoAgi.SAFeR.model.Cliente;
import com.devsDoAgi.SAFeR.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Transactional
    public ClienteResponseDTO criarCliente(ClienteRequestDTO dto) {
        Cliente cliente = clienteMapper.toEntity(dto);
        Cliente salvo = clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(salvo);
    }

    @Transactional
    public ClienteResponseDTO buscarCliente(String cpf) {
        Cliente cliente = clienteRepository.findById(cpf)
                .orElseThrow(() -> new ClientNotFound("Cliente não encontrado"));
        return clienteMapper.toResponseDTO(cliente);
    }

    @Transactional
    public List<ClienteResponseDTO> listarCliente() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toResponseDTOList(clientes);
    }

    @Transactional
    public ClienteResponseDTO atualizarCliente(String cpf, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(dto.getNome());
        cliente.setDataNascimento(LocalDate.parse(dto.getDataNascimento()));
        cliente.setScore(dto.getScore());
        cliente.setAtivo(dto.isAtivo());
        Cliente atualizado = clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(atualizado);
    }

    @Transactional
    public ClienteResponseDTO desativarCliente(String cpf) {
        Cliente cliente = clienteRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setAtivo(false);
        Cliente desativado = clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(desativado);
    }
    
}
