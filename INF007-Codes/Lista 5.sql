-- ##### CRIAÇÃO DAS TABELAS E INSERÇÃO DOS DADOS #####

/*
1. Criar o script para as tabelas com base no seguinte modelo de dados (utilizar auto-incremento quando possível e padronizar nomeclatura): 

• Editora (cod_editora (pk), descricao, endereço) 
• Livro (cod_livro (pk), ISBN, Título, num_edicao, Preco, cod_editora 
(fk)) 
• TB_Autor (Cod_autor (pk), nome, sexo, data_nascimento) 
• TB_Livro_Autor (cód_livro (pk)(fk), cód_autor (pk) (fk)) 
*/

-- Evita criar a tabela se já existir, permitindo rodar o script mais de uma vez
DROP TABLE IF EXISTS TB_Livro_Autor;
DROP TABLE IF EXISTS TB_Livro;
DROP TABLE IF EXISTS TB_Autor;
DROP TABLE IF EXISTS TB_Editora;

CREATE TABLE TB_Editora
	-- Atributos
	(
		cod_editora INT NOT NULL,
		descricao VARCHAR(30) NOT NULL,
		endereco VARCHAR(30) NULL,

		-- Restrições
		CONSTRAINT pk_editora PRIMARY KEY (cod_editora)
	);

/*
Também poderia ser sem constraints:

CREATE TABLE TB_Editora
	-- Atributos
	(
		cod_editora INT PRIMARY KEY,
		descricao VARCHAR(30),
		endereco VARCHAR(30)
	);
*/

CREATE TABLE TB_Autor
	(
		cod_autor INT NOT NULL,
		nome VARCHAR(45) NOT NULL,
		sexo CHAR(1) NOT NULL,
		data_nascimento DATE NOT NULL,
		CONSTRAINT pk_autor PRIMARY KEY (cod_autor)
	);

CREATE TABLE TB_Livro
	(
		cod_livro INT NOT NULL,
		ISBN VARCHAR(20) NOT NULL,
		titulo VARCHAR(45) NOT NULL,
		num_edicao INT NOT NULL,
		preco FLOAT(2) NOT NULL,
		cod_editora INT NOT NULL,
		CONSTRAINT pk_livro PRIMARY KEY (cod_livro),
		CONSTRAINT fk_editora 
			FOREIGN KEY (cod_editora) 
			REFERENCES TB_Editora(cod_editora)
	);

CREATE TABLE TB_Livro_Autor
	(
		cod_livro INT NOT NULL,
		cod_autor INT NOT NULL,
		CONSTRAINT pk_LA PRIMARY KEY (cod_livro, cod_autor),
		CONSTRAINT fk_livro 
			FOREIGN KEY (cod_livro) 
			REFERENCES TB_Livro(cod_livro),
		CONSTRAINT fk_autor 
			FOREIGN KEY (cod_autor) 
			REFERENCES TB_Autor(cod_autor)
	);

/*
2. Criar script de povoamento do banco de dados:

1. TB_Editora:
    a. 'Campus', 'Rua do Timbó'
    b. 'Abril'
    c. 'Editora Teste'

2. TB_Livro:
    a. '12345', 'Banco de Dados', 3, 70.00, 1
    b. '35790', 'SGBD', 1, 85.00, 2
    c. '98765', 'Redes de Computadores', 2, 80.00, 2

3. TB_Autor:
    a. 'João', 'M', '01.01.1970'
    b. 'Maria', 'F', '17.05.1974'
    c. 'José', 'M', '10.10.1977'
    d. 'Carla', 'F', '08.12.1964'

4. TB_Livro_Autor:
    a. Banco de Dados – Autores: João e Maria
    b. SGBD – Autores: Maria e Carla
    c. Redes de Computadores – Autor: José
*/

INSERT INTO TB_Editora (cod_editora, descricao, endereco)
VALUES 	(1, 'Campus', 'Rua do Timbó'),
		(2, 'Abril', NULL),
		(3, 'Editora Teste', NULL);

INSERT INTO TB_Autor (cod_autor, nome, sexo, data_nascimento)
VALUES 	(1, 'João','M','01/01/1970'),
		(2, 'Maria', 'F', '17/05/1974'),
		(3, 'José', 'M', '10/10/1977'),
		(4, 'Carla', 'F', '08/12/1964');

INSERT INTO TB_Livro (cod_livro, ISBN, titulo, num_edicao, preco, cod_editora)
VALUES	(1, '12345', 'Banco de Dados', 3, 70.00, 1),
		(2, '35790', 'SGBD', 1, 85.00, 2),
		(3, '98765', 'Redes de Computadores', 2, 80.00, 2);

INSERT INTO TB_Livro_Autor (cod_livro, cod_autor)
VALUES	(1, 1),
		(1, 2),
		(2, 2),
		(2, 4),
		(3, 3);

-- ##### CONSULTAS #####

