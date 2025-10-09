package com.devsDoAgi.SAFeR.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private boolean ativo = true;
    // Dados não são apagados, apenas desativados

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonBackReference
    private Conta conta;
    // Relacionamento OneToOne com Conta
    // Mapeado pelo campo cpfCliente em Conta
    
}
