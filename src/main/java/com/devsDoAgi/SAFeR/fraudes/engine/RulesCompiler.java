package com.devsDoAgi.SAFeR.fraudes.engine;

import com.devsDoAgi.SAFeR.fraudes.interfaces.FraudRule;
import com.devsDoAgi.SAFeR.fraudes.rules.RuleValor;
import com.devsDoAgi.SAFeR.fraudes.rules.emAnalise.RuleTime;
import com.devsDoAgi.SAFeR.fraudes.rules.emAnalise.RuleValueValidator;
import com.devsDoAgi.SAFeR.model.Transacao;
import com.devsDoAgi.SAFeR.repository.TransacaoRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class RulesCompiler {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private RuleValueValidator ruleValueValidator;

    @Autowired
    private RuleTime ruleTime;

    private List<FraudRule> regras;

    @Autowired
    private RuleValor ruleValor;
    @PostConstruct
    public void init() {
        // Agora o Spring já injetou os beans
        this.regras = List.of(ruleValueValidator, ruleTime);
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
