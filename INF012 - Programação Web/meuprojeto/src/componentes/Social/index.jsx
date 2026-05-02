// Define o componente IMC

import React from 'react';

// Exemplo 1
// function Social(props) {
//     return <h1> Olá {props.nome}. Você tem {props.idade} anos. </h1>;
// }

// Exemplo 2
function Sobre(props) {
    return(
        <h1> Olá, {props.nome}. Você tem {props.idade} anos de idade.</h1>
    );
}

function Social(props) {
    return(
        <div>
            <Sobre nome = {props.nome} idade = {props.idade}/>
            <h1>Facebook {props.facebook}</h1>
            <h1>Instagram {props.instagram}</h1>
        </div>
    )
}

export default Social;