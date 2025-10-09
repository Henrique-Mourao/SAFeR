package com.devsDoAgi.SAFeR.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.devsDoAgi.SAFeR.enums.ContaStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

    @Id
    @Column(nullable = false, unique = true)
    private String numConta;

    private String numAgencia;

    private String ispb; // Identificador do Sistema de Pagamentos Brasileiro

    @OneToOne
    @JoinColumn(name = "cpf_cliente", referencedColumnName = "cpf")
    @JsonManagedReference
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "id_dispositivo", referencedColumnName = "idDispositivo")
    @JsonManagedReference
    private Dispositivo dispositivo;

    // Relacionamento com Transacao
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Transacao> historicoTransacoes = new ArrayList<>();

    @Column(precision = 15, scale = 2)
    // Precision → número total de dígitos (antes + depois da vírgula).
    // Scale → quantos desses dígitos ficam depois da vírgula.
    private BigDecimal limiteNoturno;

    private LocalDateTime dataAbertura;

    @Enumerated(EnumType.STRING)
    private ContaStatus status;

    public void addTransacao(Transacao transacao) {
        historicoTransacoes.add(transacao);
        transacao.setConta(this);
    }
}