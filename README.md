# 💧 Projeto de Pontos de Coleta

Backend para um sistema simples de **controle de Pontos de Coleta**, com funcionalidades de cadastro e consulta de pontos de coleta, e checagem automática se a água está potável ou não.

---

## 🚀 Tecnologias Utilizadas

- **Java 21 (OpenJDK)**
- **Spring Boot**
- **PostgreSQL**
- **Docker**
- **Postman** (para testes de API)

---

## ✅ Funcionalidades Atuais

- ✅ Cadastro de pontos de coleta 
- ✅ Consulta de pontos de coleta por nome  
- ✅ Remoção de pontos de coleta  
- ✅ Validação de campos obrigatórios (`@Valid`)  
- ✅ Prevenção de duplicidade de pontos de coleta (erro ao cadastrar com nome já existente)  


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
