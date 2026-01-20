DROP TABLE IF EXISTS votacao;
DROP TABLE IF EXISTS candidato;
DROP TABLE IF EXISTS cargo;
DROP TABLE IF EXISTS partido;
DROP TABLE IF EXISTS zona_secao;

-- a) Criação das tabelas Candidato, Votacao e Zona_Secao, especificando chave primária e chaves estrangeiras;

CREATE TABLE cargo (
	-- Colunas
	cod_cargo INT NOT NULL,
	nome_cargo VARCHAR(30) NOT NULL,

	-- Primary key
	CONSTRAINT pk_cargo PRIMARY KEY (cod_cargo)
);

CREATE TABLE partido (
	-- Colunas
	cod_partido INT NOT NULL,
	nome_partido VARCHAR(30) NOT NULL,

	-- Primary key
	CONSTRAINT pk_partido PRIMARY KEY (cod_partido)
);

CREATE TABLE zona_secao (
	-- Colunas
	num_zona INT NOT NULL,
	num_secao INT NOT NULL,
	nome_zona_Secao VARCHAR(45) NOT NULL,
	qtd_eleitores INT NOT NULL,

	-- Primary key
	CONSTRAINT pk_zona_secao PRIMARY KEY (num_zona, num_secao)
);

CREATE TABLE candidato (
	-- Colunas
	num_candidato INT NOT NULL,
	cod_cargo INT NOT NULL,
	cod_partido INT NOT NULL,
	nome_candidato VARCHAR(30) NOT NULL,

	-- Primary key
	CONSTRAINT pk_candidato PRIMARY KEY (num_candidato),

	-- Foreign key
	CONSTRAINT fk_candidato_cargo FOREIGN KEY (cod_cargo)
	REFERENCES cargo(cod_cargo),
	CONSTRAINT fk_candidato_partido FOREIGN KEY (cod_partido)
	REFERENCES partido(cod_partido)
);

CREATE TABLE votacao (
	-- Colunas
	num_zona INT NOT NULL,
	num_secao INT NOT NULL,
	num_candidato INT NOT NULL,
	qtd_votos INT NOT NULL,

	-- Primary key
	CONSTRAINT pk_votacao PRIMARY KEY (num_zona, num_secao, num_candidato),

	-- Foreign key
	CONSTRAINT fk_votacao_zona_secao FOREIGN KEY (num_zona, num_secao)
	REFERENCES zona_secao(num_zona, num_secao),
	CONSTRAINT fk_votacao_candidato FOREIGN KEY (num_candidato)
	REFERENCES candidato(num_candidato)
);

/* CARGA DAS TABELAS */

/* Tabela cargo */
insert into cargo (cod_cargo, nome_cargo) values (0,'Prefeito');
insert into cargo (cod_cargo, nome_cargo) values (1,'Governador');
insert into cargo (cod_cargo, nome_cargo) values (2,'Senador');
insert into cargo (cod_cargo, nome_cargo) values (3,'Presidente');
insert into cargo (cod_cargo, nome_cargo) values (4,'Deputado Estadual');
insert into cargo (cod_cargo, nome_cargo) values (5,'Deputado Federal');

/* Tabela partido */
insert into partido (cod_partido, nome_partido) values (0,'Partido Conservador');
insert into partido (cod_partido, nome_partido) values (1,'Partido Democratico');

/* Tabela candidato */
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (0,0,0,'Joao');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (1,1,0,'Afonso');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (2,2,0,'Mario');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (3,4,0,'Luiza');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (4,4,0,'Andre');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (5,4,0,'Maria');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (6,4,0,'Ana');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (7,4,0,'Paula');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (8,4,0,'Marcos');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (9,0,1,'Monica');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (10,1,1,'Francisco');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (11,2,1,'Fabio');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (12,4,1,'Antonio');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (13,4,1,'Daniel');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (14,4,1,'Eduardo');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (15,4,1,'Alessandra');
insert into candidato (num_candidato, cod_cargo, cod_partido, nome_candidato) values (16,4,1,'Marcelo');

