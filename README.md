# 💧 Projeto de Pontos de Coleta

Backend de um sistema simples para controle de Pontos de Coleta de água, com funcionalidades de cadastro e consulta de pontos, além de verificação automática da potabilidade da água com base nas leituras de sensores.

---

## 🚀 Tecnologias Utilizadas

- **Java 21 (OpenJDK)**
- **Spring Boot**
- **PostgreSQL**
- **Docker**
- **Postman** (para testes de API)

---

## ✅ Funcionalidades Atuais

-✅ Cadastro e listagem de pontos de coleta
-✅ Registro de leituras de sensores (ex: pH, turbidez etc.)
-✅ Consulta de histórico de leituras por período
-✅ Geração de alertas automáticos quando os parâmetros saem dos limites
-✅ Envio de alertas por e-mail com Spring Mail
-✅ Tarefas agendadas com @Scheduled

---

## 🗃️ Estrutura das Tabelas

### Ponto Coleta
- `id`
- `nome`
- `latitude`
- `longitude`
- `descricao`

### Leitura Sensor
- `id`
- `ponto_coleta_id`
- `data_hora`
- `pH`
- `temperatura`
- `turbidez`
- `contaminantes`

### Alerta
- `id`
- `ponto_coleta_id`
- `mensagem`
- `data_hora`
- `status` (`enviado` ou `pendente`)
---

## ✉️ Contato

Fique à vontade para entrar em contato comigo pelo [LinkedIn](https://www.linkedin.com/in/pedrohjacinto) caso tenha dúvidas, sugestões ou queira trocar uma ideia sobre o projeto!
