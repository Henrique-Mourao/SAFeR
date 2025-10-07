package com.devsDoAgi.SAFeR.fraudes.rules.emAnalise;

import com.devsDoAgi.SAFeR.exception.AccounNotFound;
import com.devsDoAgi.SAFeR.model.Transacao;
import com.devsDoAgi.SAFeR.repository.ContaRepository;
import com.devsDoAgi.SAFeR.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RulePeriodicity {

    private ContaRepository contaRepository;
    TransacaoRepository transacaoRepository;


    public List<Transacao> getLastHour(Transacao transacao){ //Retorna as transações de mesmo tipo feitas na ultimas hora

        LocalDateTime dataTransacao = transacao.getDataHoraOperacao();
        LocalDateTime lastHour = dataTransacao.minusHours(1);

        List<Transacao> transacoes = contaRepository.findById(transacao.getNumContaOrigem())
                .orElseThrow(()-> new AccounNotFound("Conta não encontrada"))
                .getHistoricoTransacoes();

        List<Transacao> lastHourTransaction = transacoes.stream()
                .filter(t -> t.getDataHoraOperacao().isAfter(lastHour) && t.getMeioPagamento().equals(transacao.getMeioPagamento()))
                .toList();

        List<Transacao> sortedLastHourTransaction =  lastHourTransaction.stream().sorted(Comparator.comparing(Transacao::getDataHoraOperacao)).toList();

        return sortedLastHourTransaction;
    }





}