/* Tabela zona_secao */
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (0,0,'A',100000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (0,1,'B',500000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (0,2,'C',120000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (0,3,'D',240000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (0,4,'E',50000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (0,5,'F',1500);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (0,6,'G',6000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (0,7,'H',15000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (0,8,'I',800000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (0,9,'J',35000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (0,10,'L',85000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (1,0,'M',120000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (1,1,'N',130000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (1,2,'O',750000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (1,3,'P',320000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (1,4,'Q',7600);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (1,5,'R',43000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (1,6,'S',78000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (1,7,'T',5900);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (1,8,'U',31000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (1,9,'V',830000);
insert into zona_secao (num_zona, num_secao, nome_zona_secao, qtd_eleitores) values (1,10,'X',87000);

/* INSERT VOTACAO */
/* Tabela votacao */
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,0,0, 500);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,1,1, 1200);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,2,2, 300);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,3,3, 800);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,4,4, 150);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,5,5, 50);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,6,6, 200);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,7,7, 400);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,8,8, 1500);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,9,9, 600);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,10,10, 900);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,0,11, 250);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,1,12, 700);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,2,13, 1100);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,3,14, 450);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,4,15, 80);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,5,16, 300);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,0,1, 100);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,1,2, 50);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,2,3, 120);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,3,4, 30);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,4,5, 10);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,5,6, 70);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,6,7, 90);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,7,8, 200);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,8,9, 400);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,9,10, 350);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,10,11, 180);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,0,12, 220);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,1,13, 500);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,2,14, 700);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,3,15, 280);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,4,16, 100);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,5,0, 150);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,6,1, 300);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,7,2, 80);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,8,3, 600);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,9,4, 900);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (1,10,5, 120);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,0,6, 80);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,1,7, 250);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,2,8, 180);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,3,9, 320);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,4,10, 70);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,5,11, 40);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,6,12, 110);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,7,13, 190);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,8,14, 550);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,9,15, 230);
insert into votacao (num_zona, num_secao, num_candidato, qtd_votos) values (0,10,16, 90);

-- b) Alteração da estrutura da tabela Votacao, adicionando um atributo data_votacao;
ALTER TABLE votacao ADD data_votacao DATE;

-- c) Alteração da estrutura da tabela Candidato, removendo a chave estrangeira com a tabela Partido;
ALTER TABLE candidato DROP CONSTRAINT fk_candidato_partido;

-- Todas as constraints (PK, FK, CHECK, DEFAULT, UNIQUE)
SELECT 
    CONSTRAINT_NAME,
    CONSTRAINT_TYPE
FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
WHERE TABLE_NAME = 'candidato';

-- d) Criar um índice “não-clustered” na tabela Candidato, com os atributos cod_cargo e cod_partido;
CREATE INDEX idx_candidato_cargo_partido ON candidato (cod_cargo, cod_partido); -- NONCLUSTERED por default

-- O PostgreSQL não cria automaticamente índices para atributos (colunas) que não são PRIMARY KEY ou UNIQUE.
-- Sem índice, o PostgreSQL precisa percorrer todas as linhas da tabela (sequential scan), cuja complexidade é O(n).
-- Com índice, o PostgreSQL cria uma árvore B-tree tornando mais rápida a busca por uma entrada, já que a árvore é ordenada e tem complexidade O(log n).

-- e) Inserir um registro na tabela Votacao. (qtd_votos = 1000, num_secao = 10, num_zona = 5, num_candidato = 555);

INSERT INTO zona_secao
VALUES(5, 10, 'Escola Estadual Otávio Mangabeira', 5000);

INSERT INTO candidato
VALUES(555, 1, 1, 'Jaques Wagner');

INSERT INTO votacao
VALUES(5, 10, 555, 1000);

-- f) Inserir um registro na tabela Zona_Secao (qtd_eleitores = 300, nome_zona_secao = “Zona Teste”, num_zona = 7, num_secao = 99);

INSERT INTO zona_secao
VALUES(7, 99, 'Zona Teste', 300);

-- g) Alterar a quantidade de votos, multiplicando por 2, para os candidatos a governador (código do cargo = 1) e do Partido Democrático (código de partido = 1);
UPDATE votacao V
SET qtd_votos = qtd_votos * 2
FROM candidato C
WHERE V.num_candidato = C.num_candidato
	AND C.cod_cargo = 1 	-- Governador
	AND C.cod_partido = 1;	-- Partido Democrático

-- Solução 2: subnconsulta
UPDATE votacao V
SET qtd_votos = qtd_votos * 2
WHERE num_candidato IN (
	SELECT num_candidato
	FROM candidato
	WHERE cod_cargo = 1 		-- Governador
		AND cod_partido = 1	-- Partido Democrático
);

