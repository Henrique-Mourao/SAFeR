package com.devsDoAgi.SAFeR.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsDoAgi.SAFeR.dto.TransacaoRequestDTO;
import com.devsDoAgi.SAFeR.dto.TransacaoResponseDTO;
import com.devsDoAgi.SAFeR.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<TransacaoResponseDTO> criarTransacao(@RequestBody TransacaoRequestDTO dto) {
        TransacaoResponseDTO novaTransacao = transacaoService.criarTransacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> buscarTransacao(@PathVariable Long id) {
        TransacaoResponseDTO transacao = transacaoService.buscarPorId(id);
        return ResponseEntity.ok(transacao);
    }

    @GetMapping
    public ResponseEntity<List<TransacaoResponseDTO>> listarTransacoes() {
        List<TransacaoResponseDTO> transacoes = transacaoService.listarTransacoes();
        return ResponseEntity.ok(transacoes);
    }

    @PutMapping("/historico/{id}")
    public ResponseEntity<TransacaoResponseDTO> adicionarHistorico(@PathVariable Long id) {
        TransacaoResponseDTO transacaoAtualizada = transacaoService.adicionarHistorico(id);
        return ResponseEntity.ok(transacaoAtualizada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoResponseDTO> atualizarTransacao(@PathVariable Long id, @RequestBody TransacaoRequestDTO dto) {
        TransacaoResponseDTO transacaoAtualizada = transacaoService.atualizarTransacao(id, dto);
        return ResponseEntity.ok(transacaoAtualizada);
    }
    
}
