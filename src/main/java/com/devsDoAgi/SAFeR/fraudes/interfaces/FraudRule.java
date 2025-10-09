package com.devsDoAgi.SAFeR.fraudes.interfaces;

import com.devsDoAgi.SAFeR.fraudes.engine.FraudResult;
import com.devsDoAgi.SAFeR.model.Transacao;

/**
 * Interface de regras padrão
 *
 * @author  João Henrique
 * @version 1.0
 */

public interface FraudRule {

    /**
     * evaluate -> avaiar
     * Método que vai receber os critérios de avaliação de cada regra.
     *
     * @param Transaction -> Consome um objeto Transaction.
     * @return FraudResult -> Retorna um objeto FraudResult.
     */

    FraudResult evaluate(Transacao transacao);
}
