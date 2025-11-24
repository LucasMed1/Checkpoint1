# CortaGasto API 💰

API RESTful para controle de gastos pessoais - Sistema simples e eficiente para registrar, visualizar e gerenciar despesas.

## 📋 Sobre o Projeto

A **CortaGasto API** foi desenvolvida para resolver o problema de controle financeiro pessoal. Muitas pessoas têm dificuldade em acompanhar seus gastos diários e acabam perdendo o controle por falta de organização. Esta API oferece uma solução simples, sem funções desnecessárias, focada no essencial.

## 🎯 Público-Alvo

- Usuários que desejam controlar seus gastos sem depender de planilhas complicadas
- Estudantes e profissionais que precisam gerenciar o próprio orçamento
- Desenvolvedores que queiram integrar controle financeiro em outras aplicações

## 🚀 Funcionalidades

- ✅ **Registrar gastos**: Criar novos registros com descrição, valor, data e categoria
- ✅ **Listar gastos**: Visualizar todos os gastos registrados
- ✅ **Editar gastos**: Atualizar informações de gastos existentes
- ✅ **Remover gastos**: Deletar registros de gastos
- ✅ **Total do mês**: Consultar o total gasto no mês atual
- ✅ **Filtros**: Filtrar gastos por categoria ou mês específico

## 🛠️ Stack Tecnológica

- **Java 17**
- **Spring Boot 3.2.0**
- **Springdoc OpenAPI** (Swagger UI) para documentação
- **Persistência em memória** (HashMap) - com possibilidade de evoluir para arquivo JSON

## 📁 Estrutura do Projeto

```
cortagasto-api/
├── src/
│   ├── main/
│   │   ├── java/com/cortagasto/
│   │   │   ├── CortaGastoApplication.java
│   │   │   ├── config/
│   │   │   │   └── OpenApiConfig.java
│   │   │   ├── controller/
│   │   │   │   └── GastoController.java
│   │   │   ├── dto/
│   │   │   │   └── GastoRequest.java
│   │   │   ├── model/
│   │   │   │   └── Gasto.java
│   │   │   ├── repository/
│   │   │   │   └── GastoRepository.java
│   │   │   └── service/
│   │   │       └── GastoService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

- **Controller**: Camada de controle que recebe requisições HTTP
- **Service**: Camada de serviço com a lógica de negócio
- **Repository**: Camada de repositório para persistência de dados
- **Model**: Entidades do domínio
- **DTO**: Objetos de transferência de dados

## 🚀 Como Executar

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+

### Executando a aplicação

1. Clone o repositório:
```bash
git clone <url-do-repositorio>
cd cortagasto-api
```

2. Compile o projeto:
```bash
mvn clean install
```

3. Execute a aplicação:
```bash
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

## 📚 Documentação da API

Após iniciar a aplicação, acesse a documentação interativa do Swagger:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs (JSON)**: http://localhost:8080/api-docs

## 🔌 Endpoints Disponíveis

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/gastos` | Registrar um novo gasto |
| GET | `/api/gastos` | Listar todos os gastos |
| GET | `/api/gastos/{id}` | Buscar gasto por ID |
| PUT | `/api/gastos/{id}` | Atualizar um gasto |
| DELETE | `/api/gastos/{id}` | Remover um gasto |
| GET | `/api/gastos/categoria/{categoria}` | Filtrar por categoria |
| GET | `/api/gastos/mes?ano=2024&mes=1` | Filtrar por mês |
| GET | `/api/gastos/total-mes-atual` | Total do mês atual |

## 📝 Exemplo de Uso

### Criar um gasto

```bash
POST http://localhost:8080/api/gastos
Content-Type: application/json

{
  "descricao": "Almoço no restaurante",
  "valor": 45.50,
  "data": "2024-01-15",
  "categoria": "Alimentação"
}
```

### Listar todos os gastos

```bash
GET http://localhost:8080/api/gastos
```

### Consultar total do mês atual

```bash
GET http://localhost:8080/api/gastos/total-mes-atual
```

## 🔄 Próximos Passos (Pendências)

- [ ] Implementar persistência em arquivo JSON
- [ ] Adicionar autenticação e autorização de usuários
- [ ] Implementar testes unitários e de integração
- [ ] Adicionar validações de negócio mais robustas
- [ ] Implementar relatórios e gráficos
- [ ] Adicionar suporte a múltiplos usuários
- [ ] Criar frontend para consumo da API

## 👥 Equipe

[Adicionar nomes e matrículas dos integrantes]

## 📄 Licença

Este projeto é desenvolvido para fins educacionais.

---

**Desenvolvido com ❤️ para facilitar o controle financeiro pessoal**

