package com.devsDoAgi.study;

import smile.anomaly.IsolationForest;
import java.util.*;

public class IsolationForestExample {
    public static void main(String[] args) {
        // Histórico de transações "normais"
        double[][] dadosNormais = {
                {100.0, 14.0, 1.0},
                {120.0, 11.0, 1.0},
                {130.0, 10.0, 1.0},
                {110.0, 9.0, 1.0},
                {95.0, 12.0, 1.0},
                {115.0, 10.0, 1.0},
                {105.0, 11.0, 1.0},
                {102.0, 13.0, 1.0}
        };

        // Treina modelo
        IsolationForest modelo = IsolationForest.fit(dadosNormais);

        // Calcula scores para os dados normais
        double[] scoresNormais = Arrays.stream(dadosNormais)
                .mapToDouble(modelo::score)
                .toArray();

        // Ordena os scores
        Arrays.sort(scoresNormais);

        // Percentil 95
        int idx = (int) (scoresNormais.length * 0.95);
        if (idx >= scoresNormais.length) idx = scoresNormais.length - 1; // proteção
        double threshold = scoresNormais[idx];

        System.out.println("Threshold definido (95º percentil): " + threshold);

        // Nova transação para avaliar
        double[] novaTransacao = {5000.0, 3.0, 2.0};
        double scoreNova = modelo.score(novaTransacao);

        System.out.println("Score nova transação: " + scoreNova);

        if (scoreNova >= threshold) {
            System.out.println("Possível fraude detectada!");
        } else {
            System.out.println("Transação dentro do padrão.");
        }
    }
}
