import React, { useState, useEffect, useMemo, useCallback } from 'react';

function Tarefa() {
    const [texto, setTexto] = useState("Digite sua tarefa");
    const [lista, setLista] = useState(["Lavar roupa", "Varrer quarto"]);

    // Monitora o array 'lista'
    // A cada mudança, executa a função anônima
        // Transforma o array em uma string
        // Armazena no armazenamento local do navegador
        // 'tarefa' é a chave para recuperar o valor
    useEffect(() => {
        localStorage.setItem('tarefa', JSON.stringify(lista));
        console.log(`Valor armazenado: ${localStorage.getItem('tarefa')} e tipo: ${typeof localStorage.getItem('tarefa')}`);
    }, [lista]);

    // Retorna a quantidade de itens da 'lista' cada vez que ela é atualizada
    let tamanho = useMemo(() => {
        return lista.length;
    }, [lista]);

    // A cada renderização do componente
        // Cria um novo objeto função (funções também são objetos em JS)
        // A referência da função muda
    // function addTarefa() {
    //     setLista([texto, ...lista]);
    //     setTexto("");
    // }

    // Só cria uma nova função quando alguma dependência muda
    const addTarefa = useCallback(() => {
        setLista([texto, ...lista]);
        setTexto("");
    }, [texto, lista]);

    return (
        <div>
            <h2>Com function components</h2>
            <a>Você tem {tamanho} tarefas!</a>
            <ul>
                {lista.map((item, index) => <li key={index}>{item}</li>)}
            </ul>

            <input type="text" value={texto} onChange={(e) => setTexto(e.target.value)}/>
            <button onClick={addTarefa}>Incluir</button>
        </div>
    );

} export default Tarefa;

