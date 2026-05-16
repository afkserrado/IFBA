import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:3000',
})

export function buscarDados(rota) {
    return api.get(rota);
}

export default api;