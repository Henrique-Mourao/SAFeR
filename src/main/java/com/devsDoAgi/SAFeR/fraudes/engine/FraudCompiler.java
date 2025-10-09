package com.devsDoAgi.SAFeR.fraudes.engine;

import com.devsDoAgi.SAFeR.fraudes.interfaces.FraudRule;
import com.devsDoAgi.SAFeR.fraudes.rules.RegraModelo;
import com.devsDoAgi.SAFeR.fraudes.rules.emAnalise.RuleValue;
import com.devsDoAgi.SAFeR.model.Transacao;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
public class FraudCompiler {

    //Alocar aqui dependecias de funcionamento de regras

    private List<FraudRule> regras;

    private RuleValue ruleValue;

    private RegraModelo regraModelo;

    //Criação da lista após injeção de beans de regra
    @PostConstruct
    public void init() {
        // List de todas as regras
        this.regras = List.of(ruleValue, regraModelo);
}

    public FraudSummary percorrerRegras(Transacao transacao) {
        int totalScore = 0;

        for (FraudRule rule : regras) {
            FraudResult result = rule.evaluate(transacao);
            System.out.println("Regra [" + result.getRuleName() + "] -> Score: " + result.getScore());

            totalScore += result.getScore();
        }

        return new FraudSummary(transacao.getIdTransacao(), totalScore);
    }
}
