-- 1 - Municípios que não pertencem a região norte

-- Abordagem com JOIN
-- EXPLAIN (ANALYZE, BUFFERS, FORMAT JSON)
-- SELECT M.NomeMunicipio
-- FROM Estado E
-- JOIN Regiao R
-- 	ON E.CodRegiao = R.CodRegiao
-- JOIN Municipio M
-- 	ON E.CodEstado = M.CodEstado
-- WHERE R.CodRegiao <> 1;

-- Abordagem com EXISTS
-- EXPLAIN (ANALYZE, BUFFERS, FORMAT JSON)
-- SELECT M.NomeMunicipio
-- FROM Municipio M
-- WHERE NOT EXISTS (
-- 	-- Municípios da região Norte
-- 	SELECT *
-- 	FROM Estado E
-- 	WHERE M.CodEstado = E.CodEstado AND E.CodRegiao = 1
-- );

-- Abordagem com IN
-- EXPLAIN (ANALYZE, BUFFERS, FORMAT JSON)
-- SELECT M.NomeMunicipio
-- FROM Municipio M
-- WHERE M.CodEstado IN (
-- 	-- Estados que não são da região Norte
-- 	SELECT E.CodEstado
-- 	FROM Estado E
-- 	WHERE E.CodRegiao <> 1
-- );

-- 2 - Municípios que possuem o mesmo nome

-- SELECT M.NomeMunicipio, COUNT(*) AS Quantidade
-- FROM Municipio M
-- GROUP BY M.NomeMunicipio
-- HAVING COUNT(*) > 1;
-- ORDER BY M.NomeMunicipio;

-- 3 - Média de municípios por Região
-- SELECT 
-- (
-- 	SELECT COUNT(*)::DECIMAL
-- 	FROM Municipio M
-- ) / 
-- (
-- 	SELECT COUNT(*)
-- 	FROM Regiao
-- ) As MediaMunicipiosPorRegiao;

-- SELECT COUNT(M.CodMunicipio)::DECIMAL / COUNT(DISTINCT R.CodRegiao) AS MediaMunicipiosPorRegiao
-- FROM Municipio M
-- JOIN Estado E
-- 	ON M.CodEstado = E.CodEstado
-- JOIN Regiao R
-- 	ON E.CodRegiao = R.CodRegiao;

-- 4 - Sigla dos estados com as respectivas quantidades de municípios

-- Abordagem com LEFT JOIN
-- SELECT E.SiglaEstado, COUNT(M.CodMunicipio) AS QtdMunicipios
-- FROM Estado E
-- LEFT JOIN Municipio M
--        ON M.CodEstado = E.CodEstado
-- GROUP BY E.SiglaEstado;

-- Abordagem com JOIN
-- SELECT E.SiglaEstado, COUNT(*) AS QtdMunicipios
-- FROM Municipio M
-- JOIN Estado E
--      ON E.CodEstado = M.CodEstado
-- GROUP BY E.SiglaEstado;

-- 5 - Município com as pessoas mais idosas

-- Abordagem com SUBQUERY
-- SELECT M.CodMunicipio, TRIM(M.NomeMunicipio) AS NomeMunicipio, I.IDH_Longevidade	   
-- FROM Indice I
-- JOIN Municipio M
--      ON I.CodMunicipio = M.CodMunicipio
-- WHERE I.IDH_Longevidade = (
--     SELECT MAX(IDH_Longevidade)
--     FROM Indice
-- );

-- Abordagem com ORDER BY + LIMIT
-- CREATE INDEX idx_indice_longevidade 
-- ON Indice(IDH_Longevidade DESC);
-- DROP INDEX idx_indice_longevidade;

-- SELECT M.CodMunicipio, TRIM(M.NomeMunicipio) AS NomeMunicipio, I.IDH_Longevidade	
-- FROM Indice I
-- JOIN Municipio M
--      ON I.CodMunicipio = M.CodMunicipio
-- ORDER BY I.IDH_Longevidade DESC
-- LIMIT 1;

-- 6 - Ano em que Salvador obteve o melhor índice de Instrução
-- CREATE INDEX idx_indice_municipio_educacao
-- ON Indice(CodMunicipio, IDH_Educacao DESC);

-- DROP INDEX idx_indice_municipio_educacao;

-- Abordagem com JOIN
-- SELECT I.Ano, I.IDH_Educacao
-- FROM Indice I
-- JOIN Municipio M
-- 	ON I.CodMunicipio = M.CodMunicipio
-- WHERE M.NomeMunicipio = 'Salvador'
-- ORDER BY I.IDH_Educacao DESC
-- LIMIT 1;

-- Abordagem com EXISTS
-- SELECT I.Ano, I.IDH_Educacao
-- FROM Indice I
-- WHERE EXISTS (
-- 	SELECT *
-- 	FROM Municipio M
-- 	WHERE I.CodMunicipio = M.CodMunicipio AND M.NomeMunicipio = 'Salvador'
-- )
-- ORDER BY I.IDH_Educacao DESC
-- LIMIT 1;

