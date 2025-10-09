package com.devsDoAgi.SAFeR.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

// Reponsável por criação/atualização.
public class ClienteRequestDTO {
    private String cpf;
    private String nome;
    private String dataNascimento; // Formato: "yyyy-MM-dd"
    private int score;
    private boolean ativo; 
    //private Conta conta;
}
