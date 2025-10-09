package com.devsDoAgi.SAFeR.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

// Reponsável por criação/atualização.
public class RestricaoRequestDTO {
    private String id;
    private String tipo;
    private String status;
}
