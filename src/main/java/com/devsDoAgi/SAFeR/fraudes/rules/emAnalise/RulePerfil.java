package com.devsDoAgi.SAFeR.fraudes.rules.emAnalise;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.devsDoAgi.SAFeR.exception.AccounNotFound;
import com.devsDoAgi.SAFeR.fraudes.engine.FraudResult;
import com.devsDoAgi.SAFeR.fraudes.interfaces.FraudRule;
import com.devsDoAgi.SAFeR.model.Conta;
import com.devsDoAgi.SAFeR.model.Transacao;
import com.devsDoAgi.SAFeR.repository.ContaRepository;

import smile.anomaly.IsolationForest;

/**
 * Classe de regra
 * Implementa FraudRule
 *
 * @author João Henrique
 * @version 0.5
 */

public class RulePerfil implements FraudRule {

    private String ruleName = "Regra de Análise de Perfil do Cliente | IA";

    /**
     * Override evaluate -> RulePerfil
     * Método que vai receber os critérios de avaliação da regra.
     *
     * @param Transacao -> Consome um objeto Transacao.
     * @return FraudResult -> Retorna um objeto FraudResult.
     */

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public FraudResult evaluate(Transacao transacao) {

        Conta conta = contaRepository.findById(transacao.getNumContaOrigem())
                .orElseThrow(() -> new AccounNotFound("Conta não encontrada"));

        List<Transacao> historico = conta.getHistoricoTransacoes();

        // 1ª Validação: Se o histórico tiver menos de 10 transações, retorna 10
        if (historico.size() < 10) {
            return new FraudResult(ruleName, 50);
        }

        // 2ª Validação: Se o histórico tiver 10 ou mais transações, aplica o modelo de
        // IA
        if (historico.size() >= 10) {
            // Preparar os dados para o modelo de IA

            double[][] dadosHistorico = historico.stream()
                    .map(h -> new double[] {
                            h.getValor().doubleValue(),
                            h.getDataHoraOperacao().getHour() + (h.getDataHoraOperacao().getMinute() / 60.0)
                    })
                    .toArray(double[][]::new);

            // Normalizar os dados
            double minValor = Arrays.stream(dadosHistorico).mapToDouble(a -> a[0]).min().orElse(0);
            double maxValor = Arrays.stream(dadosHistorico).mapToDouble(a -> a[0]).max().orElse(1);
            double minHora = Arrays.stream(dadosHistorico).mapToDouble(a -> a[1]).min().orElse(0);
            double maxHora = Arrays.stream(dadosHistorico).mapToDouble(a -> a[1]).max().orElse(1);

            for (double[] linha : dadosHistorico) {
                linha[0] = (linha[0] - minValor) / (maxValor - minValor);
                linha[1] = (linha[1] - minHora) / (maxHora - minHora);
            }

            // Treina modelo
            IsolationForest modelo = IsolationForest.fit(dadosHistorico);

            // Calcula scores para os dados normais
            double[] scoresHistorico = Arrays.stream(dadosHistorico)
                    .mapToDouble(modelo::score)
                    .toArray();

            // Ordena os scores
            Arrays.sort(scoresHistorico);

            // Percentil 95/threshold (limite para considerar anomalia)
            int idx = (int) (scoresHistorico.length * 0.95);
            if (idx >= scoresHistorico.length)
                idx = scoresHistorico.length - 1; // proteção
            double threshold = scoresHistorico[idx];

            // Preparar os dados da nova transação
            double[][] dadosNovaTransacao = new double[][] {
                    {
                            (transacao.getValor().doubleValue() - minValor) / (maxValor - minValor),
                            (transacao.getDataHoraOperacao().getHour() + (transacao.getDataHoraOperacao().getMinute() / 60.0) - minHora) / (maxHora - minHora)
                    }
            };

            // Calcula o score da nova transação
            double scoreNovaTransacao = modelo.score(dadosNovaTransacao[0]);

            // Avalia a nova transação
            if (scoreNovaTransacao >= threshold) {
                return new FraudResult(ruleName, 50); // Transação suspeita
            } else {
                return new FraudResult(ruleName, 0); // Transação normal
            }
        }

        return new FraudResult(ruleName, 0);
    }

}
