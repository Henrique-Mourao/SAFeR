package com.devsDoAgi.SAFeR.fraudes.engine;

import com.devsDoAgi.SAFeR.fraudes.interfaces.FraudRule;
import com.devsDoAgi.SAFeR.fraudes.rules.RuleValor;
import com.devsDoAgi.SAFeR.fraudes.rules.emAnalise.RuleValueValidator;
import com.devsDoAgi.SAFeR.model.Transacao;
import com.devsDoAgi.SAFeR.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RulesCompiler {

    private TransacaoRepository transacaoRepository;

    private RuleValueValidator ruleValueValidator;

    private RuleValor ruleValor;

    private List<FraudRule> regras = List.of(ruleValueValidator, ruleValor);


    public FraudSummary PercorrerRegras(List<FraudRule> rules, Transacao transacao) {
        int totalScore = 0;

        for (FraudRule rule : rules) {
            FraudResult result = rule.evaluate(transacao);
            System.out.println("Regra [" + result.getRuleName() + "] -> Score: " + result.getScore());

            totalScore += result.getScore();
        }

        return new FraudSummary(transacao.getIdTransacao(), totalScore);
    }
}
