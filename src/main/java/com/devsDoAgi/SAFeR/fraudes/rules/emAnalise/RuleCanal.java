package com.devsDoAgi.SAFeR.fraudes.rules.emAnalise;

import com.devsDoAgi.SAFeR.fraudes.engine.FraudResult;
import com.devsDoAgi.SAFeR.fraudes.interfaces.FraudRule;
import com.devsDoAgi.SAFeR.model.Transacao;

public class RuleCanal implements FraudRule {

    private static final String RULE_NAME = "Regra de Canal/Origem";

    private static final int SCORE_BLOQUEIO_PREVENTIVO = 30;
    private static final int SCORE_ALERTA_CARTAO_CREDITO = 5;
    private static final int SCORE_MUDANCA_CANAL_CRITICA = 15;
    private static final int SCORE_MUDANCA_CANAL_GENERICA = 10;

    private static final int LIMITE_TENTATIVAS = 3;
    private static final String TIPO_CREDITO = "CREDITO";
    private static final String CANAL_APP = "APP";
    private static final String CANAL_CAIXA_ELETRONICO = "CAIXA_ELETRONICO";

    @Override
    public FraudResult evaluate(Transacao transacao) {

        int riskScore = 0;

        riskScore += avaliarTentativasCVV(transacao);

        riskScore += avaliarTentativasSenha(transacao);

        riskScore += avaliarTipoCartao(transacao);

        riskScore += avaliarMudancaCanal(transacao);

        return new FraudResult(RULE_NAME, riskScore);
    }

    private int avaliarTentativasCVV(Transacao transacao) {
        if (transacao.getTentativasErradasCVV() != null &&
                transacao.getTentativasErradasCVV() >= LIMITE_TENTATIVAS) {
            return SCORE_BLOQUEIO_PREVENTIVO;
        }
        return 0;
    }

    private int avaliarTentativasSenha(Transacao transacao) {
        if (transacao.getTentativasErradasSenha() != null &&
                transacao.getTentativasErradasSenha() >= LIMITE_TENTATIVAS) {
            return SCORE_BLOQUEIO_PREVENTIVO;
        }
        return 0;
    }

    private int avaliarTipoCartao(Transacao transacao) {
        if (transacao.getTipoCartao() != null &&
                transacao.getTipoCartao().equalsIgnoreCase(TIPO_CREDITO)) {
            return SCORE_ALERTA_CARTAO_CREDITO;
        }
        return 0;
    }

    private int avaliarMudancaCanal(Transacao transacao) {
        if (transacao.getCanalAtual() == null || transacao.getCanalUsual() == null) {
            return 0;
        }

        if (transacao.getCanalAtual().equalsIgnoreCase(transacao.getCanalUsual())) {
            return 0;
        }

        return avaliarCriticidadeMudancaCanal(transacao);
    }

    private int avaliarCriticidadeMudancaCanal(Transacao transacao) {
        if (transacao.getCanalUsual().equalsIgnoreCase(CANAL_APP) &&
                transacao.getCanalAtual().equalsIgnoreCase(CANAL_CAIXA_ELETRONICO)) {
            return SCORE_MUDANCA_CANAL_CRITICA;
        }

        return SCORE_MUDANCA_CANAL_GENERICA;
    }
}