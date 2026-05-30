import { useState, useEffect } from 'react';

import { toast } from 'react-toastify';

import { Link } from 'react-router-dom';

import './index.css';

export default function Favoritos() {

    const [salvos, setSalvos] = useState([]);

    useEffect(() => {
        let filmes = JSON.parse(localStorage.getItem('filmes')) || [];
        setSalvos(filmes);
    }, []);

    function excluirFilmes(id) {

        let filmes = salvos.filter(item => {
            return item.id !== id;
        });

        setSalvos(filmes);
        localStorage.setItem('filmes', JSON.stringify(filmes));
        toast.success('Filme excluído com sucesso!');
    }

    return(
        <div className='meus-filmes'>
            <h1>Meus filmes</h1>
            {salvos.length === 0  ? <span>Você não possui filmes salvos.</span> : null}
        
            <ul>
                {salvos.map(item => {
                    return(
                        <li key={item.id}>
                            <span>{item.nome}</span>

                            <div>
                                <Link to={`/filme/${item.id}`}>Ver detalhes</Link>
                                <span> </span>
                                <button onClick={() => excluirFilmes(item.id)}>Excluir</button>
                            </div>
                        </li>
                    );
                })}
            </ul>

            <Link to='/'>Voltar para a página inicial</Link>
        </div>
    );
}