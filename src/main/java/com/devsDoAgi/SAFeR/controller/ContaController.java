package com.devsDoAgi.SAFeR.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsDoAgi.SAFeR.dto.ContaRequestDTO;
import com.devsDoAgi.SAFeR.dto.ContaResponseDTO;
import com.devsDoAgi.SAFeR.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<ContaResponseDTO> criarConta(@RequestBody ContaRequestDTO dto) {
        ContaResponseDTO novaConta = contaService.criarConta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    @GetMapping("/{numConta}")
    public ResponseEntity<ContaResponseDTO> buscarConta(@PathVariable String numConta) {
        ContaResponseDTO conta = contaService.buscarConta(numConta);
        return ResponseEntity.ok(conta);
    }

    @GetMapping
    public ResponseEntity<java.util.List<ContaResponseDTO>> listarContas() {
        java.util.List<ContaResponseDTO> contas = contaService.listarContas();
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/ativas")
    public ResponseEntity<java.util.List<ContaResponseDTO>> listarContasAtivas() {
        java.util.List<ContaResponseDTO> contas = contaService.listarContasAtivas();
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/bloqueadas")
    public ResponseEntity<java.util.List<ContaResponseDTO>> listarContasBloqueadas() {
        java.util.List<ContaResponseDTO> contas = contaService.listarContasBloqueadas();
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/encerradas")
    public ResponseEntity<java.util.List<ContaResponseDTO>> listarContasEncerradas() {
        java.util.List<ContaResponseDTO> contas = contaService.listarContasEncerradas();
        return ResponseEntity.ok(contas);
    }

    @PutMapping("/{numConta}")
    public ResponseEntity<ContaResponseDTO> atualizarConta(@PathVariable String numConta, @RequestBody ContaRequestDTO dto) {
        ContaResponseDTO contaAtualizada = contaService.atualizarConta(numConta, dto);
        return ResponseEntity.ok(contaAtualizada);
    }

    @PutMapping("/bloquear/{numConta}")
    public ResponseEntity<ContaResponseDTO> bloquearConta(@PathVariable String numConta) {
        ContaResponseDTO contaBloqueada = contaService.bloquearConta(numConta);
        return ResponseEntity.ok(contaBloqueada);
    }

    @PutMapping("/encerrar/{numConta}")
    public ResponseEntity<ContaResponseDTO> encerrarConta(@PathVariable String numConta) {
        ContaResponseDTO contaEncerrada = contaService.encerrarConta(numConta);
        return ResponseEntity.ok(contaEncerrada);
    }

    @PutMapping("/ativar/{numConta}")
    public ResponseEntity<ContaResponseDTO> ativarConta(@PathVariable String numConta) {
        ContaResponseDTO contaAtivada = contaService.ativarConta(numConta);
        return ResponseEntity.ok(contaAtivada);
    }
    
}
