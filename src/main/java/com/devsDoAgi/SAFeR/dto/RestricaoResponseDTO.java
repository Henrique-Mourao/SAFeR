package com.devsDoAgi.SAFeR.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

// Responsável por retornar informações completas.
public class RestricaoResponseDTO {
    private String id;
    private String tipo;
    private String status;
}
