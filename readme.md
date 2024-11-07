
# Contas Médicas API

Este projeto é uma API construída em Spring Boot para calcular lançamentos de contas médicas. A aplicação aplica diferentes taxas baseadas no tipo de conta (`CONSULTA` ou `EXAME`), especialidade do exame, sexo e idade do paciente.

## Como Rodar a Aplicação

Instalando as dependências:


Para iniciar a aplicação, use o comando Maven:

```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

---

## Cenários de Teste e Resultados Esperados

Para cada cenário, o valor final é calculado como:

\[	ext{valor final} = 	ext{valor base} 	imes (1 + 	ext{taxa})\]

### Cenários para `CONSULTA`

#### Homens

1. **Idade até 20 anos** (Taxa: 0%)
   ```bash
   curl -X POST http://localhost:8080/api/calcular -H "Content-Type: application/json" -d '{
     "matricula": 1001,
     "tipo": "CONSULTA",
     "valor": 100.00,
     "idade": 18,
     "sexo": "M"
   }'
   ```
   **Resultado Esperado**: `100.00`

2. **Idade entre 21 e 40 anos** (Taxa: 20%)
   ```bash
   curl -X POST http://localhost:8080/api/calcular -H "Content-Type: application/json" -d '{
     "matricula": 1002,
     "tipo": "CONSULTA",
     "valor": 100.00,
     "idade": 30,
     "sexo": "M"
   }'
   ```
   **Resultado Esperado**: `120.00`

3. **Idade acima de 40 anos** (Taxa: 25%)
   ```bash
   curl -X POST http://localhost:8080/api/calcular -H "Content-Type: application/json" -d '{
     "matricula": 1003,
     "tipo": "CONSULTA",
     "valor": 100.00,
     "idade": 50,
     "sexo": "M"
   }'
   ```
   **Resultado Esperado**: `125.00`

#### Mulheres

1. **Idade até 20 anos** (Taxa: 0%)
   ```bash
   curl -X POST http://localhost:8080/api/calcular -H "Content-Type: application/json" -d '{
     "matricula": 1004,
     "tipo": "CONSULTA",
     "valor": 100.00,
     "idade": 18,
     "sexo": "F"
   }'
   ```
   **Resultado Esperado**: `100.00`

2. **Idade entre 21 e 40 anos** (Taxa: 15%)
   ```bash
   curl -X POST http://localhost:8080/api/calcular -H "Content-Type: application/json" -d '{
     "matricula": 1005,
     "tipo": "CONSULTA",
     "valor": 100.00,
     "idade": 30,
     "sexo": "F"
   }'
   ```
   **Resultado Esperado**: `115.00`

3. **Idade acima de 40 anos** (Taxa: 20%)
   ```bash
   curl -X POST http://localhost:8080/api/calcular -H "Content-Type: application/json" -d '{
     "matricula": 1006,
     "tipo": "CONSULTA",
     "valor": 100.00,
     "idade": 50,
     "sexo": "F"
   }'
   ```
   **Resultado Esperado**: `120.00`

### Cenários para `EXAME`

#### Ecocardiograma

1. **Homem com idade até 20 anos** (Taxa: 5%)
   ```bash
   curl -X POST http://localhost:8080/api/calcular -H "Content-Type: application/json" -d '{
     "matricula": 1007,
     "tipo": "EXAME",
     "especialidade": "Ecocardiograma",
     "valor": 200.00,
     "idade": 18,
     "sexo": "M"
   }'
   ```
   **Resultado Esperado**: `210.00`

2. **Mulher com idade acima de 50 anos** (Taxa: 13%)
   ```bash
   curl -X POST http://localhost:8080/api/calcular -H "Content-Type: application/json" -d '{
     "matricula": 1008,
     "tipo": "EXAME",
     "especialidade": "Ecocardiograma",
     "valor": 200.00,
     "idade": 60,
     "sexo": "F"
   }'
   ```
   **Resultado Esperado**: `226.00`

#### Colesterol

1. **Homem com idade acima de 50 anos** (Taxa: 16%)
   ```bash
   curl -X POST http://localhost:8080/api/calcular -H "Content-Type: application/json" -d '{
     "matricula": 1009,
     "tipo": "EXAME",
     "especialidade": "Colesterol",
     "valor": 150.00,
     "idade": 55,
     "sexo": "M"
   }'
   ```
   **Resultado Esperado**: `174.00`

2. **Mulher com idade entre 21 e 50 anos** (Taxa: 5%)
   ```bash
   curl -X POST http://localhost:8080/api/calcular -H "Content-Type: application/json" -d '{
     "matricula": 1010,
     "tipo": "EXAME",
     "especialidade": "Colesterol",
     "valor": 150.00,
     "idade": 30,
     "sexo": "F"
   }'
   ```
   **Resultado Esperado**: `157.50`

#### Glicose

1. **Mulher com idade acima de 50 anos** (Taxa: 12.5%)
   ```bash
   curl -X POST http://localhost:8080/api/calcular -H "Content-Type: application/json" -d '{
     "matricula": 1011,
     "tipo": "EXAME",
     "especialidade": "Glicose",
     "valor": 120.00,
     "idade": 60,
     "sexo": "F"
   }'
   ```
   **Resultado Esperado**: `135.00`

---

Execute cada comando `curl` e valide se o `valorFinal` retornado corresponde ao esperado. Esses cenários cobrem todas as combinações possíveis de tipo de conta, especialidade, idade e sexo.
