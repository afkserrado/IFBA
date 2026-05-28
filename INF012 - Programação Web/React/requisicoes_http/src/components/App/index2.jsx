import { useState, useEffect } from 'react';

import '../../Nutricao.css';

import { buscarPosts, criarPost } from '../../api/posts';

function Nutricao() {

    const [dicas, setDicas] = useState([]);

    // GET
    useEffect(() => {

        buscarPosts()
            .then(response => {
                // Os dados JSON já vem convertidos
                let myjson = response.data;

                // Salva os posts no estado
                setDicas(myjson);
            })
            .catch((erro) => {
                alert('Deu erro: ' + erro);
            });
    }, []);

    return(
        <>
            <header>
                <strong>Usando AXIOS: React Nutri</strong>
            </header>

            {dicas.map((item) => {
                return(
                    <article key={item.id} className="post">
                        <strong className="titulo">{item.titulo}</strong>
                        <img src={item.capa} alt={item.titulo} className="capa"/>
                        <p className="subtitulo">{item.subtitulo}</p>
                        <a className="botao">Acessar</a>
                    </article>
                );
            })}
        </>
    );

} export default Nutricao;