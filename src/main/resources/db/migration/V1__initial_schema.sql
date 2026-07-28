CREATE TABLE proprietario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    login VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    role VARCHAR(30) NOT NULL
);

CREATE TABLE barbeiro (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    role VARCHAR(30) NOT NULL
);

CREATE TABLE servico (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco NUMERIC(10,2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    descricao TEXT
);

CREATE TABLE atendimento (
    id_atendimento BIGSERIAL PRIMARY KEY,

    data TIMESTAMP NOT NULL,

    barbeiro_id BIGINT NOT NULL,

    proprietario_id BIGINT NOT NULL,

    forma_pagamento VARCHAR(30) NOT NULL,

    valor NUMERIC(10,2) NOT NULL,

    observacao TEXT,

    CONSTRAINT fk_atendimento_barbeiro
        FOREIGN KEY (barbeiro_id)
        REFERENCES barbeiro(id),

    CONSTRAINT fk_atendimento_proprietario
        FOREIGN KEY (proprietario_id)
        REFERENCES proprietario(id)
);

CREATE TABLE atendimento_servico (

    id BIGSERIAL PRIMARY KEY,

    atendimento_id BIGINT NOT NULL,

    servico_id BIGINT NOT NULL,

    total NUMERIC(10,2) NOT NULL,

    CONSTRAINT fk_atendimento_servico_atendimento
        FOREIGN KEY (atendimento_id)
        REFERENCES atendimento(id_atendimento)
        ON DELETE CASCADE,

    CONSTRAINT fk_atendimento_servico_servico
        FOREIGN KEY (servico_id)
        REFERENCES servico(id)

);