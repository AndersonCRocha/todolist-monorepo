# TodoList Monorepo

Uma aplicação de lista de tarefas com um quadro de estilo Kanban. Este projeto está estruturado como um monorepo contendo componentes de backend e frontend.

## Índice

- [Funcionalidades](#funcionalidades)
- [Arquitetura](#arquitetura)
- [Instalação](#instalação)
- [Uso](#uso)
- [Tecnologias](#tecnologias)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Funcionalidades

- **Gerenciamento de Tarefas**: Criar, atualizar, excluir e visualizar tarefas.
- **Quadro Kanban**: Visualizar tarefas em diferentes estados (A Fazer, Em Progresso, Feito).
- **Design Responsivo**: Acessível em dispositivos desktop e móveis.

## Arquitetura

Este projeto segue uma arquitetura de monorepo, combinando backend e frontend em um único repositório.

### Backend

- **Pasta**: `backend`
- **Tecnologias**: Java, Spring Boot
- **Responsabilidades**: Lidar com requisições API, armazenamento de dados e lógica de negócios.

### Frontend

- **Pasta**: `frontend`
- **Tecnologias**: TypeScript, React
- **Responsabilidades**: Interface do usuário, experiência do usuário, gerenciamento de estado.

## Instalação

### Pré-requisitos

- Docker
- Docker Compose

### Passos

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/AndersonCRocha/todolist-monorepo.git
   cd todolist-monorepo
   ```

2. **Construa e inicie os containers**:

   ```bash
   docker-compose up --build
   ```

3. **Acesse a aplicação**:
   - Frontend: `http://localhost:5173`
   - API: `http://localhost:8080`

## Uso

### Desenvolvimento

Para desenvolvimento, você pode executar o backend e o frontend separadamente:

#### Backend

Navegue até a pasta `backend` e execute:

```bash
./mvnw spring-boot:run
```

#### Frontend

Navegue até a pasta `frontend` e execute:

```bash
npm install
npm start
```

#### Produção

Use o Docker Compose para executar o backend e o frontend:

```bash
docker-compose up --build
```

#### Tecnologias

- Backend: Java, Spring Boot
- Frontend: TypeScript, React
- Containerização: Docker, Docker Compose

<img width="600" alt="image" src="https://github.com/AndersonCRocha/todolist-monorepo/assets/59173445/8fbf6a54-e174-41f2-91e4-b6202c6e4b9c">
<img width="600" alt="image" src="https://github.com/AndersonCRocha/todolist-monorepo/assets/59173445/f5b3ae09-5b77-4bcf-aeed-ed70000eb894">
<img width="600" alt="image" src="https://github.com/AndersonCRocha/todolist-monorepo/assets/59173445/5d053b62-2854-4c92-b14e-d6ec37296978">
<img width="600" alt="image" src="https://github.com/AndersonCRocha/todolist-monorepo/assets/59173445/63bdb969-3342-464a-bc5d-811ff0b9b3ca">
<img width="600" alt="image" src="https://github.com/AndersonCRocha/todolist-monorepo/assets/59173445/58157676-9a68-46da-b8c6-1bc5055b1040">





