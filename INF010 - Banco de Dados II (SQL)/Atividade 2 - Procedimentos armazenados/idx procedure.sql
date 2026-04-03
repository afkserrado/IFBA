/*
Atividade de Procedimentos armazenados com a base de dados de IDH:
- Criar um novo atributo na tabela de índices, o qual deve ser chamado de IDX
- Implementar um procedimento que preencha este atributo a partir do seguinte cálculo:

IDX = (ÍNDICE DE EDUCAÇÃO^2 * ÍNDICE DE LONGEVIDADE)/IDH_GERAL

Alunos: Anderson Serrado e Anna Luísa
*/

ALTER TABLE indice ADD COLUMN idx decimal(8,3);

CREATE OR REPLACE PROCEDURE idx()
LANGUAGE plpgsql
AS $$
BEGIN
UPDATE indice
SET idx = (power(IDH_Educacao,2) * IDH_Longevidade)/IDH_Geral;
END;
$$;

CALL idx();
