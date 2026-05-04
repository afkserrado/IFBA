import { useState, useEffect } from 'react';

import { Link } from 'react-router-dom';

import api from '../../services/index'

import './index.css';

export default function Home() {
    
    const [filmes, setFilmes] = useState([]);

    // Estilo moderno
    useEffect(() => {
        async function loadFilmes() {
            const response = await api.get('/r-api/?api=filmes');
            setFilmes(response.data);
        }

        loadFilmes();
    }, []);

    // Estilo tradicional
    // useEffect(() => {
    //     api
    //         .get('/r-api/?api=filmes')
    //         .then(response => setFilmes(response.data))
    //         .catch(erro => console.error("Erro", erro));
    // });

    return(
        <div className='conteiner'>
            <div className='lista-filmes'>
                {filmes.map(item => {
                    return(
                        <article key={item.id}>
                            <strong>{item.name}</strong>
                            <img src={item.foto} alt={item.nome}/>
                            <Link to={`/filme/${item.id}`}>Acessar</Link>
                        </article>
                    );
                })}
            </div>
        </div>
    );
}