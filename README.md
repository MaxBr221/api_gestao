💈 API de Gestão para Barbearias

Backend de uma aplicação web para gerenciamento de barbearias, desenvolvido com Java e Spring Boot.

A API fornece os recursos necessários para autenticação, gerenciamento de serviços, registro de atendimentos e geração de relatórios de faturamento.

O projeto foi desenvolvido seguindo uma arquitetura baseada em API REST, com separação de responsabilidades entre controllers, services, repositories e DTOs, além de autenticação utilizando Spring Security e JWT.

🎯 Sobre o projeto

O sistema foi desenvolvido para digitalizar o controle de serviços e faturamento de barbearias que anteriormente dependiam de registros manuais.

A API permite que o proprietário de um estabelecimento:

🔐 Realize login na plataforma

✂️ Cadastre e gerencie serviços

🧾 Registre atendimentos realizados

💈 Associe múltiplos serviços a um atendimento

💳 Registre formas de pagamento

📊 Consulte relatórios diários

📅 Consulte relatórios mensais

📈 Consulte relatórios anuais

A aplicação também foi estruturada considerando um cenário multi-tenant, permitindo que diferentes estabelecimentos utilizem o mesmo sistema mantendo seus dados isolados.

🚀 Funcionalidades

🔐 Autenticação

A API utiliza Spring Security + JWT para autenticação.

Funcionalidades:

Login do proprietário

Geração de token JWT

Autenticação stateless

Proteção dos endpoints

Controle de acesso baseado em função

Validação do token nas requisições

Identificação do estabelecimento através do usuário autenticado

Fluxo de autenticação

Cliente
   │
   │ POST /auth/login
   ▼
Spring Security
   │
   │ Validação das credenciais
   ▼
JWT
   │
   ▼
Cliente
   │
   │ Authorization: Bearer <token>
   ▼
API protegida

👤 Multi-tenancy

A aplicação foi desenvolvida considerando uma arquitetura multi-tenant.

Cada proprietário está associado a um tenant_id, utilizado para identificar o estabelecimento ao qual os dados pertencem.

Dessa forma, os dados de diferentes estabelecimentos podem permanecer isolados dentro da mesma aplicação.

Exemplo

Estabelecimento A
       │
       ├── Serviços
       ├── Atendimentos
       └── Relatórios

Estabelecimento B
       │
       ├── Serviços
       ├── Atendimentos
       └── Relatórios

O tenant_id é obtido a partir do usuário autenticado, evitando que o frontend precise enviar manualmente o identificador do estabelecimento nas requisições.

✂️ Gerenciamento de serviços

A API permite o gerenciamento dos serviços oferecidos pela barbearia.

Exemplos:

Corte de cabelo

Barba

Pintura

Outros serviços cadastrados pelo estabelecimento

Cada serviço possui informações utilizadas posteriormente nos registros de atendimento e nos relatórios.

🧾 Registro de atendimentos

O proprietário pode registrar os serviços realizados durante o atendimento de um cliente.

Um atendimento pode possuir um ou vários serviços.

Exemplo

Atendimento
│
├── Corte de cabelo
├── Barba
├── Forma de pagamento: PIX
└── Valor total

A associação entre atendimento e serviços é realizada através da tabela intermediária:

atendimento
      │
      │
      ▼
atendimento_servico
      │
      │
      ▼
servico

💳 Formas de pagamento

Os atendimentos possuem uma forma de pagamento associada.

O sistema permite registrar a forma utilizada no atendimento para posteriormente utilizar essas informações nos registros e relatórios financeiros.

📊 Relatórios

A API disponibiliza consultas para acompanhamento do faturamento.

Relatório diário

Permite consultar os atendimentos e o faturamento realizados durante o dia.

GET /relatorio/diario

Relatório mensal

Permite consultar os dados de faturamento de determinado mês.

GET /relatorio/mensal

Relatório anual

Permite consultar os dados consolidados de determinado ano.

GET /relatorio/anual

Os relatórios utilizam a data dos atendimentos para realizar os filtros e cálculos.

🕐 Controle de data e horário

Como a aplicação está direcionada ao mercado brasileiro, as operações relacionadas à data dos atendimentos consideram o fuso horário:

America/Sao_Paulo

