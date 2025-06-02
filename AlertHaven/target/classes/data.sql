-- Inserções para TB_TIPO_EMERGENCIA
INSERT INTO TB_TIPO_EMERGENCIA (id_tipo_emergencia, tipo_emergencia) VALUES (1, 'Alagamento');
INSERT INTO TB_TIPO_EMERGENCIA (id_tipo_emergencia, tipo_emergencia) VALUES (2, 'Chuva Forte');
INSERT INTO TB_TIPO_EMERGENCIA (id_tipo_emergencia, tipo_emergencia) VALUES (3, 'Onda de Calor');
INSERT INTO TB_TIPO_EMERGENCIA (id_tipo_emergencia, tipo_emergencia) VALUES (4, 'Terremoto');
INSERT INTO TB_TIPO_EMERGENCIA (id_tipo_emergencia, tipo_emergencia) VALUES (5, 'Furacão');

-- Inserções para TB_ABRIGO
INSERT INTO TB_ABRIGO (id_abrigo, nome_abrigo, capacidade_suportada_abrigo, email_abrigo)
VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430c0', 'Abrigo São Francisco', 150, 'sao.francisco@abrigo.org');
INSERT INTO TB_ABRIGO (id_abrigo, nome_abrigo, capacidade_suportada_abrigo, email_abrigo)
VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430c1', 'Centro de Apoio Esperança', 200, 'esperanca@abrigo.org');
INSERT INTO TB_ABRIGO (id_abrigo, nome_abrigo, capacidade_suportada_abrigo, email_abrigo)
VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430c2', 'Refúgio Seguro', 80, 'seguro@abrigo.org');
INSERT INTO TB_ABRIGO (id_abrigo, nome_abrigo, capacidade_suportada_abrigo, email_abrigo)
VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430c3', 'Abrigo Nova Vida', 120, 'nova.vida@abrigo.org');

-- Inserções para TB_LOCALIZACAO
INSERT INTO TB_LOCALIZACAO (id_localizacao, identificacao_unica_abrigo, latitude_abrigo, longitude_abrigo, rua_abrigo, id_abrigo)
VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430d0', '08321332', '-23.550520', '-46.633308', 'Rua São Francisco, 123', '6ba7b810-9dad-11d1-80b4-00c04fd430c0');
INSERT INTO TB_LOCALIZACAO (id_localizacao, identificacao_unica_abrigo, latitude_abrigo, longitude_abrigo, rua_abrigo, id_abrigo)
VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430d1', '32133132', '-22.906847', '-43.172896', 'Avenida Esperança, 456', '6ba7b810-9dad-11d1-80b4-00c04fd430c1');
INSERT INTO TB_LOCALIZACAO (id_localizacao, identificacao_unica_abrigo, latitude_abrigo, longitude_abrigo, rua_abrigo, id_abrigo)
VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430d2', '08932133', '-12.971599', '-38.501895', 'Travessa Segura, 789', '6ba7b810-9dad-11d1-80b4-00c04fd430c2');
INSERT INTO TB_LOCALIZACAO (id_localizacao, identificacao_unica_abrigo, latitude_abrigo, longitude_abrigo, rua_abrigo, id_abrigo)
VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430d3', '03282111', '-19.916681', '-43.934493', 'Alameda Nova, 101', '6ba7b810-9dad-11d1-80b4-00c04fd430c3');

-- Inserções para TB_ABRIGO_TIPO_EMERGENCIA
-- Abrigo São Francisco atende Alagamento e Chuva Forte
INSERT INTO TB_ABRIGO_TIPO_EMERGENCIA (id_abrigo, id_tipo_emergencia) VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430c0', 1);
INSERT INTO TB_ABRIGO_TIPO_EMERGENCIA (id_abrigo, id_tipo_emergencia) VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430c0', 2);

-- Centro de Apoio Esperança atende Onda de Calor e Furacão
INSERT INTO TB_ABRIGO_TIPO_EMERGENCIA (id_abrigo, id_tipo_emergencia) VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430c1', 3);
INSERT INTO TB_ABRIGO_TIPO_EMERGENCIA (id_abrigo, id_tipo_emergencia) VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430c1', 5);

-- Refúgio Seguro atende Terremoto
INSERT INTO TB_ABRIGO_TIPO_EMERGENCIA (id_abrigo, id_tipo_emergencia) VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430c2', 4);

-- Abrigo Nova Vida atende Alagamento, Chuva Forte e Furacão
INSERT INTO TB_ABRIGO_TIPO_EMERGENCIA (id_abrigo, id_tipo_emergencia) VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430c3', 1);
INSERT INTO TB_ABRIGO_TIPO_EMERGENCIA (id_abrigo, id_tipo_emergencia) VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430c3', 2);
INSERT INTO TB_ABRIGO_TIPO_EMERGENCIA (id_abrigo, id_tipo_emergencia) VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430c3', 5);