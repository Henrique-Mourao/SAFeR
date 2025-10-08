package com.devsDoAgi.SAFeR.fraudes.rules.emAnalise;

import com.devsDoAgi.SAFeR.exception.AccounNotFound;
import com.devsDoAgi.SAFeR.exception.EmptyNightLimit;
import com.devsDoAgi.SAFeR.fraudes.engine.FraudResult;
import com.devsDoAgi.SAFeR.fraudes.interfaces.FraudRule;
import com.devsDoAgi.SAFeR.model.Conta;
import com.devsDoAgi.SAFeR.model.Transacao;
import com.devsDoAgi.SAFeR.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@AllArgsConstructor
public class RuleTime implements FraudRule {

    ContaRepository contaRepository;

    public Boolean isInRiskTime(Transacao transacao){
        LocalDateTime dataTransacao = transacao.getDataHoraOperacao();
        LocalTime transactionTime = LocalTime.of(dataTransacao.getHour(),dataTransacao.getMinute(),dataTransacao.getSecond());
        LocalTime startTimeRisk = LocalTime.of(20,00,00);
        LocalTime endTimeRisk = LocalTime.of(06,00,00);

        return (transactionTime.isAfter(startTimeRisk) || transactionTime.isBefore(endTimeRisk));
    }

    @Override
    public FraudResult evaluate(Transacao transacao) {

        if (isInRiskTime(transacao)) {

            Conta conta = contaRepository.findById(transacao.getNumContaOrigem()).orElseThrow(() -> new AccounNotFound("Conta não encontrada"));

            BigDecimal limiteNoturno = conta.getLimiteNoturno();
            BigDecimal value = transacao.getValor();
            if (limiteNoturno == null) {
                throw new EmptyNightLimit("O cliente não possui um limite noturno definido");
            }

            if (value.compareTo(limiteNoturno) > 3) {
                FraudResult fraudResult = new FraudResult("Regra horário de risco",50);
                return fraudResult;
            }
            if (value.compareTo(limiteNoturno) == 0){
                FraudResult fraudResult = new FraudResult("Regra horário de risco",10);
                return fraudResult;
            }

        }
        return new FraudResult("Regra horário de risco",0);
    }
}
