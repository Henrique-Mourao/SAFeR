package com.devsDoAgi.SAFeR.model;

import com.devsDoAgi.SAFeR.enums.DispositivoStatus;
import com.devsDoAgi.SAFeR.enums.DispositivoTipos;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dispositivos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDispositivo;

    @Enumerated(EnumType.STRING)
    private DispositivoTipos tipo;
    // Tipo de dispositivo (e.g., MOBILE, DESKTOP, AUTO_ATENDIMENTO)

    @Enumerated(EnumType.STRING)
    private DispositivoStatus status;
    // Status do dispositivo (e.g., ATORIZADO, NAO_AUTORIZADO)

    private Double[] local;
    
    @OneToOne(mappedBy = "dispositivo", cascade = CascadeType.ALL)
    @JsonBackReference
    private Conta conta;
    // Relacionamento OneToOne com Conta
    // Mapeado pelo campo dispositivo em Conta
}
