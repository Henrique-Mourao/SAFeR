package com.devsDoAgi.SAFeR.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoResponseDTO {
    private BigDecimal valor;
    private LocalDateTime dataHoraOperacao;
    private String dispositivo;
    private Double[] local;
    private String meioPagamento;
    private String numContaOrigem;
    private String numAgenciaOrigem;
    private String ispbOrigem;
    private String cpfOrigem;
    private String numContaDestino;
    private String numAgenciaDestino;
    private String ispbDestino;
    private String cpfCnpjDestino;
    private int scoreTransacao;
    private boolean transacaoAnalisada;
    private List<String> motivos;
}