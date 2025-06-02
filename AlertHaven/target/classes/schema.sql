DROP TABLE TB_ABRIGO_TIPO_EMERGENCIA CASCADE CONSTRAINTS;
DROP TABLE TB_LOCALIZACAO CASCADE CONSTRAINTS;
DROP TABLE TB_USUARIO CASCADE CONSTRAINTS;
DROP TABLE TB_TIPO_EMERGENCIA CASCADE CONSTRAINTS;
DROP TABLE TB_ABRIGO CASCADE CONSTRAINTS;

CREATE TABLE TB_USUARIO (
    id_usuario VARCHAR(255) PRIMARY KEY,
    nome_usuario VARCHAR(255) NOT NULL,
    email_usuario VARCHAR(255) NOT NULL,
    senha_usuario VARCHAR(255) NOT NULL,
    cpf_usuario VARCHAR(255) NOT NULL,
    telefone_usuario VARCHAR(255) NOT NULL,
    data_nascimento DATE NOT NULL,
    data_criacao_usuario TIMESTAMP NOT NULL
);

CREATE TABLE TB_TIPO_EMERGENCIA (
    id_tipo_emergencia NUMERIC PRIMARY KEY,
    tipo_emergencia VARCHAR(255)
);

CREATE TABLE TB_ABRIGO (
    id_abrigo VARCHAR(255) PRIMARY KEY,
    nome_abrigo VARCHAR(255) NOT NULL,
    capacidade_suportada_abrigo NUMERIC NOT NULL,
    email_abrigo VARCHAR(50) NOT NULL
);

CREATE TABLE TB_LOCALIZACAO (
    id_localizacao VARCHAR(255) PRIMARY KEY,
    identificacao_unica_abrigo VARCHAR(255),
    latitude_abrigo VARCHAR(255),
    longitude_abrigo VARCHAR(255),
    rua_abrigo VARCHAR(255),
    id_abrigo VARCHAR(255) REFERENCES TB_ABRIGO(id_abrigo)
);

CREATE TABLE TB_ABRIGO_TIPO_EMERGENCIA (
    id_abrigo VARCHAR(255) REFERENCES TB_ABRIGO(id_abrigo),
    id_tipo_emergencia NUMERIC REFERENCES TB_TIPO_EMERGENCIA(id_tipo_emergencia),
    PRIMARY KEY (id_abrigo, id_tipo_emergencia)
);