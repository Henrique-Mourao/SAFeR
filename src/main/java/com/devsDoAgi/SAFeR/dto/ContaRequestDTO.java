package com.devsDoAgi.SAFeR.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

// Reponsável por criação/atualização.
public class ContaRequestDTO {
    private String numConta;
    private String numAgencia;
    private String ispb;
    private String cpfCliente;
    private Long idDispositivo;
    private List<TransacaoRequestDTO> historicoTransacoes;
    private String limiteNoturno;
    private String status;
}
