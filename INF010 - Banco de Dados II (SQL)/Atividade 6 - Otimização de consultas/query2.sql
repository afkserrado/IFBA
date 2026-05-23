-- 12 - Gerar um relatório com todos os departamentos, professores, turmas e média das notas dos alunos em cada turma entre 2006 a 2010.

SELECT Co.dept_name AS NomeDepartamento, I.name AS NomeProfessor, S.sec_id AS NumeroTurma,
		AVG(
        CASE grade
            WHEN 'A'  THEN 4.0
            WHEN 'A-' THEN 3.7
            WHEN 'B+' THEN 3.3
            WHEN 'B'  THEN 3.0
            WHEN 'B-' THEN 2.7
            WHEN 'C+' THEN 2.3
            WHEN 'C'  THEN 2.0
            WHEN 'C-' THEN 1.7
            WHEN 'D'  THEN 1.0
            WHEN 'F'  THEN 0.0
        END
    ) AS MediaNotas

FROM takes Ta
JOIN section S
	ON S.course_id = Ta.course_id
	AND S.sec_id = Ta.sec_id
	AND S.semester = Ta.semester
	AND S.year = Ta.year

JOIN teaches Te
	ON S.course_id = Te.course_id
	AND S.sec_id = Te.sec_id
	AND S.semester = Te.semester
	AND S.year = Te.year

JOIN instructor I
	ON Te.id = I.id

JOIN course Co
	ON Te.course_id = Co.course_id

WHERE S.year >= 2006 AND S.year <= 2010

-- A chave primária da tabela 'section' é composta. Portanto, para identificar
-- um único registro da tabela, deve-se considerar todos os atributos que compõem a 
-- chave primária
GROUP BY S.course_id, S.sec_id, S.semester, S.year, Co.dept_name, I.name;

-- 13 - Gerar relatório com nome do aluno, disciplinas cursadas com respectivos professores das turmas e notas e respectivo departamento dos professores.

-- Abordagem 1
SELECT St.name AS NomeAluno, Cr.title AS NomeDisciplina, I.name AS NomeProfessor, I.dept_name AS DepartamentoProfessor, Tk.grade AS Nota
FROM takes Tk

JOIN student St
	ON Tk.id = St.id
	
JOIN course Cr
	ON Tk.course_id = Cr.course_id  
	
JOIN teaches Te
	ON Tk.course_id = Te.course_id
	AND Tk.sec_id = Te.sec_id
	AND Tk.semester = Te.semester
	AND Tk.year = Te.year
	
JOIN instructor I
ON Te.id = I.id;

-- Abordagem teste
SELECT St.name AS NomeAluno, Cr.title AS NomeDisciplina, I.name AS NomeProfessor, I.dept_name AS DepartamentoProfessor, Tk.grade AS Nota
FROM takes Tk

JOIN student St
	ON Tk.id = St.id
 
JOIN teaches Te
	ON Tk.course_id = Te.course_id
	AND Tk.sec_id = Te.sec_id
	AND Tk.semester = Te.semester
	AND Tk.year = Te.year
	
JOIN instructor I
ON Te.id = I.id

JOIN course Cr
	ON Tk.course_id = Cr.course_id;
	

	

	











