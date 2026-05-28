import axios from 'axios';

// Instância personalizada do Axios
const API = axios.create({
    baseURL: 'https://sujeitoprogramador.com/rn-api/'
});

// GET
export function buscarPosts() {
    return API.get('?api=posts'); // Utilizando a instância base
    
    // Sem instância base
    // return axios.get(
    //     'https://sujeitoprogramador.com/rn-api/?api=posts'
    // );

    // 'get()' retorna uma Promise
    // Ficará a cargo do componente decidir o que fazer
}

// POST
export function criarPost() {

    const novoPost = {
        titulo: "Nova dica",
        categoria: "Dieta"
    };

    return API.post('?api=posts', novoPost);

    // Sem instância base
    // return axios.post(
    //     'https://sujeitoprogramador.com/rn-api/?api=posts',
    //     novoPost
    // );

    // 'post()' retorna uma Promise
    // Ficará a cargo do componente decidir o que fazer
} 

// DELETE
export function deletePost() {
    return API.delete('posts/1');

    // 'delete()' retorna uma Promise
    // Ficará a cargo do componente decidir o que fazer
}

export default API;