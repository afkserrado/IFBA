import { Link } from 'react-router-dom';

import api from '../../services/api';

import { useState, useEffect } from 'react';

// Function based component
// Página principal
function Home() {
    
    const [questoes, setQuestoes] = useState([]);

    // Invoca a requisição HTTP para obter os dados do servidor
    // na montagem do componente
    useEffect(() => {
        // async/await + Axios
        async function loadData() {
            try {
                const response = await api.get('/');
                const data = response.data;
                setQuestoes(data);
                //console.log(data);
            }
            catch(error) {
                console.error(error);
            }
        }

        loadData();

        // then/catch + Axios
        // function loadData() {
        //     api.get('/')
        //         .then(response => response.data)
        //         .then(data => {
        //             setQuestoes(data);
        //             //console.log(data);
        //         })
        //         .catch(error => {
        //             console.error(error);
        //         });
        // }

        // loadData();

        // async/await + fetch API
        // async function loadData() {
        //     try {
        //         const response = await fetch('http://localhost:3000/questoes');

        //         if(!response.ok) {
        //             throw new Error('Erro na requisição.');
        //         }

        //         const data = await response.json();
        //         setQuestoes(data);
        //         //console.log(data); 
        //     }
        //     catch(error) {
        //         console.error(error);
        //     }
        // }

        // loadData();

        // then/catch + fetch API
        // function loadData() {
        //     fetch('http://localhost:3000/questoes')
        //         .then(response => {

        //             if(!response.ok) {
        //                 throw new Error('Erro na requisição');
        //             }

        //             return response.json();
        //         })
        //         .then(data => {
        //             setQuestoes(data);
        //             //console.log(data);
        //         })
        //         .catch(error => {
        //             console.error(error);
        //         })
        // }

        // loadData();

    }, []);
    
    // Renderiza o componente
    return(
        <div>
            <h1>Lista de exercícios 1</h1>
            <span>Selecione a questão abaixo: </span>
            <ul>
                {questoes.map(questao => {
                    return(
                        <li key={questao.id}>
                            <Link to={`/questoes/${questao.id}`}>{`Questão ${questao.id}`} - {questao.titulo}</Link>
                        </li>
                    );
                })}
            </ul>
        </div>
    );
} export default Home;