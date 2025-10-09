package com.devsDoAgi.SAFeR.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.devsDoAgi.SAFeR.enums.DispositivoTipos;
import com.devsDoAgi.SAFeR.enums.TransacaoMeioPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransacao;

    @Column(nullable = false)
    private BigDecimal valor;

    private LocalDateTime dataHoraOperacao;

    @Enumerated(EnumType.STRING)
    private DispositivoTipos dispositivo;

    private Double[] local;

    @Enumerated(EnumType.STRING)
    private TransacaoMeioPagamento meioPagamento;

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

    @ManyToOne
    @JoinColumn(name = "numConta", referencedColumnName = "numConta")
    @JsonIgnore
    private Conta conta;

    private String tipoCartao;
    private String canalAtual;
    private String canalUsual;
    private Integer tentativasErradasCVV;
    private Integer tentativasErradasSenha;
}