-- 1. Atualizar o endereço da Editora Campus para ‘Av. ACM’
UPDATE TB_Editora
SET endereco = 'Av. ACM'
WHERE descricao = 'Campus';

-- 2. Atualizar os preços dos livros em 10%
UPDATE TB_Livro
SET preco = preco * 1.10;

-- 3. Excluir a ‘Editora Teste’ 
DELETE FROM TB_Editora
WHERE descricao = 'Editora Teste';
-- WHERE cod_editora = 3;

-- 4. Apresentar o nome e data de nascimento de todos os autores
SELECT A.nome, A.data_nascimento
FROM TB_Autor AS A;

-- 5. Apresentar o nome e a data de nascimento dos autores por ordem de nome
SELECT A.nome, A.data_nascimento
FROM TB_Autor AS A
ORDER BY A.nome;

-- 6. Apresentar o nome e a data de nascimento dos autores do sexo feminino ordenados pelo nome
SELECT A.nome, A.data_nascimento
FROM TB_Autor AS A
WHERE sexo = 'F'
ORDER BY A.nome;

-- 7. Apresentar o nome das editoras que não tem o endereço cadastrado
SELECT E.descricao
FROM TB_Editora AS E
WHERE endereco IS NULL;

-- 8. Apresentar o título do livro e o nome da sua editora 
-- Adicionando mais uma editora só para mostrar que ela não será exibida na consulta
INSERT INTO TB_Editora (cod_editora, descricao, endereco)
VALUES	(3, 'Editora Teste', NULL);

SELECT L.titulo, E.descricao
FROM TB_Livro AS L, TB_Editora AS E
WHERE L.cod_editora = E.cod_editora;

SELECT L.titulo, E.descricao
FROM TB_Livro AS L INNER JOIN TB_Editora AS E
	ON L.cod_editora = E.cod_editora;

/*
9. Apresentar o título do livro e o nome da sua editora. Caso haja alguma editora sem livro publicado, informar os dados da editora com valores nulos para os livros
*/
SELECT E.descricao, L.titulo
FROM TB_Editora AS E LEFT JOIN TB_Livro AS L
	ON L.cod_editora = E.cod_editora;

SELECT E.descricao, L.titulo
FROM TB_Editora AS E LEFT OUTER JOIN TB_Livro AS L
	ON L.cod_editora = E.cod_editora;

/*
-- Editoras sem livros publicados
SELECT E.descricao
FROM TB_Editora AS E LEFT JOIN TB_Livro as L
	ON (E.cod_editora = L.cod_editora)
WHERE L.cod_editora IS NULL
*/

/*
-- Editoras sem livros publicados
SELECT E.descricao
FROM TB_Editora AS E
WHERE E.cod_editora NOT IN
	-- Subconsulta
	(SELECT L.cod_editora
	FROM TB_Livro as L)
*/

/*
-- Editoras sem livros publicados
SELECT E.descricao
FROM TB_Editora AS E
WHERE NOT EXISTS
	-- Subconsulta
	(SELECT * -- Retorna todas as colunas
	FROM TB_Livro AS L
	WHERE L.cod_editora = E.cod_editora);
*/

-- 10. Apresentar o título do livro e o nome dos seus autores
SELECT L.titulo, A.nome
FROM TB_Livro AS L, TB_Autor AS A, TB_Livro_Autor AS LA
WHERE (L.cod_livro = LA.cod_livro AND A.cod_autor = LA.cod_autor);

SELECT L.titulo, A.nome
FROM TB_Livro AS L INNER JOIN TB_Livro_Autor AS LA
	ON (L.cod_livro = LA.cod_livro)
	INNER JOIN TB_Autor AS A
	ON (LA.cod_autor = A.cod_autor);

-- 11. Apresentar o nome da editora e o nome dos autores que já publicaram algum livro na editora 
SELECT E.descricao, A.nome
FROM TB_Editora AS E INNER JOIN TB_Livro AS L
	ON (E.cod_editora = L.cod_editora)
	INNER JOIN TB_Livro_Autor AS LA
	ON (L.cod_livro = LA.cod_livro)
	INNER JOIN TB_Autor AS A
	ON (LA.cod_autor = A.cod_autor);

-- 12. Apresentar o título dos livros que começam a string ‘Banco’


-- 13. Apresentar o título dos livros que tem a string ‘do’


-- 14. Apresentar o nome de cada livro e seu preço reajustado em 5%


-- 15. Apresentar o nome dos autores que nasceram no mês de outubro


-- 16. Apresentar o número de livros do acervo


-- 17. Apresentar o número de autores do livro ‘Banco de Dados’


-- 18. Apresentar o somatório dos preços dos livros do acervo


-- 19. Apresentar a média de preços dos livros da editora Campus


-- 20. Apresentar o maior preço dentre todos os livros do acervo


-- 21. Apresentar a data de nascimento do autor mais velho


-- 22. Apresentar o número de livros por editora


-- 23. Apresentar o somatório e média de preço dos livros por editora





	