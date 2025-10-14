package com.devsDoAgi.SAFeR.fraudes.engine;

/**
 * Classe responsável por executar todas as regras
 * Recebe uma lista de regras.
 *
 * @author  João Henrique
 * @version 0.5
 */

/* Executa todas via metodo "percorrerRegras"
 * Junta os resultados em um FraudSummary.
 */

import com.devsDoAgi.SAFeR.model.Transacao;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FraudEngine {

    FraudCompiler fraudCompiler;

    public FraudSummary analyze(Transacao transacao) {
        return fraudCompiler.percorrerRegras(transacao);
    }
}