-- 7 - Qual o município com a melhor distribuição de renda
-- CREATE INDEX idx_indice_renda
-- ON Indice(IDH_Renda DESC);

-- DROP INDEX idx_indice_renda;

-- SELECT M.NomeMunicipio, E.NomeEstado, I.Ano, I.IDH_Renda
-- FROM Indice I
-- JOIN Municipio M
-- 	ON I.CodMunicipio = M.CodMunicipio
-- JOIN Estado E
-- 	ON M.CodEstado = E.CodEstado
-- ORDER BY I.IDH_Renda DESC
-- LIMIT 1;

-- 8 - Quais estados possuem municípios com IDH geral maior que 0,8
-- CREATE INDEX idx_indice_idh_geral
-- ON Indice (IDH_Geral, CodMunicipio);
-- DROP INDEX idx_indice_idh_geral;

-- Abordagem com JOIN
-- SELECT DISTINCT(E.NomeEstado)
-- FROM Estado E
-- JOIN Municipio M
-- 	ON E.CodEstado = M.CodEstado
-- JOIN Indice I
-- 	ON M.CodMunicipio = I.CodMunicipio
-- WHERE I.IDH_Geral > 0.8;

-- Abordagem com EXISTS
-- SELECT DISTINCT(E.NomeEstado)
-- FROM Estado E
-- JOIN Municipio M
-- 	ON E.CodEstado = M.CodEstado
-- WHERE EXISTS (
-- 	SELECT *
-- 	FROM Indice I
-- 	WHERE M.CodMunicipio = I.CodMunicipio AND I.IDH_Geral > 0.8
-- );

-- 9 - Qual o maior IDH de educação por estado
-- SELECT E.NomeEstado, MAX(I.IDH_Educacao)
-- FROM Estado E
-- JOIN Municipio M
-- 	ON E.CodEstado = M.CodEstado
-- JOIN Indice I
-- 	ON M.CodMunicipio = I.CodMunicipio
-- GROUP BY E.NomeEstado;

-- 10- Relatório de Todos IDHs da Bahia de 91 e 2000, inclusive com a
-- diferença entre os mesmos

-- SELECT 
--     M.NomeMunicipio,

--     I1991.IDH_Geral        AS IDH_Geral_1991,
--     I1991.IDH_Renda        AS IDH_Renda_1991,
--     I1991.IDH_Longevidade  AS IDH_Longevidade_1991,
--     I1991.IDH_Educacao     AS IDH_Educacao_1991,

--     I2000.IDH_Geral        AS IDH_Geral_2000,
--     I2000.IDH_Renda        AS IDH_Renda_2000,
--     I2000.IDH_Longevidade  AS IDH_Longevidade_2000,
--     I2000.IDH_Educacao     AS IDH_Educacao_2000,

-- 	(I2000.IDH_Geral - I1991.IDH_Geral) AS Dif_Geral,
-- 	(I2000.IDH_Renda - I1991.IDH_Renda) AS Dif_Renda,
-- 	(I2000.IDH_Longevidade - I1991.IDH_Longevidade) AS Dif_Longevidade,
-- 	(I2000.IDH_Educacao - I1991.IDH_Educacao) AS Dif_Educacao

-- FROM Municipio M

-- JOIN Estado E
-- 	ON M.CodEstado = E.CodEstado
	
-- JOIN Indice I1991
-- 	ON M.CodMunicipio = I1991.CodMunicipio
-- 	AND I1991.Ano = 1991
	
-- JOIN Indice I2000
-- 	ON M.CodMunicipio = I2000.CodMunicipio
-- 	AND I2000.Ano = 2000
	
-- WHERE E.NomeEstado = 'Bahia';

-- Abordagem com vários joins
-- SELECT 
--     M.NomeMunicipio,

--     I1991.IDH_Geral        AS IDH_Geral_1991,
-- 	I2000.IDH_Geral        AS IDH_Geral_2000,
-- 	(I2000.IDH_Geral - I1991.IDH_Geral) AS Dif_Geral,
	
--     I1991.IDH_Renda        AS IDH_Renda_1991,
-- 	I2000.IDH_Renda        AS IDH_Renda_2000,
-- 	(I2000.IDH_Renda - I1991.IDH_Renda) AS Dif_Renda,
	
--     I1991.IDH_Longevidade  AS IDH_Longevidade_1991,
-- 	I2000.IDH_Longevidade  AS IDH_Longevidade_2000,
-- 	(I2000.IDH_Longevidade - I1991.IDH_Longevidade) AS Dif_Longevidade,
	
