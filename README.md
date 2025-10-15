# SAFeR - Sistema de Análise de Fraude e Risco

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![Python](https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white)
![License](https://img.shields.io/badge/license-GNU%20GPL%20v3-green?style=for-the-badge)

## 📋 Sobre o Projeto

O **SAFeR** (Sistema de Análise de Fraude e Risco) é um sistema inteligente de detecção e prevenção de fraudes bancárias desenvolvido como projeto em equipe para o AGI. Ele analisa transações bancárias em tempo real, identificando comportamentos suspeitos e ajudando a reduzir riscos e proteger clientes.

O projeto integra múltiplas fontes de dados e aplica regras de negócio e validações automáticas para sinalizar possíveis fraudes antes que causem prejuízo.

---

## ✨ Funcionalidades Principais

- ✅ Monitoramento de transações em tempo real
- 🔍 Identificação de padrões suspeitos ou anômalos através de múltiplas regras de análise
- 🎯 Sistema de pontuação (score) para classificação de risco
- 📊 Análise de perfil do cliente e histórico de transações
- 🚨 Alertas automáticos de possíveis fraudes
- 📝 Registro detalhado das atividades para auditoria

---

## 🎯 Sistema de Regras

O SAFeR implementa múltiplas regras para detectar fraudes:

- **Regra de Canal**: Analisa o canal utilizado na transação
- **Regra de Dispositivo**: Verifica se o dispositivo é conhecido
- **Regra de Restrição**: Checa restrições da conta
- **Regra de Perfil**: Compara com o perfil histórico do cliente
- **Regra de Localização**: Detecta transações em locais incomuns
- **Regra de Periodicidade**: Identifica padrões temporais suspeitos
- **Regra de Horário**: Analisa transações fora do horário habitual
- **Regra de Valor**: Verifica valores atípicos ou muito altos

### Sistema de Score

Cada regra adiciona uma pontuação à transação. Após todas as regras serem aplicadas, o sistema calcula o score final que determina se a transação é:
- **Fraudulenta**
- **Segura**
- **Requer análise manual**

Além disso, cada cliente possui uma média dos seus scores, usada para definir sua pontuação geral no banco e indicar se é considerado um bom cliente ou apresenta riscos potenciais.

---

## 🛠️ Tecnologias Utilizadas

### Backend
- **Java** - Linguagem principal
- **Spring Boot** - Framework para desenvolvimento de APIs REST
- **MySQL** - Banco de dados relacional
- **Python** - Scripts de análise e automação

### Frontend
- **React JSX** - Interface do usuário do dashboard

### Ferramentas de Desenvolvimento
- **DBeaver** - Gerenciamento de banco de dados
- **Jira** - Gestão de projetos e tarefas
- **GitHub** - Controle de versão
- **IntelliJ IDEA** - IDE principal para desenvolvimento Java
- **VS Code** - Editor de código

---

## 🏗️ Arquitetura

O sistema segue uma arquitetura modular baseada em interfaces e classes:

```
FraudRule (Interface) → FraudCompile → FraudEngine → FraudSummary → FraudResult
                                                                          ↓
                                                                    Transaction
```

### Componentes Principais

- **FraudRule**: Interface que define as regras de detecção
- **FraudCompile**: Compila e organiza a lista de regras
- **FraudEngine**: Motor de análise que aplica as regras
- **FraudSummary**: Resumo da análise com pontuações
- **FraudResult**: Resultado final com classificação
- **Transaction**: Modelo de dados da transação

---

## 📊 Dashboard

O projeto possui um dashboard interativo desenvolvido em React para visualização e análise das transações em tempo real.

🔗 [Acessar Repositório do Dashboard](https://github.com/Henrique-Mourao/safer-dashboard)

---

## 🚀 Como Executar

### Pré-requisitos

- Java 17 ou superior
- MySQL 8+
- Maven
- Node.js e npm (para o dashboard)

### Backend

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/safer.git

# Entre na pasta do projeto
cd safer

# Configure o banco de dados no application.properties
# spring.datasource.url=jdbc:mysql://localhost:3306/safer
# spring.datasource.username=seu_usuario
# spring.datasource.password=sua_senha

# Execute o projeto
mvn spring-boot:run
```

### Frontend (Dashboard)

```bash
# Clone o repositório do dashboard
git clone https://github.com/Henrique-Mourao/safer-dashboard.git

# Entre na pasta
cd safer-dashboard

# Instale as dependências
npm install

# Execute o projeto
npm start
```

---

## 📝 Estrutura de Código

Exemplo da classe `Transacao`:

```java
public class Transacao {
    private Long id;
    private Double valor;
    private String contaOrigem;
    private String contaDestino;
    private LocalDateTime dataHora;
    private String localizacao;
    private User user;
    
    public Transacao(double valor, String origem, String destino) {
        this.valor = valor;
        this.contaOrigem = origem;
        this.contaDestino = destino;
    }
    
    public boolean verificarFraude() {
        // Lógica simplificada de detecção de fraude
        return valor > 10000; // Exemplo de regra básica
    }
}
```

---

## 👥 Equipe

Projeto desenvolvido em equipe para o AGI.

---

## 📄 Licença

Este projeto está sob a licença **GNU GPL v3**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.



