import { useState, useEffect } from 'react';

import '../../Nutricao.css';

function Nutricao() {

    const [dicas, setDicas] = useState([]);

    useEffect(() => {
        let url = "https://sujeitoprogramador.com/rn-api/?api=posts";
        fetch(url)
            .then(response => response.json())
            .then(myjson => setDicas(myjson))
            .catch(err => console.error(err));
    }, []);

    return(
        <>
            <header>
                <strong>Usando Fetch API: React Nutri</strong>
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