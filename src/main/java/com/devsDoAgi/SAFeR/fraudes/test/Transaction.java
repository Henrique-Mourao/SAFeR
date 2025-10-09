package com.devsDoAgi.SAFeR.fraudes.test;

/**
 * Classe da transação
 * Reune todos os atributos de uma transação
 *
 * @author  João Henrique
 * @version 0.5
 */

import java.time.LocalDateTime;

public class Transaction {
    // Identificador da transação
    private Long id;
    // Quantia/valor em reais da transação (Futuras atualizações: mudar para BigInteger / BigDecimal)
    private double amount;
    // Dados da localização (Futuras atualizações: estruturar melhor)
    private String location;
    // Identificador do dispositivo
    private String deviceId;
    // Data e hora da transação
    private LocalDateTime timestamp;
    // Dados do destinatário (Futuras atualizações: estruturar melhor)
    private String destinationAccount;
    // Adicionar novos atributos se necessário

    public Transaction(Long id, double amount, String location, String deviceId, LocalDateTime timestamp, String destinationAccount) {
        this.id = id;
        this.amount = amount;
        this.location = location;
        this.deviceId = deviceId;
        this.timestamp = timestamp;
        this.destinationAccount = destinationAccount;
    }

    // Getters
    public Long getId() { return id; }
    public double getAmount() { return amount; }
    public String getLocation() { return location; }
    public String getDeviceId() { return deviceId; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDestinationAccount() { return destinationAccount; }
}