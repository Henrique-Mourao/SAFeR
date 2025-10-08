package com.devsDoAgi.SAFeR.fraudes.rules.emAnalise;

import com.devsDoAgi.SAFeR.fraudes.engine.FraudResult;
import com.devsDoAgi.SAFeR.fraudes.interfaces.FraudRule;
import com.devsDoAgi.SAFeR.model.Transacao;
import com.devsDoAgi.SAFeR.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Component
@AllArgsConstructor
public class RulePeriodicity implements FraudRule {

    private TransacaoRepository transacaoRepository;


    @Override
    public FraudResult evaluate(Transacao transaction) {

        List<Transacao> transactionsLastHour = getLastHour(transaction);


        if (transactionsLastHour.size() + 1 < 3) {
            return new FraudResult("Regra de periodicidade", 0);
        }
        int totalScore = 5;

        totalScore += scoreByFrequency(transactionsLastHour, transaction);
        totalScore += scoreByDestinations(transactionsLastHour);
        totalScore += scoreByValues(transactionsLastHour);

            return new FraudResult("Regra de periodicidade", totalScore);
        }

    private int scoreByFrequency(List<Transacao> transactions, Transacao current) {
        List<Duration> intervals = calcIntervalBetween(transactions, current);
        Duration average = intervals.stream()
                .reduce(Duration.ofMinutes(0), (i, i2) -> i.plusMinutes(i2.toMinutes()))
                .dividedBy(intervals.size());
        return average.compareTo(Duration.ofMinutes(15)) < 0 ? 5 : 0;
    }

    private int scoreByDestinations(List<Transacao> transactions) {
        HashMap<String, Integer> destinationsOccurrences = getDestinationsOccurrences(transactions);
        int totalScore = 0;
        for (Map.Entry<String, Integer> entry : destinationsOccurrences.entrySet())
            if (entry.getValue() >= 3) {
                totalScore += 5;
            }
        return totalScore;
    }

    private int scoreByValues(List<Transacao> transactions) {
        int totalScore = 0;
        HashMap<BigDecimal, Integer> valueOccurrences = getValueOccurrences(transactions);
        for (Map.Entry<BigDecimal, Integer> entry: valueOccurrences.entrySet()){
            if (entry.getValue() >= 3){
                totalScore += 5;
            }
        }
        return totalScore;
    }

    public List<Transacao> getLastHour(Transacao transaction) {
        LocalDateTime dataTransacao = transaction.getDataHoraOperacao();
        LocalDateTime lastHour = dataTransacao.minusHours(1);
        List<Transacao> transacoes = transacaoRepository.findAll().stream()
                .filter(t -> t.getNumContaDestino().equals(transaction.getNumContaDestino()))
                .toList();

        ArrayList<Transacao> lastHourTransaction = new ArrayList<>();
        for (Transacao t: transacoes){
            if (t.getDataHoraOperacao().isAfter(lastHour) && t.getDataHoraOperacao().isBefore(transaction.getDataHoraOperacao())){
                lastHourTransaction.add(t);
            }
        }

        List<Transacao> sortedLastHourTransaction = lastHourTransaction.stream().sorted(Comparator.comparing(Transacao::getDataHoraOperacao)).toList();
        return sortedLastHourTransaction;
    }

    protected Duration calcPeriod(Transacao t1, Transacao t2) {
        LocalDateTime dataT1 = t1.getDataHoraOperacao();
        LocalDateTime dataT2 = t2.getDataHoraOperacao();

        Duration intervalBtw = Duration.between(dataT1, dataT2);
        return intervalBtw;

    }

    private HashMap<String, Integer> getDestinationsOccurrences(List<Transacao> transactions) {
        HashMap<String, Integer> destinationsOccurrences = new HashMap<>();

        for (Transacao t : transactions) {
            String cpfCnpj = t.getCpfCnpjDestino();
            if (!destinationsOccurrences.containsKey(cpfCnpj)) {
                destinationsOccurrences.put(cpfCnpj, 1);
            } else {
                Integer currentValue = destinationsOccurrences.get(cpfCnpj);
                destinationsOccurrences.put(cpfCnpj, currentValue + 1);
            }
        }
        return destinationsOccurrences;
    }

    private List<Duration> calcIntervalBetween(List<Transacao> transactions, Transacao currentTransaction) {
        List<Duration> allIntervals = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            Transacao t1 = transactions.get(i);
            Transacao t2 = (i < transactions.size() - 1) ? transactions.get(i + 1) : currentTransaction;
            allIntervals.add(calcPeriod(t1, t2));
        }
        return allIntervals;
    }

    private HashMap<BigDecimal, Integer> getValueOccurrences(List<Transacao> transactions){
        HashMap<BigDecimal, Integer> valueOccurrences = new HashMap<>();
        for (Transacao t: transactions){
            BigDecimal value = t.getValor();
            if (!valueOccurrences.containsKey(value)) {
                valueOccurrences.put(value, 1);
            } else {
                valueOccurrences.put(value, valueOccurrences.get(value) + 1);
            }
        }
        return valueOccurrences;
    }
}
