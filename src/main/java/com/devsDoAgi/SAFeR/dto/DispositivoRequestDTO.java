package com.devsDoAgi.SAFeR.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

// Reponsável por criação/atualização.
public class DispositivoRequestDTO {
    private String tipo;
    private String status;
    private Double[] local;
    //private Conta conta;
}
