package com.devsDoAgi.study;

import java.util.Arrays;

import smile.anomaly.IsolationForest;

public class IsolationForestApply {

    public static void main(String[] args) {

        // Histórico de transações "normais"
        // Dados: Valor e hora do dia (0-23)
        double[][] dadosHistorico = {
                { 100.0, 14.0 },
                { 120.0, 11.0 },
                { 130.0, 10.0 },
                { 110.0, 9.0 },
                { 95.0, 12.0 },
                { 115.0, 10.0 },
                { 105.0, 11.0 },
                { 102.0, 13.0 },
                { 108.0, 15.0 },
                { 112.0, 14.0 },
                { 118.0, 16.0 },
                { 125.0, 10.0 },
                { 130.0, 9.0 },
                { 140.0, 11.0 },
                { 135.0, 12.0 }
        };

        // Normalizar os dados
        double minValor = Arrays.stream(dadosHistorico).mapToDouble(a -> a[0]).min().orElse(0);
        double maxValor = Arrays.stream(dadosHistorico).mapToDouble(a -> a[0]).max().orElse(1);
        double minHora = Arrays.stream(dadosHistorico).mapToDouble(a -> a[1]).min().orElse(0);
        double maxHora = Arrays.stream(dadosHistorico).mapToDouble(a -> a[1]).max().orElse(1);

        for (double[] linha : dadosHistorico) {
            linha[0] = (linha[0] - minValor) / (maxValor - minValor);
            linha[1] = (linha[1] - minHora) / (maxHora - minHora);
        }

        // -> Dados normalizados prontos | Validação <-

        System.out.println("Dados normalizados:");
        for (double[] linha : dadosHistorico) {
            System.out.println(Arrays.toString(linha));
        }

        // Treina modelo
        IsolationForest modelo = IsolationForest.fit(dadosHistorico);

        // Calcula scores para os dados normais
        double[] scoresHistorico = Arrays.stream(dadosHistorico)
                .mapToDouble(modelo::score)
                .toArray();

        // Ordena os scores
        Arrays.sort(scoresHistorico);

        // -> Exibe os scores | Validação <-
        System.out.println("\nScores do histórico:");
        for (double score : scoresHistorico) {
            System.out.println("Score: " + score);
        }

        // Percentil 95/threshold (limite para considerar anomalia)
        int idx = (int) (scoresHistorico.length * 0.95);
        if (idx >= scoresHistorico.length)
            idx = scoresHistorico.length - 1; // proteção
        double threshold = scoresHistorico[idx];

        // -> Exibe o threshold | Validação <-
        System.out.println("\nThreshold definido (95º percentil): " + threshold);

        // Preparar os dados da nova transação
        double[][] dadosNovaTransacao = new double[][] {
                {
                        (400.0 - minValor) / (maxValor - minValor),
                        (3.0 - minHora) / (maxHora - minHora)
                }
        };

        // Calcula o score da nova transação
        double scoreNovaTransacao = modelo.score(dadosNovaTransacao[0]);

        // -> Exibe o score da nova transação | Validação <-
        System.out.println("\nScore nova transação: " + scoreNovaTransacao);

        // Avalia a nova transação
        if (scoreNovaTransacao >= threshold) {
            System.out.println("Possível fraude detectada!");
        } else {
            System.out.println("Transação dentro do padrão.");
        }

    }

}
