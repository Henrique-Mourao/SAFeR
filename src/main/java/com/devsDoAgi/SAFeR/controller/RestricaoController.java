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

import com.devsDoAgi.SAFeR.dto.RestricaoRequestDTO;
import com.devsDoAgi.SAFeR.dto.RestricaoResponseDTO;
import com.devsDoAgi.SAFeR.service.RestricaoService;

@RestController
@RequestMapping("/restricoes")
public class RestricaoController {

    private final RestricaoService restricaoService;

    public RestricaoController(RestricaoService restricaoService) {
        this.restricaoService = restricaoService;
    }

    @PostMapping
    public ResponseEntity<RestricaoResponseDTO> criarRestricao(@RequestBody RestricaoRequestDTO dto) {
        RestricaoResponseDTO novaRestricao = restricaoService.criarRestricao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaRestricao);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<RestricaoResponseDTO> buscarRestricao(@PathVariable String cpf) {
        RestricaoResponseDTO restricao = restricaoService.buscarPorCPF(cpf);
        return ResponseEntity.ok(restricao);
    }

    @GetMapping
    public ResponseEntity<java.util.List<RestricaoResponseDTO>> listarRestricoes() {
        List<RestricaoResponseDTO> restricoes = restricaoService.listarRestricoes();
        return ResponseEntity.ok(restricoes);
    }
    
    @PutMapping("/normal/{cpf}")
    public ResponseEntity<RestricaoResponseDTO> atualizarRestricaoNormal(@PathVariable String cpf) {
        RestricaoResponseDTO restricaoAtualizada = restricaoService.atualizarRestricaoNormal(cpf);
        return ResponseEntity.ok(restricaoAtualizada);
    }

    @PutMapping("/restrito/{cpf}")
    public ResponseEntity<RestricaoResponseDTO> atualizarRestricaoRestrito(@PathVariable String cpf) {
        RestricaoResponseDTO restricaoAtualizada = restricaoService.atualizarRestricaoRestrito(cpf);
        return ResponseEntity.ok(restricaoAtualizada);
    }

    @PutMapping("/bloqueado/{cpf}")
    public ResponseEntity<RestricaoResponseDTO> atualizarRestricaoBloqueado(@PathVariable String cpf) {
        RestricaoResponseDTO restricaoAtualizada = restricaoService.atualizarRestricaoBloqueado(cpf);
        return ResponseEntity.ok(restricaoAtualizada);
    }

    @PutMapping("/cancelado/{cpf}")
    public ResponseEntity<RestricaoResponseDTO> atualizarRestricaoCancelado(@PathVariable String cpf) {
        RestricaoResponseDTO restricaoAtualizada = restricaoService.atualizarRestricaoCancelado(cpf);
        return ResponseEntity.ok(restricaoAtualizada);
    }
}
