ALTER TABLE servico
ADD COLUMN proprietario_id BIGINT;

ALTER TABLE servico
ADD CONSTRAINT fk_servico_proprietario
FOREIGN KEY (proprietario_id)
REFERENCES proprietario(id);