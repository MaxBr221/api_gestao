-- V7__alter_proprietario_tenant.sql

-- Remove o UNIQUE antigo apenas do login
ALTER TABLE proprietario
DROP CONSTRAINT IF EXISTS proprietario_login_key;

-- Adiciona o tenant_id inicialmente permitindo NULL
ALTER TABLE proprietario
ADD COLUMN tenant_id BIGINT;

-- Corrige os usuários existentes
UPDATE proprietario
SET tenant_id = 1
WHERE id = 1;

UPDATE proprietario
SET tenant_id = 2
WHERE id = 2;

-- Agora torna obrigatório
ALTER TABLE proprietario
ALTER COLUMN tenant_id SET NOT NULL;

-- Login único dentro de cada tenant
ALTER TABLE proprietario
ADD CONSTRAINT uk_proprietario_tenant_login
UNIQUE (tenant_id, login);