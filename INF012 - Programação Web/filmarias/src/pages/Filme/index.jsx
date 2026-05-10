import { useState, useEffect } from 'react';

import { useParams, useNavigate } from 'react-router-dom';

import { toast } from 'react-toastify';

import api from '../../services';

export default function Filme() {

    const {id} = useParams(); 
    const [filme, setFilme] = useState({});
    const [loading, setLoading] = useState(true);
    const history = useNavigate(); // Permite navegar entre páginas

    useEffect(() => {

        async function loadFilme() {
            const response = await api.get('/r-api/?api=filmes/' + id);

            // Se a requisição não retornar dados,
            // o usuário é automaticamente enviado para a home
            if(response.data.length === 0) {
                history('/'); // Manda o usuário para a home
                return;
            }

            setFilme(response.data);
            setLoading(false);
        }

        loadFilme();

    }, [history, id]);

    function salvarFilme() {

        // Pega os filmes salvos no armazenamento local do navegador
        // Converte de JSON para um objeto JavaScript
        // Se não existir nada, retornar um array vazio
        let filmesSalvos = JSON.parse(localStorage.getItem('filmes')) || [];

        // Verifica se o filme atual já está salvo no armazenamento local
        let hasFilme = filmesSalvos.some(item => item.id === filme.id);

        if(hasFilme) {
            toast.error('Você já salvou esse filme.');
            return;
        }

        filmesSalvos.push(filme);
        localStorage.setItem('filmes', JSON.stringify(filmesSalvos));
        toast.success('Filme salvo com sucesso!');
    }

    // Filme ainda não foi carregado
    if(loading) {
        return(
            <div className='filme-info'>
                <h1>Carregando filme...</h1>
            </div>
        );
    }

    return(
        <div className='filme-info'>
            <h1>{filme.nome}</h1>
            <img src={filme.foto} alt={filme.nome}/>
            <h3>Sinopse</h3>
            {filme.sinopse}

            <div>
                <button onClick={() => salvarFilme()}>Salvar</button>

                <button>
                    <a target="_blank" href={`https://www.youtube.com/results?search_query=${filme.nome} trailer`}>Trailer</a>
                </button>
            </div>
        </div>
    );
}