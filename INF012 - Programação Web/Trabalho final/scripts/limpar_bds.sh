docker exec -it biblioteca_postgres psql -U admin -d biblioteca -c "
DO \$\$
BEGIN

    -- Limpa empréstimos
    BEGIN
        TRUNCATE TABLE emprestimos.emprestimos RESTART IDENTITY CASCADE;
        RAISE NOTICE 'Tabela emprestimos.emprestimos limpa com sucesso.';
    EXCEPTION WHEN OTHERS THEN
        RAISE NOTICE 'Não foi possível limpar emprestimos.emprestimos: %', SQLERRM;
    END;

    -- Limpa livros
    BEGIN
        TRUNCATE TABLE acervo.livros RESTART IDENTITY CASCADE;
        RAISE NOTICE 'Tabela acervo.livros limpa com sucesso.';
    EXCEPTION WHEN OTHERS THEN
        RAISE NOTICE 'Não foi possível limpar acervo.livros: %', SQLERRM;
    END;

    -- Limpa usuários, preservando o ADMIN inicial
    BEGIN
        DELETE FROM usuarios.usuarios
        WHERE email <> 'admin@biblioteca.com';

        RAISE NOTICE 'Usuários não-admin removidos com sucesso.';
    EXCEPTION WHEN OTHERS THEN
        RAISE NOTICE 'Não foi possível limpar usuarios.usuarios: %', SQLERRM;
    END;

    -- Reinicia a sequência de usuários
    BEGIN
        PERFORM setval(
            pg_get_serial_sequence('usuarios.usuarios', 'id'),
            COALESCE((SELECT MAX(id) FROM usuarios.usuarios), 1)
        );

        RAISE NOTICE 'Sequência de usuarios.usuarios reiniciada com sucesso.';
    EXCEPTION WHEN OTHERS THEN
        RAISE NOTICE 'Não foi possível reiniciar a sequência de usuarios.usuarios: %', SQLERRM;
    END;

END
\$\$;
"

# Conferir usuários
docker exec -it biblioteca_postgres psql -U admin -d biblioteca -c "
SELECT id, nome, email, role
FROM usuarios.usuarios
ORDER BY id;
"