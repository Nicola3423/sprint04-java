# Projeto CP03 - Gerenciamento de Pacientes

## Video: https://www.youtube.com/watch?v=tukYhAb0dA0

## Integrantes do Grupo

*Nicola Monte Cravo Garofalo - responsável pelo código fonte.*

*Igor Akira Bortolini Tateishi - responsável pelo código fonte.*

*Willyam Santos Sousa - responsável pelo documento.*

## Requisitos do Software
 
- Instalação do Java 17;
 
## Instruções de como rodar a aplicação
 
1. Clonar o projeto;
2. Baixar as dependências do Groovy;
3. Rodar o projeto;
4. Executar as requisições.

## Descrição:

Este projeto é uma aplicação Java desenvolvida com Spring Boot, utilizando JPA para persistência de dados, destinada ao gerenciamento de informações de pacientes, incluindo médicos. 

## Estrutura das Entidades

### 1. Paciente
- Representa o paciente, contendo informações pessoais como:
  - id: Identificador único do paciente.
  - nome: Nome do paciente.
  - email: Email válido do paciente.
  - telefone: Telefone do paciente.
  - dataNascimento: Data de nascimento do paciente.

### 2. Medico
- Representa o médico responsável pelo paciente.
  - id: Identificador único do médico.
  - nome: Nome do médico.
  - telefone: Telefone do médico (máx. 15 caracteres).
  - email: Email válido do médico.
  - crm: Número do CRM, com tamanho entre 5 e 15 caracteres.

## Tecnologias Utilizadas

- *Java 17*
- *Spring Boot*
- *Jakarta Persistence API (JPA)*
- *Banco de Dados Relacional ORACLE *

## Diagrama:
![java](https://github.com/user-attachments/assets/60264022-3a21-435c-8b27-997127e997cf)

# Sistema de Gerenciamento de Médicos e Pacientes

## Teste

### Médicos

#### 1. Criar um novo médico (POST)
- **URL:** `http://localhost:8080/medicos/salvar`
- **Método:** POST
- **Headers:** Content-Type: application/json
- **Body (JSON):**
```json
{
  "nome": "Dr. João Silva",
  "email": "joao.silva@example.com",
  "crm": "123456",
  "telefone": "11999998888"
}
```

#### 2. Listar todos os médicos (GET)
- **URL:** `http://localhost:8080/medicos`
- **Método:** GET

#### 3. Buscar médico por ID (GET)
- **URL:** `http://localhost:8080/medicos/{id}`
- **Exemplo:** `http://localhost:8080/medicos/1`
- **Método:** GET

#### 4. Atualizar médico (PUT)
- **URL:** `http://localhost:8080/medicos/editar/{id}`
- **Exemplo:** `http://localhost:8080/medicos/editar/1`
- **Método:** PUT
- **Headers:** Content-Type: application/json
- **Body (JSON):**
```json
{
  "id": 1,
  "nome": "Dr. João Silva Modificado",
  "email": "joao.novo@example.com",
  "crm": "654321",
  "telefone": "11988887777"
}
```

#### 5. Excluir médico (DELETE)
- **URL:** `http://localhost:8080/medicos/excluir/{id}`
- **Exemplo:** `http://localhost:8080/medicos/excluir/1`
- **Método:** DELETE

### Pacientes

#### 1. Criar um novo paciente (POST)
- **URL:** `http://localhost:8080/pacientes/salvar`
- **Método:** POST
- **Headers:** Content-Type: application/json
- **Body (JSON):**
```json
{
  "nome": "Maria Souza",
  "email": "maria.souza@example.com",
  "telefone": "11997776666",
  "dataDeNascimento": "1990-05-20"
}
```

#### 2. Listar todos os pacientes (GET)
- **URL:** `http://localhost:8080/pacientes`
- **Método:** GET

#### 3. Buscar paciente por ID (GET)
- **URL:** `http://localhost:8080/pacientes/{id}`
- **Exemplo:** `http://localhost:8080/pacientes/1`
- **Método:** GET

#### 4. Atualizar paciente (PUT)
- **URL:** `http://localhost:8080/pacientes/editar/{id}`
- **Exemplo:** `http://localhost:8080/pacientes/editar/1`
- **Método:** PUT
- **Headers:** Content-Type: application/json
- **Body (JSON):**
```json
{
  "id": 1,
  "nome": "Maria Souza Modificada",
  "email": "maria.nova@example.com",
  "telefone": "11996665555",
  "dataDeNascimento": "1990-05-20"
}
```

#### 5. Excluir paciente (DELETE)
- **URL:** `http://localhost:8080/pacientes/excluir/{id}`
- **Exemplo:** `http://localhost:8080/pacientes/excluir/1`
- **Método:** DELETE
   


## Instalação e Configuração

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/JavaSprint02/.git
