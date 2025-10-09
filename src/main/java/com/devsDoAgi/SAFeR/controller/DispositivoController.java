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

import com.devsDoAgi.SAFeR.dto.DispositivoRequestDTO;
import com.devsDoAgi.SAFeR.dto.DispositivoResponseDTO;
import com.devsDoAgi.SAFeR.service.DispositivoService;

@RestController
@RequestMapping("/dispositivos")
public class DispositivoController {
    
    private final DispositivoService dispositivoService;   

    public DispositivoController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @PostMapping
    public ResponseEntity<DispositivoResponseDTO> criarDispositivo(@RequestBody DispositivoRequestDTO dto) {
        DispositivoResponseDTO novoDispositivo = dispositivoService.criarDispositivo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDispositivo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivoResponseDTO> buscarDispositivo(@PathVariable Long id) {
        DispositivoResponseDTO dispositivo = dispositivoService.buscarDispositivo(id);
        return ResponseEntity.ok(dispositivo);
    }

    @GetMapping
    public ResponseEntity<List<DispositivoResponseDTO>> listarDispositivos() {
        List<DispositivoResponseDTO> dispositivos = dispositivoService.listarDispositivo();
        return ResponseEntity.ok(dispositivos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispositivoResponseDTO> atualizarDispositivo(@PathVariable Long id, @RequestBody DispositivoRequestDTO dto) {
        DispositivoResponseDTO dispositivoAtualizado = dispositivoService.atualizarDispositivo(id, dto);
        return ResponseEntity.ok(dispositivoAtualizado);
    }

    // Não deleta, apenas altera status para NAO_AUTORIZADO
    @DeleteMapping("/{id}")
    public ResponseEntity<DispositivoResponseDTO> bloquearDispositivo(@PathVariable Long id) {
        DispositivoResponseDTO dispositivoBloqueado = dispositivoService.bloquearDispositivo(id);
        return ResponseEntity.ok(dispositivoBloqueado);
    }
}
