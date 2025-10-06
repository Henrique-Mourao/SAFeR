package com.devsDoAgi.SAFeR.fraudes.rules;

import java.math.BigDecimal;

import com.devsDoAgi.SAFeR.fraudes.engine.FraudResult;
import com.devsDoAgi.SAFeR.fraudes.interfaces.FraudRule;
import com.devsDoAgi.SAFeR.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Classe de regra
 * Implementa FraudRule
 *
 * @author  João Henrique
 * @version 0.5
 */

@Component
@NoArgsConstructor
public class RuleValor implements FraudRule {

    /**
     * Override evaluate -> Regra de valor
     * Método que vai receber os critérios de avaliação da regra.
     *
     * @param transacao -> Consome um objeto Transacao.
     * @return FraudResult -> Retorna um objeto FraudResult.
     */

    @Override
    public FraudResult evaluate(Transacao transacao) {
        String ruleName = "Regra de valor";
        if (transacao.getValor().compareTo(new BigDecimal(5000.00)) > 0) {
            return new FraudResult(ruleName, 10);
        }

        return new FraudResult(ruleName, 0);
    }
}

