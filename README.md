# SAFeR - Sistema Avançado de Fraude e Risco

![Linguagem](https://img.shields.io/badge/linguagem-Java-blue)
![Licença](https://img.shields.io/badge/licença-GNU%20GPL%20v3-green)

Projeto em equipe desenvolvido para o AGI. O **SAFeR** é um sistema em **Java** voltado à **detecção e prevenção de fraudes em transações bancárias**, combinando regras pré-definidas e análise inteligente de padrões para aumentar a segurança financeira.

---

## Funcionalidades Principais

- Monitoramento de transações em tempo real  
- Identificação de padrões suspeitos ou anômalos  
- Alertas automáticos de possíveis fraudes  
- Registro detalhado das atividades para auditoria  

---

## Tecnologias Utilizadas

- Java  

---

## Estrutura de Código (Exemplo)

```java
public class Transacao {
    private double valor;
    private String contaOrigem;
    private String contaDestino;
    
    public Transacao(double valor, String origem, String destino) {
        this.valor = valor;
        this.contaOrigem = origem;
        this.contaDestino = destino;
    }

    public boolean verificarFraude() {
        // Lógica simplificada de detecção de fraude
        return valor > 10000; // Exemplo de regra
    }
}
```
## DashBoard 

[Acessar Repositório](https://github.com/Henrique-Mourao/safer-dashboard)