Isso evita problemas de mudança de data causados pela diferença entre o horário do servidor e o horário local do estabelecimento.

🏗️ Arquitetura

O projeto segue uma arquitetura baseada na separação de responsabilidades.

Controller
    │
    ▼
Service
    │
    ▼
Repository
    │
    ▼
PostgreSQL

Controller

Responsável por receber as requisições HTTP e disponibilizar os endpoints da API.

Service

Responsável pelas regras de negócio da aplicação.

Repository

Responsável pela comunicação com o banco de dados utilizando Spring Data JPA.

DTO

Utilizado para transportar dados entre as camadas da aplicação, evitando expor diretamente as entidades.

Entity

Representa as estruturas persistidas no banco de dados.

🛠️ Tecnologias utilizadas

Backend

Java 21

Spring Boot

Spring Security

JWT

Spring Data JPA

Hibernate

Maven

Banco de dados

PostgreSQL

Flyway

Infraestrutura

Docker

Render

Aiven PostgreSQL

Desenvolvimento

Git

GitHub

GitHub Actions

IntelliJ IDEA

📁 Estrutura do projeto

src/
├── main/
│   ├── java/
│   │   └── ...
│   │
│   └── resources/
│       ├── db/
│       │   └── migration/
│       │
│       └── application.yml
│
└── test/

A aplicação é organizada seguindo a separação entre:

controller/
service/
repository/
entity/
dto/
security/

🔗 Principais endpoints

Autenticação

POST /auth/login

Responsável por autenticar o proprietário e retornar o token JWT.

Serviços

GET    /servico
POST   /servico
PUT    /servico/{id}
DELETE /servico/{id}

Responsáveis pelo gerenciamento dos serviços cadastrados.

Atendimentos

POST /atendimento

Responsável pelo registro de um novo atendimento.

Relatórios

GET /relatorio/diario
GET /relatorio/mensal
GET /relatorio/anual

Responsáveis pelas consultas dos dados de faturamento.

Os endpoints podem evoluir conforme novas funcionalidades forem adicionadas ao projeto.

🗄️ Banco de dados

A aplicação utiliza PostgreSQL como banco de dados.

Principais tabelas:

proprietario
barbeiro
servico
atendimento
atendimento_servico
flyway_schema_history

Relacionamento simplificado

proprietario
     │
     ├──────────► servico
     │
     └──────────► atendimento
                       │
                       ▼
                atendimento_servico
                       │
                       ▼
                    servico

🧬 Migrations

O controle da estrutura do banco de dados é realizado através do Flyway.

As alterações do banco são versionadas através de migrations.

Exemplo:

db/migration/

V1__initial_schema.sql
V2__adicionar_proprietario_em_servico.sql
V3__remover_status_servico.sql
V7__add_tenant_id_proprietario.sql

Isso permite manter o histórico das alterações realizadas no banco e reproduzir a estrutura necessária em diferentes ambientes.

🔒 Segurança

A API utiliza Spring Security para proteção dos recursos.

As requisições protegidas precisam enviar o token JWT através do header:

Authorization: Bearer <token>

A aplicação utiliza autenticação stateless, não mantendo sessão no servidor.

O token também é utilizado para identificar o usuário autenticado e seu respectivo tenant_id.

🌐 CORS

A API possui configuração de CORS para permitir a comunicação com o frontend da aplicação.

Em produção, o frontend é hospedado na Vercel e a API no Render.

Frontend
   │
   │ HTTPS
   ▼
Vercel
   │
   │ API Requests
   ▼
Render
   │
   ▼
PostgreSQL

⚙️ Variáveis de ambiente

A aplicação utiliza variáveis de ambiente para informações sensíveis e configurações específicas de cada ambiente.

Exemplo:

DB_URL=jdbc:postgresql://localhost:5432/gestao
DB_USERNAME=postgres
DB_PASSWORD=sua_senha

JWT_SECRET=sua_chave_secreta

Em produção, essas informações são configuradas diretamente no ambiente de hospedagem.

Nunca coloque senhas, tokens ou outras credenciais diretamente no código ou no repositório público.

💻 Executando localmente

1. Clone o repositório

git clone https://github.com/MaxBr221/api_gestao.git

Entre na pasta:

cd api_gestao

2. Configure o banco de dados

