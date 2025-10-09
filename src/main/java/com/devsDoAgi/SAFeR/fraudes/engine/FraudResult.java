package com.devsDoAgi.SAFeR.fraudes.engine;

/**
 * Classe de retorno de cada regra que implementou a interface FraudRule
 *
 * @author  João Henrique
 * @version 1.0
 */

public class FraudResult {
    // Nome da Regra
    private final String ruleName;
    // Pontuação da Regra
    private final int score;

    public FraudResult(String ruleName, int score) {
        this.ruleName = ruleName;
        this.score = score;
    }

    // Getters
    public String getRuleName() { return ruleName; }
    public int getScore() { return score; }
}