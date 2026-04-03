-- Criação da tabela de auditoria
CREATE TABLE Auditoria(
    id DECIMAL(6) NOT NULL,
    data DATE NOT NULL,
    valor_antigo_idx DECIMAL(8,3) NOT NULL,
    novo_valor_idx DECIMAL(8,3) NOT NULL,
    diferenca DECIMAL(8,3) NOT NULL,
    CodMunicipio DECIMAL(6) NOT NULL,
    Ano DECIMAL(4) NOT NULL,

    CONSTRAINT pk_auditoria PRIMARY KEY (id),
    CONSTRAINT fk_municipio FOREIGN KEY (CodMunicipio) REFERENCES Municipio (CodMunicipio));

-- Criação do incrementador dos ids da tabela de Auditoria
CREATE SEQUENCE auditoria_id_seq START 1;

-- Criação da função que será executada pelo trigger
CREATE OR REPLACE FUNCTION function_auditoria_idx()
RETURNS TRIGGER -- Define uma função como do tipo trigger
AS $$ -- Define o corpo da função como uma string

-- Início da função
BEGIN
	-- Calcula o novo idx
    NEW.idx := (POWER(NEW.IDH_Educacao, 2) * NEW.IDH_Longevidade) / NEW.IDH_Geral;
	
    INSERT INTO Auditoria (
        id,
        data,
        valor_antigo_idx,
        novo_valor_idx,
        diferenca,
        CodMunicipio,
        Ano
    )

    VALUES (
        NEXTVAL('auditoria_id_seq'), -- Incrementa o valor do id
        CURRENT_DATE,
        OLD.idx,
        NEW.idx,
        ABS(NEW.idx - OLD.idx),
        NEW.CodMunicipio,
        NEW.Ano
    );

	-- Obrigatório em triggers "BEFORE"
	-- Retorna os dados que serão persistidos no banco de dados
	-- Garante a continuidade da operação definida pelo trigger
    RETURN NEW;
END;

$$ 
LANGUAGE plpgsql; -- Linguagem procedural do PostgreSQL

-- Criação do trigger
CREATE TRIGGER trigger_auditoria_idx
BEFORE UPDATE OF IDH_Educacao, IDH_Longevidade, IDH_Geral
ON indice
FOR EACH ROW -- Executa somente para cada linha afetada pelo UPDATE
EXECUTE FUNCTION function_auditoria_idx();

-- Criação da função que calculará a média das diferenças entre os valores de IDX que foram alterados para o dia atual
CREATE OR REPLACE FUNCTION avg_dif_today()
RETURNS DECIMAL(10,2)
AS $$
DECLARE 
	resultado DECIMAL(10,2);

BEGIN
	SELECT AVG(A.diferenca)
	INTO resultado -- Grava o resultado na variável
	FROM Auditoria A
	WHERE A.data = CURRENT_DATE;

	RAISE NOTICE 'Média: %', resultado;

	RETURN resultado;
END
$$
LANGUAGE plpgsql;

-- Testes
UPDATE indice
SET IDH_Educacao = 0.999
WHERE CodMunicipio = 354880 AND ano = 2000;

SELECT * FROM Auditoria;

SELECT avg_dif_today();