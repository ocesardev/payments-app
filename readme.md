**📦 payments-app**

Sistema de pagamentos completo com autenticação JWT, CRUD de clientes e transações, integração React + Spring Boot e banco Postgres via Docker.

**🚀 Sobre o projeto**

O payments-app é um sistema de pagamentos inspirado no fluxo real de uma fintech.
Ele inclui login com JWT, permissões básicas, transações via PIX e CRUD de clientes/pagamentos totalmente funcional via API.

Foi desenvolvido com foco em aprendizado real, boas práticas, arquitetura limpa e stack moderna.

**🛠 Tecnologias utilizadas**

Backend

Java 17

Spring Boot

Spring Security + JWT

Spring Data JPA

PostgreSQL

Docker / Docker Compose

Frontend

TypeScript

React

Axios

CSS Puro

**⚙️ Como rodar o projeto localmente**
1. Clonar o repositório
git clone https://github.com/ocesardev/payments-app.git
cd payments-app

2. Subir o banco com Docker
docker compose up -d


O container sobe o Postgres configurado para o backend.

3. Rodar o backend
cd backend
./mvnw spring-boot:run


API subirá em:
👉 http://localhost:8080

4. Rodar o frontend
cd frontend
npm install
npm run dev


Frontend estará em:
👉 http://localhost:5173

**🔐 Autenticação JWT**

O login gera um token JWT que é utilizado para acessar rotas protegidas.

Endpoints principais:

POST /auth/login

POST /payments (token necessário)

POST /clients

GET /clients

Formato do token:

Authorization: Bearer SEU_TOKEN_AQUI

**💸 Funcionalidades**
**Backend**

Login e autenticação com JWT

CRUD completo de:

Clients

Payments

Integração com Postgres via JPA

Validação de dados

Hash de senha

Transação PIX (mock funcional)

**Frontend**

Atualmente disponível:

Tela de login

Tela de transação PIX

Consumo da API via Axios

**Próximas telas (em construção):**

Dashboard

Listagem de pagamentos

Listagem de clientes

**🖼 Demonstração Login**

COLOCAR_PRINT_LOGIN_AQUI

Transação PIX

COLOCAR_PRINT_PIX_AQUI

Fluxo completo (GIF)

COLOCAR_GIF_DEMONSTRACAO_AQUI

🧱 Arquitetura do Backend
flowchart TD
    A[Controller] --> B[Service]
    B --> C[Repository]
    C --> D[Postgres]
    B --> E[DTOs]
    A --> F[Security / JWT]

**📌 Roadmap (to-do)**

 Criar dashboard no frontend

 CRUD do frontend (clients + payments)

 Implementar testes unitários (JUnit + Mockito)

 Adicionar Swagger para documentação da API

 Deploy do backend (Railway, Render ou EC2)

 Deploy do frontend (Vercel/Netlify)

 Pipeline CI/CD

👨‍💻 Autor

Caio César
LinkedIn: COLOCAR_LINK_LINKEDIN_AQUI