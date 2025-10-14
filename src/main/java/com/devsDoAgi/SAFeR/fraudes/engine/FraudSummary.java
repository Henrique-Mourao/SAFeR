package com.devsDoAgi.SAFeR.fraudes.engine;

/**
 * Classe responsável por exibir o resultado final da análise e executar a ação adequada ao totalScore
 *
 * @author  João Henrique
 * @version 1.0
 */

public class FraudSummary {
    private final Long id;
    private final int totalScore;

    public FraudSummary(Long id, int totalScore) {
        this.id = id;
        this.totalScore = totalScore;

        System.out.println("Total Score: " + getTotalScore());
        System.out.println("É fraude? " + isFraud());
        System.out.println("Precisa de revisão? " + needsReview());
        System.out.println("Está limpo? " + isClean());
    }

    /**
     * É fraude -> bloquear
     *
     * @return boolean.
     */

    public boolean isFraud() {
        return totalScore >= 90;
    }

    /**
     * Encaminhar para analise
     *
     * @return boolean.
     */

    public boolean needsReview() {
        return totalScore >= 50;
    }

    /**
     * Transação aprovada
     *
     * @return boolean.
     */

    public boolean isClean() {
        return totalScore < 50;
    }

    // Getters
    public Long getId() { return id; }
    public int getTotalScore() { return totalScore; }
}
