-- V8__create_despesa.sql

CREATE TABLE despesa (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor NUMERIC(10, 2) NOT NULL,
    data DATE NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    observacao VARCHAR(500),
    proprietario_id BIGINT NOT NULL,

    CONSTRAINT fk_despesa_proprietario
        FOREIGN KEY (proprietario_id)
        REFERENCES proprietario(id)
);