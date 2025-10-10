// package com.devsDoAgi.SAFeR.controller;


// import com.devsDoAgi.SAFeR.model.Transacao;
// import com.devsDoAgi.SAFeR.fraudes.engine.FraudResult;
// import com.devsDoAgi.SAFeR.fraudes.rules.emAnalise.RuleDeviceMismatch;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;

// import lombok.AllArgsConstructor;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/teste-regra")
// @AllArgsConstructor
// @Tag(name = "Teste de Regras de Fraude") // ← aparece como seção no Swagger
// public class TesteRegraController {

//     private final RuleDeviceMismatch ruleDeviceMismatch;

//     @PostMapping
//     @Operation(summary = "Testa a regra de dispositivo (mismatch)", description = "Executa a RuleDeviceMismatch com uma transação de teste.")
//     public FraudResult testarRegra(@RequestBody Transacao transacao) {
//         return ruleDeviceMismatch.evaluate(transacao);
//     }
// }
