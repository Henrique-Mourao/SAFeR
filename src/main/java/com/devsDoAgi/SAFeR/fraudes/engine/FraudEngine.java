package com.devsDoAgi.SAFeR.fraudes.engine;

/**
 * Classe responsável por executar todas as regras
 * Recebe uma lista de regras.
 *
 * @author  João Henrique
 * @version 0.5
 */

/* Executa todas via evaluate(transacao).
 * Junta os resultados em um FraudSummary.
 */

import java.util.List;

import com.devsDoAgi.SAFeR.fraudes.interfaces.FraudRule;
import com.devsDoAgi.SAFeR.fraudes.rules.RuleValor;
import com.devsDoAgi.SAFeR.fraudes.rules.emAnalise.RuleValueValidator;
import com.devsDoAgi.SAFeR.model.Transacao;
import org.springframework.beans.factory.annotation.Autowired;

public class FraudEngine {
    private final List<FraudRule> rules;

    /**
     * Construtor Seletivo
     * Executa apenas as regras que forem atribuidas
     */

    @Autowired
    public FraudEngine(RuleValueValidator valueValidator) {
        this.rules = List.of(valueValidator); // usa o bean gerenciado pelo Spring
    }

    public FraudEngine(List<FraudRule> rules) {
        this.rules = rules;
    }

    /**
     * Construtor Padrão
     * Executa todas as regras por padrão
     */

//    public FraudEngine() {
//        this.rules = (List.of(
//                new RuleValueValidator()
//        ));
//    }

    /**
     * Avalia a transação com todas as regras atribuidas no construtor.
     *
     * @param transacao -> Consome um objeto Transacao.
     * @return FraudSummary -> Retorna um objeto FraudSummary.
     */

    public FraudSummary analyze(Transacao transacao) {
        int totalScore = 0;

        for (FraudRule rule : rules) {
            FraudResult result = rule.evaluate(transacao);
            System.out.println("Regra [" + result.getRuleName() + "] -> Score: " + result.getScore());

            totalScore += result.getScore();
        }

        return new FraudSummary(transacao.getIdTransacao(), totalScore);
    }
}
