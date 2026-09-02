import axios from 'axios';

const SESSION_KEYS = ['@Biblioteca:token', '@Biblioteca:tipo', '@Biblioteca:user'];

export function clearStoredSession() {
  SESSION_KEYS.forEach((key) => localStorage.removeItem(key));
}

export function getApiErrorMessage(error, fallback = 'Não foi possível concluir a operação. Tente novamente.') {
  if (!error.response) {
    return 'Não foi possível conectar ao servidor. Verifique se o gateway está em execução.';
  }

  const data = error.response.data;
  if (data?.mensagem || data?.message) return data.mensagem || data.message;

  if (data?.campos) {
    const errors = Object.entries(data.campos)
      .flatMap(([field, messages]) => {
        const normalizedMessages = Array.isArray(messages) ? messages : [messages];
        return normalizedMessages.map((message) => `${field}: ${message}`);
      });
    if (errors.length) return errors.join(' ');
  }

  if (error.response.status === 403) {
    return 'Você não possui permissão para realizar esta operação.';
  }

  return fallback;
}

const api = axios.create({
  // Em desenvolvimento, a URL relativa usa o proxy do Vite e evita CORS no navegador.
  // VITE_API_URL continua disponível para ambientes que tenham proxy reverso próprio.
  baseURL: import.meta.env.VITE_API_URL || '',
  headers: { 'Content-Type': 'application/json' },
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem('@Biblioteca:token');

  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }

  return config;
});

api.interceptors.response.use(
  (response) => response,
  (error) => {
    const isLoginRequest = error.config?.url?.includes('/auth/login');

    // Um 401 de uma chamada protegida significa que a sessão não é mais válida.
    // 403 é mantido na página atual para que a interface mostre a regra de negócio.
    if (error.response?.status === 401 && !isLoginRequest) {
      clearStoredSession();
      if (window.location.pathname !== '/login') {
        window.location.replace('/login');
      }
    }

    return Promise.reject(error);
  },
);

export default api;
