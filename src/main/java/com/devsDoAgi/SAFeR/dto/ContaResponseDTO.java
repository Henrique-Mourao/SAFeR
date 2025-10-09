package com.devsDoAgi.SAFeR.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.devsDoAgi.SAFeR.model.Cliente;
import com.devsDoAgi.SAFeR.model.Dispositivo;
import com.devsDoAgi.SAFeR.model.Transacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

// Responsável por retornar informações completas.
public class ContaResponseDTO {
    private String numConta;
    private String numAgencia;
    private String ispb;
    private Cliente cliente;
    private Dispositivo dispositivo;
    private List<Transacao> historicoTransacoes;
    private String limiteNoturno;
    private LocalDateTime dataAbertura;
    private String status;
}
