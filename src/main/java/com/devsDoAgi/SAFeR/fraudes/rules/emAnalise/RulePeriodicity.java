package com.devsDoAgi.SAFeR.fraudes.rules.emAnalise;

import com.devsDoAgi.SAFeR.model.Transacao;
import com.devsDoAgi.SAFeR.repository.ContaRepository;
import com.devsDoAgi.SAFeR.repository.TransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RulePeriodicity {

    private ContaRepository contaRepository;



}
