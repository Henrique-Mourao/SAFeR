package com.devsDoAgi.SAFeR.model;

import com.devsDoAgi.SAFeR.enums.RestricaoTipos;
import com.devsDoAgi.SAFeR.enums.RestricaoStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restricoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restricao {

    @Id
    @Column(nullable = false, unique = true)
    private String id;

    @Enumerated(EnumType.STRING)
    private RestricaoTipos tipo;
    // Tipo de restrição (e.g., CPF, CNPJ)

    @Enumerated(EnumType.STRING)
    private RestricaoStatus status;
    // Status da restrição (e.g., SEM_RESTRICAO, COM_RESTRICAO, BLOQUEADO, CANCELADO)
}
