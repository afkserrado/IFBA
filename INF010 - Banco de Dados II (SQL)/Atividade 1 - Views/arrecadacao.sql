-- Mostrar a quantidade de passageiros e a receita arrecadada por linha em uma data

DROP MATERIALIZED VIEW IF EXISTS Arrecadacao;

CREATE MATERIALIZED VIEW Arrecadacao AS
SELECT
	L.numero,
	L.descricao,
	V.data_entrada AS data_viagem,
	COUNT(V.id_viagem) AS passageiros,
	SUM(V.tarifa) AS receita

FROM Linha L
JOIN Viagem V
	ON L.numero = V.num_linha
GROUP BY L.numero, L.descricao, V.data_entrada
ORDER BY V.data_entrada, L.numero;

-- REFRESH MATERIALIZED VIEW Arrecadacao;
--SELECT * FROM Arrecadacao;
