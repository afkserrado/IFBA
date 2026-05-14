import axios from 'axios';

// API para fazer requisições ao banco de questões da lista
const api = axios.create({
    baseURL: 'http://localhost:3000/questoes'
});

export default api;