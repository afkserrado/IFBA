import axios from 'axios';

// Criamos a instância central do Axios apontando para o seu futuro backend
const api = axios.create({
  baseURL: 'http://localhost:8080/api',
});

// INTERCEPTOR DE REQUISIÇÃO: Antes de enviar qualquer pedido ao backend, pega o JWT e anexa
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('@Biblioteca:token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
}, (error) => Promise.reject(error));

// INTERCEPTOR DE RESPOSTA: Se o backend devolver erro 401 (Não Autorizado) ou 403 (Proibido), expulsa pro login
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && (error.response.status === 401 || error.response.status === 403)) {
      alert('Sessão expirada ou sem permissão! Por favor, faça login novamente.');
      localStorage.removeItem('@Biblioteca:token');
      localStorage.removeItem('@Biblioteca:user');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default api;