--     I1991.IDH_Educacao     AS IDH_Educacao_1991,
--     I2000.IDH_Educacao     AS IDH_Educacao_2000,
-- 	(I2000.IDH_Educacao - I1991.IDH_Educacao) AS Dif_Educacao

-- FROM Municipio M

-- JOIN Estado E
-- 	ON M.CodEstado = E.CodEstado
	
-- JOIN Indice I1991
-- 	ON M.CodMunicipio = I1991.CodMunicipio
-- 	AND I1991.Ano = 1991
	
-- JOIN Indice I2000
-- 	ON M.CodMunicipio = I2000.CodMunicipio
-- 	AND I2000.Ano = 2000
	
-- WHERE E.NomeEstado = 'Bahia';

-- Abordagem com redução de joins
-- SELECT M.NomeMunicipio,

-- 	-- Para cada linha, retorna o maior valor entre I.IDH_Geral e null
-- 	MAX(CASE WHEN I.Ano = 1991 THEN I.IDH_Geral END) AS IDH_Geral_1991,
-- 	MAX(CASE WHEN I.Ano = 2000 THEN I.IDH_Geral END) AS IDH_Geral_2000,
-- 	(
--         MAX(CASE WHEN I.Ano = 2000 THEN I.IDH_Geral END)
--         -
--         MAX(CASE WHEN I.Ano = 1991 THEN I.IDH_Geral END)
--     ) AS Dif_Geral,

-- 	MAX(CASE WHEN I.Ano = 1991 THEN I.IDH_Renda END) AS IDH_Renda_1991,
-- 	MAX(CASE WHEN I.Ano = 2000 THEN I.IDH_Renda END) AS IDH_Renda_2000,
-- 	(
--         MAX(CASE WHEN I.Ano = 2000 THEN I.IDH_Renda END)
--         -
--         MAX(CASE WHEN I.Ano = 1991 THEN I.IDH_Renda END)
--     ) AS Dif_Renda,

-- 	MAX(CASE WHEN I.Ano = 1991 THEN I.IDH_Longevidade END) AS IDH_Longevidade_1991,
-- 	MAX(CASE WHEN I.Ano = 2000 THEN I.IDH_Longevidade END) AS IDH_Longevidade_2000,
-- 	(
--         MAX(CASE WHEN I.Ano = 2000 THEN I.IDH_Longevidade END)
--         -
--         MAX(CASE WHEN I.Ano = 1991 THEN I.IDH_Longevidade END)
--     ) AS Dif_Longevidade,

-- 	MAX(CASE WHEN I.Ano = 1991 THEN I.IDH_Educacao END) AS IDH_Educacao_1991,
-- 	MAX(CASE WHEN I.Ano = 2000 THEN I.IDH_Educacao END) AS IDH_Educacao_2000,
--     (
--         MAX(CASE WHEN I.Ano = 2000 THEN I.IDH_Educacao END)
--         -
--         MAX(CASE WHEN I.Ano = 1991 THEN I.IDH_Educacao END)
--     ) AS Dif_Educacao

-- FROM Municipio M

-- JOIN Estado E
-- 	ON M.CodEstado = E.CodEstado

-- JOIN Indice I
-- 	ON M.CodMunicipio = I.CodMunicipio

-- WHERE E.NomeEstado = 'Bahia'
-- GROUP BY M.NomeMunicipio;

-- 11 - Relatório comparativo entre as médias dos IDHs de SC e AL,
-- de 2000 e 91
-- SELECT E.SiglaEstado,

-- 	AVG(CASE WHEN I.Ano = 1991 THEN I.IDH_Geral END) AS IDH_Geral_1991,
-- 	AVG(CASE WHEN I.Ano = 2000 THEN I.IDH_Geral END) AS IDH_Geral_2000,

-- 	AVG(CASE WHEN I.Ano = 1991 THEN I.IDH_Renda END) AS IDH_Renda_1991,
-- 	AVG(CASE WHEN I.Ano = 2000 THEN I.IDH_Renda END) AS IDH_Renda_2000,
	
-- 	AVG(CASE WHEN I.Ano = 1991 THEN I.IDH_Longevidade END) AS IDH_Longevidade_1991,
-- 	AVG(CASE WHEN I.Ano = 2000 THEN I.IDH_Longevidade END) AS IDH_Longevidade_2000,
	
-- 	AVG(CASE WHEN I.Ano = 1991 THEN I.IDH_Educacao END) AS IDH_Educacao_1991,
-- 	AVG(CASE WHEN I.Ano = 2000 THEN I.IDH_Educacao END) AS IDH_Educacao_2000

-- FROM Indice I

-- JOIN Municipio M
-- 	ON I.CodMunicipio = M.CodMunicipio

-- JOIN Estado E
-- 	ON M.CodEstado = E.CodEstado

-- WHERE E.SiglaEstado IN ('SC', 'AL')
-- GROUP BY E.SiglaEstado;