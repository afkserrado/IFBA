# Apagar registros (exceto ADMIN inicial)
docker exec -it biblioteca_postgres psql -U admin -d biblioteca -c "
DO \$\$
DECLARE
    admin_email TEXT := 'admin@biblioteca.com';
BEGIN

    -- Remove dados dependentes primeiro
    TRUNCATE TABLE 
        emprestimos.emprestimos,
        acervo.livros
    RESTART IDENTITY CASCADE;

    -- Remove usuários exceto o admin inicial
    DELETE FROM usuarios.usuarios
    WHERE email <> admin_email;

    -- Reinicia sequência da tabela de usuários
    PERFORM setval(
        pg_get_serial_sequence('usuarios.usuarios', 'id'),
        COALESCE((SELECT MAX(id) FROM usuarios.usuarios), 1)
    );

END
\$\$;
"

# Conferir
docker exec -it biblioteca_postgres psql -U admin -d biblioteca -c "
SELECT id, nome, email, role
FROM usuarios.usuarios;
"

clear