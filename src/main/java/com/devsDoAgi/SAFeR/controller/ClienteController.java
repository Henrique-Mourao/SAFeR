package com.devsDoAgi.SAFeR.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsDoAgi.SAFeR.dto.ClienteRequestDTO;
import com.devsDoAgi.SAFeR.dto.ClienteResponseDTO;
import com.devsDoAgi.SAFeR.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO novoCliente = clienteService.criarCliente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDTO> buscarCliente(@PathVariable String cpf) {
        ClienteResponseDTO cliente = clienteService.buscarCliente(cpf);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        List<ClienteResponseDTO> clientes = clienteService.listarCliente();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable String cpf, @RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO clienteAtualizado = clienteService.atualizarCliente(cpf, dto);
        return ResponseEntity.ok(clienteAtualizado);
    }

    // Não deleta, apenas altera status para inativo
    @DeleteMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDTO> desativarCliente(@PathVariable String cpf) {
        ClienteResponseDTO clienteDesativado = clienteService.desativarCliente(cpf);
        return ResponseEntity.ok(clienteDesativado);
    }
}