-- h) Alterar a quantidade de eleitores, somando 100, para as zonas/seções onde ocorreu votação para candidatos ao Senado (código do cargo = 2); 
UPDATE zona_secao AS ZS
SET qtd_eleitores = qtd_eleitores + 100
FROM votacao AS V, candidato AS C -- INNER JOIN implícito com a cláusula WHERE
WHERE (ZS.num_zona, ZS.num_secao) = (V.num_zona, V.num_secao)
	  AND V.num_candidato = C.num_candidato 
	  AND C.cod_cargo = 2;

-- Solução 2: subconsulta
UPDATE zona_secao AS ZS
SET qtd_eleitores = qtd_eleitores + 100
WHERE (num_zona, num_secao) IN (
	SELECT V.num_zona, V.num_secao
	FROM votacao AS V 
	INNER JOIN candidato AS C
		ON V.num_candidato = C.num_candidato
	
	WHERE C.cod_cargo = 2
);

-- i) Apagar todos os cargos que não possuem candidatos;
DELETE FROM cargo
WHERE cod_cargo NOT IN (
	SELECT cod_cargo
	FROM candidato
);

-- j) Apagar as zonas/seções que possuam menos de 1.000 eleitores e que o nome comecem com a letra “A”;
DELETE FROM zona_secao
WHERE qtd_eleitores < 1000 AND nome_zona_secao like 'A%';

SELECT *
FROM zona_secao;

-- k) Selecionar nome do partido, nome do candidato e nome do cargo, em ordem alfabética do nome do partido;
SELECT P.nome_partido, CA.nome_candidato, CR.nome_cargo
FROM partido P
INNER JOIN candidato CA
	ON P.cod_partido = CA.cod_partido
INNER JOIN cargo CR
	ON CR.cod_cargo = CA.cod_cargo
ORDER BY P.nome_partido;

-- l) Selecionar código e nome do partido, nome do cargo e a quantidade de candidatos por partido, para cada cargo;
SELECT P.cod_partido, P.nome_partido, CR.nome_cargo, COUNT(*)
FROM partido P
INNER JOIN candidato CA
	ON P.cod_partido = CA.cod_partido
INNER JOIN cargo CR
	ON CR.cod_cargo = CA.cod_cargo
GROUP BY P.cod_partido, P.nome_partido, CR.nome_cargo;

-- m) Selecionar as zonas/seções onde não ocorreu votação;
SELECT nome_zona_secao
FROM zona_secao Z
WHERE (num_zona, num_secao) NOT IN (
	SELECT num_zona, num_secao
	FROM votacao
);
	
-- n) Selecionar número do candidato, nome do candidato, nome do cargo, nome do partido e quantidade total de votos de cada candidato, apenas para os candidatos que tiveram uma votação superior 100.000 votos;
SELECT CA.num_candidato, CA.nome_candidato, CR.nome_cargo, P.nome_partido, SUM(V.qtd_votos) AS votos
FROM partido P INNER JOIN candidato CA ON P.cod_partido = CA.cod_partido
INNER JOIN cargo CR ON CA.cod_cargo = CR.cod_cargo
INNER JOIN votacao V ON CA.num_candidato = V.num_candidato
GROUP BY CA.num_candidato, CA.nome_candidato, CR.nome_cargo, P.nome_partido
HAVING SUM(V.qtd_votos) > 100000;

-- o) Selecionar o número do candidato, nome do candidato e a sua média de votos obtidos nas zonas/seções;
SELECT CA.num_candidato, CA.nome_candidato, AVG(V.qtd_votos) AS media_votos
FROM candidato CA INNER JOIN votacao V ON CA.num_candidato = V.num_candidato
GROUP BY CA.num_candidato, CA.nome_candidato;

-- p) Selecionar o código do partido, nome do partido, nome do candidato e a média de votos obtidos, apenas para os candidatos que tiveram média de votos superior à média de votos do seu partido;
SELECT P.cod_partido, P.nome_partido, CA.nome_candidato, AVG(V.qtd_votos) AS media_votos
FROM partido P 
INNER JOIN candidato CA ON P.cod_partido = CA.cod_partido
INNER JOIN votacao V ON CA.num_candidato = V.num_candidato
GROUP BY P.cod_partido, P.nome_partido, CA.nome_candidato
HAVING AVG(V.qtd_votos) > (
	-- Subconsulta escalar correlacionada
	SELECT AVG(V2.qtd_votos)
	FROM votacao V2 INNER JOIN candidato CA2 ON V2.num_candidato = CA2.num_candidato
	WHERE CA2.cod_partido = P.cod_partido -- Correlação
);

-- A subconsulta correlacionada é resolvida após a resolução da consulta.
-- Para cada linha da consulta, compara com o resultado da subconsulta.
