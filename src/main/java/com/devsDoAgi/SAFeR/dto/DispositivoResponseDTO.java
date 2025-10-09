package com.devsDoAgi.SAFeR.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

// Responsável por retornar informações completas.
public class DispositivoResponseDTO {
    private Long idDispositivo;
    private String tipo;
    private String status;
    private Double[] local;
    //private Conta conta;
}
