-- Migration para adicionar a coluna rank na tabela Cadastro

ALTER TABLE TB_CADASTRO
ADD COLUMN rank VARCHAR(255);