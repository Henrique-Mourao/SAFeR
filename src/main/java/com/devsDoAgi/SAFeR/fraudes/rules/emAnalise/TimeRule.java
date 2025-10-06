package com.devsDoAgi.SAFeR.fraudes.rules.emAnalise;

import com.devsDoAgi.SAFeR.exception.AccounNotFound;
import com.devsDoAgi.SAFeR.exception.EmptyNightLimit;
import com.devsDoAgi.SAFeR.fraudes.engine.FraudResult;
import com.devsDoAgi.SAFeR.fraudes.interfaces.FraudRule;
import com.devsDoAgi.SAFeR.model.Conta;
import com.devsDoAgi.SAFeR.model.Transacao;
import com.devsDoAgi.SAFeR.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
@AllArgsConstructor
public class TimeRule implements FraudRule {

    @Autowired
    RuleValueValidator ruleValueValidator;

    @Autowired
    ContaRepository contaRepository;

    @Override
    public FraudResult evaluate(Transacao transacao) {

        LocalDateTime dataTransacao = transacao.getDataHoraOperacao();
        LocalDateTime startTimeRisk = LocalDateTime.of(dataTransacao.getYear(), dataTransacao.getMonth(), dataTransacao.getDayOfMonth(), 20, 0, 0);
        LocalDateTime endTimeRisk = LocalDateTime.of(dataTransacao.getYear(), dataTransacao.getMonth(), dataTransacao.getDayOfMonth(), 06, 0, 0).plusDays(1);

        if (transacao.getDataHoraOperacao().isAfter(startTimeRisk) && transacao.getDataHoraOperacao().isBefore(endTimeRisk)) {

            Conta conta = contaRepository.findById(transacao.getNumContaOrigem()).orElseThrow(() -> new AccounNotFound("Conta não encontrada"));
            BigDecimal limiteNorturno = conta.getLimiteNoturno();
            BigDecimal value = transacao.getValor();
            if (limiteNorturno == null) {
                throw new EmptyNightLimit("O cliente não possui um limite noturno definid");
            }

            //  Verifica se o valor da transação é menor ou igual ao teto de alarme

            //  a.compareTo(b) -> retornará
            //  1 -> if a > b
            //  0 -> if a == b
            // -1 -> if a < b

            if (value.compareTo(limiteNorturno) < 0) {
                FraudResult fraudResult = new FraudResult("Regra horário de risco",50);

            }


        }
        return new FraudResult("Regra horário de risco",0);
    }
}
