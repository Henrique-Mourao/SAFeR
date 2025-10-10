package com.devsDoAgi.SAFeR.fraudes.rules.emAnalise;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.devsDoAgi.SAFeR.fraudes.engine.FraudResult;
import com.devsDoAgi.SAFeR.fraudes.interfaces.FraudRule;
import com.devsDoAgi.SAFeR.model.Dispositivo;
import com.devsDoAgi.SAFeR.model.Transacao;
import com.devsDoAgi.SAFeR.repository.DispositivoRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RuleDeviceMismatch implements FraudRule {

    private final DispositivoRepository dispositivoRepository;

    @Override
    public FraudResult evaluate(Transacao transacao) {
        String ruleName = "Regra de dispositivo (mismatch)";

        try {
            Optional<Dispositivo> dispositivoOpt = dispositivoRepository.findByContaNumConta(transacao.getNumContaOrigem());

            if (dispositivoOpt.isPresent()) {
                Dispositivo dispositivoConta = dispositivoOpt.get();

                // Compara enums (DispositivoTipos) com segurança
                if (dispositivoConta.getTipo() == null || transacao.getDispositivo() == null) {
                    return new FraudResult(ruleName, 0);
                }

                if (!dispositivoConta.getTipo().name().equals(transacao.getDispositivo().name())) {
                    return new FraudResult(ruleName, 27);
                }
            } else {
                // Sem dispositivo cadastrado, não pontua aqui (ou poderia pontuar se desejado)
                return new FraudResult(ruleName, 0);
            }
        } catch (Exception e) {
            // Em caso de erro ao consultar, não pontua para evitar falsos positivos
            System.out.println("RuleDeviceMismatch: erro ao consultar dispositivo - " + e.getMessage());
            return new FraudResult(ruleName, 0);
        }

        return new FraudResult(ruleName, 0);
    }
}