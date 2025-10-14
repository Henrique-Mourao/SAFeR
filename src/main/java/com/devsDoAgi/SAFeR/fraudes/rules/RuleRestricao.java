package com.devsDoAgi.SAFeR.fraudes.rules;

import org.springframework.beans.factory.annotation.Autowired;

import com.devsDoAgi.SAFeR.enums.RestricaoStatus;
import com.devsDoAgi.SAFeR.fraudes.engine.FraudResult;
import com.devsDoAgi.SAFeR.fraudes.interfaces.FraudRule;
import com.devsDoAgi.SAFeR.model.Restricao;
import com.devsDoAgi.SAFeR.model.Transacao;
import com.devsDoAgi.SAFeR.repository.RestricaoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Classe de regra
 * Implementa FraudRule
 *
 * @author  João Henrique
 * @version 0.5
 */

@Component
public class RuleRestricao implements FraudRule {

    private String ruleName = "Regra de Análise de Restrição em CPF/CNPJ de Destino";

    /**
     * Override evaluate -> RuleRestricao
     * Método que vai receber os critérios de avaliação da regra.
     *
     * @param Transacao -> Consome um objeto Transacao.
     * @return FraudResult -> Retorna um objeto FraudResult.
     */

    @Autowired
    private RestricaoRepository restricaoRepository;

    @Override
    public FraudResult evaluate(Transacao transacao) {


        Optional<Restricao> restricaoOptional = restricaoRepository.findById(transacao.getCpfCnpjDestino());

        if (restricaoOptional.isPresent()) {
            Restricao restricao = restricaoOptional.get();
            if (restricao.getStatus().equals(RestricaoStatus.CANCELADO)) {
                return new FraudResult(ruleName, 90);
            } else if (restricao.getStatus().equals(RestricaoStatus.BLOQUEADO)) {
                return new FraudResult(ruleName, 90);
            } else if (restricao.getStatus().equals(RestricaoStatus.COM_RESTRICAO)) {
                return new FraudResult(ruleName, 7);
            } else if (restricao.getStatus().equals(RestricaoStatus.SEM_RESTRICAO)) {
                return new FraudResult(ruleName, 0);
            }
        }
        return new FraudResult(ruleName, 0);
    }
}