Crie um banco PostgreSQL chamado:

gestao

Depois configure as variáveis de ambiente necessárias.

3. Execute a aplicação

Utilizando Maven:

mvn spring-boot:run

A API será executada por padrão em:

http://localhost:8080

As migrations do Flyway serão executadas automaticamente durante a inicialização da aplicação.

🐳 Docker

O projeto também possui suporte à execução através de Docker.

Para criar a imagem:

docker build -t api-gestao .

Para executar o container:

docker run -p 8080:8080 api-gestao

As variáveis de ambiente necessárias devem ser configuradas no ambiente de execução.

📦 Build

Para gerar o arquivo .jar:

mvn clean package -DskipTests

O arquivo será gerado dentro da pasta:

target/

🚀 Deploy

A API está hospedada utilizando:

Render para execução da aplicação Spring Boot

Aiven para o banco de dados PostgreSQL

Arquitetura de produção:

                    ┌───────────────┐
                    │    Usuário    │
                    └───────┬───────┘
                            │
                            ▼
                    ┌───────────────┐
                    │    Vercel     │
                    │    Frontend   │
                    └───────┬───────┘
                            │
                         HTTPS
                            │
                            ▼
                    ┌───────────────┐
                    │    Render     │
                    │ Spring Boot   │
                    └───────┬───────┘
                            │
                            ▼
                    ┌───────────────┐
                    │     Aiven     │
                    │  PostgreSQL   │
                    └───────────────┘

API em produção

https://api-gestao-8urq.onrender.com

🔄 CI/CD

O projeto utiliza GitHub Actions para automatizar processos relacionados ao desenvolvimento e integração do código.

O código-fonte é versionado no GitHub e o processo de deploy é realizado através da infraestrutura configurada para o projeto.

🔌 Integração com o frontend

O frontend da aplicação foi desenvolvido utilizando:

React

Next.js

TypeScript

Tailwind CSS

Axios

A comunicação entre frontend e backend é realizada através de requisições HTTP para a API REST.

O Axios utiliza um interceptor para adicionar automaticamente o JWT às requisições autenticadas.

Next.js
   │
   │ Axios
   ▼
Spring Boot API
   │
   │ JWT
   ▼
PostgreSQL

Frontend em produção:

https://gestao-front-eight.vercel.app

📚 Conceitos aplicados

Durante o desenvolvimento foram aplicados conceitos importantes de desenvolvimento backend:

API REST

Arquitetura em camadas

Injeção de dependência

DTOs

Spring Data JPA

Hibernate

Spring Security

JWT

Autenticação stateless

Controle de acesso

Multi-tenancy

Relacionamentos entre entidades

PostgreSQL

Migrations com Flyway

Docker

Variáveis de ambiente

CORS

Deploy em cloud

Integração entre frontend e backend

Versionamento com Git

🎓 Aprendizados

O desenvolvimento da API proporcionou experiência prática com a construção de uma aplicação backend completa, passando por diferentes etapas:

Modelagem
    ↓
Banco de dados
    ↓
API REST
    ↓
Regras de negócio
    ↓
Autenticação
    ↓
Multi-tenancy
    ↓
Integração com frontend
    ↓
Docker
    ↓
Deploy

Além da implementação das funcionalidades, o projeto também envolveu problemas reais de desenvolvimento, como configuração de banco de dados em produção, permissões, CORS, autenticação, variáveis de ambiente, diferenças de timezone e integração entre serviços hospedados em plataformas diferentes.

🛣️ Roadmap

Possíveis evoluções para o projeto:

Dashboard com métricas financeiras

Melhorias nos relatórios

Exportação de relatórios

Mais filtros de faturamento

Melhorias na gestão de usuários

Testes automatizados

Monitoramento da aplicação

Melhorias na infraestrutura

Novos recursos para gerenciamento das barbearias

🔗 Repositório

GitHub:

https://github.com/MaxBr221/api_gestao

👨‍💻 Autor

Maxsuel Lima

Desenvolvedor Full Stack em formação.

Tecnologias de interesse:

Java • Spring Boot • React • TypeScript • PostgreSQL

📄 Licença

Este projeto foi desenvolvido para fins de estudo, portfólio e demonstração de conhecimentos em desenvolvimento de software.
